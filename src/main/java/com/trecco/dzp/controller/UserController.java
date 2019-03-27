package com.trecco.dzp.controller;

import com.trecco.dzp.model.User;
import com.trecco.dzp.service.UserService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

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
@Api(tags = {"UserController用户接口"},description = "用户基本信息操作")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value="创建用户" ,notes="创建用户时的注意事项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户姓名", dataType = "String", paramType = ""),
            @ApiImplicitParam(name = "idCard", value = "用户身份证", dataType = "String", paramType = "")
    })
    @GetMapping(value = "/saveUser")
    public User saveUser(@RequestParam String userName,
                         @RequestParam String idCard) {
        User user = new User();//Id是自增长的，不需要传
        user.setUserName(userName);
        user.setIdCard(idCard);
        return userService.save(user);
    }

    @ApiOperation(value = "根据姓名查询用户", notes = "根据姓名查询用户信息")
    @ApiImplicitParam(name = "userName", value = "用户的名字", paramType = "query", dataType = "String")
    @GetMapping(value = "/findByAndSort")
    public List<User> findByAndSort(@RequestParam String userName) {
        Sort sort = new Sort(Sort.Direction.DESC, "userName");
        return userService.findByAndSort(userName, sort);
    }

    //提供服务请求者访问
    @ApiOperation(value = "根据身份证号查询用户", notes = "根据身份证号查询用户信息")
    @ApiImplicitParam(name = "idCard", value = "身份证号", paramType = "query", dataType = "String")
    @GetMapping(value = "/findByIdCard")
    public User findByIdCard(@RequestParam(name = "idCard")  String idCard) {
        //加上@Slf4j，直接使用log变量
        log.info(idCard);
        return userService.findByIdCard(idCard);
    }

    @ApiOperation(value = "根据身份证号查询用户", notes = "根据身份证号查询用户信息")
    @ApiImplicitParam(name = "idCard", value = "身份证号", paramType = "query", dataType = "String")
    @GetMapping(value = "/findByIdCard2")
    public User findByIdCard2(@RequestParam(name = "idCard") String idCard) {
        log.info(idCard);
        return userService.findByIdCard2(idCard);
    }

    @ApiOperation(value = "根据身份证号查询用户", notes = "根据身份证号查询用户信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "idCard", value = "身份证号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "token", value = "token", paramType = "query", dataType = "String")
    })
    @GetMapping(value = "/findByIdCard3")
    public User findByIdCard3(@RequestParam String idCard,@RequestParam String token) {
        log.info(idCard);
        log.info(token);
        return userService.findByIdCard3(idCard);
    }

    @ApiOperation(value = "根据姓名查询用户", notes = "根据姓名查询用户信息")
    @ApiImplicitParam(name = "userName", value = "用户的名字", paramType = "query", dataType = "String")
    @GetMapping(value = "/findByLastNameWithPageable")
    public Page<User> findByLastNameWithPageable(@RequestParam String userName) {
        log.info(userName);
        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(5,5,sort);
        return userService.findByLastNameWithPageable(userName, pageable);
    }

    @ApiIgnore() // 添加这个注解将不会显示在UI界面上
    @ApiOperation(value = "查询所有用户", notes = "查询所有用户")
    @GetMapping(value = "/findAll")
    public List<User> findAll() {
        Sort sort = new Sort(Sort.Direction.ASC, "id");
        return userService.findAll(sort);
    }

    @ApiOperation(value="更新用户" ,notes="更新用户时的注意事项")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户姓名"),
            @ApiImplicitParam(name = "idCard", value = "用户身份证")
    })
    @GetMapping(value = "/updateUser")
    public int updateUser(@RequestParam String userName, @RequestParam String idCard) {
        return userService.updateUser(userName, idCard);
    }

    @ApiOperation(value = "删除用户", notes = "根据身份证号删除用户")
    @DeleteMapping(value = "/deleteByIdCard/{idCard}")
    public String deleteByIdCard(@ApiParam(name = "idCard", value = "身份证号") @PathVariable String idCard) {
        userService.deleteByIdCard(idCard);
        return "SUCCESS";
    }

    @ApiOperation(value = "删除用户", notes = "根据身份证号删除用户")
    @DeleteMapping(value = "/deleteByIdCard2/{idCard}")
    public String deleteByIdCard2(@ApiParam(name = "idCard", value = "身份证号") @PathVariable String idCard) {
        userService.deleteByIdCard2(idCard);
        return "SUCCESS";
    }

    @ApiOperation(value = "删除用户", notes = "根据主键删除用户")
    @DeleteMapping(value = "/deleteById/{id}")
    public String deleteById(@ApiParam(name = "id", value = "主键") @PathVariable int id) {
        userService.deleteById(id);
        return "SUCCESS";
    }

}