package com.ruoyi.project.system.controller;

import java.util.List;
import java.util.Set;

import com.ruoyi.project.business.domain.UserLearnedWords;
import com.ruoyi.project.business.domain.UserMarkedWords;
import com.ruoyi.project.business.domain.Users;
import com.ruoyi.project.business.service.IUserLearnedWordsService;
import com.ruoyi.project.business.service.IUserMarkedWordsService;
import com.ruoyi.project.system.service.ISysUserService;
import com.ruoyi.project.system.service.impl.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.security.LoginBody;
import com.ruoyi.framework.security.service.SysLoginService;
import com.ruoyi.framework.security.service.SysPermissionService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.system.domain.SysMenu;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysMenuService;

import static com.ruoyi.framework.web.domain.AjaxResult.error;
import static com.ruoyi.framework.web.domain.AjaxResult.success;

/**
 * 登录验证
 * 
 * @author ruoyi
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private IUserLearnedWordsService userLearnedWordsService;

    @Autowired
    private IUserMarkedWordsService markedWordsService;

    @Autowired
    private  EmailService emailService;


    /**
     * 登录方法
     * 
     * @param loginBody 登录信息
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody)
    {
        AjaxResult ajax = success();
        // 生成令牌
        String token = loginService.login(loginBody.getUsername(), loginBody.getPassword(), loginBody.getCode(),
                loginBody.getUuid());
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    @PostMapping("/registerOrLogin")
    public AjaxResult registerOrLogin(@RequestBody LoginBody loginBody) {
        loginBody.setUsername(loginBody.getEmail());
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(loginBody, sysUser);
        sysUser.setUserName(loginBody.getUsername());
        sysUser.setNickName(loginBody.getUsername());

        boolean isNewUser = sysUserService.checkEmailUnique(sysUser);

        if (isNewUser) {
            // 用户不存在，进行注册流程
            sysUser.setPassword(SecurityUtils.encryptPassword(sysUser.getPassword()));
            int insertResult = sysUserService.insertUser(sysUser);
            if (insertResult <= 0) {
                // 注册失败
                return error("注册失败");
            }
        }

        String token = loginService.loginByBussiness(
                loginBody.getUsername(),
                loginBody.getPassword(),
                loginBody.getCode(),
                loginBody.getUuid()
        );

        if (token == null) {
            // 登录失败
            return error("登录失败");
        }

        SysUser user = sysUserService.selectUserByUserName(sysUser.getUserName());
        AjaxResult ajax = success();
        UserLearnedWords userLearnedWords = userLearnedWordsService.selectUserLearnedWordsByUserId(user.getUserId());
        UserMarkedWords userMarkedWords = markedWordsService.selectUserMarkedWordsByUserId(user.getUserId());
        if(userMarkedWords!=null){
            ajax.put("markedWords", userMarkedWords.getMarkedWords());
        }
        if(userLearnedWords != null){
            ajax.put("learnedWords", userLearnedWords.getLearnedWords());
        }

        ajax.put(Constants.TOKEN, token);
        ajax.put("userId", user.getUserId());
        ajax.put("username", user.getUserName());

        return ajax;
    }

    @PostMapping("/reset-password")
    public AjaxResult resetPassword(@RequestBody SysUser sysUser) {
        if (!sysUserService.checkEmailUnique(sysUser)) {
            // 发送重置密码的邮件
            emailService.sendResetPasswordEmail(sysUser.getEmail());
            return success("重置密码邮件已发送，请查收");
        } else {
            return error("邮箱不存在");
        }
    }

    @PostMapping("/reset-password-email")
    public AjaxResult resetPassword(@RequestParam String token, @RequestParam String newPassword, @RequestParam String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return AjaxResult.error("新密码和确认密码不一致");
        }

        boolean isValidToken = sysUserService.validateToken(token); // 假设这是你的服务方法
        if (!isValidToken) {
            return AjaxResult.error("无效的重置密码链接");
        }

        boolean isResetSuccessful = sysUserService.resetPassword(token, newPassword);
        if (isResetSuccessful) {
            return AjaxResult.success("密码重置成功");
        } else {
            return AjaxResult.error("密码重置失败");
        }
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return success(menuService.buildMenus(menus));
    }
}
