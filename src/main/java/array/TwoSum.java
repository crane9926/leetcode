package array;

import java.util.HashMap;
import java.util.Map;

/**
 * 【1】两数之和
 *
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 *
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 * 示例 2：
 *
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 * 示例 3：
 *
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 * 提示：
 *
 * 2 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 * 进阶：你可以想出一个时间复杂度小于 O(n2) 的算法吗？
 *
 * Related Topics
 * 数组
 * 哈希表
 */
public class TwoSum {
    //1.粗暴思路：双循环遍历查找

    //2.借助map:key为数组中的元素，vaue为数组中元素的下标。
    // 先遍历一遍数组，把值和下表放入map。
    //第二次遍历数组，查找map中是否存在该值的目标值，找到则返回

    /**
     * 思路三：只遍历一次数组
     * 借助Map：key为数组中的元素，vaue为数组中元素的下标。
     * 遍历数组，判断当前值的目标值是否在map中。不在则放入map继续遍历，存在则返回当前值和目标值的下标，跳出循环。
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result=new int[2];
        //key为数组中的元素，vaue为数组中元素的下表
        Map<Integer,Integer> numsMap=new HashMap();
        for (int i=0;i<nums.length;i++) {
            //i对应的目标值
            int diff=target-nums[i];
            if(numsMap.containsKey(diff)){
                result[1]=i;
                result[0]=numsMap.get(diff);
                break;
            }
            numsMap.put(nums[i],i);
        }
        return result;

    }

    public static void main(String[] args) {
        int[]nums ={2,7,11,15};
        int target = 9;
        twoSum(nums,target);
    }
}
