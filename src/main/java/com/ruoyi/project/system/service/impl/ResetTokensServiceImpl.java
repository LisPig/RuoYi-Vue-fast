package com.ruoyi.project.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.system.mapper.ResetTokensMapper;
import com.ruoyi.project.system.domain.ResetTokens;
import com.ruoyi.project.system.service.IResetTokensService;

/**
 * 重置密码tokenService业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-20
 */
@Service
public class ResetTokensServiceImpl implements IResetTokensService 
{
    @Autowired
    private ResetTokensMapper resetTokensMapper;

    /**
     * 查询重置密码token
     * 
     * @param id 重置密码token主键
     * @return 重置密码token
     */
    @Override
    public ResetTokens selectResetTokensById(Long id)
    {
        return resetTokensMapper.selectResetTokensById(id);
    }

    /**
     * 查询重置密码token列表
     * 
     * @param resetTokens 重置密码token
     * @return 重置密码token
     */
    @Override
    public List<ResetTokens> selectResetTokensList(ResetTokens resetTokens)
    {
        return resetTokensMapper.selectResetTokensList(resetTokens);
    }

    /**
     * 新增重置密码token
     * 
     * @param resetTokens 重置密码token
     * @return 结果
     */
    @Override
    public int insertResetTokens(ResetTokens resetTokens)
    {
        return resetTokensMapper.insertResetTokens(resetTokens);
    }

    /**
     * 修改重置密码token
     * 
     * @param resetTokens 重置密码token
     * @return 结果
     */
    @Override
    public int updateResetTokens(ResetTokens resetTokens)
    {
        return resetTokensMapper.updateResetTokens(resetTokens);
    }

    /**
     * 批量删除重置密码token
     * 
     * @param ids 需要删除的重置密码token主键
     * @return 结果
     */
    @Override
    public int deleteResetTokensByIds(Long[] ids)
    {
        return resetTokensMapper.deleteResetTokensByIds(ids);
    }

    /**
     * 删除重置密码token信息
     * 
     * @param id 重置密码token主键
     * @return 结果
     */
    @Override
    public int deleteResetTokensById(Long id)
    {
        return resetTokensMapper.deleteResetTokensById(id);
    }

    @Override
    public ResetTokens findByToken(String token) {
        return resetTokensMapper.findByToken(token);
    }

    @Override
    public int deleteResetTokensByToken(String resetToken) {
        return resetTokensMapper.deleteResetTokensByToken(resetToken);
    }

}
