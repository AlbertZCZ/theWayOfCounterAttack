package interview;

/**
 * Create by zhang on 2018/3/16
 * 对于集合对象声明为final指的是引用不能被更改，但是你可以向其中增加，删除或者改变内容。譬如：
 private final List Loans = new ArrayList();
 list.add(“home loan”);  //valid
 list.add("personal loan"); //valid
 loans = new Vector();  //not valid
 */
public class TestFinal {
    public void change(final Book book) {
        book.setName("huhu");//final对象可以改变其内部属性
        //book = new Book("gg");//error不能改变引用
    }

    public static void main(String[] args) {
        final Book book = new Book("haha");
        TestFinal test = new TestFinal();
        System.out.println(book.getName());
        test.change(book);
        System.out.println(book.getName());
    }
}

class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
