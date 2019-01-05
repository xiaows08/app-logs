package cn.xiaows.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// import tk.mybatis.spring.annotation.MapperScan;// 不推荐使用 容易搞混!!!

/**
    如果dao.UserMapper extends MyMapper<User>
    dao.UserMapper就不需要添加@org.apache.ibatis.annotations.Mapper注解
    就必须添加@tk.mybatis.spring.annotation.MapperScan("cn.xiaows.app.dao")该注解

    如果dao.UserMapper没有添加Mapper注解
    则这里需要添加MapperScan("cn.xiaows.app.dao")
 */
@SpringBootApplication
@MapperScan("cn.xiaows.app.dao")
public class AppLogsWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppLogsWebApplication.class, args);
    }

}

