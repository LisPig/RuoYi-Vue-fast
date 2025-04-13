package com.ruoyi.project.business.mapper;

import java.util.List;
import com.ruoyi.project.business.domain.UserMarkedWords;

/**
 * 用户自标记单词Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-17
 */
public interface UserMarkedWordsMapper 
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
     * 删除用户自标记单词
     * 
     * @param userId 用户自标记单词主键
     * @return 结果
     */
    public int deleteUserMarkedWordsByUserId(Long userId);

    /**
     * 批量删除用户自标记单词
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserMarkedWordsByUserIds(Long[] userIds);
}
