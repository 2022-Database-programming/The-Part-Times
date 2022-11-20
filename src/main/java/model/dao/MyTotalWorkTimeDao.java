package model.dao;

import model.dto.MyTodayWorkTimeDto;
import model.dto.MyTotalWorkTimeDto;
import util.JDBCUtil;

import java.sql.SQLException;

public class MyTotalWorkTimeDao {
    private final String TABLE_NAME = "MYTOTAL_WORKTIME";
    private JDBCUtil jdbcUtil = null;

    public MyTotalWorkTimeDao() {
        jdbcUtil = new JDBCUtil();
    }

    public void isInsertOrUpdate(MyTodayWorkTimeDto myTodayWorkTimeDto) {

    }

    public int insert(MyTotalWorkTimeDto myTotalWorkTimeDto) throws SQLException {
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
        String sqlQuery = "UPDATE " + TABLE_NAME
                + " SET total_work_time_of_month=?, salary=(salary + ?)" +
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

}
