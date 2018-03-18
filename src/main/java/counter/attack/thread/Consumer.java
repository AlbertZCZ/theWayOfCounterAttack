package counter.attack.thread;

import java.util.Queue;

/**
 * Create by zhang on 2018/3/18
 * 消费者
 * 当缓冲队列为空时使用wait()暂停消费
 * 当缓冲队列不为空时notify()通知
 */
public class Consumer extends Thread{
    private Queue<Integer> queue;
    private int maxSize;
    public Consumer(Queue<Integer> queue,int maxSize,String name) {
        super(name);
        this.queue = queue;
        this.maxSize = maxSize;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (queue) {

                while (queue.isEmpty()) {
                    System.out.println("缓冲队列为空，消费者停止消费，等待生产者生产");
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("开始消费：" + queue.remove());
                queue.notifyAll();
            }
        }
    }
}
