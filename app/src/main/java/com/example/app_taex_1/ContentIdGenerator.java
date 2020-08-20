package com.example.app_taex_1;

import java.net.UnknownHostException;
import java.util.Random;

public class ContentIdGenerator {
    static int seq = 0;
    static String hostname;

    static {
        try {
            hostname = java.net.InetAddress.getLocalHost().getCanonicalHostName();
        } catch (UnknownHostException e) {
            // we can't find our hostname? okay, use something no one else is likely to use
            hostname = new Random(System.currentTimeMillis()).nextInt(100000) + ".localhost";
        }
    }

    public static synchronized int getSeq() {
        return (seq++) % 100000;
    }

    public static String getContentId() {
        int c = getSeq();
        return c + "." + System.currentTimeMillis() + "@" + hostname;
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.println(ContentIdGenerator.getContentId());
            }
            Thread.sleep(100);
        }
    }
}
