package string;

import java.util.HashMap;
import java.util.Map;

/**
 * [3]无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是
 * "abc"，所以其
 * 长度为 3。
 * 示例 2:
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是
 * "b"
 * ，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是
 * "wke"
 * ，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，
 * "pwke"
 * 是一个子序列，不是子串。
 * 提示：
 * <p>
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 * Related Topics
 * 哈希表
 * 字符串
 * 滑动窗口
 */
public class LongestSubstring {
    /**
     * 暴力解法
     *双循环，借助map,数组：时间和空间复杂度高
     * @param s
     * @return
     */
    public static int solution(String s) {
        //边界处理
        if(s==null||"".equals(s)){
            return 0;
        }
        String[] arr = s.split("");
        if(arr.length<2){
            return arr.length;
        }
        //记录查找过程中最长子串的长度
        int maxSubLength=0;
        //key为字符，value为字符下标
        Map<String,Integer> map=new HashMap<>();
        for (int i=0;i<arr.length-1;i++){
            map.put(arr[i],i);
            //游标，记录一次遍历中字符长度
            int num=1;
            for (int j=i+1;j<arr.length;j++){
                //如果map中已经存在该字符，说明存在重复
                if(map.containsKey(arr[j])){
                    //清空map，切换子串的起点，重新遍历
                    map.clear();
                    break;
                }else {
                    map.put(arr[j],j);
                    num++;
                }
            }
            //内部循环结束，更新最长子串的长度
            maxSubLength=Math.max(maxSubLength,num);
        }
        return maxSubLength;
    }

    /**
     * 遍历一次，map、双指针滑动窗口
     * @param s
     * @return
     */
    public static int solution2(String s){
        //边界处理
        if(s.length()==0){
            return 0;
        }
        //双指针：左右指针
        int left=0;
        int right=0;
        //记录最长子串的长度
        int maxSubLength=0;
        //hashmap，辅助重复字符的判断。key为字符，value为字符下标
        Map<Character,Integer> map=new HashMap<>();
        while (right<s.length()){
            //右侧指针遇到重复字符时，更新左指针位置(两个指针中间为非重复字符，发生重复时，一定是右指针新指向的元素与左指元素重复)
            if(map.containsKey(s.charAt(right))){
                //left指向map中重复字符的下一个位置
                //（对于abba情况，当右指针指向a时，left会回退到第一个a的位置，所以需要取
                // Math.max(left,map.get(s.charAt(right)) + 1)，而不是map.get(s.charAt(right)）
                left=Math.max(left,map.get(s.charAt(right))+1);
            }
            map.put(s.charAt(right),right);
            //更新最长子串的长度
            maxSubLength=Math.max(maxSubLength,right-left+1);
            right++;
        }
        return maxSubLength;
    }

    public static void main(String[] args) {
        String s = "abba";
        System.out.println(s.length());
        solution(s);
        solution2(s);
    }

}
