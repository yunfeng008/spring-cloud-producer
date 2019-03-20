package com.trecco.dzp.controller;

import com.trecco.dzp.model.User;
import com.trecco.dzp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * POST - Create a new resource
 * GET - Read a resource
 * PUT - Update an existing resource
 * DELETE - Delete a resource
 * Tomcat by default is not enabled for HTTP PUT command.
 * 只要让地址栏的参数可以传入函数里面，就能执行修改、新增、删除操作，method用RequestMethod.GET即可
 * 用PUT、POST、DELETE会报405错误，因为输入到地址栏默认使用GET方法
 */

@Slf4j
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/saveUser/{userName}/{idCard}",method = RequestMethod.GET)
    public User saveUser(@PathVariable String userName,
                         @PathVariable String idCard) {
        User user = new User();//Id是自增长的，不需要传
        user.setUserName(userName);
        user.setIdCard(idCard);
        return userService.save(user);
    }

    @RequestMapping(value = "/findByAndSort/{userName}", method = RequestMethod.GET)
    public List<User> findByAndSort(@PathVariable String userName) {
        Sort sort = new Sort(Sort.Direction.DESC, "userName");
        return userService.findByAndSort(userName, sort);
    }

    //提供服务请求者访问
    @RequestMapping(value = "/findByIdCard", method = RequestMethod.GET)
    public User findByIdCard(@RequestParam(name = "idCard")  String idCard) {
        //加上@Slf4j，直接使用log变量
        log.info(idCard);
        return userService.findByIdCard(idCard);
    }

    @RequestMapping(value = "/findByIdCard2/{idCard}", method = RequestMethod.GET)
    public User findByIdCard2(@PathVariable(name = "idCard") String idCard) {
        log.info(idCard);
        return userService.findByIdCard2(idCard);
    }

    @RequestMapping(value = "/findByIdCard3/idCard/{idCard}/token/{token}", method = RequestMethod.GET)
    public User findByIdCard3(@PathVariable String idCard,@PathVariable String token) {
        log.info(idCard);
        log.info(token);
        return userService.findByIdCard3(idCard);
    }

    @RequestMapping(value = "/findByLastNameWithPageable/{userName}", method = RequestMethod.GET)
    public Page<User> findByLastNameWithPageable(@PathVariable String userName) {
        log.info(userName);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(5,5,sort);
        return userService.findByLastNameWithPageable(userName, pageable);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public List<User> findAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return userService.findAll(sort);
    }

    @RequestMapping(value = "/updateUser/{userName}/{idCard}", method = RequestMethod.GET)
    public int updateUser(@PathVariable String userName, @PathVariable String idCard) {
        return userService.updateUser(userName, idCard);
    }

    @RequestMapping(value = "/deleteByIdCard/{idCard}", method = RequestMethod.GET)
    public String deleteByIdCard(@PathVariable String idCard) {
        userService.deleteByIdCard(idCard);
        return "SUCCESS";
    }

    @RequestMapping(value = "/deleteByIdCard2/{idCard}", method = RequestMethod.GET)
    public String deleteByIdCard2(@PathVariable String idCard) {
        userService.deleteByIdCard2(idCard);
        return "SUCCESS";
    }

    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.GET)
    public String deleteById(@PathVariable int id) {
        userService.deleteById(id);
        return "SUCCESS";
    }

}