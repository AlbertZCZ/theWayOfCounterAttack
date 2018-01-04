package counter.attack.thread;

import java.util.Arrays;

/**
 * Create by zhang on 2018/1/4
 * think in java 练习2：遵循generic/Fibonacci.java 的形式，
 * 创建一个任务，它可以产生由n个斐波那契数字组成的序列，其中n是通过任务的构造器而提供的。
 * 使用线程创建大量的这种任务并驱动它们。
 */
class Fibonacci1 implements Runnable{
    private final int n;
    //构造器传入n
    public Fibonacci1(int n) {
        this.n = n;
    }
    //斐波那契数列
    private int getFibo(int n) {
        if (n == 1 || n == 2)
            return 1;
        else
            return getFibo(n - 1) + getFibo(n - 2);
    }
    //生成斐波那契数列
    private int[] getFiboArray() {
        int[] fibo = new int[n];
        for (int i = 0;i < fibo.length;i++) {
            fibo[i] = getFibo(i + 1);
        }
        return fibo;
    }
    @Override
    public void run() {
        //打印斐波那契数列
        System.out.format("Fibo of %d : %s \n",n, Arrays.toString(getFiboArray()));
    }
}

public class Fibonacci {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new Fibonacci1(i + 1)).start();
        }
    }
}
