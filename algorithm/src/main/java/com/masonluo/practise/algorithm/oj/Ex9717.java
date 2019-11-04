package com.masonluo.practise.algorithm.oj;

import java.util.Scanner;

/**
 * 取数对弈游戏问题：
 * <p>
 * 取数游戏是一个 2 人对策游戏。游戏开始时将 n 个数在棋盘上从左到右排成一行。
 * <p>
 * 甲乙双方轮流在这一行数的左右两端取数，直至全部取完 n 个数。每人所取得的数的总和
 * 为其得分值。
 * <p>
 * 最后双方得分多者获胜。（游戏规定由甲方先取数。）
 * <p>
 * 这里，甲乙双方都采用如下最优策略:
 * 1)甲每次取都希望取到的这个数使自己得分最高
 * 2)乙每次取都希望取到的这个数令甲的得分最低
 * <p>
 * 其实，由于两方无论怎么取，双方总和是固定的，甲每次都挑能使自己得分最高的数来取，乙要使得甲得分最低，
 * 其实也就是使自己得分最高。所以甲乙双方都是按照相同的动机来取数的，那就是都是为了使自己得分最高。
 * <p>
 * 请编程实现：在甲乙双方都采用最优策略的前提下，计算甲方先取数时双方的最后得分。
 * <p>
 * <p>
 * <p>
 * 输入格式
 * 对于每组输入数据，输入数据的第 1 行有 1 个正整数 n (1<=n<=100)，表示有 n 个
 * 数在棋盘上从左到右排成一行。
 * 接下来的 n 个数表示在棋盘上依次排列的 n 个数。
 * <p>
 * <p>
 * 输出格式
 * 在甲乙双方都采用最优策略的前提下，输出计算出的双方的最后得分。甲方得分在前，乙方得分在后。
 * 如输入6个数:
 * 4 7 2 9 5 2
 * 甲取2 7 9，可得18分，乙取4 5 2得11分。这也是甲所能获得的最高分。
 * <p>
 * <p>
 * 输入样例
 * 6
 * 4 7 2 9 5 2
 * <p>
 * <p>
 * 输出样例
 * 18 11
 *
 * @author masonluo
 * @date 2019/11/4 11:37 PM
 */
public class Ex9717 {
    public static void solution(int[] p, int n){
        int[][] m = new int[n + 1][n + 1];
        int[] sum = new int[n + 1];
        for(int i = 1; i <= n; i++){
            sum[i] += (sum[i -  1] + p[i]);
            m[i][i] = p[i];
        }
        for(int r = 2; r <= n; r++){
            for(int i = 1; i <= n - r + 1; i++){
                int j = r + i - 1;
                m[i][j] = (sum[j] - sum[i - 1]) - Math.min(m[i + 1][j], m[i][j - 1]);
            }
        }
        System.out.println(m[1][n] + " " + (sum[n] - m[1][n]));
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] p = new int[n + 1];
        for(int i = 1; i <= n; i++){
            p[i] = input.nextInt();
        }
        solution(p, n);
    }
}
