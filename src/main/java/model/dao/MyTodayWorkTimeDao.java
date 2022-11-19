package model.dao;

import model.dto.MyTodayWorkTimeDto;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MyTodayWorkTimeDao {
    private final String TABLE_NAME = "\"mytodayworktime\"";
    private JDBCUtil jdbcUtil = null;

    public MyTodayWorkTimeDao() {
        jdbcUtil = new JDBCUtil();
    }

    // 오늘 근무한 시간 저장
    public int insert(MyTodayWorkTimeDto myTodayWorkTimeDto) throws SQLException {
        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
        String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE \"id\"=?";
        jdbcUtil.setSqlAndParameters(sqlQuery, new Object[] { id });

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

    public MyTodayWorkTimeDto findMyWorkTimeByDate(int id) {
        String sqlQuery = "SELECT * "
                + "FROM " + TABLE_NAME + " WHERE \"id\"=?";

        jdbcUtil.setSqlAndParameters(sqlQuery, new Object[] {id});

        try {
            ResultSet resultSet = jdbcUtil.executeQuery();

            if(resultSet.next()) {
                MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
                        id, resultSet.getInt("mytotal_worktime_id"), resultSet.getTime("work_start_time"),
                        resultSet.getTime("work_finish_time"), resultSet.getTime("break_start_time"), resultSet.getTime("break_finish_time"),
                        resultSet.getTime("total_work_time_of_day"), resultSet.getTime("total_break_time_of_day"),
                        resultSet.getDate("work_date"), resultSet.getTimestamp("created_at"), resultSet.getTimestamp("updated_at")
                );

                return myTodayWorkTimeDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }

        return null;
    }
}
