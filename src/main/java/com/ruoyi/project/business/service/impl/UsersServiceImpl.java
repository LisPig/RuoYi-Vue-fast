package com.ruoyi.project.business.service.impl;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.business.mapper.UsersMapper;
import com.ruoyi.project.business.domain.Users;
import com.ruoyi.project.business.service.IUsersService;

/**
 * 用户Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-07-14
 */
@Service
public class UsersServiceImpl implements IUsersService 
{
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public boolean checkUserNameUnique(Users user) {
        Long userId = StringUtils.isNull(user.getId()) ? -1L : user.getId();
        Users info = usersMapper.checkUserNameUnique(user.getUsername());
        if (StringUtils.isNotNull(info) && info.getId().longValue() != userId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 查询用户
     * 
     * @param id 用户主键
     * @return 用户
     */
    @Override
    public Users selectUsersById(Long id)
    {
        return usersMapper.selectUsersById(id);
    }

    @Override
    public Users checkUserNameUnique(String username) {
        return usersMapper.selectUserByUserName(username);
    }

    /**
     * 查询用户列表
     * 
     * @param users 用户
     * @return 用户
     */
    @Override
    public List<Users> selectUsersList(Users users)
    {
        return usersMapper.selectUsersList(users);
    }

    /**
     * 新增用户
     * 
     * @param users 用户
     * @return 结果
     */
    @Override
    public int insertUsers(Users users)
    {
        return usersMapper.insertUsers(users);
    }

    /**
     * 修改用户
     * 
     * @param users 用户
     * @return 结果
     */
    @Override
    public int updateUsers(Users users)
    {
        return usersMapper.updateUsers(users);
    }

    /**
     * 批量删除用户
     * 
     * @param ids 需要删除的用户主键
     * @return 结果
     */
    @Override
    public int deleteUsersByIds(Long[] ids)
    {
        return usersMapper.deleteUsersByIds(ids);
    }

    /**
     * 删除用户信息
     * 
     * @param id 用户主键
     * @return 结果
     */
    @Override
    public int deleteUsersById(Long id)
    {
        return usersMapper.deleteUsersById(id);
    }

    @Override
    public Users checkEmailUnique(Users user) {
        Long userId = StringUtils.isNull(user.getId()) ? -1L : user.getId();
        Users info = usersMapper.checkEmailUnique(user.getEmail());
        return info;
    }
}
