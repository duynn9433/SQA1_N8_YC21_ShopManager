package com.duynn.sqa1_n8_yc21_shopmanager.servlet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

public class test2 extends MyTestWatcher{
    @BeforeEach
    void setUp() {
        super.setLogger(test2.class);
    }

    @Test
    @ExtendWith(MyTestWatcher.class)
    void test1() {

    }
}
