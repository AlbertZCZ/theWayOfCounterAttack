package counter.attack.Interview.serializable;

import java.io.Serializable;

/**
 * Create by zhang on 2018/3/8
 */
public class User implements Serializable {
    private String name;
    private int age;
    private transient int password;//使用transient关键字修饰的变量不会被序列化

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", password=" + password +
                '}';
    }
}
