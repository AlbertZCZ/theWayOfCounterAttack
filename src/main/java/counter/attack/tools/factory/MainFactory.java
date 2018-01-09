package counter.attack.tools.factory;


import counter.attack.tools.imp.DataBaseHandleImp;
import counter.attack.tools.imp.FileHandleImp;
import counter.attack.tools.imp.JsonHandleImp;
import counter.attack.tools.imp.ToolsImp;
import counter.attack.tools.inter.DataBaseHandle;
import counter.attack.tools.inter.FileHandle;
import counter.attack.tools.inter.JsonHandle;
import counter.attack.tools.inter.Tools;

/**工厂：	主工程类
@author zhang
*1.底层数据库操作
*2.常用工具接口
*3.文件操作
*4.JSON操作
*/
public class MainFactory {
	/**接口说明： 底层数据库操作*/
	public static DataBaseHandle getDataBaseMethod(String dbName){
		return new DataBaseHandleImp(dbName);
	}
	/**接口说明： 常用工具接口*/ 
	public static Tools getTools(){
		return new ToolsImp();
	}
	/***接口说明： 文件操作*/
	public static FileHandle getFileHandleMethod(){
		return new FileHandleImp();
	}
	/***接口说明： JSON操作*/
	public static JsonHandle getJsonHandleMethod(){
		return new JsonHandleImp();
	}
}
