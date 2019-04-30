package by.itacademy.service.service;

import by.itacademy.database.dao.UserDao;
import by.itacademy.database.entity.User;

public class UserService {

    private static final UserService USER_SERVICE = new UserService();
    private final UserDao userDao = UserDao.getUserDao();

    public User getDefaultUser() {
        return userDao.getDefaultUser();
    }

    public static UserService getUserService() {
        return USER_SERVICE;
    }
}
