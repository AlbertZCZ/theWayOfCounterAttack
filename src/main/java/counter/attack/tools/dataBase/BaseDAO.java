package counter.attack.tools.dataBase;

import java.sql.Connection;

public class BaseDAO implements IBaseDAO {
	public Connection getConnByPool(String dbName) {// tomcat连接池
		return PoolOperation.getConnByPool(dbName);
	}
}
