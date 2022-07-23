package list;

/**
 * [2]两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * <p>
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * <p>
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例 1：
 * <p>
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 * <p>
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 * <p>
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 * 提示：
 * <p>
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 * Related Topics
 * 递归
 * 链表
 * 数学
 */
public class AddTwoNumbers {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int value) {
            this.val = value;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


    /**
     * 计算两个链表对应节点之和
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoList(ListNode l1, ListNode l2) {
        //结果链表的表头和表尾
        ListNode head = null;
        ListNode tail = null;
        //进位
        int carry = 0;
        //只要两个节点不同时为空，则把节点值相加(为空节点的值为0)，再加上 上一步的进位值
        while (l1 != null || l2 != null) {
            int v1 = l1 != null ? l1.val : 0;
            int v2 = l2 != null ? l2.val : 0;
            //求和
            int sum = v1 + v2 + carry;
            if (head == null) {//如果是第一次求和
                head = tail = new ListNode(sum % 10);
            } else {
                tail.next = new ListNode(sum % 10);
                tail = tail.next;
            }
            //计算进位
            carry = sum / 10;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        //如果计算最后一个节点时，还有进位，则需要再生成一个节点
        if (carry > 0) {
            tail.next = new ListNode(carry);
            tail = tail.next;
        }
        return head;
    }

    public static void main(String[] args) {
        //构造初始值
        ListNode l1 = null;
        ListNode l2 = null;
        ListNode tail1 = null;
        ListNode tail2 = null;
        int[] arr1 = {2, 4, 3};
        int[] arr2 = {5, 6, 4};
        for (int v : arr1) {
            if (l1 == null) {
                l1 = tail1 = new ListNode(v);
            } else {
                tail1.next = new ListNode(v);
                tail1 = tail1.next;
            }
        }

        for (int v2 : arr2) {
            if (l2 == null) {
                l2 = tail2 = new ListNode(v2);
            } else {
                tail2.next = new ListNode(v2);
                tail2 = tail2.next;
            }
        }
       //计算两个链表之和
        addTwoList(l1,l2);
    }
}
