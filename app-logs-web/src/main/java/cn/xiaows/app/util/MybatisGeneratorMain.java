package cn.xiaows.app.util;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * mybatis generator 代码生成器
 *
 * @author: xiaows
 * @create: 2019-01-08 14:55
 * @version: v1.0
 */
public class MybatisGeneratorMain {

	public static void main(String[] args) throws Exception {
		List<String> warnings = new ArrayList<>();
		ConfigurationParser cp = new ConfigurationParser(warnings);
		Configuration config = cp.parseConfiguration(new File("app-logs-web/src/main/resources/mybatis-generator-hive.xml"));
		// Configuration config = cp.parseConfiguration(ClassLoader.getSystemResourceAsStream("mybatis-generator.xml"));
		DefaultShellCallback callback = new DefaultShellCallback(true);
		MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
		myBatisGenerator.generate(null);
		for (String warning : warnings) {
			System.out.println(warning);
		}
	}
}
