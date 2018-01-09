package counter.attack.tools;

import counter.attack.tools.factory.MainFactory;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * httpclient3.1的用法post请求
 */
public class PostNetWork {
	private Logger logger = null;
	private PostMethod post=null;
	private NameValuePair[] postData=null;
	private HttpClient httpclient=null;
	private HttpConnectionManagerParams managerParams=null;
	private String infoInputStream="";
	private int connectionTimeout=60000;
	private int soTimeout=60000;
	private int netState=-1;
	private PostNetWork(){}
	/**
	 * @param url 链接url
	 */
	public PostNetWork(String url){
		this.post=new PostMethod(url);
		this.httpclient = new HttpClient(new HttpClientParams(), new SimpleHttpConnectionManager(true));
	}
	
	/**
	 * 访问网络
	 */
	public void executeMethod(){
		// 设置发送的信息数据流
		if(!infoInputStream.equals("")){
			byte[] b = infoInputStream.getBytes();
			ByteArrayInputStream bs = new ByteArrayInputStream(b, 0, b.length);
			this.post.setRequestEntity(new InputStreamRequestEntity(bs, b.length));
		}
		// 设置post请求参数
		if(this.postData!=null){
			this.post.setRequestBody(this.postData);
		}
		this.managerParams = httpclient.getHttpConnectionManager().getParams();
		// 设置参数字符编码
		this.post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		// 设置连接超时时间(单位毫秒)
		this.managerParams.setConnectionTimeout(this.connectionTimeout);
		// 设置读数据超时时间(单位毫秒)
		this.managerParams.setSoTimeout(this.soTimeout);
		// 开始访问网路
		try {
			this.netState=this.httpclient.executeMethod(this.post);
		} catch (Exception e) {
			this.logger.error(MainFactory.getTools().getErrorInfo(e));
		}
	}
	
	/**
	 * 访问网络
	 */
	public void executeMethod(byte[] info){
		// 设置post请求参数
		if(this.postData!=null){
			this.post.setRequestBody(this.postData);
		}
		// 设置发送的信息数据流
		if(info!=null){
			byte[] b = info;
			ByteArrayInputStream bs = new ByteArrayInputStream(b, 0, b.length);
			this.post.setRequestEntity(new InputStreamRequestEntity(bs, b.length));
		}
		this.managerParams = httpclient.getHttpConnectionManager().getParams();
		// 设置参数字符编码
		this.post.getParams().setParameter(HttpMethodParams.HTTP_CONTENT_CHARSET, "utf-8");
		// 设置连接超时时间(单位毫秒)
		this.managerParams.setConnectionTimeout(this.connectionTimeout);
		// 设置读数据超时时间(单位毫秒)
		this.managerParams.setSoTimeout(this.soTimeout);
		// 开始访问网路
		try {
			this.netState=this.httpclient.executeMethod(this.post);
		} catch (Exception e) {
			this.logger.error(MainFactory.getTools().getErrorInfo(e));
		}
	}
	
	/**
	 * 返回信息数据流
	 * @return
	 */
	public InputStream getInputStream(){
		try {
			byte[] data = this.post.getResponseBody();
			return new ByteArrayInputStream(data);
		} catch (Exception e) {
			this.logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return null;
	}
	
	/**
	 * 返回信息字符串
	 * @return
	 */
	public String getString(){
		try {
			InputStream inputStream = this.post.getResponseBodyAsStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream,"utf-8"));
		    StringBuffer stringBuffer = new StringBuffer();
		    String str;
		    while((str = br.readLine()) != null){   
		         stringBuffer.append(str );   
		    }   
		    return stringBuffer.toString();
		} catch (Exception e) {
			this.logger.error(MainFactory.getTools().getErrorInfo(e));
		} 
		return null;
	}
	
	/**
	 * 关闭链接
	 */
	public void closeConn(){
		try {
			this.post.releaseConnection();
			((SimpleHttpConnectionManager) this.httpclient.getHttpConnectionManager()).shutdown();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 返回联网状态吗
	 * @return
	 */
	public int getNetState(){
		return this.netState;
	} 
	/**
	 * 获取org.apache.commons.httpclient.methods.PostMethod对象，以供其他灵活操作
	 * @return org.apache.commons.httpclient.methods.PostMethod
	 */
	public PostMethod getPostMethod(){
		return this.post;
	}
	
	/**
	 * 获得请求信息html字符串
	 * @return
	 */
	public String getRequestInfo(){
		HashMap hashMap=new HashMap();
		try {
			//获取访问url
			hashMap.put("Url", this.post.getURI().toString());
			
			//获取请求头信息
			Header[]  headers=this.post.getRequestHeaders();
			HashMap headerHm=new HashMap();
			for(Header header:headers){
				headerHm.put(header.getName(), header.getValue());
			}
			hashMap.put("Headers", headerHm);
			
			//获取参数信息
			NameValuePair[] parameters=this.post.getParameters();
			HashMap paramHm=new HashMap();
			for(NameValuePair parameter:parameters){
				paramHm.put(parameter.getName(), parameter.getValue());
			}
			hashMap.put("Parameters", paramHm);
		} catch (Exception e) {
			this.logger.error(MainFactory.getTools().getErrorInfo(e));
		}
		return MainFactory.getJsonHandleMethod().getJsonData(hashMap).toString();
	}
	
	public String getInfoInputStream() {
		return infoInputStream;
	}
	public void setInfoInputStream(String infoInputStream) {
		this.infoInputStream = String.valueOf(infoInputStream);
	}
	public int getConnectionTimeout() {
		return connectionTimeout;
	}
	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}
	public int getSoTimeout() {
		return soTimeout;
	}
	public void setSoTimeout(int soTimeout) {
		this.soTimeout = soTimeout;
	}

	public NameValuePair[] getPostData() {
		return postData;
	}

	public void setPostData(NameValuePair[] postData) {
		this.postData = postData;
	}
	
}
