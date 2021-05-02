package cz.fi.muni.pa165.hockeymanager.service;

import cz.fi.muni.pa165.hockeymanager.dao.UserDao;
import cz.fi.muni.pa165.hockeymanager.entity.User;
import cz.fi.muni.pa165.hockeymanager.exceptions.ManagerServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Kristian Kosorin (456620)
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User create(User user) {
        try {
            userDao.create(user);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not create user " + user + " exc: " + e);
        }
        return user;
    }

    @Override
    public void update(User user) {
        try {
            userDao.update(user);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not update user " + user + " exc: " + e);
        }
    }

    @Override
    public void remove(User user) {
        try {
            userDao.remove(user);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not remove user " + user + " exc: " + e);
        }
    }

    @Override
    public List<User> findAll() {
        List<User> userList;
        try {
            userList = userDao.findAll();
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not find all users exc: " + e);
        }
        return userList;
    }

    @Override
    public User findById(Long id) {
        User user;
        try {
            user = userDao.findById(id);
        } catch (DataAccessException e) {
            throw new ManagerServiceException("Could not find user with id: " + id + "exc: " + e);
        }
        return user;
    }
}
