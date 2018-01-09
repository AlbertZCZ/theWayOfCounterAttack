package counter.attack.tools.inter;

import java.util.HashMap;

import net.sf.json.JSONObject;


/**接口说明：JSON操作
 * @author zhang
 *
 */
public interface JsonHandle {
		
	/**将Hashtable数据集转换为JSONObject数据集
	 * @param hashMap java.util.Hashtable
	 * @return net.sf.json.JSONObject
	 */
    JSONObject getJsonData(HashMap hashMap);
	/**将String字符串转换为JSONObject数据集
	 * @param str java.long.String
	 * @return net.sf.json.JSONObject
	 */
    JSONObject getJsonData(String str);
}
