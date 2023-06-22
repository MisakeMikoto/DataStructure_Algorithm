/**
 * @Author MisakiMikoto
 * @Date 2023/6/22
 */
public class Three_2023_6_22 {
    public static void preOrderRecur(Node head){
        if(head == null){
            return;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }
    public static void inOrderRecur(Node head){
        if(head == null){
            return;
        }
        inOrderRecur(head.left);
        System.out.print(head.left + " ");
        inOrderRecur(head.right);
    }

    public static void postOrderRecur(Node head){
        if(head == null){
            return;
        }
        preOrderRecur(head.left);
        preOrderRecur(head.right);
        System.out.print(head.value + " ");
    }
}
class Node<V>{
    V value;
    Node left;
    Node right;
}