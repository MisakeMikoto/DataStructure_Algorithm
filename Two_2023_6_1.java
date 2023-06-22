import org.junit.Test;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * @Author MisakiMikoto
 * @Date 2023/6/1
 */

public class Two_2023_6_1 {
    public static void main(String[] args) {


    }

    @Test
    public void testMergeLists() {
        ListNode[] listNodes = generateLists(new int[][]{{-2,-1,-1,-1}, {}});
//        printList(listNodes[1]);
//        System.out.println();
//        System.out.println("#########################");
        ListNode head = mergeKLists(listNodes);
        ListNode loop = isLoop(head);
        if(loop != null){
            System.out.println(loop.val);
        }else {
            printList(head);
        }

    }

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new ListNodeComparator());
        ListNode head;
        for (int i = 0; i < lists.length; i++) {
            head = lists[i];
            while (head != null){
                queue.add(head);
                head = head.next;
            }
        }
        head = queue.poll();
        ListNode tmp = head;
        while (!queue.isEmpty()){
            tmp.next = queue.poll();
            tmp = tmp.next;
        }

        return head;
    }

    /**
     *
     * @param lists 一个二维整形数组，根据二维整形数组创建链表数组
     * @return
     */
    public static ListNode[] generateLists(int[][] lists){
        ListNode[] list = new ListNode[lists.length];
        for (int i = 0; i < lists.length; i++) {
            ListNode head = null;
            if(lists[i].length > 0){
                head = new ListNode(lists[i][0], null);;
            }
            ListNode tmp = head;
            for (int j = 1; j < lists[i].length; j++) {
                head.next = new ListNode(lists[i][j], null);
                head = head.next;
            }
            list[i] = tmp;
        }
        return list;
    }

    @Test
    public void testList(){
        ListNode[] listNodes = generateLists(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}});
        for (int i = 0; i < listNodes.length; i++) {
            ListNode head = listNodes[i];
            while (head != null){
                System.out.print(head.val + " ");
                head = head.next;
            }
            System.out.println();
        }
    }

    public static void printList(ListNode head){
        while (head != null){
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println();
    }

    // 检查链表是否有环，有环则返回第一个环节点，无环则返回null

    /**
     * 检查链表是否有环
     * @param head 链表头节点
     * @return 有环则返回第一个环节点，无环则返回null
     */
    public static ListNode isLoop(ListNode head){
        ListNode fast = head, slow = head;
        if(head == null || head.next == null){
            return null;
        }
        fast = fast.next.next;
        slow = slow.next;
        while (fast != slow){
            if(fast == null || fast.next == null){
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        }
        fast = head;
        while (fast != slow ){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    @Test
    public void testIsLoop() {
        ListNode node1 = new ListNode(1, null);
        ListNode node2 = new ListNode(2, null);
        ListNode node3 = new ListNode(3, null);
        ListNode node4 = new ListNode(4, null);
        ListNode node5 = new ListNode(5, null);
        ListNode node6 = new ListNode(6, null);
        ListNode node7 = new ListNode(7, null);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
//        node7.next = null;
        System.out.println(isLoop(node1).val);
    }

    class ListNodeComparator implements Comparator<ListNode> {

        @Override
        public int compare(ListNode t1, ListNode t2) {
            return t1.val - t2.val;
        }
    }


    /**
     * LeetCode 24 两两交换链表中的节点
     * @param head
     * @return
     */
    public ListNode swapPairs(ListNode head) {
        ListNode tmp = head;
        if(head == null || head.next == null){
            return head;
        }
        int index = 1;
        HashMap<Integer, ListNode> map = new HashMap<>();
        while (head != null){
            map.put(index++, head);
            head = head.next;
        }
        int size = map.size();
        ListNode left = map.get(1);
        ListNode right = map.get(2);
        head = right;
        right.next = left;
        if(size % 2 == 0){
            for (int i = 3; i <= map.size() ; i = i + 2) {
                left.next = map.get(i + 1);
                left = map.get(i);
                right = map.get(i + 1);
                right.next = left;
            }
            left.next = null;
        }else {
            for (int i = 3; i <= map.size() - 1 ; i = i + 2) {
                left.next = map.get(i + 1);
                left = map.get(i);
                right = map.get(i + 1);
                right.next = left;
            }
            ListNode lastNode = map.get(size);
            left.next = lastNode;
            lastNode.next = null;
        }
        return head;
    }

    @Test
    public void testSwapParis() {
        int[][] arr = new int[][]{{1}};
        ListNode[] lists = generateLists(arr);
        printList(lists[0]);
        System.out.println("+++++++++++++++");
        ListNode head = swapPairs(lists[0]);
        printList(head);
    }

    /**
     * 25. K 个一组翻转链表
     * @param head
     * @param k
     * @return
     */
    public ListNode reverseKGroup(ListNode head, int k) {
        int size = 0;
        ListNode tmp = head;
        while (tmp != null){
            size++;
            tmp = tmp.next;
        }
        ListNode[] lists = reverseKGroupOfReverse(head, k);
        head = lists[0];
        tmp = lists[1];
        ListNode next = lists[2];
        for (int i = k; i < size; i = i + k) {
            lists = reverseKGroupOfReverse(next, k);
            tmp.next = lists[0];
            tmp = lists[1];
            next = lists[2];
        }
        return head;
    }

    /**
     * 反转 以head为头节点后k个节点
     * @param head
     * @param k
     * @return 返回反转后的链表的尾节点
     */
    public ListNode[] reverseKGroupOfReverse(ListNode head, int k){
        if(head == null){
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        ListNode next = null;
        int count = 0;
        while (count < k && cur != null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            count++;
        }

        if(count < k){
            cur = pre;
            pre = null;
            next = null;
            while (count > 0 && cur != null){
                next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
                count--;
            }
        }

        return new ListNode[]{pre, head, next};
    }

    @Test
    public void testReverseKGroup() {
        int[][] arr = new int[][]{{1,2,3,4,5}};
        ListNode[] lists = generateLists(arr);
        printList(lists[0]);
        ListNode head = reverseKGroup(lists[0], 1);
        System.out.println("*********************");
        printList(head);
    }

    public ListNode rotateRight(ListNode head, int k) {
        ListNode tmp = head;
        int count = 0;
        ListNode last = null;
        while(tmp != null){
            if(tmp.next == null){
                last = tmp;
            }
            tmp = tmp.next;
            count++;
        }
        if(k % count == 0){
            return head;
        }
        k = k % count;

        int rk = (count - k);
        tmp = head;
        int i = 0;
        while (i < rk - 1){
            tmp = tmp.next;
            i++;
        }
        ListNode x = tmp.next;
        tmp.next = null;
        last.next = head;
        return x;
    }

    @Test
    public void testRotateRight() {
        int[][] arr = new int[][]{{1,2,3,4,5}};
        ListNode[] lists = generateLists(arr);
        printList(lists[0]);
        ListNode head = rotateRight(lists[0], 6);
        printList(head);
    }
}


 class ListNode {
     int val;
     ListNode next;
     ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }
