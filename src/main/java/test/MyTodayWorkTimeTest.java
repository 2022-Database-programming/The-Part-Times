package test;

import model.dao.MyTodayWorkTimeDao;
import model.dao.MyTotalWorkTimeDao;
import model.dto.MyTodayWorkTimeDto;
import model.dto.MyTotalWorkTimeDto;
import model.dto.TimeSettingDto;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

public class MyTodayWorkTimeTest {
    public static void main(String[] args) throws SQLException {
        MyTotalWorkTimeDao myTotalWorkTimeDao = new MyTotalWorkTimeDao();
        MyTodayWorkTimeDao myTodayWorkTimeDao = new MyTodayWorkTimeDao();

        // PK and FK
        int id = (int) (Math.random() * 1000000000);
        // 오늘 일한 날짜로 외래키 찾기
        Date workDate = Date.valueOf(LocalDate.now());
        MyTotalWorkTimeDto myTotalWorkTimeDto = myTotalWorkTimeDao.findMyTotalWorkTImeByDate(workDate);

        TimeSettingDto timeSettingDto = new TimeSettingDto(13, 0, 16, 0, 15, 0, 15, 30);

        // 최저 시급
        int minimumWage = 9160;

        // 데이터 트래킹 필드
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

        MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
                id, myTotalWorkTimeDto, timeSettingDto.getWorkStartTime(), timeSettingDto.getWorkFinishTime(), timeSettingDto.getBreakStartTime(), timeSettingDto.getBreakFinishTime(),
                timeSettingDto.getTotalWorkTime(), timeSettingDto.getTotalBreakTime(), workDate, minimumWage, createdAt, updatedAt
        );

        int result = myTodayWorkTimeDao.insert(myTodayWorkTimeDto, 1);
        System.out.println(result);
    }
}
