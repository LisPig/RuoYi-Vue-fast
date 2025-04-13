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
import com.ruoyi.project.business.domain.UserMarkedWords;
import com.ruoyi.project.business.service.IUserMarkedWordsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 用户自标记单词Controller
 * 
 * @author ruoyi
 * @date 2024-07-17
 */
@RestController
@RequestMapping("/business/UserMarkedWords")
public class UserMarkedWordsController extends BaseController
{
    @Autowired
    private IUserMarkedWordsService userMarkedWordsService;

    /**
     * 查询用户自标记单词列表
     */
    @PreAuthorize("@ss.hasPermi('business:UserMarkedWords:list')")
    @GetMapping("/list")
    public TableDataInfo list(UserMarkedWords userMarkedWords)
    {
        startPage();
        List<UserMarkedWords> list = userMarkedWordsService.selectUserMarkedWordsList(userMarkedWords);
        return getDataTable(list);
    }

    /**
     * 导出用户自标记单词列表
     */
    @PreAuthorize("@ss.hasPermi('business:UserMarkedWords:export')")
    @Log(title = "用户自标记单词", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, UserMarkedWords userMarkedWords)
    {
        List<UserMarkedWords> list = userMarkedWordsService.selectUserMarkedWordsList(userMarkedWords);
        ExcelUtil<UserMarkedWords> util = new ExcelUtil<UserMarkedWords>(UserMarkedWords.class);
        util.exportExcel(response, list, "用户自标记单词数据");
    }

    /**
     * 获取用户自标记单词详细信息
     */
    //@PreAuthorize("@ss.hasPermi('business:UserMarkedWords:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(userMarkedWordsService.selectUserMarkedWordsByUserId(userId));
    }

    /**
     * 新增用户自标记单词
     */
    //@PreAuthorize("@ss.hasPermi('business:UserMarkedWords:add')")
    @Log(title = "用户自标记单词", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody UserMarkedWords userMarkedWords)
    {
        UserMarkedWords isUserMarkedWord = userMarkedWordsService.selectUserMarkedWordsByUserId(userMarkedWords.getUserId());
        if(isUserMarkedWord!=null){
            return toAjax(userMarkedWordsService.updateUserMarkedWords(userMarkedWords));
        }else{
            return toAjax(userMarkedWordsService.insertUserMarkedWords(userMarkedWords));
        }

    }

    /**
     * 修改用户自标记单词
     */
    @PreAuthorize("@ss.hasPermi('business:UserMarkedWords:edit')")
    @Log(title = "用户自标记单词", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody UserMarkedWords userMarkedWords)
    {
        return toAjax(userMarkedWordsService.updateUserMarkedWords(userMarkedWords));
    }

    /**
     * 删除用户自标记单词
     */
    //@PreAuthorize("@ss.hasPermi('business:UserMarkedWords:remove')")
    @Log(title = "用户自标记单词", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(userMarkedWordsService.deleteUserMarkedWordsByUserIds(userIds));
    }
}
