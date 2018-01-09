package counter.attack.tools.inter;

import com.sun.rowset.CachedRowSetImpl;
import net.sf.json.JSONObject;

import java.util.ArrayList;

/**接口说明： 数据库的基本操作接口
 @author zahng
 */
public interface DataBaseHandle {
	/**
	 * 接口说明： jdbc查询多个纪录
	 *
	 * @param sqlStr
	 *            sql语句
	 * @return com.sun.rowset.CachedRowSetImp
	 */
	ArrayList<JSONObject> executeQueryToJSON(String sqlStr);

	/**
	 * 接口说明： jdbc查询一条数据
	 * @param sqlStr
	 * @return
	 */
	JSONObject executeQueryToOneJSON(String sqlStr) ;
	/**接口说明： jdbc查询
	 @param sqlStr sql语句
	 @return com.sun.rowset.CachedRowSetImp
	 */
	CachedRowSetImpl executeQuery(String sqlStr);
	
	/**接口说明： 执行更新
	 @param sqlStr 执行更新sql语句
	 @return java.long.boolean
	 */
	boolean upDate(String sqlStr) ;
	
	/**接口说明： 执行事务处理  主要针对操作多张表进行编写的数据回滚
	 @param sqlArray java.util.ArrayList(多条sql语句)
	 @return java.long.boolean
	 */
	boolean handleTransaction(ArrayList<String> sqlArray);
	
	/**
	 * 接口说明： 执行更新并返回当前更新的id
	 * 
	 * @param sqlStr
	 *            执行更新sql语句
	 * @return id
	 */
	int upDateId(String sqlStr);
	
	/**
	 * 将json插入数据库
	 * @param tableName	表名
	 * @param json
	 * @return
	 */
	boolean insertJson(String tableName, JSONObject json);
}
