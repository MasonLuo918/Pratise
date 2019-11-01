package com.masonluo.practise.algorithm.oj;

import java.util.Scanner;

/**
 * @author masonluo
 * @date 2019/11/1 5:49 PM
 * <p>
 * <p>
 * <p>
 * Description
 * һ����,��,�߷ֱ���m,n,p�ĳ����屻�ָ��m*n*p��С�����塣ÿ��С�������ں�һ��������
 * �������һ���㷨���������������������ӳ����塣�ӳ�����Ĵ�С�����ڲ�������������֮��ȷ����
 * Լ�������ó���������Ԫ�ؾ�Ϊ����ʱ���������ӳ�����Ϊ0��
 * <p>
 * �����ʽ
 * ��һ��3��������m,n,p������ 1<=m,n,p<=50
 * ��������m*n����ÿ��p����������ʾС�������е�����
 * <p>
 * �����ʽ
 * ��һ���е����Ǽ����������ӳ�����Ĵ�С��
 * <p>
 * ��������
 * 3 3 3
 * 0 -1 2
 * 1 2 2
 * 1 1 -2
 * -2 -1 -1
 * -3 3 -2
 * -2 -3 1
 * -2 3 3
 * 0 1 3
 * 2 1 -3
 * <p>
 * �������
 * 14
 */
public class Ex8601 {
    public static int maxSum1(int[] array, int length) {
        if (length == 0 || array.length != length)
            return 0;
        int max = 0, b = 0;
        for (int anArray : array) {
            b = Math.max(anArray, b + anArray);
            if (b > max)
                max = b;
        }
        return max;
    }

    public static int maxSum2(int[][] array, int length, int width) {
        if (length == 0 || width == 0) {
            return 0;
        }
        int last[] = new int[width];
        int max = 0;
        for (int i = 0; i < array.length; i++) {
            setZero(last);
            for (int j = i; j < array.length; j++) {
                for (int k = 0; k < array[i].length; k++) {
                    last[k] += array[j][k];
                }
                int temp = maxSum1(last, last.length);
                if (temp > max)
                    max = temp;
            }
        }
        return max;
    }

    public static int maxSum3(int[][][] array, int length, int width, int height) {
        if (length == 0 || width == 0 || height == 0) {
            return 0;
        }
        int last[][] = new int[length][width];
        int max = 0;
        for (int i = 0; i < height; i++) {
            setZero(last);
            for (int j = i; j < height; j++) {
                for (int m = 0; m < length; m++) {
                    for (int n = 0; n < width; n++) {
                        last[m][n] += array[m][n][j];
                    }
                }
                int temp = maxSum2(last, length, width);
                if (temp > max) {
                    max = temp;
                }
            }
        }
        return max;
    }

    private static void setZero(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = 0;
        }
    }

    private static void setZero(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            setZero(array[i]);
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int m, n, p;
        m = input.nextInt();
        n = input.nextInt();
        p = input.nextInt();
        int[][][] array = new int[m][n][p];
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                for(int t = 0; t < p; t++){
                    array[i][j][t] = input.nextInt();
                }
            }
        }
        System.out.println(maxSum3(array, m, n, p));
    }
}
