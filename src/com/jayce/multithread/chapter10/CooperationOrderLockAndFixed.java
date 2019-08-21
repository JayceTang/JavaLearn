package com.jayce.multithread.chapter10;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/8/21 11:35
 * @description     在协作对象之间的锁由于顺序产生的死锁   及  修复方案
 */
public class CooperationOrderLockAndFixed {
    /***************************************************************************************
    *   10-6
     *  注意，容易发生死锁
    ****************************************************************************************/
    private static class Taxi1 {


    }

    private static class Dispatcher1 {

    }

    /***************************************************************************************/

    /***************************************************************************************
    *  10-7
    *
    ****************************************************************************************/
    private static class Taxi2 {

    }

    private static class Dispatcher2 {

    }
}
