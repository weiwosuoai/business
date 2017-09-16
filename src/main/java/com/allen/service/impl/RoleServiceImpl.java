package com.allen.service.impl;

import com.allen.common.pojo.BootstrapTableResult;
import com.allen.dao.RoleMapper;
import com.allen.pojo.Role;
import com.allen.pojo.RoleExample;
import com.allen.pojo.vo.RoleVo;
import com.allen.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by Allen on 2017/9/15.
 */
@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Override
	public BootstrapTableResult<RoleVo> findRoleByPagination(int offset, int limit) {
		PageHelper.startPage((offset/limit) + 1, limit);

		RoleExample example = new RoleExample();
		example.setOrderByClause("create_time desc");
		List<RoleVo> list = roleMapper.selectByExample(example);

		// 格式化日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (RoleVo role : list) {
			role.setCreateTimeStr(sdf.format(role.getCreateTime()));
		}

		PageInfo<RoleVo> pageInfo = new PageInfo<RoleVo>(list);

		BootstrapTableResult<RoleVo> result = new BootstrapTableResult<>();
		result.setTotal(pageInfo.getTotal());
		result.setRows(pageInfo.getList());
		return result;
	}

	@Override
	public Role findRoleById(int roleId) {
		return roleMapper.selectByPrimaryKey(roleId);
	}

	@Override
	public List<RoleVo> findAll() {
		RoleExample example = new RoleExample();
		return roleMapper.selectByExample(example);
	}

	@Override
	public int deleteRoleById(int roleId) {
		return roleMapper.deleteByPrimaryKey(roleId);
	}

	@Override
	public int addRole(Role role) {
		return roleMapper.insert(role);
	}

	@Override
	public int editRoleById(Role role) {
		return roleMapper.updateByPrimaryKeySelective(role);
	}
}
