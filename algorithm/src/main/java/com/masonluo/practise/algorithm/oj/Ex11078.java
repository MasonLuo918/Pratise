package com.masonluo.practise.algorithm.oj;

import java.util.Scanner;

/**
 * ����������ģ�͵�ʯ�Ӻϲ�������ģ��ʯ�Ӷ������ƶ����У��Һϲ�������������������ʯ���У�
 * <p>
 * ��1����һ��ģ�ͣ�һ�����������ںϲ�
 * ��n��ʯ��A1,A2,...,An�γ�һ�У�ÿ��ʯͷ������Ϊai(1<=i<=n)���������ѿɺϲ����ϲ��ķ�ֵΪ�¶ѵ�
 * ʯ��������ϲ�Ϊһ�ѵ���͵÷ֺ���ߵ÷֡�
 * <p>
 * ��2���ڶ���ģ�ͣ�һȦ���������ںϲ�
 * ��n��ʯ��A1,A2,...,An�γ���λ������һ�����Σ�An��A1���ڣ�ÿ��ʯͷ������Ϊai(1<=i<=n)����������
 * �ɺϲ����ϲ��ķ�ֵΪ�¶ѵ�ʯ��������ϲ�Ϊһ�ѵ���͵÷ֺ���ߵ÷֡�
 * <p>
 * ����4��ʯ�ӣ�ÿ��ʯ�Ӹ�����9 4 4 5
 * ���ų�һ�У���С��ֵ��(4+4)+(8+5)+(9+13)=43������ֵ��(9+4)+(13+4)+(17+5)=52��
 * ���ų�Ȧ״����С��ֵ��(4+4)+(8+5)+(9+13)=43������ֵ��(9+5)+(14+4)+(18+4)=54��
 * <p>
 * �����Ե�һģ�͵���͵÷�Ϊ�����ܶ�ͬѧ���Ų������Ǵ���С�������������ֵ�˼�룬��Ϊ����õ�Ҳ������
 * �͵÷֡������̰�Ĳ����ǲ��Եġ����·�����
 * <p>
 * ʯ�ӣ�9 4 6 1 5
 * <p>
 * ̰�Ĳ��ԣ�
 * 9 4 6 6      �Ʒ�6
 * 9 10 6       �Ʒ�10
 * 9 16         �Ʒ�16
 * 25           �Ʒ�25
 * �÷ֹ��ƣ�6+10+16+25=57
 * <p>
 * ��9 4 6 1 5 �����·�ʽ�ϲ���
 * 13 6 1 5     �Ʒ�13
 * 13 6 6       �Ʒ�6
 * 13 12        �Ʒ�12
 * 25           �Ʒ�25
 * 13+6+12+25=56
 * <p>
 * ��
 * 9 4 6 6      �Ʒ�6
 * 9 4 12       �Ʒ�12
 * 13 12        �Ʒ�13
 * 25           �Ʒ�25
 * 6+12+13+25=56
 * <p>
 * �����ַ�ʽ�ϲ�����56����̰�Ĳ��Ե�57���ĸ��ͣ���Ϊ��ѡ����С����������ȥ�ϲ��������ܱ�֤����ÿ��
 * ��������С��Ҳ��������С���º������ַ�ֵ�ϴ�
 * <p>
 * <p>
 * <p>
 * �����ʽ
 * ���С���һ��n���ڶ���a1 a2 �� an��ÿ��ai(1<=i<=n)��ʾ��i��ʯ�ӵĸ�����n<=100
 * <p>
 * <p>
 * �����ʽ
 * ���С���һ���ǵ�һ��ģ�͵���͵÷ֺ���ߵ÷֣��м�ո��������ڶ����ǵڶ���ģ�͵���͵÷ֺ�
 * ��ߵ÷֣��м�ո�������
 * <p>
 * <p>
 * ��������
 * 4
 * 9 4 4 5
 * <p>
 * <p>
 * �������
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
