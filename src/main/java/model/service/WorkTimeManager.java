package model.service;

import model.dao.MyTodayWorkTimeDao;
import model.dao.PartTimerWorkplaceDao;
import model.dao.MyTotalWorkTimeDao;
import model.dao.MemberDao;
import model.dto.MyTodayWorkTimeDto;
import model.dto.MyTotalWorkTimeDto;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public class WorkTimeManager {
    private static WorkTimeManager workTimeManager = new WorkTimeManager();
    private MyTodayWorkTimeDao myTodayWorkTimeDao;
    private MyTotalWorkTimeDao myTotalWorkTimeDao;
    private MemberDao memberDao;
    private PartTimerWorkplaceDao partTimerWorkplaceDao;
    private String memberId;

    private WorkTimeManager() {
        try {
            this.myTodayWorkTimeDao = new MyTodayWorkTimeDao();
            this.myTotalWorkTimeDao = new MyTotalWorkTimeDao();
            this.memberDao = new MemberDao();
            this.partTimerWorkplaceDao = new PartTimerWorkplaceDao();
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

    public void setMemberId(String memberId) {
        this.memberId = memberId;

    }

    public MyTotalWorkTimeDto findMyWorkTimeByDateAndPartTimerWorkplaceId(Date today, int partTimerWorkplaceId) {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

        MyTotalWorkTimeDto myTotalWorkTimeDto = new MyTotalWorkTimeDao().findMyTotalWorkTImeByDateAndPartTimerWorkplaceId(today, partTimerWorkplaceId);
        if (myTotalWorkTimeDto == null) {
            myTotalWorkTimeDto = new MyTotalWorkTimeDto(partTimerWorkplaceId, new Time(0, 0, 0),
                    today, 0, createdAt, updatedAt);
            myTotalWorkTimeDao.insert(myTotalWorkTimeDto);
            myTotalWorkTimeDto = myTotalWorkTimeDao.findMyTotalWorkTImeByDateAndPartTimerWorkplaceId(today, partTimerWorkplaceId);

        }

        return myTotalWorkTimeDto;
    }

}
