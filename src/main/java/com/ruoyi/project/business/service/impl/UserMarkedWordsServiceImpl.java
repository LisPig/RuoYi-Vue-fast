package com.ruoyi.project.business.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.mapper.UserMarkedWordsMapper;
import com.ruoyi.project.business.domain.UserMarkedWords;
import com.ruoyi.project.business.service.IUserMarkedWordsService;

/**
 * 用户自标记单词Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-17
 */
@Service
public class UserMarkedWordsServiceImpl implements IUserMarkedWordsService 
{
    @Autowired
    private UserMarkedWordsMapper userMarkedWordsMapper;

    /**
     * 查询用户自标记单词
     * 
     * @param userId 用户自标记单词主键
     * @return 用户自标记单词
     */
    @Override
    public UserMarkedWords selectUserMarkedWordsByUserId(Long userId)
    {
        return userMarkedWordsMapper.selectUserMarkedWordsByUserId(userId);
    }

    /**
     * 查询用户自标记单词列表
     * 
     * @param userMarkedWords 用户自标记单词
     * @return 用户自标记单词
     */
    @Override
    public List<UserMarkedWords> selectUserMarkedWordsList(UserMarkedWords userMarkedWords)
    {
        return userMarkedWordsMapper.selectUserMarkedWordsList(userMarkedWords);
    }

    /**
     * 新增用户自标记单词
     * 
     * @param userMarkedWords 用户自标记单词
     * @return 结果
     */
    @Override
    public int insertUserMarkedWords(UserMarkedWords userMarkedWords)
    {
        return userMarkedWordsMapper.insertUserMarkedWords(userMarkedWords);
    }

    /**
     * 修改用户自标记单词
     * 
     * @param userMarkedWords 用户自标记单词
     * @return 结果
     */
    @Override
    public int updateUserMarkedWords(UserMarkedWords userMarkedWords)
    {
        return userMarkedWordsMapper.updateUserMarkedWords(userMarkedWords);
    }

    /**
     * 批量删除用户自标记单词
     * 
     * @param userIds 需要删除的用户自标记单词主键
     * @return 结果
     */
    @Override
    public int deleteUserMarkedWordsByUserIds(Long[] userIds)
    {
        return userMarkedWordsMapper.deleteUserMarkedWordsByUserIds(userIds);
    }

    /**
     * 删除用户自标记单词信息
     * 
     * @param userId 用户自标记单词主键
     * @return 结果
     */
    @Override
    public int deleteUserMarkedWordsByUserId(Long userId)
    {
        return userMarkedWordsMapper.deleteUserMarkedWordsByUserId(userId);
    }
}
