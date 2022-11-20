package model.dao;

import model.dto.MyTodayWorkTimeDto;
import model.dto.MyTotalWorkTimeDto;
import util.JDBCUtil;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyTotalWorkTimeDao {
    private final String TABLE_NAME = "MYTOTAL_WORKTIME";
    private JDBCUtil jdbcUtil = null;

    public MyTotalWorkTimeDao() {
        jdbcUtil = new JDBCUtil();
    }

    public void insertOrUpdate(MyTotalWorkTimeDto myTotalWorkTimeDto) throws SQLException {
        if (findMyTotalWorkTImeByDate((Date) myTotalWorkTimeDto.getWorkDateOfMonth())) {
            int result = update(myTotalWorkTimeDto);
        } else {
            int result = insert(myTotalWorkTimeDto);
        }
    }

    public int insert(MyTotalWorkTimeDto myTotalWorkTimeDto) throws SQLException {
        System.out.println("insert start");
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        Object[] params = new Object[] {
                myTotalWorkTimeDto.getId(), myTotalWorkTimeDto.getPartTimerWorkplaceId(),
                myTotalWorkTimeDto.getTotalWorkTimeOfMonth(), myTotalWorkTimeDto.getWorkDateOfMonth(),
                myTotalWorkTimeDto.getSalary(), myTotalWorkTimeDto.getCreatedAt(), myTotalWorkTimeDto.getUpdatedAt()
        };

        jdbcUtil.setSqlAndParameters(sqlQuery, params);

        try {
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }

        return 0;
    }

    public int update(MyTotalWorkTimeDto myTotalWorkTimeDto) throws SQLException{
        System.out.println("update start");
        String sqlQuery = "UPDATE " + TABLE_NAME
                + " SET total_work_time_of_month=?, salary=salary + ?" +
                "WHERE work_date_of_month=?";

        try {
            jdbcUtil.setSqlAndParameters(sqlQuery, new Object[] {myTotalWorkTimeDto.getTotalWorkTimeOfMonth(), myTotalWorkTimeDto.getSalary(),
                    myTotalWorkTimeDto.getTotalWorkTimeOfMonth()});

            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (SQLException e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }

        return 0;
    }

    public boolean findMyTotalWorkTImeByDate(Date date) {
        String sqlQuery = "SELECT * "
                + "FROM " + TABLE_NAME + " WHERE work_date_of_month=?";

        jdbcUtil.setSqlAndParameters(sqlQuery, new Object[] {date});

        try {
            ResultSet resultSet = jdbcUtil.executeQuery();

            if(resultSet.next()) {
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }

}
