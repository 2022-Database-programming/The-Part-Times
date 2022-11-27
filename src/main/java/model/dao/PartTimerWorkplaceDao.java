package model.dao;

import model.dto.PartTimerWorkplaceDto;
import util.JDBCUtil;

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
}
