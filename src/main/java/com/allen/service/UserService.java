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

	BootstrapTableResult<UserVo> findUserByPagination(int offset, int limit, User user);

	User findUserById(int userId);

	int deleteUserById(int userId);

	int addUser(User user);

	int editUserById(User user);

}
