package counter.attack.thread;

/**
 * Create by zhang on 2018/1/5
 * 一个关注于运算，所以不能读取控制台输入，另一个把运算放在任务里单独运行,
 * 此时就可以在进行运算的同时监听控制台输入
 */
public class ResponsiveUI extends Thread{

    private volatile static double d = 1;
    public ResponsiveUI() {
        setDaemon(true);//设置为后台线程
        start();
    }

    @Override
    public void run() {
        while (true) {
            d = d + (Math.PI + Math.E) / d;
        }
    }

    /**
     * 要想让程序有响应，就得把计算程序放在run方法中，这样它就能让出处理器给别的程序。
     * 当你按下‘回车’健的时候，可以看到计算确实在作为后台程序运行，同时还在等待用户输入。
     */
    public static void main(String[] args) throws Exception {
        new ResponsiveUI();
        //TimeUnit.SECONDS.sleep(2);//睡2秒
        System.in.read();
        System.out.println(d);
    }
}
