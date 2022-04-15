package com.duynn.sqa1_n8_yc21_shopmanager.servlet;

import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyTestWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MyTestWatcher.class)
public class test2 extends MyTestWatcher{
    @BeforeEach
    void setUp() {
        super.setLogger(test2.class);
    }

    @Test
    void test1() {

    }
}
