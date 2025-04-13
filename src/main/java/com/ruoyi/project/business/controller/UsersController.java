package com.ruoyi.project.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.security.service.SysLoginService;
import com.ruoyi.project.business.domain.FeedbackRequest;
import com.ruoyi.project.business.domain.Users;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysUserService;
import com.ruoyi.project.system.service.impl.EmailService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.business.service.IUsersService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户Controller
 * 
 * @author ruoyi
 * @date 2024-07-14
 */
@RestController
@RequestMapping("/business/Users")
public class UsersController extends BaseController
{
    @Autowired
    private IUsersService usersService;

    @Autowired
    private ISysUserService sysUserService;

    @Autowired
    private SysLoginService loginService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/sendFeedback")
    public ResponseEntity<String> sendFeedback(@RequestBody FeedbackRequest request) {
        try {
            emailService.sendFeedback(request.getTitle(), request.getContent());
            return ResponseEntity.ok("Feedback sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body("Failed to send feedback.");
        }
    }

    //@PreAuthorize("@ss.hasPermi('business:Users:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping("/registerOrLogin")
    public AjaxResult registerOrLogin(@RequestBody Users user)
    {
        user.setUsername(user.getEmail());
        Users existingUser = usersService.checkEmailUnique(user);

        if (existingUser == null) {
            // 用户不存在，进行注册流程
            user.setPasswordHash(SecurityUtils.encryptPassword(user.getPasswordHash()));
            int insertResult = usersService.insertUsers(user);
            if (insertResult > 0) {
                // 注册成功
                return AjaxResult.success("注册并登录成功");
            } else {
                // 注册失败
                return error("注册失败");
            }
        } else {
            // 用户已存在，进行登录流程
            String token = loginService.loginByBussiness(user.getUsername(), user.getPasswordHash(), user.getCode(), user.getUuid());
            if (token != null) {
                // 登录成功
                AjaxResult ajax = AjaxResult.success();
                ajax.put(Constants.TOKEN, token);
                return ajax;
            } else {
                // 登录失败
                return error("登录失败");
            }
        }
    }

    /**
     * 查询用户列表
     */
    @PreAuthorize("@ss.hasPermi('business:Users:list')")
    @GetMapping("/list")
    public TableDataInfo list(Users users)
    {
        startPage();
        List<Users> list = usersService.selectUsersList(users);
        return getDataTable(list);
    }

    /**
     * 导出用户列表
     */
    @PreAuthorize("@ss.hasPermi('business:Users:export')")
    @Log(title = "用户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Users users)
    {
        List<Users> list = usersService.selectUsersList(users);
        ExcelUtil<Users> util = new ExcelUtil<Users>(Users.class);
        util.exportExcel(response, list, "用户数据");
    }

    /**
     * 获取用户详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:Users:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(usersService.selectUsersById(id));
    }

    /**
     * 新增用户
     */
    @PreAuthorize("@ss.hasPermi('business:Users:add')")
    @Log(title = "用户", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Users users)
    {
        return toAjax(usersService.insertUsers(users));
    }

    /**
     * 修改用户
     */
    @PreAuthorize("@ss.hasPermi('business:Users:edit')")
    @Log(title = "用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Users users)
    {
        return toAjax(usersService.updateUsers(users));
    }

    /**
     * 删除用户
     */
    @PreAuthorize("@ss.hasPermi('business:Users:remove')")
    @Log(title = "用户", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(usersService.deleteUsersByIds(ids));
    }
}
