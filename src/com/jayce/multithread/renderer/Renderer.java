package com.jayce.multithread.renderer;

import com.jayce.util.ExceptionUtil;
import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName Renderer
 * @description 使用CompletionService,使页面元素在下载完成后立即显示出来
 * @date 2019/5/2 12:33
 */
public class Renderer {
    private final ExecutorService exec;

    public Renderer(ExecutorService exec) {
        this.exec = exec;
    }

    public void renderPage(CharSequence source) {
        List<ImageInfo> imageInfos = scanForImage(source);
        CompletionService<ImageData> comp = new ExecutorCompletionService(exec);
        for (final ImageInfo imageInfo : imageInfos) {
            /**
             * public Future<V> submit(Callable<V> task) {
             *         if (task == null) throw new NullPointerException();
             *   1.    RunnableFuture<V> f = newTaskFor(task);
             *   2.    executor.execute(new QueueingFuture(f));
             *   3.    return f;
             *     }
             *     这是submit方法源码，由callable转换成FutureTask,FututeTask实现了RunableFuture接口，
             * 而RunnableFuture接口则继承了Runnable，Future两个接口，然后是new QueueingFuture(f)，
             * QueueingFuture是ExecutorCompletionService的内部类，继承自FutureTask，重写了done方法，
             * 而该方法在父类FututeTask中是个空方法，就是用于给子类重写。
             *  private class QueueingFuture extends FutureTask<Void> {
             *         QueueingFuture(RunnableFuture<V> task) {
             *             super(task, null);
             *             this.task = task;
             *         }
             *         protected void done() { completionQueue.add(task); }
             *         private final Future<V> task;
             *     }
             *     上面即是QueueingFuture类，在它的构造方法里，首先调用了父类及FutureTask中的构造方法，把task当作
             *  Runnable传过去，通过层层构造，你会发现，它最终成为了一个实现了callable的RunnableAdapter。。。，
             *  然后看上面构造方法的第二句代码，QueueingFuture中的task指向这个RunnableAdapter，然后执行execute方法，
             *  QueueingFuture则执行父类FutureTask中的run方法，然后则会执行RunnableAdapter中的call方法，最后会
             *  因为没有堵塞，则最终会执行run方法中的set(result)方法，该方法会执行finishCompletion方法，里面就有一个done方法
             *  需要执行，根据多态，会执行子类的done方法，所以会把子类中的task放到completionQueue中
             *
             */
           comp.submit(/*new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }
            }*/
                   () -> imageInfo.downloadImage());
        }
        renderText(source);

        try {
            for (int m = 0; m < imageInfos.size(); m++) {
                //  从ExecutorCompletionService中的内置BlockingQueue（completionQueue）中获取future对象
                Future<ImageData> imageDataFuture = comp.take();
                //  堵塞式的获取
                ImageData imageData = imageDataFuture.get();
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw ExceptionUtil.launderThrowable(e.getCause());
        }

    }

    private void renderImage(ImageData imageData) {
    }

    private void renderText(CharSequence source) {
    }

    private List<ImageInfo> scanForImage(CharSequence source) {
        return new ArrayList<>();
    }
}
