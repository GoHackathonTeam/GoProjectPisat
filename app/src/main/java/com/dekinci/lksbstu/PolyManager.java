package com.dekinci.lksbstu;

import com.dekinci.lksbstu.communication.PolyApi;
import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.ResultCallback;

import java.util.concurrent.atomic.AtomicReference;

public class PolyManager {
    private static final AtomicReference<PolyManager> manager = new AtomicReference<>();

    private PolyApi api;
    private User currentUser;
    private String id;

    public static PolyManager get() {
        if (manager.get() == null)
            synchronized (PolyManager.class) {
                if (manager.get() == null)
                    manager.set(new PolyManager());
            }

        return manager.get();
    }

    private PolyManager() {
    }

    public void setApi(PolyApi api) {
        this.api = api;
    }

    public void setUser() {
        api.getUserInfo(user -> currentUser = user);
    }

    public PolyApi getApi() {
        return api;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}
