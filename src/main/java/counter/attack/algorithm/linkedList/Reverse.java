package counter.attack.algorithm.linkedList;

/**
 * Create by zhang on 2018/3/17
 * 单项链表反向
 */
public class Reverse {
    public Node reverse(Node head) {
        if (head == null || head.next == null)
            return head;
        //初始条件
        Node prev = null;//prev指针指向head的上一个
        Node next;//next指针
        while (head != null) {
            //循环体
            next = head.next;//next指针指向head.next
            head.next = prev;//将head的下一个元素指向上一个
            prev = head;//向后移动prev指针
            head = next;//向后移动head指针
        }
        return prev;
    }

    public static void main(String[] args) {
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;
        System.out.println(n1.value+","+n1.next.value+","+n1.next.next.value);
        Reverse reverse = new Reverse();
        Node newNode = reverse.reverse(n1);
        System.out.println(newNode.value+","+newNode.next.value+","+newNode.next.next.value);
    }
}
