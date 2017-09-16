package com.allen.controller.sys;

import com.allen.common.pojo.BootstrapTableResult;
import com.allen.common.pojo.ServerResponse;
import com.allen.pojo.Role;
import com.allen.pojo.vo.RoleVo;
import com.allen.service.RoleService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 角色管理
 * Created by Allen on 2017/9/15.
 */

@Controller
@RequestMapping("/sys/role")
public class RoleManageController {

	private static final Log logger = LogFactory.getLog(RoleManageController.class);

	@Autowired
	private RoleService roleService;
	/**
	 * 展示角色管理页面
	 * @return
	 */
	@RequestMapping("/manage/list")
	public String showRoleManageList() {
		return "/sys/role-manage-list";
	}

	/**
	 * 获取角色管理数据
	 * @return
	 */
	@RequestMapping("/manage/list/data")
	@ResponseBody
	public BootstrapTableResult<RoleVo> findRoleManageListData(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
															   @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
		return roleService.findRoleByPagination(offset, limit);
	}

	/**
	 * 角色删除
	 * @return
	 */
	@RequestMapping("/manage/{roleId}/delete")
	@ResponseBody
	public ServerResponse deleteRoleById(@PathVariable int roleId) {
		int count = roleService.deleteRoleById(roleId);
		if (count > 0) {
			return ServerResponse.createBySuccessMessage("删除成功");
		} else {
			return ServerResponse.createByErrorMessage("删除失败");
		}
	}

	/**
	 * 角色添加视图
	 * @return
	 */
	@RequestMapping("/manage/add/view")
	public String showRoleAddView() {
		return "/sys/role-manage-add";
	}

	/**
	 * 角色添加
	 * @return
	 */
	@RequestMapping("/manage/add")
	@ResponseBody
	public ServerResponse addRole(Role role) {
		int count = roleService.addRole(role);
		if (count > 0) {
			return ServerResponse.createBySuccessMessage("添加成功");
		} else {
			return ServerResponse.createByErrorMessage("添加失败");
		}
	}

	/**
	 * 角色编辑视图
	 * @return
	 */
	@RequestMapping("/manage/{roleId}/edit/view")
	public String showRoleEditView(@PathVariable int roleId, Model model) {
		model.addAttribute("role", roleService.findRoleById(roleId));
		return "/sys/role-manage-edit";
	}

	/**
	 * 角色编辑视图
	 * @return
	 */
	@RequestMapping("/manage/edit")
	@ResponseBody
	public ServerResponse editRoleById(Role role) {
		int count = roleService.editRoleById(role);
		if (count > 0) {
			return ServerResponse.createBySuccessMessage("编辑成功");
		} else {
			return ServerResponse.createByErrorMessage("编辑失败");
		}
	}
}
