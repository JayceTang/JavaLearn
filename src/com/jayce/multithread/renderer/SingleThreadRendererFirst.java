package com.jayce.multithread.renderer;

import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName SingleThreadRendererFirst
 * @description 初始版本：串行的进行HTML渲染
 *
 * @date 2019/5/2 1:31
 */
public class SingleThreadRendererFirst {
    void renderPage(CharSequence source) {
        renderText(source);
        List<ImageData> imageData = new ArrayList<>();
        for (ImageInfo imageInfo : scanForImageInfo(source)) {
            imageData.add(imageInfo.downloadImage());
        }
        for (ImageData data : imageData) {
            renderImage(data);
        }
    }


    /**
     * 以下方法皆为假设处理了
     */

    private void renderText(CharSequence source) {
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return new ArrayList<>(6);
    }
    private void renderImage(ImageData data) {
    }
}
