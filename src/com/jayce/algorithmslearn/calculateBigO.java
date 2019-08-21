package com.jayce.algorithmslearn;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName algorithms4learn
 * @description   学习算法的空间. 时间复杂度分析    使用unit_time来表示单位时间
 * @date 2018/9/27 22:07
 */
public class calculateBigO {
    int calculate1(int n) {
        int sum = 0;                // 1个unit_time
        int i = 1;                  // 1个unit_time
        for (; i <= n; i++) {       // n个unit_time
            sum += i;               // n个unit_time
        }                           //  总计T(n) = ( (2n+2)unit_time
        return sum;
    }



    /**大O      T(n)=O(f(n))        */

    int calculate2(int n) {
        int sum = 0;                // 1个unit_time
        int i = 1;                  // 1个unit_time
        int j = 1;                  // 1个unit_time
        for (; i <= n; i++) {       // n个unit_time
            j = 1;                  // n个unit_time
            for (; j <= n; j++) {   // n^2个unit_time
                sum += j;           // n^2个unit_time
            }
        }                           //  总计T(n) = (2n^2+2n+3)unit_time
        return sum;
    }
}
