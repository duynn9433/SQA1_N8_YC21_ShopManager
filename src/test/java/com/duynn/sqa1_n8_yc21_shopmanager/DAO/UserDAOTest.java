package com.duynn.sqa1_n8_yc21_shopmanager.DAO;

import com.duynn.sqa1_n8_yc21_shopmanager.utils.MyTestWatcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MyTestWatcher.class)
class UserDAOTest extends MyTestWatcher{
    private UserDAO userDAO;
    @BeforeEach
    void setUp() {
        super.setLogger(UserDAO.class);
        userDAO = new UserDAO();
    }

    @Test
    void getUser() {
    }

    @Test
    void checkLogin() {
    }

    @Test
    void getAllStaff() {
    }
}