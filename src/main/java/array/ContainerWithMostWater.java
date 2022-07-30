package array;

/**
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 *
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 * 示例 1：
 *
 *
 *
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例 2：
 *
 * 输入：height = [1,1]
 * 输出：1
 * 提示：
 *
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 * Related Topics
 * 贪心
 * 数组
 * 双指针
 */
public class ContainerWithMostWater {
    //双指针，两边夹逼，低的一侧往中间移动一位
    public static int maxArea(int[] height) {
        //边界处理
        if(height.length<2){
            return 0;
        }
        //最大水量时的X轴的头和尾
        int start=0;
        int end=height.length-1;
        //最大水量
        int max=0;
        while (start<end){
            int xSize=end-start;
            int ySize=height[start]>height[end]? height[end]:height[start];
            int areaValue=xSize*ySize;
            //更新最大水量和首位标记
            if(max<areaValue){
                max=areaValue;
            }
            //比较首位柱子的高低，低的一侧往中间移动一位
            if (height[start]>height[end]){
                end--;
            }else{
                start++;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] height={1,8,6,2,5,4,8,3,7};
        maxArea(height);
    }
}
