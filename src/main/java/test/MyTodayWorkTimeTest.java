package test;

import model.dao.MyTodayWorkTimeDao;
import model.dto.MyTodayWorkTimeDto;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Scanner;

public class MyTodayWorkTimeTest {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);

        MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
        2, 1, new Time(1, 0, 0), new Time(1, 30, 0), new Time(0, 10, 0), new Time(0, 0, 0),
                new Time(2, 0, 1), new Time(0, 0, 0), Date.valueOf("2022-01-01"), new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())
        );

        MyTodayWorkTimeDao myTodayWorkTimeDao = new MyTodayWorkTimeDao();
//        int result = myTodayWorkTimeDao.insert(myTodayWorkTimeDto);
//        System.out.println(result);

//        myTodayWorkTimeDao.delete(2);

        MyTodayWorkTimeDto myTodayWorkTimeDto2 = myTodayWorkTimeDao.findMyWorkTimeByDate(Date.valueOf("2022-01-01"));
        System.out.println(myTodayWorkTimeDto2);
    }
}
