package com.dekinci.lksbstu.communication.structure;

import com.dekinci.lksbstu.communication.structure.pojos.User;

public class UserFactory {

    public User getStudent() {
        return User.simpleUser(UserStatus.STUDENT);
    }

    //TODO...
}
