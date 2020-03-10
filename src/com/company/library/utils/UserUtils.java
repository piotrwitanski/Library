package com.company.library.utils;

import com.company.library.dto.User;

public class UserUtils {

    private DataBaseUtils dataBaseUtils;

    public UserUtils() {
        this.dataBaseUtils = new DataBaseUtils();
    }

    public void createUser(User user) {

        dataBaseUtils.createUser(user);

    }

    public User downloadUser(int userId) {

        return dataBaseUtils.downloadUser(userId);

    }
}
