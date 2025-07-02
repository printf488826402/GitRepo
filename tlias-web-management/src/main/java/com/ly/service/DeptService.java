package com.ly.service;

import com.ly.pojo.Dept;
import org.springframework.stereotype.Component;

import java.util.List;

@Component//用于将Dao层实现类交给IOC容器管理,这里的注解可以换成@Service
public interface DeptService {
    //查询所有部门
    List<Dept> findAll();
    //根据id删除部门
    void deleteById(Integer id);
    //新增部门
    void add(Dept dept);
    //根据id查询部门
    Dept getById(Integer id);
    //修改部门
    void update(Dept dept);
}
