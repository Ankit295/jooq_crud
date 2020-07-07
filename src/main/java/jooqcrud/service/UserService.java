package jooqcrud.service;

import jooqcrud.dao.UserDao;

import org.example.tables.pojos.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    public List<Users> getUserList() {
                return userDao.getUsers();
    }

    public String insertUser(Users usersVO) {
       return userDao.insertUser(usersVO);
    }
}
