package model.dao;

import model.dto.MyTotalWorkTimeDto;
import util.JDBCUtil;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MyTotalWorkTimeDao {
    private final String TABLE_NAME = "MYTOTAL_WORKTIME";
    private final String ID = "id";
    private final String PARTTIMER_WORKPLACE_ID = "parttimer_workplace_id";
    private final String TOTAL_WORK_HOUR_OF_MONTH = "total_work_hour_of_month";
    private final String TOTAL_WORK_MINUTE_OF_MONTH = "total_work_minute_of_month";
    private final String WORK_DATE_OF_MONTH = "work_date_of_month";
    private final String SALARY = "salary";
    private final String CREATED_AT = "created_at";
    private final String UPDATED_AT = "updated_at";
    private final String ID_SEQUENCE = TABLE_NAME + "_seq.nextval";

    private final JDBCUtil JDBC_UTIL;

    private final String INSERT_QUERY = "INSERT INTO " + TABLE_NAME + " VALUES (" + ID_SEQUENCE + ", ?, ?, ?, ?, ?, ?, ?)";
    private final String UPDATE_QUERY = "UPDATE " + TABLE_NAME + " SET " + TOTAL_WORK_HOUR_OF_MONTH + "=NVL( " + TOTAL_WORK_HOUR_OF_MONTH + ", 0) + ?, "
            + TOTAL_WORK_MINUTE_OF_MONTH + "=NVL( " + TOTAL_WORK_MINUTE_OF_MONTH + ", 0) + ?, "
            + SALARY + "=NVL(" + SALARY + ", 0) + ?, " + UPDATED_AT + "=?" +
            " WHERE " + WORK_DATE_OF_MONTH + "=? AND " + PARTTIMER_WORKPLACE_ID + "=?";
    private final String FIND_QUERY = "SELECT * FROM " + TABLE_NAME + " WHERE " + WORK_DATE_OF_MONTH + "=? AND " + PARTTIMER_WORKPLACE_ID + "=?";

    public MyTotalWorkTimeDao() {
        JDBC_UTIL = new JDBCUtil();
    }

    public int insertOrUpdate(MyTotalWorkTimeDto myTotalWorkTimeDto) throws SQLException {
        if (isNotMyTotalWorkTimeNull(myTotalWorkTimeDto)) {
            return update(myTotalWorkTimeDto);
        }

        return insert(myTotalWorkTimeDto);
    }

    private boolean isNotMyTotalWorkTimeNull(MyTotalWorkTimeDto myTotalWorkTimeDto) {
        if (findMyTotalWorkTimeByDateAndPartTimerWorkplaceId(myTotalWorkTimeDto.getWorkDateOfMonth(), myTotalWorkTimeDto.getPartTimerWorkplaceId()) != null) {
            return true;
        }

        return false;
    }

    public int insert(MyTotalWorkTimeDto myTotalWorkTimeDto) {
        Object[] params = new Object[] {
                myTotalWorkTimeDto.getPartTimerWorkplaceId(),
                myTotalWorkTimeDto.getTotalWorkHourOfMonth(), myTotalWorkTimeDto.getWorkDateOfMonth(), myTotalWorkTimeDto.getSalary(),
                myTotalWorkTimeDto.getCreatedAt(), myTotalWorkTimeDto.getUpdatedAt(), myTotalWorkTimeDto.getTotalWorkMinuteOfMonth()
        };

        return executeInsertOrUpdateQuery(INSERT_QUERY, params);
    }

    private int update(MyTotalWorkTimeDto myTotalWorkTimeDto) {
        Object[] params = new Object[] { myTotalWorkTimeDto.getTotalWorkHourOfMonth(), myTotalWorkTimeDto.getTotalWorkMinuteOfMonth(),
                myTotalWorkTimeDto.getSalary(), new Timestamp(System.currentTimeMillis()),
                myTotalWorkTimeDto.getWorkDateOfMonth(), myTotalWorkTimeDto.getPartTimerWorkplaceId() };

        return executeInsertOrUpdateQuery(UPDATE_QUERY, params);
    }

    private int executeInsertOrUpdateQuery(String query, Object[] params) {
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

        return 0;
    }

    public MyTotalWorkTimeDto findMyTotalWorkTimeByDateAndPartTimerWorkplaceId(String month, int partTimerWorkplaceId) {
        Object[] params = new Object[] { month, partTimerWorkplaceId };

        return executeSelectQuery(params);
    }

    private MyTotalWorkTimeDto executeSelectQuery(Object[] params) {
        try {
            JDBC_UTIL.setSqlAndParameters(FIND_QUERY, params);
            ResultSet resultSet = JDBC_UTIL.executeQuery();

            if (resultSet.next()) {
                MyTotalWorkTimeDto myTotalWorkTimeDto = new MyTotalWorkTimeDto (
                        resultSet.getInt(ID), resultSet.getInt(PARTTIMER_WORKPLACE_ID), resultSet.getInt(TOTAL_WORK_HOUR_OF_MONTH), resultSet.getInt(TOTAL_WORK_MINUTE_OF_MONTH),
                        resultSet.getString(WORK_DATE_OF_MONTH), resultSet.getInt(SALARY), resultSet.getTimestamp(CREATED_AT), resultSet.getTimestamp(UPDATED_AT)
                );

                return myTotalWorkTimeDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_UTIL.close();
        }

        return null;
    }

}