package com.duynn.sqa1_n8_yc21_shopmanager.servlet;


import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyTestWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import static org.junit.jupiter.api.Assertions.assertEquals;

class mainTest extends MyTestWatcher {
    @BeforeEach
    void setUp() {
        super.setLogger(mainTest.class);
    }

    @Test
    @ExtendWith(mainTest.class)
    void testMain() {

//        logger.info("Test main");
//        logger.error("Test main error");
//        logger.debug("Test main debug");
        assertEquals(1, 1);
    }
}
//    private static String watchedLog;
//
//    @Rule
//    public TestWatcher watchman= new TestWatcher() {
//        @Override
//        protected void failed(Throwable e, Description description) {
//            watchedLog+= description + "\n";
//        }
//
//        @Override
//        protected void succeeded(Description description) {
//            watchedLog+= description + " " + "success!\n";
//        }
//    };
//
//
//    @Test
//    void add() {
//        assertEquals(4, main.add(2, 2));
//        System.out.println(watchedLog);
//    }
//
//    @Test
//    void multiply() {
//        assertAll(() -> assertEquals(4, main.multiply(2, 2)),
//                () -> assertEquals(-4, main.multiply(2, -2)),
//                () -> assertEquals(4, main.multiply(-2, -2)),
//                () -> assertEquals(0, main.multiply(1, 0)));
//        System.out.println(watchedLog);
//    }
//}