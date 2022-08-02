package array;

import java.util.*;

/**
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 *
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 示例 2：
 *
 * 输入：nums = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：[]
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 * Related Topics
 * 数组
 * 双指针
 * 排序
 */
public class ThreeSum {
    //排序,双指针
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result=new ArrayList<>();
        int length=nums.length;
        if(length<3){
            return result;
        }
        //排序
        Arrays.sort(nums);
        //遍历所有元素，寻找该元素之后是否存在两个数之和 与当前的数互斥
        for (int i = 0; i < length; i++) {
            //去重
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            //设置目标值
            int target=-nums[i];
            //左指针
            for (int left = i+1; left <length ; left++) {
                //去重
                if(left>i+1&&nums[left]==nums[left-1]){
                    continue;
                }
                //右指针
                int right=length-1;
                //双指针夹逼判断两指针之和是否等于target
                while (left<right&&(nums[left]+nums[right])>target){
                    right--;
                }

                if(left<right&&(nums[left]+nums[right])==target){
                    List<Integer>list=new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                }
            }
        }
        return result;
    }

    //排序,双指针,两边夹逼
    public static List<List<Integer>> solution(int[] nums){
        List<List<Integer>> result=new ArrayList<>();
        int length=nums.length;
        if(length<3){return result;}
        //排序
        Arrays.sort(nums);
        for (int i = 0; i < length; i++) {
            //去重
            if(i>0&&nums[i]==nums[i-1]){continue;}
            int target=-nums[i];
            //左指针
            int left =i+1;
            //右指针
            int right=length-1;
            //双指针夹逼
            while (left<right){
                //去重
                if(left>(i+1)&&nums[left]==nums[left-1]){
                    left++;
                    continue;
                }
                if((nums[left]+nums[right])>target){
                    right--;
                }else if((nums[left]+nums[right])<target){
                    left++;
                }else {
                    List<Integer>list=new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    left++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums ={0,0,0,0};
        threeSum(nums);
        solution(nums);
    }

}
