package com.ly.service.impl;

import com.ly.mapper.DeptMapper;
import com.ly.pojo.Dept;
import com.ly.service.DeptService;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void add(Dept dept) {
        //1.补全基本属性
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用mapper接口方法
        deptMapper.insert(dept);
    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        //1.补全基本属性updatetime
        dept.setUpdateTime(LocalDateTime.now());
        //2.调用Mapper接口方法更新部门
        deptMapper.update(dept);
    }

    @Override
    public void deleteById(Integer id) {
    deptMapper.deleteById(id);
    }
}
