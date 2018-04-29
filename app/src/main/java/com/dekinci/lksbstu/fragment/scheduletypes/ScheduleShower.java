package com.dekinci.lksbstu.fragment.scheduletypes;

import java.util.Date;

public interface ScheduleShower {
    void next();
    void previous();
    void show(Date date);
}
