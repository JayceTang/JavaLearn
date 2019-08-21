package com.jayce.multithread.futuretask;

import com.jayce.util.ExceptionUtil;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestPreloader
 * @description 使用utureTask来提前加载稍后需要的数据
 * @date 2019/4/29 23:49
 */
public class TestPreloader {
    private final FutureTask<ProductInfo> future = new FutureTask<>(new Callable<ProductInfo>() {
        @Override
        public ProductInfo call() throws DataLoadException {
            //  提前加载数据库中的数据，这里简化了
            return new ProductInfo(1, "小明");
        }
    });

    private final Thread thread = new Thread(future);

    public void start() {
        thread.start();
    }

    public ProductInfo get() throws DataLoadException, InterruptedException {
        try {
            return future.get();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            if (cause instanceof DataLoadException) {
                throw (DataLoadException) cause;
            } else {
                throw ExceptionUtil.launderThrowable(cause);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        TestPreloader preloader = new TestPreloader();
        preloader.start();
        ProductInfo productInfo = preloader.get();
        System.out.println(productInfo);

    }
}
