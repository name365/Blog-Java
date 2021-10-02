package com.louis.mango.admin.service;

import java.util.List;

import com.louis.mango.admin.model.SysConfig;
import com.louis.mango.core.service.CurdService;

/**
 * 系统配置管理
 * @author Louis
 * @date Jan 13, 2019
 */
public interface SysConfigService extends CurdService<SysConfig> {

	/**
	 * 根据名称查询
	 * @param lable
	 * @return
	 */
	List<SysConfig> findByLable(String lable);
}
