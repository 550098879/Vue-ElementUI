package org.zyx.vue_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.zyx.vue_server.entity.User;
import org.zyx.vue_server.repository.UserRepository;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/findAll/{currPage}/{limit}")
    public Page<User> findAll(@PathVariable("currPage") int currPage, @PathVariable("limit")int limit) {
        Pageable pageable = PageRequest.of(currPage-1,limit);
        return userRepository.findAll(pageable);
    }

    @PostMapping("/addUser")
    public boolean addUser(@RequestBody User user){
        //前端请求参数为json格式的数据,所以后台需要使用RequestBody注解来接收
        System.out.println(user);
        System.out.println(userRepository.save(user));

        return true;
    }

}
