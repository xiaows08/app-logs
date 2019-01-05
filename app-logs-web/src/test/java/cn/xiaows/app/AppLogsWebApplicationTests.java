package cn.xiaows.app;

import cn.xiaows.app.dao.UserMapper;
import cn.xiaows.app.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppLogsWebApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testMapper() {
        List<User> users = userMapper.selectAll();
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("===============");

    }

/*
    @Autowired
    private cn.xiaows.app.dao0.UserMapper userMapper0;
    @Test
    public void testMapper0() {
        List<User> users1 = userMapper0.selectAll();
        for (User user : users1) {
            System.out.println(user);
        }
        System.out.println("===============");

    }
*/
    // @Test
    // public void testGetUsers() {
    //     List<User> users = userMapper.getUsers();
    //     for (User user : users) {
    //         System.out.println(user);
    //     }
    // }


}

