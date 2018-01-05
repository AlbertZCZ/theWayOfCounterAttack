package counter.attack.thread;

/**
 * Create by zhang on 2018/1/5
 * 加入一个线程
 * 一个线程可以在其他线程之上调用join（）方法，其效果是等待一段时间直到第二个线程结束才继续执行。
 * 如果某个线程在另一个线程T上调用t.join() 此线程将被挂起，直到目标线程t结束才恢复（即 t.isAlive()返回为假）
 */
public class Joining {
    public static void main(String[] args) {
        //线程1
        Sleeper sleepy=new Sleeper("Sleepy",1500),
                grumpy=new Sleeper("Grumpy",1500);
        //
        Joiner Dopey=new Joiner("Dopey",sleepy),
                doc=new Joiner("doc",grumpy);
        grumpy.interrupt();
    }
}

class Sleeper extends Thread {
    private int duration;
    public Sleeper(String name,int sleepTime) {
        super(name);
        duration=sleepTime;
        start();
    }
    /**
     * Sleeper 它要休眠一段时间
     */
    @Override
    public void run() {
        try {
            sleep(duration);
        } catch (Exception e) {
            //根据 isInterrupted的返回值报告这个中断，当另一个线程在该线程上调用interrupt()时,
            //将给该线程设定一个标志，表明该线程已经中断，然而，异常被捕获时将清理这个标志，所以在catch子句中，
            //在异常被捕获的时候这个标志总是为假。除异常之外，这个标志还可有于其他情况，比如线程可能会检查其中断状态
            System.out.println(getName()+" was interrupted."+"isInterrupted() "+ isInterrupted());
            return;
        }
        System.out.println(getName()+"  has awakened");
    }

}
//////////////////////////////////
class Joiner extends Thread {
    private Sleeper sleeper;
    public Joiner(String name,Sleeper sleeper) {
        super(name);
        this.sleeper=sleeper;
        start();
    }

    @Override
    public void run() {
        /**
         * Joiner 线程将通过在sleeper对象上调用join方法来等待sleeper醒来.在main里面
         * 每个sleeper都有一个joiner,这可以在输出中发现，如果sleeper被 中断或者是正常结束，
         * joiner将和sleeper一同结束
         *
         */
        try {
            sleeper.join();
        } catch (InterruptedException e) {
            System.out.println("interrupted");
        }
        System.out.println(getName()+" join completed");
    }

}
