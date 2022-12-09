package model.service;

import model.dao.MyTodayWorkTimeDao;
import model.dao.PartTimerWorkplaceDao;
import model.dto.MyTodayWorkTimeDto;
import model.dto.MyTotalWorkTimeDto;
import model.dao.MyTotalWorkTimeDao;
import model.dao.MemberDao;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public class WorkTimeManager {
    private static WorkTimeManager workTimeManager = new WorkTimeManager();
    private MyTodayWorkTimeDao myTodayWorkTimeDao;
    private MyTotalWorkTimeDao myTotalWorkTimeDao = new MyTotalWorkTimeDao();
    private MemberDao memberDao = new MemberDao();
    private PartTimerWorkplaceDao partTimerWorkplaceDao = new PartTimerWorkplaceDao();

    private WorkTimeManager() {
        try {
            myTodayWorkTimeDao = new MyTodayWorkTimeDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WorkTimeManager getInstance() {
        return workTimeManager;
    }

    public int create(MyTodayWorkTimeDto myTodayWorkTimeDto, MyTotalWorkTimeDto myTotalWorkTimeDto, int partTimerWorkplaceId) throws SQLException {
        return myTodayWorkTimeDao.insert(myTodayWorkTimeDto, myTotalWorkTimeDto, partTimerWorkplaceId);
    }

    public MyTotalWorkTimeDto findMyWorkTimeByDateAndTotalWorkTimeId(Date today, int partTimerWorkplaceId) {
        // 데이터 트래킹 필드
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

        // 오늘 일한 날짜와 근무지를 가지고 월별 총 근무 기록 조회
        MyTotalWorkTimeDto myTotalWorkTimeDto = new MyTotalWorkTimeDao().findMyTotalWorkTImeByDateAndWorkplace(today, partTimerWorkplaceId);
        // 없으면 하나 만들어준다. 0시간 일했으며 급여는 0원
        if (myTotalWorkTimeDto == null) {
            myTotalWorkTimeDto = new MyTotalWorkTimeDto(partTimerWorkplaceId, new Time(0, 0, 0),
                    today, 0, createdAt, updatedAt);
            myTotalWorkTimeDao.insert(myTotalWorkTimeDto);
        }

        // insert한 값으로 다시 한 번 찾아준다. id 값을 받아오기 위함
        // TODO: 향후 메소드 분리 예정
        myTotalWorkTimeDto = myTotalWorkTimeDao.findMyWorkTimeByDateAndTotalWorkTimeId(today, partTimerWorkplaceId);

        return myTotalWorkTimeDto;
    }

}
