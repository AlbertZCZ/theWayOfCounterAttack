package counter.attack.tools.imp;


import counter.attack.tools.inter.JsonHandle;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**实现：JSON操作
 * @author zhang
 *
 */
public class JsonHandleImp implements JsonHandle {
	public Logger logger;
	
	public JSONObject getJsonData(HashMap hashMap) {

		if (hashMap != null) {
			JSONObject jsonObj = new JSONObject();
			Set keys = hashMap.keySet();
			Iterator it = keys.iterator();
			while (it.hasNext()) {
				String key = (String) it.next();
				jsonObj.put(key, hashMap.get(key));
			}
			return jsonObj;
		} else {
			return null;
		}

	}

	public JSONObject getJsonData(String str) {
		try{
			JSONObject jsonObj = JSONObject.fromObject(str);
			return jsonObj;
		}catch(JSONException e){
			logger.error("", e);
		}
		return null;
	}

	

}
