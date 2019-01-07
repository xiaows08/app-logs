package cn.xiaows.applogs.dubbo.provider.dao;

import cn.xiaows.dubbo.entity.ExtAppLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExtAppLogMapper {

    List<ExtAppLog> getAll();

    int getCount();
}
