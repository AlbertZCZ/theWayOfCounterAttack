package counter.attack.Interview.serializable;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * Create by zhang on 2018/3/8
 */
public class Book implements Externalizable {
    @Override
    /**
     * 指定序列化时的属性
     */
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(name);
    }

    /**
     * 指定反序列化时读取属性的顺序以及读取的属性
     * 如果你写反了属性读取的顺序，你可以发现反序列化的读取的对象的指定的属性值也会与你写的读取方式一一对应。
     * 因为在文件中装载对象是有序的x
     * @param in
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        name = (String) in.readObject();
    }
    private String name;
    private double price;

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
