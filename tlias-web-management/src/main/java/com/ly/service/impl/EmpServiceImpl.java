package com.ly.service.impl;

import ch.qos.logback.classic.pattern.ClassOfCallerConverter;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ly.mapper.EmpExprMapper;
import com.ly.mapper.EmpMapper;
import com.ly.pojo.*;
import com.ly.service.EmpLogService;
import com.ly.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class EmpServiceImpl implements EmpService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;
//    ====================//原始方法实现分页查询=======================
//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
//        //1.调用mapper查询总记录数
//        long total = empMapper.count();
//        //2.调用mapper查询结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//        //3.封装PageResult并返回
//        return new PageResult<Emp>(total, rows);
//    }
//    //========================插件实现分页查询=========================
//        @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize,String name,
//                                Integer gender,
//                                LocalDate begin,
//                                LocalDate end) {
////    1.设置分页参数
//            PageHelper.startPage(page, pageSize);
////    2.执行查询
//            List<Emp> empList = empMapper.list(name,gender,begin,end);
////    3.解析查询结果，并封装
//            Page<Emp> p = (Page<Emp>) empList;
//            return new PageResult<Emp>(p.getTotal(), p.getResult());
//    }
    //=========================分页查询优化==================================
@Override
public PageResult<Emp> page(EmpQueryParam empQueryParam) {
//    1.设置分页参数
    PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
//    2.执行查询
    List<Emp> empList = empMapper.list(empQueryParam);
//    3.解析查询结果，并封装
    Page<Emp> p = (Page<Emp>) empList;
    return new PageResult<Emp>(p.getTotal(), p.getResult());
}
@Transactional(rollbackFor = {Exception.class})//事务管理--默认出现运行时异常RuntimeException才会回滚

    @Override
    public void save(Emp emp) throws Exception {
    try {
        //1.补全基本属性
        emp.setCreateTime(LocalDateTime.now());
        emp.setUpdateTime(LocalDateTime.now());
        //2.保存员工基本信息
        empMapper.insert(emp);

int i=1/0;

        //3.保存员工工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合为empId赋值
            exprList.forEach(empExpr -> {
                empExpr.setEmpId(emp.getId());
            });
        empExprMapper.insertBatch(exprList);
    }
    } finally {//需要zai新开启一个事务，与当前事务分离
        //记录操作日志
        EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工"+emp);
        empLogService.insertLog(empLog);
    }

}
//    ==========================批量删除==============================
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //1.删除员工基本信息
        empMapper.deleteByIds(ids);
        //2.删除员工工作经历信息
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }
    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //1.根据ID修改员工基本信息
        emp.setCreateTime(LocalDateTime.now());
        empMapper.updateById(emp);
        //2.修改员工工作经历信息
        //2.1先删除原有的工作经历信息
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2.2再添加新的工作经历信息
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //遍历集合为empId赋值
            exprList.forEach(empExpr ->empExpr.setEmpId(emp.getId()));
            empExprMapper.insertBatch(exprList);
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public LoginInfo login(Emp emp) {
        //1.调用mapper接口
        Emp e =empMapper.selectByUsernameAndPassword(emp);
        //2.判断是否存在这个员工，如果存在组装成功信息，不存在返回null
        if(e != null){
            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),"");
        }
        return null;
    }
}
