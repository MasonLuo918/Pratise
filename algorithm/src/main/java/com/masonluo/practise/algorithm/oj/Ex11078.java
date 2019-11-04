package com.masonluo.practise.algorithm.oj;

import java.util.Scanner;

/**
 * 做如下两个模型的石子合并，如下模型石子都不能移动出列，且合并都仅发生在相邻两堆石子中：
 * <p>
 * （1）第一个模型：一行排列且相邻合并
 * 有n堆石子A1,A2,...,An形成一行，每堆石头个数记为ai(1<=i<=n)，相邻两堆可合并，合并的分值为新堆的
 * 石子数。求合并为一堆的最低得分和最高得分。
 * <p>
 * （2）第二个模型：一圈排列且相邻合并
 * 有n堆石子A1,A2,...,An形成首位相连的一个环形，An和A1相邻，每堆石头个数记为ai(1<=i<=n)，相邻两堆
 * 可合并，合并的分值为新堆的石子数。求合并为一堆的最低得分和最高得分。
 * <p>
 * 例如4堆石子，每堆石子个数：9 4 4 5
 * 若排成一行，最小分值：(4+4)+(8+5)+(9+13)=43，最大分值：(9+4)+(13+4)+(17+5)=52。
 * 若排成圈状，最小分值：(4+4)+(8+5)+(9+13)=43，最大分值：(9+5)+(14+4)+(18+4)=54。
 * <p>
 * 此题以第一模型的最低得分为例，很多同学想着采用总是从最小的相邻两堆下手的思想，认为最后获得的也就是最
 * 低得分。但这个贪心策略是不对的。如下反例：
 * <p>
 * 石子：9 4 6 1 5
 * <p>
 * 贪心策略：
 * 9 4 6 6      计分6
 * 9 10 6       计分10
 * 9 16         计分16
 * 25           计分25
 * 得分共计：6+10+16+25=57
 * <p>
 * 但9 4 6 1 5 若如下方式合并：
 * 13 6 1 5     计分13
 * 13 6 6       计分6
 * 13 12        计分12
 * 25           计分25
 * 13+6+12+25=56
 * <p>
 * 或
 * 9 4 6 6      计分6
 * 9 4 12       计分12
 * 13 12        计分13
 * 25           计分25
 * 6+12+13+25=56
 * <p>
 * 后两种方式合并出的56都比贪心策略的57来的更低，因为总选择最小的相邻两堆去合并，并不能保证后续每步
 * 都可以最小，也许这轮最小导致后续几轮分值较大。
 * <p>
 * <p>
 * <p>
 * 输入格式
 * 两行。第一行n，第二行a1 a2 … an，每个ai(1<=i<=n)表示第i堆石子的个数，n<=100
 * <p>
 * <p>
 * 输出格式
 * 两行。第一行是第一个模型的最低得分和最高得分，中间空格相连，第二行是第二个模型的最低得分和
 * 最高得分，中间空格相连。
 * <p>
 * <p>
 * 输入样例
 * 4
 * 9 4 4 5
 * <p>
 * <p>
 * 输出样例
 * 43 52
 * 43 54
 *
 * @author masonluo
 * @date 2019/11/2 2:28 PM
 */
public class Ex11078 {
    public static void problem1(int[] p, int n) {
        int[][] maxNum = new int[n + 1][n + 1];
        int[][] minNum = new int[n + 1][n + 1];
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + p[i];
        }
        for (int r = 2; r <= n; r++) {
            for (int i = 1; i <= n - r + 1; i++) {
                int j = i + r - 1;
                maxNum[i][j] = maxNum[i + 1][j] + sum[j] - sum[i - 1];
                minNum[i][j] = minNum[i + 1][j] + sum[j] - sum[i - 1];
                for (int k = i + 1; k < j; k++) {
                    int tmax = maxNum[i][k] + maxNum[k + 1][j] + sum[j] - sum[i - 1];
                    int tmin = minNum[i][k] + minNum[k + 1][j] + sum[j] - sum[i - 1];
                    if (tmax > maxNum[i][j]) {
                        maxNum[i][j] = tmax;
                    }
                    if (tmin < minNum[i][j]) {
                        minNum[i][j] = tmin;
                    }
                }
            }
        }
        System.out.println(minNum[1][n] + " " + maxNum[1][n]);
    }

    public static void problem2(int[] p, int n) {
        int[] p2 = new int[2 * n];
        int numCount = 2 * n - 1;
        for (int i = 1; i <= n; i++) {
            p2[i] = p[i];
        }
        for (int i = n + 1; i < 2 * n; i++) {
            p2[i] = p[i % n];
        }
        int maxNum[][] = new int[numCount + 1][numCount + 1];
        int minNum[][] = new int[numCount + 1][numCount + 1];
        for (int r = 2; r <= n; r++) {
            for (int i = 1; i <= numCount - r + 1; i++) {
                int j = i + r - 1;
                int sum = 0;
                for (int t = i; t <= j; t++) {
                    sum += p2[t];
                }
                maxNum[i][j] = maxNum[i + 1][j] + sum;
                minNum[i][j] = minNum[i + 1][j] + sum;
                for (int k = i + 1; k < j; k++) {
                    int tmax = maxNum[i][k] + maxNum[k + 1][j] + sum;
                    int tmin = minNum[i][k] + minNum[k + 1][j] + sum;
                    if (tmax > maxNum[i][j]) {
                        maxNum[i][j] = tmax;
                    }
                    if (tmin < minNum[i][j]) {
                        minNum[i][j] = tmin;
                    }
                }
            }
        }
        int max = maxNum[1][n];
        int min = maxNum[1][n];
        for (int i = 2; i <= n; i++) {
            int j = i + n - 1;
            if (max < maxNum[i][j]) {
                max = maxNum[i][j];
            }
            if (min > minNum[i][j]) {
                min = minNum[i][j];
            }
        }
        System.out.println(min + " " + max);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int p[] = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = input.nextInt();
        }
        problem1(p, n);
        problem2(p, n);
    }
}
