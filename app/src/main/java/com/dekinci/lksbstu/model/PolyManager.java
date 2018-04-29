package com.dekinci.lksbstu.model;

import com.dekinci.lksbstu.communication.PolyApi;
import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.ImageLoader;

import java.util.concurrent.atomic.AtomicReference;

public class PolyManager {
    private static final AtomicReference<PolyManager> manager = new AtomicReference<>();

    private PolyApi api;
    private CurrentUser user = new CurrentUser();;
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
        api.getUserInfo(user::setUser);
    }

    public PolyApi getApi() {
        return api;
    }

    public CurrentUser getUser() {
        return user;
    }
}
