package com.allen.service.impl;

import com.allen.common.pojo.BootstrapTableResult;
import com.allen.dao.RoleMapper;
import com.allen.dao.UserMapper;
import com.allen.pojo.Role;
import com.allen.pojo.RoleExample;
import com.allen.pojo.User;
import com.allen.pojo.UserExample;
import com.allen.pojo.vo.RoleVo;
import com.allen.pojo.vo.UserVo;
import com.allen.service.RoleService;
import com.allen.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Allen on 2017/9/15.
 */
@Service
public class UserServiceImpl implements UserService {

	private static final Log logger = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RoleMapper roleMapper;

	@Override
	public BootstrapTableResult<UserVo> findRoleByPagination(int offset, int limit, User user) {
		PageHelper.startPage((offset / limit) + 1, limit);

		// 条件设置
		UserExample example = new UserExample();
		example.setOrderByClause("create_time desc");

		UserExample.Criteria criteria = example.createCriteria();
		if (!StringUtils.isEmpty(user.getUsername())) {
			criteria.andUsernameLike("%" + user.getUsername() + "%");
		}

		if (user.getRoleId() != null) {
			criteria.andRoleIdEqualTo(user.getRoleId());
		}

		List<UserVo> list = userMapper.selectByExample(example);

		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (UserVo vo : list) {
			vo.setCreateTimeStr(sdf.format(vo.getCreateTime()));

			// 角色关联的角色名称，角色信息启用了 mybatis 的缓存，无需通过关联查询，从数据库取，提升性能
			Role role = roleMapper.selectByPrimaryKey(vo.getRoleId());
			vo.setRole(role);
		}

		PageInfo<UserVo> pageInfo = new PageInfo<>(list);

		BootstrapTableResult<UserVo> result = new BootstrapTableResult<>();
		result.setTotal(pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}

	@Override
	public Role findRoleById(int roleId) {
		return null;
	}

	@Override
	public int deleteRoleById(int roleId) {
		return 0;
	}

	@Override
	public int addUser(User user) {
		try {
			// 密码 md5 加密
			user.setPassword(DigestUtils.md5Hex(user.getPassword().getBytes()));
			return userMapper.insert(user);
		} catch (Exception e) {
			logger.error("", e);
		}
		return 0;
	}

	@Override
	public int editRoleById(Role role) {
		return 0;
	}
}
