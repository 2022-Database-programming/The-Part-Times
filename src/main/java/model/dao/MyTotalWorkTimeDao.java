package model.dao;

import model.dto.MyTotalWorkTimeDto;
import util.JDBCUtil;

import java.sql.SQLException;

public class MyTotalWorkTimeDao {
    private final String TABLE_NAME = "\"mytotal_worktime\"";
    private JDBCUtil jdbcUtil = null;

    public MyTotalWorkTimeDao() {
        jdbcUtil = new JDBCUtil();
    }

    public int insert(MyTotalWorkTimeDto myTotalWorkTimeDto) throws SQLException {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?)";

        Object[] params = new Object[] {
                myTotalWorkTimeDto.getId(), myTotalWorkTimeDto.getPartTimerWorkplaceId(),
                myTotalWorkTimeDto.getTotalWorkTimeOfDay(), myTotalWorkTimeDto.getDate(),
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

}
