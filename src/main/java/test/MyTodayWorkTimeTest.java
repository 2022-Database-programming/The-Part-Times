package test;


import model.dao.*;
import model.dto.*;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

// 해당 클래스를 frontend 로 가정한다.
public class MyTodayWorkTimeTest {
    public static void main(String[] args) throws SQLException {
        MyTodayWorkTimeDao myTodayWorkTimeDao = new MyTodayWorkTimeDao();
        MyTotalWorkTimeDao myTotalWorkTimeDao = new MyTotalWorkTimeDao();
        PartTimerWorkplaceDao partTimerWorkplaceDao = new PartTimerWorkplaceDao();
        MemberDao memberDao = new MemberDao();

        // Parttimer workplace 리스트를 가져오기 위해서는 멤버 아이디가 필요하다.
        // userId를 가져온다.
        String userId = "abcdef";
        MemberDto memberDto = memberDao.findMember(userId);

        // 사용자의 workplace를 죄다 가져온다.
        List<PartTimerWorkplaceDto> workplaces = partTimerWorkplaceDao.findAllWorkplace(memberDto.getId());

        // 임시로 오늘 일한 아이디가 999라고 가정
        int partTimerWorkplaceId = 999;

        // 오늘 일한 날짜
        Date workDate = Date.valueOf(LocalDate.now());

        // 데이터 트래킹 필드
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

        // 찾은 근무지 저장 테이블을 기반으로 이번달 토탈 워크타임 데이터를 찾는다. (외래키 찾기)
        Date today = Date.valueOf(LocalDate.now());
        MyTotalWorkTimeDto myTotalWorkTimeDto = new MyTotalWorkTimeDao().findMyTotalWorkTImeByDateAndWorkplace(today, partTimerWorkplaceId);

        // 없으면 하나 만들어준다.
        if (myTotalWorkTimeDto == null) {
            myTotalWorkTimeDto = new MyTotalWorkTimeDto(partTimerWorkplaceId, new Time(0, 0, 0),
                   workDate, 0, createdAt, updatedAt);
            myTotalWorkTimeDao.insert(myTotalWorkTimeDto);
        }

        // totalWorkTime의 id까지 가져오기 위해 객체를 모두 찾아준다.
        MyTotalWorkTimeDto myTotalWorkTimeDtoWithId = myTotalWorkTimeDao.findMyTotalWorkTImeByDateAndWorkplace(today, partTimerWorkplaceId);

        // 오늘 일한 시간
        TimeSettingDto timeSettingDto = new TimeSettingDto(13, 0, 16, 0, 15, 0, 15, 30);

        // 최저 시급
        int minimumWage = 9160;

        System.out.println(myTotalWorkTimeDto.getId());

        MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
                myTotalWorkTimeDto.getId(), timeSettingDto.getWorkStartTime(), timeSettingDto.getWorkFinishTime(), timeSettingDto.getBreakStartTime(), timeSettingDto.getBreakFinishTime(),
                timeSettingDto.getTotalWorkTime(), timeSettingDto.getTotalBreakTime(), today, minimumWage, createdAt, updatedAt
        );

        int result = myTodayWorkTimeDao.insert(myTodayWorkTimeDto, myTotalWorkTimeDtoWithId, partTimerWorkplaceId);
        System.out.println(result);
    }
}