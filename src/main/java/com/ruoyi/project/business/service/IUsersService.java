package com.ruoyi.project.business.service;

import java.util.List;
import com.ruoyi.project.business.domain.Users;
import com.ruoyi.project.system.domain.SysUser;

/**
 * 用户Service接口
 * 
 * @author ruoyi
 * @date 2024-07-14
 */
public interface IUsersService 
{
    public boolean checkUserNameUnique(Users user);
    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    public Users selectUsersById(Long id);

    public Users checkUserNameUnique(String username);

    /**
     * 查询用户列表
     * 
     * @param users 用户
     * @return 用户集合
     */
    public List<Users> selectUsersList(Users users);

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
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键集合
     * @return 结果
     */
    public int deleteUsersByIds(Long[] ids);

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    public int deleteUsersById(Long id);

    Users checkEmailUnique(Users user);
}
