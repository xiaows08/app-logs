package cn.xiaows.applogs.dubbo.provider;

import cn.xiaows.applogs.dubbo.provider.dao.ExtAppLogMapper;
import cn.xiaows.dubbo.entity.ExtAppLog;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DubboProviderApplicationTests {
    // @Autowired
    // private ApplogDao applogDao;

    @Autowired
    private ExtAppLogMapper extAppLogMapper;

    @Test
    public void contextLoads() {

    }

    @Test
    public void testExtAppLog() {
        List<ExtAppLog> all = extAppLogMapper.getAll();
        for (ExtAppLog extAppLog : all) {
            System.out.println(extAppLog);
        }
    }
    /*@Test
    public void test() {
        List<AppLog> list = applogDao.getall();
        for (AppLog appLog : list) {
            System.out.println(appLog);
        }
    }*/
}

