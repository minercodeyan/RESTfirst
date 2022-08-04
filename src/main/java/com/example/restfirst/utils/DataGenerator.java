package com.example.restfirst.utils;
import com.example.restfirst.model.GroupUni;
import com.example.restfirst.model.Student;

import java.util.*;

public class DataGenerator {

    private static String[] fnames=new String[]{"Ян", "Август", "Августин", "Авраам","Артем"
            ,"Андрей","Роман", "Валерий" ,"Дани", "Влад","Влада","Яна","Анна","Ася","Анастасия",
            "Дина","Аврора","Алеся","Дана","Кана","Александра","Варвара"};

    private static String[] lnames=new String[]{
            "Абрамов", "Авдеев", "Агафонов", "Аксёнов", "Александров", "Алексеев", "Андреев",
            "Анисимов", "Белов", "Белозёров", "Белоусов", "Беляев", "Беляков", "Беспалов", "Бирюков", "Блинов",
            "Блохин", "Бобров", "Бобылёв", "Богданов", "Большаков", "Борисов", "Владимиров", "Власов", "Волков", "Воробьёв", "Воронов", "Воронцов", "Гаврилов",
            "Галкин", "Герасимов", "Голубев", "Горбачёв", "Гусев", "Гущин", "Давыдов", "Данилов", "Дементьев",
            "Денисов", "Дмитриев", "Доронин", "Дорофеев", "Дроздов", "Дьячков", "Евдокимов", "Егоров", "Елисеев", "Емельянов",
            "Зимин", "Зиновьев", "Зуев", "Иванков", "Иванов", "Игнатов", "Казаков", "Калашников", "Калинин", "Карпов", "Кириллов", "Киселёв"
    };
    private static String[] patronomics = new String[20];


    public static List<Student> generateRandomStudents(int num){
        for (int i = 0; i < 20; i++) {
            if(i<10){
                patronomics[i]=fnames[i]+"ов";
            }
            else {
                patronomics[i]=fnames[i]+"овна";
            }
        }
        List<Student> sList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Student student = new Student();
            student.setPatronymic(patronomics[(int) (Math.random() * 20)]);
            student.setGroupUni(new GroupUni(1L));
            Calendar calendar = new GregorianCalendar(2000 + (int) (Math.random() * 3), (int) (Math.random() * 12), (int) (Math.random() * 20));
            Date date = calendar.getTime();
            student.setDateOfBirth(date);
            System.out.println(student.getPatronymic());
            if(!student.getPatronymic().contains("овна")){
                student.setName(fnames[(int) (Math.random() * 10)]);
                student.setSurname(lnames[(int) (Math.random() * 60)]);
                student.setSex("МУЖ");
            }
            else {
                student.setName(fnames[10+(int) (Math.random() * 10)]);
                student.setSurname(lnames[(int) (Math.random() * 60)]+"a");
                student.setSex("ЖЕН");
            }
            sList.add(student);

        }
        return sList;
    }
}
