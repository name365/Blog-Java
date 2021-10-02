package com.louis.mango.backup.service.impl;

import org.springframework.stereotype.Service;

import com.louis.mango.backup.service.MysqlBackupService;
import com.louis.mango.backup.util.MySqlBackupRestoreUtils;

@Service
public class MysqlBackupServiceImpl implements MysqlBackupService {

	@Override
	public boolean backup(String host, String userName, String password, String backupFolderPath, String fileName,
			String database) throws Exception {
		return MySqlBackupRestoreUtils.backup(host, userName, password, backupFolderPath, fileName, database);
	}

	@Override
	public boolean restore(String restoreFilePath, String host, String userName, String password, String database)
			throws Exception {
		return MySqlBackupRestoreUtils.restore(restoreFilePath, host, userName, password, database);
	}

}
