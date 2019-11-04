package com.masonluo.practise.algorithm.oj;

import java.util.Scanner;

/**
 * @author masonluo
 * @date 2019/11/1 8:25 PM
 */
public class Ex11090 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int m = input.nextInt();
        int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = input.nextInt();
        }
        int last[] = new int[n + 1];
        for (int i = 1; i < last.length; i++) {
            last[i] = 0;
        }
        int lastMax = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = i; j <= n; j++) {
                last[j] = Math.max(last[j - 1] + array[j], lastMax + array[j]);
                if(last[j] > lastMax)
                    lastMax = last[j];
            }
        }
        System.out.println(last[n]);
    }
}
