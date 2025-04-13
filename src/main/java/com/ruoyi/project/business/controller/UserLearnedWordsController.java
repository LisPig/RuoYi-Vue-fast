package com.ruoyi.project.business.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.project.business.domain.UserLearnedWords;
import com.ruoyi.project.business.service.IUserLearnedWordsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户已学会单词Controller
 * 
 * @author ruoyi
 * @date 2024-07-17
 */
@RestController
@RequestMapping("/business/UserLearnedWords")
public class UserLearnedWordsController extends BaseController
{
    @Autowired
    private IUserLearnedWordsService userLearnedWordsService;

    /**
     * 查询用户已学会单词列表
     */
    @PreAuthorize("@ss.hasPermi('business:UserLearnedWords:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserLearnedWords userLearnedWords)
    {
        startPage();
        List<UserLearnedWords> list = userLearnedWordsService.selectUserLearnedWordsList(userLearnedWords);
        return getDataTable(list);
    }

    /**
     * 导出用户已学会单词列表
     */
    @PreAuthorize("@ss.hasPermi('business:UserLearnedWords:export')")
    @Log(title = "用户已学会单词", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserLearnedWords userLearnedWords)
    {
        List<UserLearnedWords> list = userLearnedWordsService.selectUserLearnedWordsList(userLearnedWords);
        ExcelUtil<UserLearnedWords> util = new ExcelUtil<UserLearnedWords>(UserLearnedWords.class);
        util.exportExcel(response, list, "用户已学会单词数据");
    }

    /**
     * 获取用户已学会单词详细信息
     */
    @PreAuthorize("@ss.hasPermi('business:UserLearnedWords:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(userLearnedWordsService.selectUserLearnedWordsByUserId(userId));
    }

    /**
     * 新增用户已学会单词
     */
    //@PreAuthorize("@ss.hasPermi('business:UserLearnedWords:add')")
    @Log(title = "用户已学会单词", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserLearnedWords userLearnedWords)
    {
        UserLearnedWords isUserLearnedWords= userLearnedWordsService.selectUserLearnedWordsByUserId(userLearnedWords.getUserId());
        if(isUserLearnedWords!=null){
            return toAjax(userLearnedWordsService.updateUserLearnedWords(userLearnedWords));
        }else {
            return toAjax(userLearnedWordsService.insertUserLearnedWords(userLearnedWords));
        }

    }

    /**
     * 修改用户已学会单词
     */
    @PreAuthorize("@ss.hasPermi('business:UserLearnedWords:edit')")
    @Log(title = "用户已学会单词", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserLearnedWords userLearnedWords)
    {
        return toAjax(userLearnedWordsService.updateUserLearnedWords(userLearnedWords));
    }

    /**
     * 删除用户已学会单词
     */
    @PreAuthorize("@ss.hasPermi('business:UserLearnedWords:remove')")
    @Log(title = "用户已学会单词", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userLearnedWordsService.deleteUserLearnedWordsByUserIds(userIds));
    }
}
