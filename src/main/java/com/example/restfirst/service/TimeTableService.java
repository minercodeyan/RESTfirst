package com.example.restfirst.service;
import com.example.restfirst.dto.SubjectDto;

import java.util.List;
import java.util.Map;


public interface TimeTableService {

     Map<Integer, List<SubjectDto>> getGroupTimeTable(Integer number);
}
