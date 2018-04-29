package com.dekinci.lksbstu.model;

import android.graphics.Bitmap;

import com.dekinci.lksbstu.communication.structure.pojos.User;
import com.dekinci.lksbstu.utils.ImageLoader;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class CurrentUser {
    private User user;
    private Bitmap avatar;

    private Set<Consumer<Bitmap>> avatarWaters = new HashSet<>();
    private Set<Consumer<User>> userWaters = new HashSet<>();

    public void getUser(Consumer<User> consumer) {
        if (user != null)
            consumer.accept(user);
        else
            userWaters.add(consumer);
    }

    public void setUser(User user) {
        this.user = user;
        new ImageLoader(this::setAvatar).execute(user.getAvatarUrl());

        for (Consumer<User> consumer : userWaters)
            consumer.accept(user);
        userWaters.clear();
    }

    private void setAvatar(Bitmap avatar) {
        this.avatar = avatar;

        for (Consumer<Bitmap> consumer : avatarWaters)
            consumer.accept(avatar);
        avatarWaters.clear();
    }

    public void getAvatar(Consumer<Bitmap> receiver) {
        if (avatar != null)
            receiver.accept(avatar);
        else
            avatarWaters.add(receiver);
    }
}
