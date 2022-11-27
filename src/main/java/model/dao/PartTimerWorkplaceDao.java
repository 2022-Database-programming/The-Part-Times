package model.dao;

import model.dto.PartTimerWorkplaceDto;
import util.JDBCUtil;

import java.sql.Timestamp;

public class PartTimerWorkplaceDao {

    private JDBCUtil jdbcUtil;

    PartTimerWorkplaceDao() {
        jdbcUtil = new JDBCUtil();
    }

    public int create(PartTimerWorkplaceDto partTimerWorkplaceDto) {
        String sql = "insert into PARTTIMER_WORKPLACE " +
                "VALUES (parttimer_workplace_seq.nextval, ?, ?, ?, ?, default, default)";

        Object[] param = new Object[] { partTimerWorkplaceDto.getMemberId(),
                partTimerWorkplaceDto.getWorkplaceId(), partTimerWorkplaceDto.getSalaryForm(),
                partTimerWorkplaceDto.getSalaryDay()};

        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            return jdbcUtil.executeUpdate();
        } catch (Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return 0;
    }

    public int update(PartTimerWorkplaceDto partTimerWorkplaceDto) {
        String sql = "update PARTTIMER_WORKPLACE " +
                "set SALARY_FORM=?, SALARY_DAY=?, UPDATED_AT=? " +
                "where MEMBER_ID=? and WORKPLACE_ID=?";
        Object[] param = new Object[] { partTimerWorkplaceDto.getSalaryForm(),
                partTimerWorkplaceDto.getSalaryDay(), new Timestamp(System.currentTimeMillis()),
                partTimerWorkplaceDto.getMemberId(), partTimerWorkplaceDto.getWorkplaceId()};
        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            return jdbcUtil.executeUpdate();
        } catch(Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return 0;
    }

}
