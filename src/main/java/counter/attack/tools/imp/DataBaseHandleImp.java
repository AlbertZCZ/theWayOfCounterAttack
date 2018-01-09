package counter.attack.tools.imp;


import com.sun.rowset.CachedRowSetImpl;
import counter.attack.tools.dataBase.BaseDAO;
import counter.attack.tools.inter.DataBaseHandle;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;

/**
 * 实现：数据库的基本操作
 *
 * @author zhang
 */
public class DataBaseHandleImp extends BaseDAO implements DataBaseHandle {
    private Connection conn;
    public Logger logger;
    private Statement stmt = null;

    public DataBaseHandleImp(String dbName) {
        try {
            conn = getConnByPool(dbName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<JSONObject> executeQueryToJSON(String sqlStr) {
        return executeQueryData(sqlStr);
    }

    public JSONObject executeQueryToOneJSON(String sqlStr) {
        ArrayList<JSONObject> data = executeQueryData(sqlStr);
        if (data.size() == 0) {
            return null;
        } else {
            return data.get(0);
        }
    }

    public ArrayList<JSONObject> executeQueryData(String sqlStr) {
        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
        if (!sqlStr.equals("")) {
            ResultSet rs;
            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sqlStr);
                ResultSetMetaData rsmd = rs.getMetaData();
                while (rs.next()) {
                    JSONObject valueMap = new JSONObject();
                    for (int i = 0; i < rsmd.getColumnCount(); i++) {
                        valueMap.put(rsmd.getColumnName(i + 1), rs.getString(i + 1));
                    }
                    list.add(valueMap);
                }
            } catch (SQLException SQLE) {
                logger.error("", SQLE);
            } finally {
                try {
                    closeConnect();
                } catch (Exception ex1) {
                    logger.error("", ex1);
                }
            }
        } else {
            logger.error("sql不能为空");
        }
        return list;
    }

    public CachedRowSetImpl executeQuery(String sqlStr) {
        logger.info(sqlStr);
        if (!sqlStr.equals("")) {
            ResultSet rs = null;
            CachedRowSetImpl crs;
            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(sqlStr);
                crs = new CachedRowSetImpl();
                crs.populate(rs);
            } catch (SQLException SQLE) {
                crs = null;
                logger.error("", SQLE);
            } finally {
                try {
                    closeConnect();
                } catch (Exception ex1) {
                    logger.error("", ex1);
                }
            }
            return crs;
        } else {
            logger.info("sql不能为空");
            return null;
        }
    }

    public boolean upDate(String sqlStr) {
        logger.info(sqlStr);
        boolean result = false;
        if (!sqlStr.equals("")) {
            try {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                stmt.executeUpdate(sqlStr);
                result = true;
            } catch (SQLException SQLE) {
                result = false;
                logger.error("", SQLE);
            } finally {
                try {
                    closeConnect();
                } catch (Exception ex1) {
                    logger.error("", ex1);
                }
            }
            return result;
        } else {
            logger.info("sql不能为空");
            return result;
        }
    }

    public int upDateId(String sqlStr) {
        logger.info(sqlStr);
        int result = -1;
        if (!sqlStr.equals("")) {
            try {
                stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
                stmt.executeUpdate(sqlStr, Statement.RETURN_GENERATED_KEYS);
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    result = rs.getInt(1);
                }
            } catch (SQLException SQLE) {
                logger.error("", SQLE);
            } finally {
                try {
                    closeConnect();
                } catch (Exception ex1) {
                    logger.error("", ex1);
                }
            }
            return result;
        } else {
            logger.info("sql不能为空");
            return result;
        }
    }

    public boolean handleTransaction(ArrayList<String> sqlArray) {
        boolean result = false;
        int AList = sqlArray.size();
        if (AList != 0) {
            try {
                stmt = conn.createStatement();
                conn.setAutoCommit(false);
                for (int i = 0; i < AList; i++) {
                    stmt.executeUpdate(String.valueOf(sqlArray.get(i)));
                }
                conn.commit();
                conn.setAutoCommit(true);
                result = true;
            } catch (SQLException SQLE) {
                try {
                    conn.rollback();
                } catch (SQLException e) {
                    logger.error("", e);
                }
                logger.error("", SQLE);
                return result;
            } finally {
                try {
                    closeConnect();
                } catch (Exception ex1) {
                    logger.error("", ex1);
                }
            }
            return result;
        } else {
            logger.info("没有可以更新的sql语句");
            return result;
        }
    }

    /**
     * 关闭连接
     * @throws SQLException
     */
    private void closeConnect() throws SQLException {
        if (stmt != null)
            stmt.close();
        if (conn != null)
            conn.close();
    }

    public boolean insertJson(String tableName, JSONObject json) {

        StringBuffer colNames = new StringBuffer();
        StringBuffer values = new StringBuffer();
        Iterator<Entry<String, Object>> iterator = json.entrySet().iterator();
        json.size();
        int index = 0;

        while (iterator.hasNext()) {
            Entry<String, Object> entry = iterator.next();

            colNames.append(entry.getKey());
            if (index < json.size() - 1) {
                colNames.append(',');
            }
            // String value = Tools.toString(entry.getValue());
            String value = String.valueOf(entry.getValue());

            if (!value.contains("()")) {
                values.append("'");
            }
            values.append(value);
            if (!value.contains("()")) {
                values.append("'");
            }
            if (index < json.size() - 1) {
                values.append(',');
            }

            index++;
        }

        StringBuffer buffer = new StringBuffer();
        buffer.append("INSERT INTO ");

        buffer.append(tableName);
        buffer.append(" (");
        buffer.append(colNames.toString());
        buffer.append(") ");

        buffer.append("VALUES");
        buffer.append(" (");
        buffer.append(values.toString());
        buffer.append(") ");

        return upDate(buffer.toString());

    }
}
