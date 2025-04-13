package com.ruoyi.project.business.service;

import java.util.List;
import com.ruoyi.project.business.domain.UserMarkedWords;

/**
 * 用户自标记单词Service接口
 * 
 * @author ruoyi
 * @date 2024-07-17
 */
public interface IUserMarkedWordsService 
{
    /**
     * 查询用户自标记单词
     * 
     * @param userId 用户自标记单词主键
     * @return 用户自标记单词
     */
    public UserMarkedWords selectUserMarkedWordsByUserId(Long userId);

    /**
     * 查询用户自标记单词列表
     * 
     * @param userMarkedWords 用户自标记单词
     * @return 用户自标记单词集合
     */
    public List<UserMarkedWords> selectUserMarkedWordsList(UserMarkedWords userMarkedWords);

    /**
     * 新增用户自标记单词
     * 
     * @param userMarkedWords 用户自标记单词
     * @return 结果
     */
    public int insertUserMarkedWords(UserMarkedWords userMarkedWords);

    /**
     * 修改用户自标记单词
     * 
     * @param userMarkedWords 用户自标记单词
     * @return 结果
     */
    public int updateUserMarkedWords(UserMarkedWords userMarkedWords);

    /**
     * 批量删除用户自标记单词
     * 
     * @param userIds 需要删除的用户自标记单词主键集合
     * @return 结果
     */
    public int deleteUserMarkedWordsByUserIds(Long[] userIds);

    /**
     * 删除用户自标记单词信息
     * 
     * @param userId 用户自标记单词主键
     * @return 结果
     */
    public int deleteUserMarkedWordsByUserId(Long userId);
}
