package com.example.restfirst.service.impl;

import com.example.restfirst.dto.SubjectDto;
import com.example.restfirst.exceptions.NotFoundException;
import com.example.restfirst.model.TimeTable;
import com.example.restfirst.repo.GroupRepo;
import com.example.restfirst.repo.TimeTableRepo;
import com.example.restfirst.service.TimeTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimeTableServiceImpl implements TimeTableService {
    private final TimeTableRepo timeTableRepo;
    private final GroupRepo groupRepo;

    @Autowired
    public TimeTableServiceImpl(TimeTableRepo timeTableRepo, GroupRepo groupRepo) {
        this.timeTableRepo = timeTableRepo;
        this.groupRepo = groupRepo;
    }

    @Override
    public Map<Integer, List<SubjectDto>> getGroupTimeTable(Integer number) {
        if(groupRepo.findByGroupNumber(number).isPresent()) {
            List<TimeTable> list1 = timeTableRepo.findAllByGroupUniGroupNumber(number);
            Map<Integer, List<SubjectDto>> timeTable = new HashMap<>();
            int k = 0;
            for (int i = 0; i < 5; i++) {
                List<SubjectDto> subjectList = new ArrayList<>();
                for (int j = 0; j < 5; j++) {
                    subjectList.add(new SubjectDto(list1.get(k).getSubject()));
                    k++;
                }
                timeTable.put(i, subjectList);
            }
            for (int i = 0; i < 5; i++) {
                for (int j = i + 1; j < 5; j++) {
                    SubjectDto temp = timeTable.get(i).get(j);
                    timeTable.get(i).set(j, timeTable.get(j).get(i));
                    timeTable.get(j).set(i, temp);
                }
            }
            return timeTable;
        }else {
            throw new NotFoundException("group");
        }
    }
}
