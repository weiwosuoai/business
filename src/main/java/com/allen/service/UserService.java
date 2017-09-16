package com.allen.service;

import com.allen.common.pojo.BootstrapTableResult;
import com.allen.pojo.Role;
import com.allen.pojo.User;
import com.allen.pojo.vo.RoleVo;
import com.allen.pojo.vo.UserVo;

/**
 * Created by Allen on 2017/9/15.
 */
public interface UserService {

	BootstrapTableResult<UserVo> findRoleByPagination(int offset, int limit, User user);

	Role findRoleById(int roleId);

	int deleteRoleById(int roleId);

	int addUser(User user);

	int editRoleById(Role role);

}
