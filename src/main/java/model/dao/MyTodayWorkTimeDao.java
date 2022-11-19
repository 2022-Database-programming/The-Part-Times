package model.dao;

import model.dto.MyTodayWorkTimeDto;
import util.JDBCUtil;

import java.sql.SQLException;

public class MyTodayWorkTimeDao {
    private JDBCUtil jdbcUtil = null;

    public MyTodayWorkTimeDao() {
        jdbcUtil = new JDBCUtil();
    }

    // 오늘 근무한 시간 저장
    public int insert(MyTodayWorkTimeDto myTodayWorkTimeDto) throws SQLException {
        String sqlQuery = "INSERT INTO \"mytodayworktime\" VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Object[] params = new Object[] {
                myTodayWorkTimeDto.getId(), myTodayWorkTimeDto.getMyTotalWorkTimeId(),
                myTodayWorkTimeDto.getWorkStartTime(), myTodayWorkTimeDto.getWorkFinishTime(),
                myTodayWorkTimeDto.getBreakStartTime(), myTodayWorkTimeDto.getBreakFinishTime(),
                myTodayWorkTimeDto.getTotalWorkTimeOfDay(), myTodayWorkTimeDto.getTotalBreakTimeOfDay(),
                myTodayWorkTimeDto.getWorkDate(), myTodayWorkTimeDto.getCreatedAt(), myTodayWorkTimeDto.getUpdatedAt()
        };

        jdbcUtil.setSqlAndParameters(sqlQuery, params);

        try {
            int result = jdbcUtil.executeUpdate();
            return result;
        } catch (SQLException e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }

        return 0;
    }

    // 오늘 근무 시간 삭제
    public int delete(int id) {
        String sqlQuery = "DELETE FROM mytodayworktime WHERE id=?";
        jdbcUtil.setSqlAndParameters(sqlQuery, new Object[] {id});

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
