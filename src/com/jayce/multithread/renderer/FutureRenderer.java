package com.jayce.multithread.renderer;

import com.jayce.util.ExceptionUtil;
import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName FutureRenderer
 * @description 使用future来加载页面渲染
 *      由于页面渲染可分解为两个任务，一个是加载文本，一个是下载所有的图片
 *         （一个是cpu密集型，一个是IO密集型）
 * @date 2019/5/2 1:46
 */
public class FutureRenderer {
    //  最好是自定义，或者重写
    private final ExecutorService exec = Executors.newFixedThreadPool(100);

    public void renderPage(CharSequence source) {
        final List<ImageInfo> imageInfos = scanForImage(source);
        Callable<List<ImageData>> call = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> list = new ArrayList<>();
                for (ImageInfo imageInfo : imageInfos) {
                    list.add(imageInfo.downloadImage());
                }
                return list;
            }
        };
        Future<List<ImageData>> future = exec.submit(call);
        renderText(source);
        
        try {
            List<ImageData> imageData = future.get();
            for (ImageData data : imageData) {
                renderImage(data);
            }
        } catch (InterruptedException e) {
            //  重新设置线程的中断状态
            Thread.currentThread().interrupt();
            //  由于不需要结果，那么就取消任务
            future.cancel(true);
        } catch (ExecutionException e) {
            throw ExceptionUtil.launderThrowable(e.getCause());
        }
    }

    private void renderImage(ImageData data) {
    }

    private void renderText(CharSequence source) {
    }

    private List<ImageInfo> scanForImage(CharSequence source) {
        return new ArrayList<>();
    }
}
