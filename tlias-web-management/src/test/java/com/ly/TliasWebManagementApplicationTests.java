package com.ly;

import com.ly.mapper.DeptMapper;
import com.ly.pojo.Dept;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest//springboot单元注释的注解，当前测试类的测试方法运行时，启动项目的IOC容器
class SpringbootMybatisQuickstarApplicationTests {

    @Autowired
    private DeptMapper deptMapper;

    @Test
    @DisplayName("查询所有")
//    @ParameterizedTest
//    @ValueSource()//存数组
    public void testFindAll() {
        List<Dept> userList = deptMapper.findAll();
//      userList.forEach(user ->System.out.println(user));
        userList.forEach(System.out::println);//将lambad替换成方法引用
    }
}
