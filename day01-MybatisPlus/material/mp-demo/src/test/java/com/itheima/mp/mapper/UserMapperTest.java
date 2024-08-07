package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
        //user.setId(6L);
        user.setUsername("Jack");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(1600);
        user.setInfo("{\"age\": 21, \"intro\": \"佛系青年\", \"gender\": \"male\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryByIds() {
        List<User> users = userMapper.selectBatchIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(20000);
        userMapper.updateById(user);
    }

    @Test
    void testDeleteUser() {
        userMapper.deleteById(5L);
    }

    @Test
    void testQueryWrapper() {
        var wrapper = new QueryWrapper<User>()
                .select("id", "username", "info", "balance")
                .like("username", "o")
                .ge("balance", 1000);

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testQueryLambdaWrapper() {
        var wrapper = new LambdaQueryWrapper<User>()
                .select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .like(User::getUsername, "o")
                .ge(User::getBalance, 1000);

        List<User> users = userMapper.selectList(wrapper);
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateByQueryWrapper() {
        User user = new User();
        user.setBalance(2000);
        var wrapper = new QueryWrapper<User>()
                .eq("username", "jack");
        userMapper.update(user, wrapper);
    }

    @Test
    void testUpdateBatch() {
        var wrapper = new UpdateWrapper<User>()
                .setSql("balance = balance - 200")
                .in("id", 1, 2, 4);
        userMapper.update(null, wrapper);
    }

    @Test
    void testCustomUpdate() {
        var ids = List.of(1L, 2L, 4L);
        int amount = 200;
        var wrapper = new LambdaQueryWrapper<User>().in(User::getId, ids);
        userMapper.updateBalanceByIds(wrapper, amount);
    }

}