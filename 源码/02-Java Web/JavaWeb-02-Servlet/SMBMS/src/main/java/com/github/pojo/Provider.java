package com.github.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Package: com.github.pojo
 * @ClassName: Provider
 * @Author: subei
 * @CreateTime: 2021/8/5 10:34
 * @Description:
 */

@Data
public class Provider {
    private Integer id;  // 主键ID
    private String proCode; // 供应商编码
    private String proName; // 供应商名称
    private String proDesc; // 供应商详细描述
    private String proContact;  // 供应商联系人
    private String proPhone;    // 联系电话
    private String proAddress;  // 地址
    private String proFax;  // 传真
    private Integer createdBy;   // 创建者
    private Date creationDate;    // 创建时间
    private Date modifyDate;  // 更新时间
    private Integer modifyBy;    //更新者


}
