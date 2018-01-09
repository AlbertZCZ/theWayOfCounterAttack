/**
 * 作者： 	cuilei
 * 类说明：	Tools接口的实现类
 */
package counter.attack.tools.imp;


import counter.attack.tools.factory.MainFactory;
import counter.attack.tools.inter.Tools;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现：在项目中常用的方法
 * 
 * @author zhang
 */
public class ToolsImp implements Tools {
	public Logger logger;
	public Date dataFormatToData(String arg0, String arg1) {

		Date date = null;
		SimpleDateFormat sdf = null;
		try {
			sdf = new SimpleDateFormat(arg1);
			date = sdf.parse(arg0);
		} catch (ParseException e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return date;
	}

	public String dataFormatToStrig(String arg0, String arg1) {
		String DataStr = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(arg1);
			DataStr = sdf.format(arg0);
		} catch (IllegalArgumentException e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return DataStr;
	}

	public Timestamp dataFormatToTimestamp(String arg0, String arg1) {
		DateFormat dateFormat = new SimpleDateFormat(arg1, Locale.ENGLISH);// 设定格式
		dateFormat.setLenient(false);
		Date timeDate = null;
		try {
			timeDate = dateFormat.parse(arg0);
		} catch (ParseException e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return new Timestamp(timeDate.getTime());
	}

	public int timeComparison(String arg0, String arg1, String arg2) {
		int type = 0;
		java.text.DateFormat df = new java.text.SimpleDateFormat(arg2);
		java.util.Calendar c1 = java.util.Calendar.getInstance();
		java.util.Calendar c2 = java.util.Calendar.getInstance();
		try {
			c1.setTime(df.parse(arg0));
			c2.setTime(df.parse(arg1));
		} catch (java.text.ParseException e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		int result = c1.compareTo(c2);
		if (result == 0)
			type = 0;
		else if (result < 0)
			type = 1;
		else
			type = 2;
		return type;
	}

	public String getDatetostr(Date arg0, String arg1) {
		String dataStr = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(arg1);
			dataStr = sdf.format(arg0);
		} catch (Exception e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return dataStr;
	}

	public String getForDate(String arg0) {
		String formatData = "";
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(arg0);
			Calendar rightNow = Calendar.getInstance();
			formatData = sdf.format(rightNow.getTime());
		} catch (Exception e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return formatData;
	}

	public String getCoding(String string, String coding) {
		String pageEncoding = "";
		try {
			pageEncoding = new String(string.getBytes("iso-8859-1"), coding);
		} catch (UnsupportedEncodingException e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return pageEncoding;
	}

	public String getCoding(byte[] string, String coding) {
		String pageEncoding = "";
		try {
			pageEncoding = new String(string, coding);
		} catch (UnsupportedEncodingException e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return pageEncoding;
	}

	public String getUuid(boolean arg0, int num, Random random) {
		String sRand = "";
		try {
			if (num > 0) {
				if (arg0) {
					char[] engdigit = { '1', '2', '3', '4', '5', '6', '7','8', '9',
							'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'a',
							's', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x',
							'c', 'v', 'b', 'n', 'm'};
					for (int i = 0; i < num; i++) {
						sRand += String.valueOf(engdigit[random
								.nextInt(engdigit.length)]);
					}
				} else {
					char[] engdigit = { '1', '2', '3', '4', '5', '6', '6', '9' };
					for (int i = 0; i < num; i++) {
						sRand += String.valueOf(engdigit[random
								.nextInt(engdigit.length)]);
					}
				}
			} else {
				logger.info("您所要生成的位数必须大于0");
			}
		} catch (Exception e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return sRand;
	}

	public int getUuid(int num, Random random, int[] engdigit) {
		int sRand = 0;
		try {
			if (num > 0) {
				for (int i = 0; i < num; i++) {
					sRand = engdigit[random.nextInt(engdigit.length)];
				}
			} else {
				logger.info("toolsImp类中getUuid方法");
			}
		} catch (Exception e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return sRand;
	}
	
	public String getFileName(String filePath) {
		if (filePath.length() > 0) {
			int index = filePath.lastIndexOf(File.separatorChar);
			if (index < 0 || index >= filePath.length() - 2)
				return null;
			return filePath.substring(index + 1);
		} else {
			logger.info("参数不能为空");
			return null;
		}

	}

	public Object getRemoveRepeated(Object[] obj) {
		if (obj != null) {
			Set s = new HashSet();
			for (int i = 0; i < obj.length; i++)
				s.add(obj[i]);
			obj = new Object[s.size()];
			s.toArray(obj);
			return obj;
		} else {
			logger.info("参数不能为空");
			return null;
		}
	}

	public String getWeekOfDate(Date dt) {
		String[] weekDays = { "7", "1", "2", "3", "4", "5", "6" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);

		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;

		return weekDays[w];
	}

	public HashMap<String, Long> getDateDiff(String startTime, String endTime,
                                             String format) {
		// 按照传入的格式生成一个simpledateformate对象
		SimpleDateFormat sd = new SimpleDateFormat(format);
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		long nh = 1000 * 60 * 60;// 一小时的毫秒数
		long nm = 1000 * 60;// 一分钟的毫秒数
		long ns = 1000;// 一秒钟的毫秒数
		long diff;
		HashMap<String, Long> ht = new HashMap<String, Long>();
		try {
			// 获得两个时间的毫秒时间差异
			diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
			long day = diff / nd;// 计算差多少天
			long hour = diff % nd / nh;// 计算差多少小时
			long min = diff % nd % nh / nm;// 计算差多少分钟
			long sec = diff % nd % nh % nm / ns;// 计算差多少秒
			// 输出结果
			ht.put("day", day);
			ht.put("hour", hour);
			ht.put("min", min);
			ht.put("sec", sec);
		} catch (ParseException e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return ht;
	}

	public long getCountSec(String startTime, String endTime, String format) {
		HashMap<String, Long> ht = MainFactory.getTools().getDateDiff(
				startTime, endTime, format);
		long day = ht.get("day") * 24 * 60 * 60;
		long hour = ht.get("hour") * 60 * 60;
		long min = ht.get("min") * 60;
		long sec = ht.get("sec");
		return day + hour + min + sec;
	}

	@SuppressWarnings("rawtypes")
	public String getHttpURL(HttpServletRequest request) {
		HttpServletRequest httpRequest = request;
		String strBackUrl = "http://" + request.getServerName() // 服务器地址
				+ ":" + request.getServerPort() // 端口号
				+ httpRequest.getContextPath() // 项目名称
				+ httpRequest.getServletPath() // 请求页面或其他地址
				+ "?" + (httpRequest.getQueryString()); // 参数

		return strBackUrl;
	}

	public String getIP(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getHeader("WL-Proxy-Client-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
			ip = request.getRemoteAddr();
		return ip;
	}
	
	public boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
			return true;
		}
		return false;
	}

	public boolean isMessyCode(String strName) {
		Pattern p = Pattern.compile("\\s*|\t*|\r*|\n*");
		Matcher m = p.matcher(strName);
		String after = m.replaceAll("");
		String temp = after.replaceAll("\\p{P}", "");
		char[] ch = temp.trim().toCharArray();
		float chLength = ch.length;
		float count = 0;
		for (int i = 0; i < ch.length; i++) {
			char c = ch[i];
			if (!Character.isLetterOrDigit(c)) {

				if (!MainFactory.getTools().isChinese(c)) {
					count = count + 1;
				}
			}
		}
		float result = count / chLength;
		if (result > 0.4) {
			return true;
		} else {
			return false;
		}
	}
	
	public InputStream httpConnection(PostMethod post) {
		HttpClient httpclient = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		HttpConnectionManagerParams managerParams = httpclient.getHttpConnectionManager().getParams();
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(20000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(20000);
		try {
			httpclient.executeMethod(post);
			byte [] data= post.getResponseBody();
			return new ByteArrayInputStream(data);
		} catch (Exception se) {
			se.printStackTrace();
			logger.error(MainFactory.getTools().getErrorInfo(se));
		} finally {
			post.releaseConnection();
		}
		return null;
	}

	public String httpConnection(PostMethod post, String pram) {
		byte[] b = pram.getBytes();
		ByteArrayInputStream bs = new ByteArrayInputStream(b, 0, b.length);
		post.setRequestEntity(new InputStreamRequestEntity(bs, b.length));
		
		HttpClient httpclient = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
		HttpConnectionManagerParams managerParams = httpclient.getHttpConnectionManager().getParams();
		post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET,"utf-8");
		managerParams.setConnectionTimeout(10000);// 设置连接超时时间(单位毫秒)
		managerParams.setSoTimeout(10000);// 设置读数据超时时间(单位毫秒)

		try {
			logger.debug("开始发送数据");
			httpclient.executeMethod(post);
			//byte [] data= post.getResponseBody();
			//return new ByteArrayInputStream(data);
			//InputStream is=post.getResponseBodyAsStream();
			InputStream inputStream = post.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
		    StringBuffer stringBuffer = new StringBuffer();
		    String str;
		    while((str = br.readLine()) != null){   
		         stringBuffer.append(str );   
		    }   
		    return stringBuffer.toString();
		} catch (Exception se) {
			logger.error(MainFactory.getTools().getErrorInfo(se));
			return "";
		} finally {
			((SimpleHttpConnectionManager) httpclient.getHttpConnectionManager()).shutdown();
			try {
				bs.close();
			} catch (IOException e) {
				logger.error(MainFactory.getTools().getErrorInfo(e));
			}
		}
	}
	
	 /**
     * 字符串截取
     * @param text 初始文字
     * @param target_start 文件头
     * @param target_end 文件尾
     * @param start_p 偏移头
     * @param end_p 偏移尾
     * @return 截取内容
     */
    public String getString(String text, String target_start, String target_end, int start_p, int end_p) {
    	String a="nothing";
    	int start=0;
    	int end=0;
    	for(int i=0;i<text.length()-target_start.length();i++) {
    		if(text.substring(i,i+target_start.length()).equals(target_start)) {
    			start = i;
    			break;
    		}
    	}
    	for(int i=start;i<text.length()-target_end.length();i++) {
    		if(text.substring(i,i+target_end.length()).equals(target_end)) {
    			end = i;
    			break;
    		}
    	}
    	if(start!=0&&end!=0) {
    		a = text.substring(start+start_p,end+end_p);
    	}
    	return a;
    }

    @Override
    public String httpConnectionString(HttpMethod method) throws Exception {
        HttpClient httpclient = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
        HttpConnectionManagerParams managerParams = httpclient.getHttpConnectionManager().getParams();
        method.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
        // 设置连接超时时间(单位毫秒)
        managerParams.setConnectionTimeout(10000);
        // 设置读数据超时时间(单位毫秒)
        managerParams.setSoTimeout(10000);
        try {
            httpclient.executeMethod(method);
            InputStream inputStream = method.getResponseBodyAsStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
            StringBuffer stringBuffer = new StringBuffer();
            String str;
            while((str = br.readLine()) != null){
                stringBuffer.append(str );
            }
            return stringBuffer.toString();
        } catch (Exception se) {
            logger.error(MainFactory.getTools().getErrorInfo(se));
        } finally {
            method.releaseConnection();
        }
        return null;
    }


	public String getErrorInfo(Throwable t) {
		String errorInfo="";
		StringWriter stringWriter= new StringWriter();
        PrintWriter writer= new PrintWriter(stringWriter);
		try {
	        t.printStackTrace(writer);
	        StringBuffer buffer= stringWriter.getBuffer();
	        errorInfo=buffer.toString();
		} catch (Exception e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		} finally {
			try {
				writer.close();
				stringWriter.close();
			} catch (IOException e) {
				logger.error(MainFactory.getTools().getErrorInfo(e));
			}
		}
		return errorInfo;
	}
	public String getHttpInfo(String url) {
		String html = "";
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(url);
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		HttpConnectionManagerParams managerParams = httpClient.getHttpConnectionManager().getParams();
		managerParams.setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		// 设置连接超时时间(单位毫秒)
		managerParams.setConnectionTimeout(20000);
		// 设置读数据超时时间(单位毫秒)
		managerParams.setSoTimeout(20000);
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				logger.debug(getMethod.getStatusLine().toString());
			}
			// 处理内容
			InputStream inputStream = getMethod.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
		    StringBuffer stringBuffer = new StringBuffer();
		    String str;
		    while((str = br.readLine()) != null){   
		        stringBuffer.append(str );   
		    }   
		    return stringBuffer.toString();
		} catch (HttpException e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		} catch (IOException e) {
			logger.error(MainFactory.getTools().getErrorInfo(e));
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return html;
	}
}
