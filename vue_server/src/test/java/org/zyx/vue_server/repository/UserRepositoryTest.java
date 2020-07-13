package org.zyx.vue_server.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    void findAll(){
        userRepository.findAll().forEach(System.out::println);
    }

    @Test
    void findByPage(){
        Pageable pageable = PageRequest.of(1,2);
         userRepository.findAll(pageable).toList().forEach(System.out::println);
    }



}