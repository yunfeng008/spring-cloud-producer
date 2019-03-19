package com.trecco.dzp.service;

import com.trecco.dzp.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {

    User save(User user);

    List<User> findByAndSort(String userName, Sort sort);

    User findByIdCard(String idCard);

    User findByIdCard2(String idCard);

    User findByIdCard3(String idCard);

    Page<User> findByLastNameWithPageable(String userName, Pageable pageable);

    List<User> findAll(Sort sort);

    int updateUser(String userName, String idCard);

    void deleteByIdCard(String idCard);

    void deleteByIdCard2(String idCard);

    void deleteById(int id);

}