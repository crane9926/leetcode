package string;

/**
 * [5]最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 *
 * 输入：s = "cbbd"
 * 输出："bb"
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 * Related Topics
 * 字符串
 * 动态规划
 */
public class LongestPalindromicSubstring {
    /**
     * 思路一
     * 暴力解法
     * 双层循环：判断每个子串是否是回文子串，并且更新最大长度
     */
    public static String solution(String s){
        int length=s.length();
        //记录最长子串的长度
        int max=0;
        //记录最长子串
        String subPalindromic="";
        if(length<2){
            subPalindromic=s;
        }
        //循环遍历所有子串
        for(int i=0;i<length;i++){
            for (int j=i+1;j<=length;j++){
                String str=s.substring(i,j);
                if(isPalindromic(str)&&max<str.length()){
                    max=str.length();
                    subPalindromic=s.substring(i,j);
                }
            }
        }
        return subPalindromic;
    }

    /**
     * 判断字符串是否是回文串
     * @param s
     * @return
     */
    public static boolean isPalindromic(String s){
        int length=s.length();
        //遍历；判断每个字符是否与它的镜像字符相等
        for(int i=0;i<length/2;i++){
            if(s.charAt(i)!=s.charAt(length-i-1)){
                return false;
            }
        }
        return true;
    }

    /**
     * 思路二
     * 动态规划:一个串为回文，则除去头和尾还是回文.(回文的头和尾分别上一个相同的字符，依然是回文)
     * p(i,j)=p(i+1,j-1)&&s[i]==s[j]
     * @param s
     * @return
     */
    public static String solution2(String s){
        int max=0;
        String maxPalindromic="";
        int length=s.length();
        //二维数组记录所有子串是否是回文
        boolean[][] dp=new boolean[length][length];
        //长度为1的串一定是回文
        for(int k=0;k<length;k++){
            dp[k][k]=true;
        }
        //遍历所有子串的长度（为啥遍历长度：因为大的串是否是回文 需要依赖短的子串。先求接短的串，再根据短的串求解长的串）
        for(int len=1;len<=length;len++){
            //左指针，从左往右判断长度为len的串是否是回文
            for(int start=0;start<length;start++){
                //根据长度和左指针计算出右指针end-start+1=len
                int end=start+len-1;
                if(end>=length){//右指针越界(当end==lenght时s.charAt(end)就越界了)
                    break;
                }
                //长度为2 的单独判断下
                dp[start][end]=(len==1||len==2||dp[start+1][end-1])&&s.charAt(start)==s.charAt(end);
                //如果是回文则更新，最长回文变量
                if(dp[start][end]&&max<len){
                    max=len;
                    maxPalindromic=s.substring(start,end+1);
                }
            }
        }
        return maxPalindromic;
    }

    /**
     * 思路三
     * 中心扩展算法：
     * 遍历字符，分别以每个字符为中心，像两边扩展判断是否是回文。是回文继续向两边扩展，不再是回文则记录最长子串和长度
     * @param s
     * @return
     */
    public static String solution3(String s){
        int length=s.length();
        //记录最长回文子串的起始位置
        int start=0;
        int end=0;
        //记录最长回文子串
        String maxSubPalind="";
        //遍历字符，分别以字符为中心(分为奇数和偶数情况)进行扩展判断。
        for(int i=0;i<length;i++){
            //奇数情况以该字符为中心点
            int len1=expandByCenter(s,i,i);
            //偶数情况以两个字符中间为中心点
            int len2=expandByCenter(s,i,i+1);
            int len=Math.max(len1,len2);
            if(len>(end-start)){
                start=i-(len-1)/2;
                end=i+len/2;
            }
        }
        maxSubPalind=s.substring(start,end+1);
        return maxSubPalind;
    }

    /**
     * 根据中心向两边扩展，获取最长回文子串长度
     * @return
     */
    public static int expandByCenter(String s,int left,int right){
        while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        return right-left-1;
    }

    public static void main(String[] args) {
        String str="bb";
        solution(str);
        solution2(str);
        solution3(str);
    }

}
