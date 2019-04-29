package by.itacademy.database.dao;

import by.itacademy.database.entity.User;

public class UserDao {

    private static final UserDao USER_DAO=new UserDao();

    public User getDefaultUser (){
    return null;
    }

    public static UserDao getUserDao() {
        return USER_DAO;
    }
}
