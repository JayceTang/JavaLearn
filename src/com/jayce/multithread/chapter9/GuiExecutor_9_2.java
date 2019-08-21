package com.jayce.multithread.chapter9;

import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Jayce Tang
 * @version 1.0
 * @date 2019/8/15 11:59
 * @description    基于SwingUtilities构建的executor
 */
public class GuiExecutor_9_2 extends AbstractExecutorService {

    private static final GuiExecutor_9_2 guiExec = new GuiExecutor_9_2();

    public static GuiExecutor_9_2 getInstance() {
        return guiExec;
    }

    @Override
    public void execute(Runnable r) {

        if (SwingUtilities_9_1.isEvenDispatchThread()) {
            r.run();
        } else {
            SwingUtilities_9_1.invokeLater(r);
        }
    }

    /** not allow instance */
    private GuiExecutor_9_2(){}



    @Override
    public void shutdown() {

    }

    @Override
    public List<Runnable> shutdownNow() {
        return null;
    }

    @Override
    public boolean isShutdown() {
        return false;
    }

    @Override
    public boolean isTerminated() {
        return false;
    }

    @Override
    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return false;
    }
}
