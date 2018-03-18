package counter.attack.thread;

import java.util.Queue;
import java.util.Random;

/**
 * Create by zhang on 2018/3/18
 * 生产者一直生产，当缓冲队列满时停止生产(wait)，当队列不满时使用notify()
 * 通知生产者
 */
public class Producter extends Thread{
    private Queue<Integer> queue;
    private int maxSize;
    public Producter(Queue<Integer> queue,int maxSize,String name) {
        super(name);
        this.queue = queue;
        this.maxSize = maxSize;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (queue) {
                while (queue.size() == maxSize) {
                    System.out.println("队列已满，等待消费者消费");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                Random random = new Random();
                int i = random.nextInt();
                System.out.println("生产者生产："+i);
                queue.add(i);
                queue.notifyAll();
            }
        }
    }
}
