package com.jayce.multithread;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName VisualComponent
 * @description
 * @date 2019/4/28 8:22
 */
public class VisualComponent {
    private final List<KeyListener> keyListeners = new CopyOnWriteArrayList<>();
    private final List<MouseListener> mouseListeners = new CopyOnWriteArrayList<>();

    public void addKeyListener(KeyListener keyListener) {
        keyListeners.add(keyListener);
    }

    public void addMouseListener(MouseListener mouseListener) {
        mouseListeners.add(mouseListener);
    }

    public void removeKeyListener(KeyListener keyListener) {
        keyListeners.remove(keyListener);
    }

    public void removeMouseListener(MouseListener mouseListener) {
        mouseListeners.remove(mouseListener);
    }
}
