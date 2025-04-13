package com.ruoyi.project.business.service;

import java.util.List;
import com.ruoyi.project.business.domain.UserLearnedWords;

/**
 * 用户已学会单词Service接口
 * 
 * @author ruoyi
 * @date 2024-07-17
 */
public interface IUserLearnedWordsService 
{
    /**
     * 查询用户已学会单词
     * 
     * @param userId 用户已学会单词主键
     * @return 用户已学会单词
     */
    public UserLearnedWords selectUserLearnedWordsByUserId(Long userId);

    /**
     * 查询用户已学会单词列表
     * 
     * @param userLearnedWords 用户已学会单词
     * @return 用户已学会单词集合
     */
    public List<UserLearnedWords> selectUserLearnedWordsList(UserLearnedWords userLearnedWords);

    /**
     * 新增用户已学会单词
     * 
     * @param userLearnedWords 用户已学会单词
     * @return 结果
     */
    public int insertUserLearnedWords(UserLearnedWords userLearnedWords);

    /**
     * 修改用户已学会单词
     * 
     * @param userLearnedWords 用户已学会单词
     * @return 结果
     */
    public int updateUserLearnedWords(UserLearnedWords userLearnedWords);

    /**
     * 批量删除用户已学会单词
     * 
     * @param userIds 需要删除的用户已学会单词主键集合
     * @return 结果
     */
    public int deleteUserLearnedWordsByUserIds(Long[] userIds);

    /**
     * 删除用户已学会单词信息
     * 
     * @param userId 用户已学会单词主键
     * @return 结果
     */
    public int deleteUserLearnedWordsByUserId(Long userId);
}
