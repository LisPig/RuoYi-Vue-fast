package com.ruoyi.project.business.service.impl;

import java.util.List;

import com.ruoyi.framework.redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.mapper.UserLearnedWordsMapper;
import com.ruoyi.project.business.domain.UserLearnedWords;
import com.ruoyi.project.business.service.IUserLearnedWordsService;

/**
 * 用户已学会单词Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-17
 */
@Service
public class UserLearnedWordsServiceImpl implements IUserLearnedWordsService 
{
    @Autowired
    private UserLearnedWordsMapper userLearnedWordsMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询用户已学会单词
     * 
     * @param userId 用户已学会单词主键
     * @return 用户已学会单词
     */
    @Override
    public UserLearnedWords selectUserLearnedWordsByUserId(Long userId)
    {
        return userLearnedWordsMapper.selectUserLearnedWordsByUserId(userId);
    }

    /**
     * 查询用户已学会单词列表
     * 
     * @param userLearnedWords 用户已学会单词
     * @return 用户已学会单词
     */
    @Override
    @Cacheable(value = "userLearnedWords", key = "#userLearnedWords.userId")
    public List<UserLearnedWords> selectUserLearnedWordsList(UserLearnedWords userLearnedWords)
    {
        return userLearnedWordsMapper.selectUserLearnedWordsList(userLearnedWords);
    }

    /**
     * 新增用户已学会单词
     * 
     * @param userLearnedWords 用户已学会单词
     * @return 结果
     */
    @Override
    @CachePut(value = "userLearnedWords", key = "#userLearnedWords.userId")
    public int insertUserLearnedWords(UserLearnedWords userLearnedWords)
    {
        return userLearnedWordsMapper.insertUserLearnedWords(userLearnedWords);
    }

    /**
     * 修改用户已学会单词
     * 
     * @param userLearnedWords 用户已学会单词
     * @return 结果
     */
    @Override
    @CachePut(value = "userLearnedWords", key = "#userLearnedWords.userId")
    public int updateUserLearnedWords(UserLearnedWords userLearnedWords)
    {
        return userLearnedWordsMapper.updateUserLearnedWords(userLearnedWords);
    }

    /**
     * 批量删除用户已学会单词
     * 
     * @param userIds 需要删除的用户已学会单词主键
     * @return 结果
     */
    @Override
    public int deleteUserLearnedWordsByUserIds(Long[] userIds)
    {
        return userLearnedWordsMapper.deleteUserLearnedWordsByUserIds(userIds);
    }

    /**
     * 删除用户已学会单词信息
     * 
     * @param userId 用户已学会单词主键
     * @return 结果
     */
    @Override
    public int deleteUserLearnedWordsByUserId(Long userId)
    {
        return userLearnedWordsMapper.deleteUserLearnedWordsByUserId(userId);
    }
}
