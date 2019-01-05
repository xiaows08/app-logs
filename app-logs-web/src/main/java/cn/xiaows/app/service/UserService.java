// package cn.xiaows.app.service;
//
// import cn.xiaows.app.dao.UserMapper;
// import cn.xiaows.app.entity.User;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;
//
// import java.util.List;
//
// @Service
// public class UserService {
//
//     @Autowired
//     private UserMapper userMapper;
//
//     public void getAll() {
//         List<User> users = userMapper.selectAll();
//         for (User user : users) {
//             System.out.println(user);
//         }
//     }
// }
