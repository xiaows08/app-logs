package cn.xiaows.app.dao0;

import cn.xiaows.app.entity0.Project;
import java.util.List;

public interface ProjectMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project
     *
     * @mbg.generated Sat Jan 05 19:17:57 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project
     *
     * @mbg.generated Sat Jan 05 19:17:57 CST 2019
     */
    int insert(Project record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project
     *
     * @mbg.generated Sat Jan 05 19:17:57 CST 2019
     */
    Project selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project
     *
     * @mbg.generated Sat Jan 05 19:17:57 CST 2019
     */
    List<Project> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table project
     *
     * @mbg.generated Sat Jan 05 19:17:57 CST 2019
     */
    int updateByPrimaryKey(Project record);
}