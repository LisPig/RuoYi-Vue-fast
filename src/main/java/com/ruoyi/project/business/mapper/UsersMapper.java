package com.ruoyi.project.business.mapper;

import java.util.List;
import com.ruoyi.project.business.domain.Users;
import com.ruoyi.project.system.domain.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * 用户Mapper接口
 * 
 * @author ruoyi
 * @date 2024-07-14
 */
public interface UsersMapper 
{
    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    public Users selectUsersById(Long id);

    public Users selectUserByUserName(String userName);

    public Users checkUserNameUnique(String userName);

    /**
     * 查询用户列表
     * 
     * @param users 用户
     * @return 用户集合
     */
    public List<Users> selectUsersList(@Param("users") Users users);

    /**
     * 新增用户
     * 
     * @param users 用户
     * @return 结果
     */
    public int insertUsers(Users users);

    /**
     * 修改用户
     * 
     * @param users 用户
     * @return 结果
     */
    public int updateUsers(Users users);

    /**
     * 删除用户
     * 
     * @param id 用户主键
     * @return 结果
     */
    public int deleteUsersById(Long id);

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUsersByIds(Long[] ids);

    Users checkEmailUnique(String email);
}
