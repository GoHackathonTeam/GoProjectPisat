package com.dekinci.lksbstu;

import com.dekinci.lksbstu.communication.PolyApi;

import java.util.concurrent.atomic.AtomicReference;

public class PolyManager {
    private static final AtomicReference<PolyApi> manager = new AtomicReference<>();
}
