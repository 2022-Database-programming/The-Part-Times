package test;


import model.dao.MyTodayWorkTimeDao;
import model.dao.MyTotalWorkTimeDao;
import model.dto.MyTodayWorkTimeDto;
import model.dto.MyTotalWorkTimeDto;
import model.dto.TimeSettingDto;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

// 해당 클래스를 frontend 로 가정한다.
public class MyTodayWorkTimeTest {
    public static void main(String[] args) {
        MyTotalWorkTimeDao myTotalWorkTimeDao = new MyTotalWorkTimeDao();
        MyTodayWorkTimeDao myTodayWorkTimeDao = new MyTodayWorkTimeDao();


        // PK and FK
        int id = (int) (Math.random() * 1000000000);

        // 오늘 일한 날짜로 외래키 찾기
        Date workDate = Date.valueOf(LocalDate.now());
        MyTotalWorkTimeDto myTotalWorkTimeDto = myTotalWorkTimeDao.findMyTotalWorkTImeByDate(workDate);

        // 오늘 일한 시간 및 쉬는 시간 저장
        TimeSettingDto timeSettingDto = new TimeSettingDto(13, 0, 16, 0, 15, 0, 15, 30);

        // 최저 시급
        int minimumWage = 9160;

        // 데이터 트래킹 필드
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

        // DTO에 저장
        MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
                id, myTotalWorkTimeDto, timeSettingDto.getWorkStartTime(), timeSettingDto.getWorkFinishTime(), timeSettingDto.getBreakStartTime(), timeSettingDto.getBreakFinishTime(),
                timeSettingDto.getTotalWorkTime(), timeSettingDto.getTotalBreakTime(), workDate, minimumWage, createdAt, updatedAt
        );

        // Parttimer workplace id를 가져온다.
        // workplace id를 가져오려면, 로그인 된 유저 아이디와 근무지 정보 테이블을 참고한다.

        // userId를 가져온다.


        // 이 부분 컨트롤러로 변경
//        int result = myTodayWorkTimeDao.insert(myTodayWorkTimeDto, 1);
//        System.out.println(result);
    }
}