package model.dao;

import model.dto.MyTodayWorkTimeDto;
import model.dto.MyTotalWorkTimeDto;
import util.JDBCUtil;
import java.sql.*;

public class MyTodayWorkTimeDao {
    private final String TABLE_NAME = "MYTODAYWORKTIME";
    private final String ID = "id";
    private final String MYTOTAL_WORKTIME_ID = "mytotal_worktime_id";
    private final String WORK_START_TIME = "work_start_time";
    private final String WORK_FINISH_TIME = "work_finish_time";
    private final String BREAK_START_TIME = "break_start_time";
    private final String BREAK_FINISH_TIME = "break_finish_time";
    private final String TOTAL_WORK_TIME_OF_DAY = "total_work_time_of_day";
    private final String TOTAL_BREAK_TIME_OF_DAY = "total_break_time_of_day";
    private final String MINIMUM_WAGE = "minimum_wage";
    private final String WORK_DATE = "work_date";
    private final String CREATED_AT = "created_at";
    private final String UPDATED_AT = "updated_at";
    private final String ID_SEQUENCE = TABLE_NAME + "_seq.nextval";

    private final String insertQuery = "INSERT INTO " + TABLE_NAME + " VALUES (" + ID_SEQUENCE + ", ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final String deleteQuery = "DELETE FROM " + TABLE_NAME + " WHERE " + ID + "=?";
    private final String findQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + WORK_DATE + "=? AND " + MYTOTAL_WORKTIME_ID + "=?";

    private final JDBCUtil JDBC_UTIL;
    private final MyTotalWorkTimeDao MY_TOTAL_WORKTIME_DAO;

    public MyTodayWorkTimeDao() {
        JDBC_UTIL = new JDBCUtil();
        MY_TOTAL_WORKTIME_DAO = new MyTotalWorkTimeDao();
    }

    // 오늘 근무한 시간 저장
    public int insert(MyTodayWorkTimeDto myTodayWorkTimeDto, MyTotalWorkTimeDto myTotalWorkTimeDto, int partTimerWorkPlaceId) throws SQLException {
        saveMyTotalWorkTime(myTodayWorkTimeDto, myTotalWorkTimeDto);
        System.out.println(myTotalWorkTimeDto.getId());

        Object[] params = new Object[] {
                myTotalWorkTimeDto.getId(),
                myTodayWorkTimeDto.getWorkStartTime(), myTodayWorkTimeDto.getWorkFinishTime(),
                myTodayWorkTimeDto.getBreakStartTime(), myTodayWorkTimeDto.getBreakFinishTime(),
                myTodayWorkTimeDto.getTotalWorkTimeOfDay(), myTodayWorkTimeDto.getTotalBreakTimeOfDay(), myTodayWorkTimeDto.getMinimumWage(),
                myTodayWorkTimeDto.getWorkDate(), myTodayWorkTimeDto.getCreatedAt(), myTodayWorkTimeDto.getUpdatedAt()
        };

        return executeInsertOrDelete(insertQuery, params);
    }

    private int saveMyTotalWorkTime(MyTodayWorkTimeDto myTodayWorkTimeDto, MyTotalWorkTimeDto myTotalWorkTimeDto) throws SQLException {
        System.out.println("total work time save start...");
        String[] times = String.valueOf(myTodayWorkTimeDto.getTotalWorkTimeOfDay()).split(":");
        int salary = Integer.valueOf(times[0]) * myTodayWorkTimeDto.getMinimumWage();

        if (Integer.valueOf(times[1]) == 30) {
            salary += myTodayWorkTimeDto.getMinimumWage() / 2;
        }

        myTotalWorkTimeDto.setSalary(salary);

        return MY_TOTAL_WORKTIME_DAO.insertOrUpdate(myTotalWorkTimeDto);
    }

    // 오늘 근무 시간 삭제
    public int delete(int id) {
        Object[] params = new Object[] { id };

        return executeInsertOrDelete(deleteQuery, params);
    }

    private int executeInsertOrDelete(String query, Object[] params) {
        try {
            JDBC_UTIL.setSqlAndParameters(query, params);
            int result = JDBC_UTIL.executeUpdate();

            return result;
        } catch (Exception e) {
            JDBC_UTIL.rollback();
            e.printStackTrace();
        } finally {
            JDBC_UTIL.commit();
            JDBC_UTIL.close();
        }

        return -1;
    }

    // 오늘 날짜와 이번달 근무 시간 번호로 찾기
    public MyTodayWorkTimeDto findMyWorkTimeByDateAndTotalWorkTimeId(Date date, int partTimerWorkplaceId) {
        MyTotalWorkTimeDto myTotalWorkTimeDto = MY_TOTAL_WORKTIME_DAO.findMyTotalWorkTImeByDateAndWorkplace(date, partTimerWorkplaceId);
        Object[] params = new Object[] { date, myTotalWorkTimeDto.getId()};

        return executeSelectQuery(params);
    }

    private MyTodayWorkTimeDto executeSelectQuery(Object[] params) {
        try {
            JDBC_UTIL.setSqlAndParameters(findQuery, params);
            ResultSet resultSet = JDBC_UTIL.executeQuery();

            if (resultSet.next()) {
                MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
                        resultSet.getInt(ID), resultSet.getInt(MYTOTAL_WORKTIME_ID), resultSet.getTime(WORK_START_TIME),
                        resultSet.getTime(WORK_FINISH_TIME), resultSet.getTime(BREAK_START_TIME), resultSet.getTime(BREAK_FINISH_TIME),
                        resultSet.getTime(TOTAL_WORK_TIME_OF_DAY), resultSet.getTime(TOTAL_BREAK_TIME_OF_DAY),
                        resultSet.getDate(WORK_DATE), resultSet.getInt(MINIMUM_WAGE), resultSet.getTimestamp(CREATED_AT), resultSet.getTimestamp(UPDATED_AT)
                );

                return myTodayWorkTimeDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_UTIL.close();
        }

        return null;
    }
}
