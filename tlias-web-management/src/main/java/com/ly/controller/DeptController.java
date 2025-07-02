package com.ly.controller;
/*
1.接受请求
2.调用Service层方法
3.响应结果
*/

import com.ly.pojo.Dept;
import com.ly.pojo.Result;
import com.ly.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j

@RequestMapping("/depts")
@RestController//相当于加了两个注解@Controller,@ResponseBody将封装的对象和集合转为json
public class DeptController {

    @Autowired
    private DeptService deptService;

//方法一    //@RequestMapping("/depts")//这里并没有限定请求方式，apifox任何请求类型都会响应成功
//方法二    @RequestMapping(value = "/depts",method = RequestMethod.GET)//RequestMethod为枚举类型，包含GET,HEAD,POST,PUT,PATCH,DELETE,OPTIONS,TRACE;
    @GetMapping//方法三
    public Result list(){

//        System.out.println("查询全部部门数据");
        log.info("查询全部部门数据");

        List<Dept> deptList= deptService.findAll();
        return Result.success(deptList);
    }
    /*
Spring MVC 是通过 注解（如 @GetMapping, @PostMapping）来映射请求路径 的。
方法名在 Spring MVC 中只是 Java 方法标识符，对 HTTP 请求的路由没有任何影响。
所以你可以将方法名命名为：list(),getAll(),getDeptList(),queryDepts(),等等……
只要保证：注解路径不变（即 @GetMapping("/depts")）返回值类型一致（如 Result）方法逻辑不变就不会影响程序运行。
*/




    //方式一：基于HttpServletRequest手动类型转换
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr=request.getParameter("id");
//        int id =Integer.parseInt(idStr);
//        System.out.println("根据id删除部门"+id);
//        return Result.success();
//    }


//    //方式二：通过RequestParam注解，将请求参数绑定给方法形参，该参数请求时必须传递,默认为true
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam(value = "id",required=false) Integer deptId){
//        System.out.println("根据id删除部门"+deptId);
//        return Result.success();
//    }


    @DeleteMapping //前端传参与服务器方法形参一致
    public Result delete(Integer id){

//      System.out.println("根据id删除部门"+id);
        log.info("根据id删除部门:{}",id);

        deptService.deleteById(id);
        return Result.success();
    }

    @PostMapping
    public Result add(@RequestBody Dept dept){
//        System.out.println("新增部门"+dept);
        log.info("新增部门:{}",dept);

        deptService.add(dept);
        return  Result.success();
    }
    //根据id查询部门
//    @GetMapping("/depts/{id}")
//    public Result getInfo(@PathVariable("id") Integer deptId){
//        System.out.println("根据id查询部门："+deptId);
//        return Result.success();
//    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
//        System.out.println("根据id查询部门："+id);
        log.info("根据id查询部门{}",id);
        Dept dept=deptService.getById(id);
        return Result.success(dept);
    }
    @PutMapping
    public Result update(@RequestBody  Dept dept){
//        System.out.println("修改部门"+dept);
        log.info("修改部门{}",dept);
        deptService.update(dept);
        return Result.success();
    }
}

