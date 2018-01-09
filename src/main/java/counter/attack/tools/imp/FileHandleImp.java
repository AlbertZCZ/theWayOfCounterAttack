package counter.attack.tools.imp;


import counter.attack.tools.factory.MainFactory;
import counter.attack.tools.inter.FileHandle;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletOutputStream;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * 实现：文件操作基础方法
 * 
 * @author cuilei
 */
public class FileHandleImp implements FileHandle {
	public Logger logger;

	public boolean createFolder(String arg0) throws Exception {
		boolean result = false;
		if (!arg0.equals("")) {
			try {
				File file = new File(arg0);
				result = file.mkdirs();
				if (!result) {
					logger.info("目录创建失败您所创建的目录是:" + arg0);
				}
			} catch (SecurityException e) {
				logger.error("", e);
			}
		}
		return result;
	}

	public void emptyDirectory(File arg0) {
		try {
			File[] entries = arg0.listFiles();
			for (int i = 0; i < entries.length; i++) {
				entries[i].delete();
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public String getFileName(String arg0) {
		String fileName = "";
		int index = 0;
		try {
			index = arg0.lastIndexOf(File.separator);
			if (index > 0 || index <= arg0.length() - 2) {
				fileName = arg0.substring(index + 1);
			} else {
				logger.info("获取文件名失败您输入的文件路径是：" + arg0);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return fileName;
	}

	public boolean isDel(String arg0) {
		boolean result = false;
		try {
			if (!arg0.equals("")) {
				File file = new File(arg0);
				result = file.delete();
			} else {
				logger.info("输入的文件路径不能为空:" + arg0);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}

	public boolean makeFile(String arg0) {
		boolean result = false;
		try {
			if (!arg0.equals("")) {
				File file = new File(arg0);
				result = file.createNewFile();
				file = null;
			} else {
				logger.info("输入的文件路径不能为空:" + arg0);
			}
		} catch (IOException e) {
			logger.error("", e);
		}
		return result;
	}

	public boolean renamefile(String arg0, String arg1) {
		boolean result = false;
		try {
			if (!arg0.equals("") && !arg1.equals("")) {
				File f = new File(arg0);
				String fileParent = f.getParent();
				File rf = new File(fileParent + "//" + arg1);
				if (f.renameTo(rf)) {
					result = true;
				}
				f = null;
				rf = null;
			} else {
				logger.info("参数不能为空");
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return result;
	}

	public boolean saveURLAs(String photoURL, String fileName) {
		try {
			URL URL = new URL(photoURL);
			HttpURLConnection connection = (HttpURLConnection) URL.openConnection();
			DataInputStream in = new DataInputStream(connection.getInputStream());
			DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
			byte[] buffer = new byte[4096];
			int count = 0;
			while ((count = in.read(buffer)) > 0) {
				out.write(buffer, 0, count);
			}
			out.close();
			in.close();
			return true;
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}

	public InputStream String2InputStream(String str, String coding) {
		ByteArrayInputStream stream = null;
		try {
			stream = new ByteArrayInputStream(str.getBytes(coding));
		} catch (UnsupportedEncodingException e) {
			logger.error("", e);
		}
		return stream;
	}

	public void responseInt(int value, ServletOutputStream sosOutputStream) {
		try {

			byte[] buff = new byte[4];
			buff[0] = (byte) (value >> 24);
			buff[1] = (byte) (value >> 16);
			buff[2] = (byte) (value >> 8);
			buff[3] = (byte) value;
			sosOutputStream.write(buff, 0, 4);
		} catch (IOException e) {
			logger.error("", e);
		}

	}

	public void responseLong(long value, ServletOutputStream sosOutputStream) {
		try {
			byte writeBuffer[] = new byte[8];
			writeBuffer[0] = (byte) (value >>> 56);
			writeBuffer[1] = (byte) (value >>> 48);
			writeBuffer[2] = (byte) (value >>> 40);
			writeBuffer[3] = (byte) (value >>> 32);
			writeBuffer[4] = (byte) (value >>> 24);
			writeBuffer[5] = (byte) (value >>> 16);
			writeBuffer[6] = (byte) (value >>> 8);
			writeBuffer[7] = (byte) (value >>> 0);
			sosOutputStream.write(writeBuffer, 0, 8);
		} catch (IOException e) {
			logger.error("", e);
		}

	}

	public void responseString(String value, ServletOutputStream sosOutputStream) {
		try {
			byte[] b = value.getBytes("utf-8");
			MainFactory.getFileHandleMethod().responseInt(b.length, sosOutputStream);
			sosOutputStream.write(b);
		} catch (IOException e) {
			logger.error("", e);
		}
	}

	public void responseArray(int[] array, ServletOutputStream sosOutputStream) {
		try {
			int len = array.length;
			MainFactory.getFileHandleMethod().responseInt(len, sosOutputStream);
			for (int i = 0; i < len; i++) {
				MainFactory.getFileHandleMethod().responseInt(array[i], sosOutputStream);
			}
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	public void responseArray(ArrayList<Integer> array, ServletOutputStream sosOutputStream) {
		try {
			int len = array.size();
			MainFactory.getFileHandleMethod().responseInt(len, sosOutputStream);
			for (int i = 0; i < len; i++) {
				MainFactory.getFileHandleMethod().responseInt(array.get(i), sosOutputStream);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void responseArray(int[] array, DataOutputStream dos) {
		try {
			int len = array.length;
			dos.writeInt(len);
			for (int i = 0; i < len; i++) {
				dos.writeInt(array[i]);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void responseArray(String[] array, DataOutputStream dos) {
		try {
			int len = array.length;
			dos.writeInt(len);
			for (int i = 0; i < len; i++) {
				dos.writeUTF(array[i]);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void responseArray(String[] array, ServletOutputStream sosOutputStream) {
		try {
			int len = array.length;
			MainFactory.getFileHandleMethod().responseInt(len, sosOutputStream);
			for (int i = 0; i < len; i++) {
				MainFactory.getFileHandleMethod().responseString(array[i], sosOutputStream);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void responseBoolean(boolean value, ServletOutputStream sosOutputStream) {

		try {
			int ret = value ? 1 : 0;
			sosOutputStream.write(ret);
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	public void responseArray(boolean[] array, ServletOutputStream sosOutputStream) {

		try {
			int len = array.length;
			MainFactory.getFileHandleMethod().responseInt(len, sosOutputStream);
			for (int i = 0; i < len; i++) {
				MainFactory.getFileHandleMethod().responseBoolean(array[i], sosOutputStream);
			}
		} catch (Exception e) {
			logger.error("", e);
		}

	}

	public void responseByte(byte value, ServletOutputStream sosOutputStream) {
		try {
			sosOutputStream.write(value);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public void responseArray(byte[] array, ServletOutputStream sosOutputStream) {
		try {
			int len = array.length;
			MainFactory.getFileHandleMethod().responseInt(len, sosOutputStream);
			for (int i = 0; i < len; i++) {
				MainFactory.getFileHandleMethod().responseByte(array[i], sosOutputStream);
			}
		} catch (Exception e) {
			logger.error("", e);
		}
	}

}
