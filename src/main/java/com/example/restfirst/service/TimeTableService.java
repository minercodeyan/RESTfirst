package com.example.restfirst.service;
import java.util.List;

public interface TimeTableService {

    List<List<String>> getGroupTimeTable(Integer number);
}
