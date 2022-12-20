package model.service;

import model.dao.MyTodayWorkTimeDao;
import model.dao.PartTimerWorkplaceDao;
import model.dao.MyTotalWorkTimeDao;
import model.dto.MyTodayWorkTimeDto;
import model.dto.MyTotalWorkTimeDto;
import model.dto.PartTimerWorkplaceDto;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WorkTimeManager {
    private static WorkTimeManager workTimeManager = new WorkTimeManager();
    private MyTodayWorkTimeDao myTodayWorkTimeDao;
    private MyTotalWorkTimeDao myTotalWorkTimeDao;
    private PartTimerWorkplaceDao partTimerWorkplaceDao;

    private WorkTimeManager() {
        try {
            this.myTodayWorkTimeDao = new MyTodayWorkTimeDao();
            this.myTotalWorkTimeDao = new MyTotalWorkTimeDao();
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

    public MyTotalWorkTimeDto findMyTotalWorkTimeByDateAndTotalWorkTimeId(Date today, int partTimerWorkplaceId) {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        Timestamp updatedAt = new Timestamp(System.currentTimeMillis());

        MyTotalWorkTimeDto myTotalWorkTimeDto = new MyTotalWorkTimeDao().findMyTotalWorkTimeByDateAndPartTimerWorkplaceId(today, partTimerWorkplaceId);

        if (myTotalWorkTimeDto == null) {
            myTotalWorkTimeDto = createMyTotalWorkTime(partTimerWorkplaceId, today, createdAt, updatedAt);
        }

        return myTotalWorkTimeDto;
    }

    private MyTotalWorkTimeDto createMyTotalWorkTime(int partTimerWorkplaceId, Date today, Timestamp createdAt, Timestamp updatedAt) {
        MyTotalWorkTimeDto myTotalWorkTimeDto = new MyTotalWorkTimeDto(partTimerWorkplaceId, 0, 0,
                today, 0, createdAt, updatedAt);
        myTotalWorkTimeDao.insert(myTotalWorkTimeDto);

        myTotalWorkTimeDto = myTotalWorkTimeDao.findMyTotalWorkTimeByDateAndPartTimerWorkplaceId(today, partTimerWorkplaceId);

        return myTotalWorkTimeDto;
    }

    public HashMap<Integer, List<MyTodayWorkTimeDto>> findAllMyTodayWorkTimeByDateAndTotalWorkTime(Date today, List<Integer> totalWorkTimeIds) {
        List<MyTodayWorkTimeDto> myTodayWorkTimes;
        HashMap<Integer, List<MyTodayWorkTimeDto>> myTodayWorkTimesMap = new HashMap<>();

        for (int i = 0; i < totalWorkTimeIds.size(); i++) {
            myTodayWorkTimes = myTodayWorkTimeDao.findMyWorkTimeByDateAndTotalWorkTimeId(today, totalWorkTimeIds.get(i));
            myTodayWorkTimesMap.put(totalWorkTimeIds.get(i), myTodayWorkTimes);
        }

        return myTodayWorkTimesMap;
    }

    public List<Integer> findAllTotalWorkTimeIdByPartTimerWorkplaceIdAndWorkDate(Date today, List<Integer> partTimerWorkplaceIds) {
        List<Integer> myTotalWorkTimeIds = new ArrayList<>();

        for (int i = 0; i < partTimerWorkplaceIds.size(); i++) {
            MyTotalWorkTimeDto myTotalWorkTimeDto = myTotalWorkTimeDao.findMyTotalWorkTimeByDateAndPartTimerWorkplaceId(today, partTimerWorkplaceIds.get(i));

            if (myTotalWorkTimeDto != null) {
                myTotalWorkTimeIds.add(myTotalWorkTimeDto.getId());
            }
        }

        return myTotalWorkTimeIds;
    }

    public List<Integer> findAllPartTimerWorkplaceIdByMemberId(int memberId) {
        List<PartTimerWorkplaceDto> partTimerWorkplaces = partTimerWorkplaceDao.findAllWorkplace(memberId);

        List<Integer> partTimerWorkplaceIds = initPartTimerWorkplaceIds(partTimerWorkplaces);

        return partTimerWorkplaceIds;
    }

    private List<Integer> initPartTimerWorkplaceIds(List<PartTimerWorkplaceDto> partTimerWorkplaces) {
        List<Integer> partTimerWorkplaceIds = new ArrayList<>();

        for (int i = 0; i < partTimerWorkplaces.size(); i++) {
            partTimerWorkplaceIds.add(partTimerWorkplaces.get(i).getId());
        }

        return partTimerWorkplaceIds;
    }

}
