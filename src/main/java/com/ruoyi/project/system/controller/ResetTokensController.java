package com.ruoyi.project.system.controller;

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
import com.ruoyi.project.system.domain.ResetTokens;
import com.ruoyi.project.system.service.IResetTokensService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.page.TableDataInfo;

/**
 * 重置密码tokenController
 * 
 * @author ruoyi
 * @date 2024-07-20
 */
@RestController
@RequestMapping("/system/tokens")
public class ResetTokensController extends BaseController
{
    @Autowired
    private IResetTokensService resetTokensService;

    /**
     * 查询重置密码token列表
     */
    @PreAuthorize("@ss.hasPermi('system:tokens:list')")
    @GetMapping("/list")
    public TableDataInfo list(ResetTokens resetTokens)
    {
        startPage();
        List<ResetTokens> list = resetTokensService.selectResetTokensList(resetTokens);
        return getDataTable(list);
    }

    /**
     * 导出重置密码token列表
     */
    @PreAuthorize("@ss.hasPermi('system:tokens:export')")
    @Log(title = "重置密码token", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ResetTokens resetTokens)
    {
        List<ResetTokens> list = resetTokensService.selectResetTokensList(resetTokens);
        ExcelUtil<ResetTokens> util = new ExcelUtil<ResetTokens>(ResetTokens.class);
        util.exportExcel(response, list, "重置密码token数据");
    }

    /**
     * 获取重置密码token详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:tokens:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(resetTokensService.selectResetTokensById(id));
    }

    /**
     * 新增重置密码token
     */
    @PreAuthorize("@ss.hasPermi('system:tokens:add')")
    @Log(title = "重置密码token", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ResetTokens resetTokens)
    {
        return toAjax(resetTokensService.insertResetTokens(resetTokens));
    }

    /**
     * 修改重置密码token
     */
    @PreAuthorize("@ss.hasPermi('system:tokens:edit')")
    @Log(title = "重置密码token", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ResetTokens resetTokens)
    {
        return toAjax(resetTokensService.updateResetTokens(resetTokens));
    }

    /**
     * 删除重置密码token
     */
    @PreAuthorize("@ss.hasPermi('system:tokens:remove')")
    @Log(title = "重置密码token", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(resetTokensService.deleteResetTokensByIds(ids));
    }
}
