package counter.attack.Interview.string;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Create by zhang on 2018/3/21
 * 得到字符串中第一个没有重复的字符
 */
public class FirstChar {
    public static void getFirstChar(String str) {
        //不要忘了非空判断
        if (str == null || str.equals("")) {
            System.out.println("字符串不能为空");
            return;
        }
        Map<Character,Integer> charCount = new HashMap<>();
        char[] strArray = str.toCharArray();
        for (int i = 0;i < strArray.length;i++) {
            int count = charCount.get(strArray[i]) == null?0:charCount.get(strArray[i]);
            charCount.put(strArray[i],++count);
        }

        for (Map.Entry entry:charCount.entrySet()) {
            System.out.print(entry.getValue());
        }
        charCount.keySet().iterator();
        Iterator<Map.Entry<Character, Integer>> iterator = charCount.entrySet().iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().getValue());
        }

        //因为hashMap中key是无序的，不能遍历map。需要一次遍历字符串
        for (char key:strArray) {
            if (charCount.get(key) == 1) {
                System.out.println(key);
                return;
            }

        }
    }

    public static void main(String[] args) {
        String abc = "aabbcdef";
        getFirstChar(abc);
        getFirstChar("");
    }
}
