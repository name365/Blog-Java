package com.louis.mango.backup.datasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 数据源
 * @author Louis
 * @date Jan 15, 2019
 */
@Component  
@ConfigurationProperties(prefix = "mango.backup.datasource")  
public class BackupDataSourceProperties {
	
	private String host;
	private String userName;
	private String password;
	private String database;
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDatabase() {
		return database;
	}
	public void setDatabase(String database) {
		this.database = database;
	}
	
}  