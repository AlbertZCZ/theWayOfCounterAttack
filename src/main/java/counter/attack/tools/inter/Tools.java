package counter.attack.tools.inter;

import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

/**接口说明：在项目中常用的方法
@author zhang
*/
public interface Tools {
	
	/**接口说明： 获得服务器时间
	 @param	arg0 时间格式：如yyyy-MM-dd HH:mm:ss
	 @return java.lang.String
	 */
	String getForDate(String arg0);
	
	/**接口说明：String类型的时间格式转化
	 @param	arg0 String时间
	 @param	arg1 时间格式：如yyyy-MM-dd HH:mm:ss
	 @return java.lang.String
	 */
	String dataFormatToStrig(String arg0, String arg1);
	
	/**接口说明：String时间转换Date时间
	 @param	arg0 String类型时间
	 @param	arg1 时间格式：如yyyy-MM-dd HH:mm:ss
	 @return java.util.Date
	 */
	Date dataFormatToData(String arg0, String arg1);
	
	/**接口说明：  String时间转换Timestamp时间
	 @param	arg0 String类型时间
	 @param	arg1 时间格式：	如yyyy-MM-dd HH:mm:ss
	 @return Timestamp
	 */
	Timestamp dataFormatToTimestamp(String arg0, String arg1);
	
	/**接口说明：时间比较大小
	 @param	arg0  时间1：
	 @param	arg1  时间2
	 @param	arg2 时间格式：	如yyyy-MM-dd HH:mm:ss
	 @return int 0等于、1小于 、2是大于
	 */
	int timeComparison(String arg0, String arg1, String arg2);

	/**接口说明：Date时间转换String时间
	 @param	arg0 Date类型时间
	 @param	arg1 时间格式： 如yyyy-MM-dd HH:mm:ss
	 @return java.lang.String
	 */
	String getDatetostr(Date arg0, String arg1);

	/**接口说明：生成随机数
	 @param	arg0 boolean类型值 true：生成随机英文字母和数字混合字符串	false：生成随机数字字符串
	 @param	num int类型值：	生成的随机数位数
	 @return java.lang.String
	 */
	String getUuid(boolean arg0, int num, Random random);

	/**
	 * 获取之间范围的随机数字
	 * @param num
	 *            int类型值： 生成的随机数位数
	 * @param random
	 *            java.util.Random
	 * @param engdigit
	 *            随机范围
	 * @return
	 */
	int getUuid(int num, Random random, int[] engdigit);

	/**接口说明：字符编码转换
	 @param	arg0  需要转换的值
	 @param	arg1  需要转换的编码：如UTF-8
	 @return java.lang.String
	 */
	String getCoding(String arg0, String arg1);

	/**接口说明：字符编码转换
	 @param	arg0  需要转换的值
	 @param	arg1  需要转换的编码：如UTF-8
	 @return java.lang.String
	 */
	String getCoding(byte[] arg0, String arg1);

	/**接口说明：解析制定路径下的文件名如F:\\workspace\\javaScript\\WebRoot\\javaScript\\text.txt
	 @param	filePath  字符串类型的文件路径
     @return java.lang.String
	 */
	String getFileName(String filePath);

	/**接口说明： 去掉重复值
	 *参数列表：	0、Object类型的数组
	 *返回类型：	Object类型的数组
	 */
	Object getRemoveRepeated(Object[] obj);

	/**接口说明： 获取指定日期是星期几
	 @param	dt date类型时间
	 @return java.lang.String	1-7 代表星期几
	 */
	String getWeekOfDate(Date dt);

	/**接口说明：计算两个日期相差几天
	 @param	startTime  时间1：
	 @param	endTime  时间2
	 @param	format  时间格式：	如yyyy-MM-dd HH:mm:ss
	 @return  java.util.Hashtable  key： 天、时、分、秒
	 */
	HashMap<String, Long> getDateDiff(String startTime, String endTime, String format);
	/**
	 * 计算两个时间相差多少秒
	 * @param startTime 起始时间
	 * @param endTime 结束时间
	 * @param format 时间格式： 如yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	long getCountSec(String startTime, String endTime, String format);

	/**
	 *获取完整的httpUrl
	 * @param request HttpServletRequest
	 * @return httpUrl
	 */
	String getHttpURL(HttpServletRequest request);
	/**
	 *获取IP
	 * @param request HttpServletRequest
	 * @return IP
	 */
	String getIP(HttpServletRequest request);

	/**
	 * 判断是否为中文字符
	 * @param c 内容
	 * @return 是返回true，不是返回fasle
	 */
	boolean isChinese(char c);

	/**
	 * 判断字符按串是否是乱码
	 * @param strName  内容
	 * @return 是乱码返回true，不是返回fasle
	 */
	boolean isMessyCode(String strName);

	/**
	 * 接口说明：http连接访问链接
	 * @param post
	 * @return
	 * @throws Exception
	 */
	InputStream httpConnection(PostMethod post) throws Exception;

	/**
	 * 接口说明：http连接访问链接并且发送字符串流
	 * @param post 访问url
	 * @param pram 发送的字符串
	 * @return String结果
	 * @throws Exception
	 */
	String httpConnection(PostMethod post, String pram) throws Exception;

	 /**
     * 字符串截取
     * @param text 初始文字
     * @param target_start 文件头
     * @param target_end 文件尾
     * @param start_p 偏移头
     * @param end_p 偏移尾
     * @return 截取内容
     */
	 String getString(String text, String target_start, String target_end, int start_p, int end_p);
    /**
	 * 接口说明：post或get方式http连接访问链接
	 * @param method post or get
	 * @return
	 * @throws Exception
	 */
	String httpConnectionString(HttpMethod method) throws Exception;

    /**
     * 获取详细错误信息
     * @param
     * @param t 异常类
     * @return
     */
	String getErrorInfo(Throwable t);
    
    /**
     * http get访问url
     * @param url
     * @return
     */
	String getHttpInfo(String url);
}
