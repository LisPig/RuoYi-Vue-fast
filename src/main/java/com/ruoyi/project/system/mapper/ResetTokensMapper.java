package com.ruoyi.project.system.mapper;

import java.util.List;
import com.ruoyi.project.system.domain.ResetTokens;

/**
 * 重置密码tokenMapper接口
 * 
 * @author ruoyi
 * @date 2024-07-20
 */
public interface ResetTokensMapper 
{
    /**
     * 查询重置密码token
     * 
     * @param id 重置密码token主键
     * @return 重置密码token
     */
    public ResetTokens selectResetTokensById(Long id);

    /**
     * 查询重置密码token列表
     * 
     * @param resetTokens 重置密码token
     * @return 重置密码token集合
     */
    public List<ResetTokens> selectResetTokensList(ResetTokens resetTokens);

    /**
     * 新增重置密码token
     * 
     * @param resetTokens 重置密码token
     * @return 结果
     */
    public int insertResetTokens(ResetTokens resetTokens);

    /**
     * 修改重置密码token
     * 
     * @param resetTokens 重置密码token
     * @return 结果
     */
    public int updateResetTokens(ResetTokens resetTokens);

    /**
     * 删除重置密码token
     * 
     * @param id 重置密码token主键
     * @return 结果
     */
    public int deleteResetTokensById(Long id);

    /**
     * 批量删除重置密码token
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteResetTokensByIds(Long[] ids);

    ResetTokens findByToken(String token);

    public int deleteResetTokensByToken(String token);
}
