package com.ly.service;

import com.ly.pojo.Emp;
import com.ly.pojo.EmpQueryParam;
import com.ly.pojo.LoginInfo;
import com.ly.pojo.PageResult;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Component
public interface EmpService {
    //分页查询优化
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp) throws Exception;

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    void update(Emp emp);

    LoginInfo login(Emp emp);
    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页显示的记录数
     */
//    PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender, LocalDate begin, LocalDate end);

}
