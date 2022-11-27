package model.dao;

import model.dto.EmployerWorkplaceDto;
import util.JDBCUtil;

public class EmployerWorkplaceDao {
    private JDBCUtil jdbcUtil;

    EmployerWorkplaceDao() {
        jdbcUtil = new JDBCUtil();
    }

    public int create(EmployerWorkplaceDto employerWorkplaceDto) {
        String sql = "insert into EMPLOYER_WORKPLACE " +
                "values (employer_workplace_seq.nextval, ?, ?, ?, ?, default, default)";
        Object[] param = new Object[] { employerWorkplaceDto.getMemberId(),
                employerWorkplaceDto.getWorkplaceId(), employerWorkplaceDto.getSalaryForm(),
                employerWorkplaceDto.getSalaryDay()};
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

    public int update(EmployerWorkplaceDto employerWorkplaceDto) {
        String sql = "update EMPLOYER_WORKPLACE " +
                "set SALARY_FORM=?, SALARY_DAY=?, UPDATED_AT=? " +
                "where MEMBER_ID = ? and WORKPLACE_ID=?";

        Object[] param = new Object[] { employerWorkplaceDto.getSalaryForm(),
                employerWorkplaceDto.getSalaryDay(), employerWorkplaceDto.getSalaryDay(),
                employerWorkplaceDto.getMemberId(), employerWorkplaceDto.getWorkplaceId()};

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
