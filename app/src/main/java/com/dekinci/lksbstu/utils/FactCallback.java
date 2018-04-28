package com.dekinci.lksbstu.utils;

public interface FactCallback extends ResultCallback<Void> {
    default void success() {
        success(null);
    }
}
