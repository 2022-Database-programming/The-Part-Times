package test;

import model.dao.MyTodayWorkTimeDao;
import model.dao.MyTotalWorkTimeDao;
import model.dto.MyTodayWorkTimeDto;
import model.dto.MyTotalWorkTimeDto;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Scanner;

public class MyTodayWorkTimeTest {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        MyTotalWorkTimeDao myTotalWorkTimeDao = new MyTotalWorkTimeDao();

        // 오늘 일한 날짜로 외래키 검색
        MyTotalWorkTimeDto myTotalWorkTimeDto = myTotalWorkTimeDao.findMyTotalWorkTImeByDate(Date.valueOf("2022-11-21"));
        MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
                (int) (Math.random() * 100), myTotalWorkTimeDto,
                new Time(13, 0, 0), new Time(16, 0, 0),
                new Time(15, 0, 0), new Time(15, 30, 0),
                new Time(2, 30, 0), new Time(0, 30, 0),
                Date.valueOf("2022-11-30"), 9160, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()
        ));

        MyTodayWorkTimeDao myTodayWorkTimeDao = new MyTodayWorkTimeDao();
        int result = myTodayWorkTimeDao.insert(myTodayWorkTimeDto, 1);   // parttimer workplace 검색하는 로직 추가 필요
        System.out.println(result);

    }
}
