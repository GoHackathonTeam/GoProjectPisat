package com.dekinci.lksbstu.utils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class Synchronizer<T> {
    private final AtomicReference<T> result = new AtomicReference<>();
    private final CountDownLatch latch = new CountDownLatch(1);

    public T get() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new IllegalStateException("Interrupted" + e.getMessage());
        }
        return result.get();
    }

    public void set(T result) {
        this.result.set(result);
        latch.countDown();
    }
}
