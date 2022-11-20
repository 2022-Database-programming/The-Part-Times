package model.dao;

import model.dto.MyTodayWorkTimeDto;
import model.dto.MyTotalWorkTimeDto;
import util.JDBCUtil;

import java.sql.*;

public class MyTodayWorkTimeDao {
    private final String TABLE_NAME = "MYTODAYWORKTIME";
    private JDBCUtil jdbcUtil = null;
    private MyTotalWorkTimeDao myTotalWorkTimeDao = null;

    public MyTodayWorkTimeDao() {
        jdbcUtil = new JDBCUtil();
        myTotalWorkTimeDao = new MyTotalWorkTimeDao();
    }

    // 오늘 근무한 시간 저장
    public int insert(MyTodayWorkTimeDto myTodayWorkTimeDto) throws SQLException {
        String[] times = String.valueOf(myTodayWorkTimeDto.getTotalWorkTimeOfDay()).split(":");
        int salary = Integer.valueOf(times[0]) * myTodayWorkTimeDto.getMinimumWage();

        MyTotalWorkTimeDto myTotalWorkTimeDto = new MyTotalWorkTimeDto(
                1, 999, myTodayWorkTimeDto.getTotalWorkTimeOfDay(), myTodayWorkTimeDto.getWorkDate(), salary, new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())
        );

        myTotalWorkTimeDao.insert(myTotalWorkTimeDto);

        String sqlQuery = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        Object[] params = new Object[] {
                myTodayWorkTimeDto.getId(), myTodayWorkTimeDto.getMyTotalWorkTimeId(),
                myTodayWorkTimeDto.getWorkStartTime(), myTodayWorkTimeDto.getWorkFinishTime(),
                myTodayWorkTimeDto.getBreakStartTime(), myTodayWorkTimeDto.getBreakFinishTime(),
                myTodayWorkTimeDto.getTotalWorkTimeOfDay(), myTodayWorkTimeDto.getTotalBreakTimeOfDay(), myTodayWorkTimeDto.getMinimumWage(),
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

    // 오늘 날짜로 찾기
    public MyTodayWorkTimeDto findMyWorkTimeByDate(Date date) {
        String sqlQuery = "SELECT * "
                + "FROM " + TABLE_NAME + " WHERE work_date=?";

        jdbcUtil.setSqlAndParameters(sqlQuery, new Object[] {date});

        try {
            ResultSet resultSet = jdbcUtil.executeQuery();

            if(resultSet.next()) {
                MyTodayWorkTimeDto myTodayWorkTimeDto = new MyTodayWorkTimeDto(
                        resultSet.getInt("id"), resultSet.getInt("mytotal_worktime_id"), resultSet.getTime("work_start_time"),
                        resultSet.getTime("work_finish_time"), resultSet.getTime("break_start_time"), resultSet.getTime("break_finish_time"),
                        resultSet.getTime("total_work_time_of_day"), resultSet.getTime("total_break_time_of_day"),
                        resultSet.getDate("work_date"), resultSet.getInt("minimum_wage"), resultSet.getTimestamp("created_at"), resultSet.getTimestamp("updated_at")
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
