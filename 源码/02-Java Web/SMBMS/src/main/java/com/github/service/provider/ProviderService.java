package com.github.service.provider;

import com.github.pojo.Provider;

import java.util.List;

/**
 * @Package: com.github.service.provider
 * @ClassName: ProviderService
 * @Author: subei
 * @CreateTime: 2021/8/15 9:15
 * @Description:
 */
public interface ProviderService {
    // 增加供应商
    public boolean add(Provider provider);

    // 通过供应商名称、编码获取供应商列表-模糊查询-providerList
    public List<Provider> getProviderList(String proName, String proCode);

    // 通过proId删除Provider
    public int deleteProviderById(String delId);

    // 通过proId获取Provider
    public Provider getProviderById(String id);

    // 修改用户信息
    public boolean modify(Provider provider);

}
