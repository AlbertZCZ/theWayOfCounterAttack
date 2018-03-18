package interview;

import counter.attack.thread.Consumer;
import counter.attack.thread.Producter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Create by zhang on 2018/3/18
 * 生产者消费者模式测试用例
 * 1. 你可以使用wait和notify函数来实现线程间通信。你可以用它们来实现多线程（>3）之间的通信。
 *
 *2. 永远在synchronized的函数或对象里使用wait、notify和notifyAll，不然Java虚拟机会生成 IllegalMonitorStateException。
 *
 *3. 永远在while循环里而不是if语句下使用wait。这样，循环会在线程睡眠前后都检查wait的条件，并在条件实际上并未改变的情况下处理唤醒通知。
 *
 *4. 永远在多线程间共享的对象（在生产者消费者模型里即缓冲区队列）上使用wait。
 */
public class ProducerConsumerInJava {
    public static void main(String[] args) {
        System.out.println("解决生产者消费者问题");
        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 10;
        Thread producter = new Producter(buffer,maxSize,"生产者");
        Thread consumer = new Consumer(buffer,maxSize,"消费者");
        producter.start();
        consumer.start();
    }
}
