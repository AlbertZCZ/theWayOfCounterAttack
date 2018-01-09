package counter.attack.tools.inter;

import javax.servlet.ServletOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

/**接口说明： 文件操作的接口类
@author zhang
*/
public interface FileHandle {
	/**接口说明：创建目录
	 @param	arg0 目录路径 
	 @return java.lang.Boolean
	 */
	boolean createFolder(String arg0)throws Exception;
	/**接口说明：解析此路径的文件名
	 @param	arg0 文件路径 
	 @return java.lang.String
	 */
	String getFileName(String arg0);

	/**接口说明：删除目录下所有文件
	 @param	arg0 File 对象的文件路径
	 */
	void emptyDirectory(File arg0);

	/**接口说明：创建文件
	 @param	arg0 文件所在目录路径
	 @return java.lang.boolean
	 */
	boolean makeFile(String arg0) ;
	/**接口说明：删除文件
	 @param	arg0 文件所在物理路径
	 @return java.lang.boolean
	 */
	boolean isDel(String arg0);

	/**接口说明：文件重命名
	 @param	arg0 文件所在物理路径
	 @param	arg1 新文件名
	 @return java.lang.boolean
	 */
	boolean renamefile(String arg0, String arg1);

	/**接口说明：获取网络文件保存至本地,此方法只能用户HTTP协议
	 @param	arg0 网络文件地址
	 @param	arg1 网络文件地址本地文件地址
	 @return java.lang.boolean
	 */
	boolean saveURLAs(String arg0, String arg1);

	/**
	 * 接口说明：String转InputStream
	 * @param str 内容
	 * @param coding 编码
	 * @return
	 */
	InputStream String2InputStream(String str, String coding);
	/**接口说明：向客户端返回int流
	 @param	value  写入的值：
	 @param	sosOutputStream  HttpServletResponse
	 */
	void responseInt(int value, ServletOutputStream sosOutputStream);
	/**接口说明：向客户端返回Long流
	 @param	value  写入的值：
	 @param	sosOutputStream  HttpServletResponse
	 */
	void responseLong(long value, ServletOutputStream sosOutputStream);
	
	/**接口说明：向客户端返回字节流
	 @param	value  写入的值：
	 @param	sosOutputStream  HttpServletResponse
	 */
	void responseString(String value, ServletOutputStream sosOutputStream);
	/**接口说明：向客户端返回int数组
	 @param	array  写入的值：
	 @param	sosOutputStream  HttpServletResponse
	 */
	void responseArray(int[] array, ServletOutputStream sosOutputStream);
	
	/**接口说明：向客户端返回int数组
	 @param	array  写入的值：
	 @param	sosOutputStream  HttpServletResponse
	 */
	void responseArray(ArrayList<Integer> array, ServletOutputStream sosOutputStream);
	
	/**
	 *接口说明：向客户端返回int数组
	 * @param array  写入的值：
	 * @param dos DataOutputStream
	 */
	void responseArray(int[] array, DataOutputStream dos);
	/**
	 *接口说明：向客户端返回String数组
	 * @param array  写入的值：
	 * @param dos DataOutputStream
	 */
	void responseArray(String[] array, DataOutputStream dos);
	/**接口说明：向客户端返回String数组
	 @param	array  写入的值：
	 @param	sosOutputStream  HttpServletResponse
	 */
	void responseArray(String[] array, ServletOutputStream sosOutputStream) ;
	
	/**
	 * 接口说明：向客户端返回boolean
	 * @param value
	 * @param sosOutputStream
	 */
	void responseBoolean(boolean value, ServletOutputStream sosOutputStream);
	/**
	 * 接口说明：向客户端返回boolean[]
	 * @param array
	 * @param sosOutputStream
	 */
	void responseArray(boolean[] array, ServletOutputStream sosOutputStream);
	/**
	 * 接口说明：向客户端返回byte
	 * @param value
	 * @param sosOutputStream
	 */
	void responseByte(byte value, ServletOutputStream sosOutputStream);
	
	/**
	 * 接口说明：向客户端返回byte[]
	 * @param array
	 * @param sosOutputStream
	 */
	void responseArray(byte[] array, ServletOutputStream sosOutputStream);
	
	
}
