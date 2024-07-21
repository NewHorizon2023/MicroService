package com.itheima.mp.service;

import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserServiceImplTest {

    @Resource
    private UserService userService;

    @Test
    void testSaveUser() {
        User user = new User();
        //user.setId(6L);
        user.setUsername("LiLei");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(1600);
        user.setInfo("{\"age\": 21, \"intro\": \"佛系青年\", \"gender\": \"male\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userService.save(user);
    }

    @Test
    void testQuery() {
        List<User> users = userService.listByIds(List.of(1, 2, 4));
        users.forEach(System.out::println);
    }

}