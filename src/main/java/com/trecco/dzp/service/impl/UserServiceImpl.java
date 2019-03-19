package com.trecco.dzp.service.impl;

import com.trecco.dzp.model.User;
import com.trecco.dzp.service.UserRepository;
import com.trecco.dzp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor ={Exception.class,RuntimeException.class})
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findByAndSort(String userName, Sort sort) {
        return userRepository.findByAndSort(userName, sort);
    }

    @Override
    public User findByIdCard(String idCard) {
        return userRepository.findByIdCard(idCard);
    }

    @Override
    public User findByIdCard2(String idCard) {
        return userRepository.findByIdCard2(idCard);
    }

    @Override
    public User findByIdCard3(String idCard) {
        return userRepository.findByIdCard3(idCard);
    }

    @Override
    public Page<User> findByLastNameWithPageable(String userName, Pageable pageable) {
        return userRepository.findByLastNameWithPageable(userName, pageable);
    }

    @Override
    public List<User> findAll(Sort sort) {
        return userRepository.findAll(sort);
    }

    @Override
    public int updateUser(String userName, String idCard) {
        return userRepository.updateUser(userName, idCard);
    }

    @Override
    public void deleteByIdCard(String idCard) {
        userRepository.deleteByIdCard(idCard);
    }

    @Override
    public void deleteByIdCard2(String idCard) {
        userRepository.deleteByIdCard2(idCard);
    }

    @Override
    public void deleteById(int id) {
        userRepository.deleteById(id);
    }

}