package com.example.restfirst.service.impl;

import com.example.restfirst.model.TimeTable;
import com.example.restfirst.repo.TimeTableRepo;
import com.example.restfirst.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimeTableServiceImpl implements TimeTableService {

    private final TimeTableRepo timeTableRepo;

    @Autowired
    public TimeTableServiceImpl(TimeTableRepo timeTableRepo) {
        this.timeTableRepo = timeTableRepo;
    }

    @Override
    public List<List<String>> getGroupTimeTable(Integer number) {
        List<TimeTable> list1 = timeTableRepo.findAllByGroupUniGroupNumber(number);
        List<List<String>> lists =new ArrayList<>();
        int k=0;
        for (int i = 0; i < 5; i++) {
            List<String> subjectList = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                if(list1.get(k).getSubject()==null)
                    subjectList.add("нет занятий");
                else
                subjectList.add(list1.get(k).getSubject().getSubjectName());
                k++;
            }
            lists.add(subjectList);
        }
        return lists;
    }
}
