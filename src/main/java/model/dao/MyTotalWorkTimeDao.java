package model.dao;

import model.dto.MyTotalWorkTimeDto;
import util.JDBCUtil;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyTotalWorkTimeDao {
    private final String TABLE_NAME = "MYTOTAL_WORKTIME";
    private final String ID = "id";
    private final String PARTTIMER_WORKPLACE_ID = "parttimer_workplace_id";
    private final String TOTAL_WORK_TIME_OF_MONTH = "total_work_time_of_month";
    private final String WORK_DATE_OF_MONTH = "work_date_of_month";
    private final String SALARY = "salary";
    private final String CREATED_AT = "created_at";
    private final String UPDATED_AT = "updated_at";

    private final JDBCUtil JDBC_UTIL;

    private final String insertQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String updateQuery = "UPDATE " + TABLE_NAME + " SET " + TOTAL_WORK_TIME_OF_MONTH + "=?, " + SALARY + "=NVL(" + SALARY + ", 0) + ?" +
            "WHERE " + WORK_DATE_OF_MONTH + "=?";
    private final String findQuery = "SELECT * FROM " + TABLE_NAME + " WHERE " + WORK_DATE_OF_MONTH + "=?";

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
        if (findMyTotalWorkTImeByDate((Date) myTotalWorkTimeDto.getWorkDateOfMonth()) != null) {
            return true;
        }

        return false;
    }

    private int insert(MyTotalWorkTimeDto myTotalWorkTimeDto) {
        Object[] params = new Object[] {
                myTotalWorkTimeDto.getId(), myTotalWorkTimeDto.getPartTimerWorkplaceId(),
                myTotalWorkTimeDto.getTotalWorkTimeOfMonth(), myTotalWorkTimeDto.getWorkDateOfMonth(),
                myTotalWorkTimeDto.getSalary(), myTotalWorkTimeDto.getCreatedAt(), myTotalWorkTimeDto.getUpdatedAt()
        };

        return executeInsertOrUpdateQuery(insertQuery, params);
    }

    private int update(MyTotalWorkTimeDto myTotalWorkTimeDto) {
        Object[] params = new Object[] { myTotalWorkTimeDto.getTotalWorkTimeOfMonth(), myTotalWorkTimeDto.getSalary(), myTotalWorkTimeDto.getWorkDateOfMonth() };

        return executeInsertOrUpdateQuery(updateQuery, params);
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

    public MyTotalWorkTimeDto findMyTotalWorkTImeByDate(Date date) {
        Object[] params = new Object[] { date };

        return executeSelectQuery(params);
    }

    private MyTotalWorkTimeDto executeSelectQuery(Object[] params) {
        try {
            JDBC_UTIL.setSqlAndParameters(findQuery, params);
            ResultSet resultSet = JDBC_UTIL.executeQuery();

            if (resultSet.next()) {
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

        return null;
    }

}
