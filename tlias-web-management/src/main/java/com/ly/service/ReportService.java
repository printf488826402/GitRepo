package com.ly.service;

import com.ly.pojo.JobOption;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ReportService {

    JobOption getEmpJobData();

    List<Map<String, Object>> getEmpGenderData();
}
