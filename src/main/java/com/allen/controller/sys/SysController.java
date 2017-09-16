package com.allen.controller.sys;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台主页
 * Created by Allen on 2017/9/15.
 */

@Controller
public class SysController {

	/**
	 * 展示后台页面
	 * @return
	 */
	@RequestMapping("/sys")
	public String showSysIndex() {
		return "/sys/index";
	}
}
