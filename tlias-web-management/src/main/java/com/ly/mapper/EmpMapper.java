package com.ly.mapper;

import com.ly.pojo.Emp;
import com.ly.pojo.EmpQueryParam;
import org.apache.ibatis.annotations.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {
// //   ======================原始方法实现分页查询======================================
//    @Select("SELECT count(*) from emp e left join dept d on e.dept_id=d.id")
//    public  long count();//查询总记录数
//    @Select("SELECT e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id" +
//            " order by e.update_time desc limit #{start},#{pageSize};")
//    public List<Emp> list(Integer start, Integer pageSize);//分页查询

// //   ======================插件实现分页查询======================================
   //这里注释掉是因为MyBatis用xml已经实现了
//@Select("SELECT e.*,d.name deptName from emp e left join dept d on e.dept_id=d.id" +
//        " order by e.update_time desc")

//public List<Emp> list(String name,
//                      Integer gender,
//                      LocalDate begin,
//                      LocalDate end);//条件分页查询
    public List<Emp> list(EmpQueryParam empQueryParam);//优化条件分页查询
//    ======================新增员工基本信息======================================
    @Options(useGeneratedKeys = true,keyProperty = "id")//获取生成的主键----主键返回
    @Insert("insert into emp(username,name,gender,phone,job,salary,image,entry_date,dept_id,create_time,update_time)" +
            "values(#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);

    void deleteByIds(List<Integer> ids);

    Emp getById(Integer id);
    //根据id更新员工基本信息
    void updateById(Emp emp);
    //
    @MapKey("pos")
    List<Map<String,Object>> countEmpJobData();
    @MapKey("name")
    List<Map<String, Object>> countEmpGenderData();
    //登录:根据用户名密码查询用户信息
//    @Select("select id,username,name from emp where username=#{username} and password=#{password}")
    Emp selectByUsernameAndPassword(Emp emp);
}
