// package cn.xiaows.applogs.dubbo.provider.dao;
//
// import cn.xiaows.dubbo.entity.AppLog;
// import org.junit.Test;
// import org.junit.runner.RunWith;
// import org.springframework.test.context.ContextConfiguration;
// import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
// import javax.annotation.Resource;
//
// import java.util.List;
//
// @RunWith(SpringJUnit4ClassRunner.class)
// @ContextConfiguration({"classpath:spring-dao.xml"})
// public class ApplogDaoTest {
//
//     @Resource
//     private ApplogDao applogDao;
//
//     @Test
//     public void test1() {
//         List<AppLog> all = applogDao.getall();
//         System.out.println(all.size());
//         System.out.println(all);
//
//     }
//
// }