package counter.attack.Interview.serializable;

import java.io.*;
import java.util.Collections;

/**
 * Create by zhang on 2018/3/8
 */
public class SerializableTest {
    public static void main(String[] args) {
        User user = new User();
        user.setAge(20);
        user.setName("Albert");
        user.setPassword(123456);

        try {
            ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream("C:\\Users\\zhang\\Desktop\\user.txt"));
            ois.writeObject(user);
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("C:\\Users\\zhang\\Desktop\\user.txt"));
            try {
                Object o = in.readObject();
                System.out.println(o);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            ois.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
    }
}
