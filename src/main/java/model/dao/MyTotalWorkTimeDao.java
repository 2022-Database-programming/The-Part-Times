package model.dao;

import model.dto.MyTotalWorkTimeDto;
import util.JDBCUtil;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class MyTotalWorkTimeDao {
    private final String TABLE_NAME = "MYTOTAL_WORKTIME";
    private final String ID = "id";
    private final String PARTTIMER_WORKPLACE_ID = "parttimer_workplace_id";
    private final String TOTAL_WORK_TIME_OF_MONTH = "total_work_time_of_month";
    private final String WORK_DATE_OF_MONTH = "work_date_of_month";
    private final String SALARY = "salary";
    private final String CREATED_AT = "created_at";
    private final String UPDATED_AT = "updated_at";

    private final String ID_SEQUENCE = TABLE_NAME + "_seq.nextval";

    private final JDBCUtil JDBC_UTIL;

    private final String insertQuery = "INSERT INTO " + TABLE_NAME + " VALUES (" + ID_SEQUENCE + ", ?, ?, ?, ?, ?, ?)";
    private final String updateQuery = "UPDATE " + TABLE_NAME + " SET " + TOTAL_WORK_TIME_OF_MONTH + "=?, " + SALARY + "=NVL(" + SALARY + ", 0) + ?, " + UPDATED_AT + "=?" +
            " WHERE " + WORK_DATE_OF_MONTH + "=? AND " + PARTTIMER_WORKPLACE_ID + "=?";
    private final String findQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + WORK_DATE_OF_MONTH + "=? AND " + PARTTIMER_WORKPLACE_ID + "=?";

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
            System.out.println("not null");
            return true;
        }

        return false;
    }

    public int insert(MyTotalWorkTimeDto myTotalWorkTimeDto) {
        System.out.println("insert");
        Object[] params = new Object[] {
                myTotalWorkTimeDto.getPartTimerWorkplaceId(),
                myTotalWorkTimeDto.getTotalWorkTimeOfMonth(), myTotalWorkTimeDto.getWorkDateOfMonth(),
                myTotalWorkTimeDto.getSalary(), myTotalWorkTimeDto.getCreatedAt(), myTotalWorkTimeDto.getUpdatedAt()
        };

        return executeInsertOrUpdateQuery(insertQuery, params);
    }

    private int update(MyTotalWorkTimeDto myTotalWorkTimeDto) {
        System.out.println("update");
        Object[] params = new Object[] { myTotalWorkTimeDto.getTotalWorkTimeOfMonth(), myTotalWorkTimeDto.getSalary(), new Timestamp(System.currentTimeMillis()), myTotalWorkTimeDto.getWorkDateOfMonth(), myTotalWorkTimeDto.getPartTimerWorkplaceId() };

        return executeInsertOrUpdateQuery(updateQuery, params);
    }

    private int executeInsertOrUpdateQuery(String query, Object[] params) {
        try {
            System.out.println("insert start");
            JDBC_UTIL.setSqlAndParameters(query, params);
            System.out.println("insert end");
            int result = JDBC_UTIL.executeUpdate();
            System.out.println(result);
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

    public MyTotalWorkTimeDto findMyTotalWorkTimeByDateAndPartTimerWorkplaceId(Date today, int partTimerWorkplaceId) {
        Object[] params = new Object[] { today, partTimerWorkplaceId };

        return executeSelectQuery(params);
    }

    private MyTotalWorkTimeDto executeSelectQuery(Object[] params) {
        try {
            JDBC_UTIL.setSqlAndParameters(findQuery, params);
            ResultSet resultSet = JDBC_UTIL.executeQuery();

            if (resultSet.next()) {
                System.out.println("값이 있지롱");
                MyTotalWorkTimeDto myTotalWorkTimeDto = new MyTotalWorkTimeDto (
                        resultSet.getInt(ID), resultSet.getInt(PARTTIMER_WORKPLACE_ID), resultSet.getTime(TOTAL_WORK_TIME_OF_MONTH),
                        resultSet.getDate(WORK_DATE_OF_MONTH), resultSet.getInt(SALARY), resultSet.getTimestamp(CREATED_AT), resultSet.getTimestamp(UPDATED_AT)
                );

                return myTotalWorkTimeDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_UTIL.close();
        }

        System.out.println("값이 없지롱");
        return null;
    }

}