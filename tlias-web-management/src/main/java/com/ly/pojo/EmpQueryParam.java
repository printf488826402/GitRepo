package com.ly.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class EmpQueryParam {
    private Integer page=1;
    private Integer pageSize=10;
    private String name;
    private Short gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate begin;
    private LocalDate end;
}
