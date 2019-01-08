package cn.xiaows.applogs.dubbo.provider.dao;

import cn.xiaows.dubbo.entity.ExtAppLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 * @author: xiaows
 * @create: 2019-01-08 14:59
 * @version: v1.0
 */
@Mapper
public interface ExtAppLogMapper {

    List<ExtAppLog> getAll();

    int getCount();
}
