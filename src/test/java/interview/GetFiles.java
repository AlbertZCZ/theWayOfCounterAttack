package interview;

import java.io.File;

/**
 * Create by zhang on 2018/3/15
 * 用递归方法遍历一个目录下的所有文件
 */
public class GetFiles {
    public void getFiles(String path) {
        File file = new File(path);
        String[] files = file.list();
        for (String name:files) {
            System.out.println(name);
            if (new File(path + "\\" + name).isDirectory())
                getFiles(path + "\\" + name);
        }
    }
    public static void main(String[] args) {
        GetFiles getFiles = new GetFiles();
        getFiles.getFiles("C:\\idea_space\\theWayOfCounterAttack\\test");
    }
}
