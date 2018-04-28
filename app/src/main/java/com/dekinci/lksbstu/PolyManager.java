package com.dekinci.lksbstu;

import com.dekinci.lksbstu.communication.PolyApi;

import java.util.concurrent.atomic.AtomicReference;

public class PolyManager {
    private static final AtomicReference<PolyManager> manager = new AtomicReference<>();

    private PolyApi api;
    private String id;

    public static PolyManager get() {
        if (manager.get() == null)
            synchronized (PolyManager.class) {
                if (manager.get() == null)
                    manager.set(new PolyManager());
            }

        return manager.get();
    }

    public void setApi(PolyApi api) {
        this.api = api;
    }

    public PolyApi getApi() {
        return api;
    }

    public void setCurrentId(String id) {

    }
}
