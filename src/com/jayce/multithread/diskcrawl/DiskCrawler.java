package com.jayce.multithread.diskcrawl;

import java.io.File;
import java.io.FileFilter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName FileCrawler
 * @description
 * @date 2019/4/29 12:10
 */
public class DiskCrawler {
    public static void startIndexing(File[] roots) {
        BlockingQueue<File> queue = new LinkedBlockingQueue<>(20);
        FileFilter fileFilter = new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                return true;
            }
        };
        for (File root : roots) {
            new Thread(new FileCrawler(queue, fileFilter, root)).start();
        }

        for (int i=0; i < 5; i++) {
            new Thread(new Indexer(queue)).start();
        }
    }

    public static void main(String[] args) {
        File file = new File("D:\\ideaworkspace\\basejavaknowlegdereview\\src\\com\\jayce");
        File[] files = file.listFiles();
        startIndexing(files);
    }
}

class FileCrawler implements Runnable {
    private final BlockingQueue<File> queue;
    private final FileFilter fileFilter;
    private final File root;

    public FileCrawler(BlockingQueue<File> queue, FileFilter fileFilter, File root) {
        this.queue = queue;
        this.fileFilter = fileFilter;
        this.root = root;
    }

    @Override
    public void run() {
        try {
            crawl(root);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void crawl(File root) throws InterruptedException {
        File[] entries = root.listFiles(fileFilter);
        if (entries != null) {
            for (File entry : entries) {
                if (entry.isDirectory()) {
                    crawl(entry);
                    //这里并不能确保线程的安全性，因为两操作不是原子性的
                    // ！！！(不对，其实已经确保了，因为只有该线程处理这个文件夹，不会与其他线程共享某个文件夹)
                } else if (!queue.contains(entry)) {
                    queue.put(entry);
                }
            }
        }
    }
}

class Indexer implements Runnable {

    private final BlockingQueue<File> queue;

    public Indexer(BlockingQueue<File> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        //  这里的消费者线程永远不会退出，程序则无法终止，后面会提到如何解决该问题
        while (true) {
            try {
                File take = queue.take();
                System.out.println(take.getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
