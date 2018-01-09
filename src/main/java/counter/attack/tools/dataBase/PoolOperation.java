package counter.attack.tools.dataBase;

/** 
 * Title: ConnectPool.java 
 * Description: 数据库操作 
 * Copyright: Copyright (c) 2008/6/25 
 * Company: 
 * Author : 
 * remark : 加入指针回滚 
 * Version 2.0 
 */


import org.apache.logging.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class PoolOperation {
	private static Logger logger;

	/**
	 * 对象连接初始化
	 */
	public static Connection getConnByPool(String dbName) {
		try {
			// 1.得到部署有连接池对象的环境
			Context ctx = new InitialContext();
			// 2. 从环境中得到连接池
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/" + dbName);
			// 3. 得到连接
			return ds.getConnection();

		} catch (Exception e) {
			logger.error("获得数据库连接错误", e);
			return null;
		}

	}
}
