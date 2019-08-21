package com.jayce.thinkinginjava;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * @author jayce tang
 * @version 1.0
 * @ClassName TestScanner
 * @description
 * @date 2018/10/4 17:54
 */
public class TestScanner implements Readable {
    private static Random rand = new Random(47);
    private static final char[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] lowers = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] vowels = "aeiou".toCharArray();
    private int count;

    public TestScanner(int count) {
        this.count = count;
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        if (count-- == 0) {
            return -1;
        }
        cb.append(capitals[rand.nextInt(capitals.length)]);
        for (int i=0; i<4; i++) {
            cb.append(vowels[rand.nextInt(vowels.length)]);
            cb.append(lowers[rand.nextInt(lowers.length)]);
        }
        cb.append(" ");
        return 10;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(new TestScanner(10));
        while (s.hasNext()) {
            System.out.println(s.next());
        }
    }
}
