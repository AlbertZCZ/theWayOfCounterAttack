package counter.attack.tools.dataBase;

import java.sql.Connection;

public interface IBaseDAO {
	Connection getConnByPool(String dbName);
}
