package model.dao;

import model.dto.EmployerWorkplaceDto;
import model.dto.PartTimerWorkplaceDto;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.Timestamp;

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
                employerWorkplaceDto.getSalaryDay(), new Timestamp(System.currentTimeMillis()),
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

    public int remove(int memberId, int workplaceId) {
        String sql = "delete from EMPLOYER_WORKPLACE " +
                "where MEMBER_ID=1 and WORKPLACE_ID=1235";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId, workplaceId});

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

    public EmployerWorkplaceDto findByMemberIdAndWorkplaceId(int memberId, int workplaceId) {
        String sql = "select * from EMPLOYER_WORKPLACE " +
                "where MEMBER_ID=? and WORKPLACE_ID=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId, workplaceId});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if(rs.next()) {
                return new EmployerWorkplaceDto(
                        rs.getInt("id"),
                        rs.getInt("member_id"),
                        rs.getInt("workplace_id"),
                        rs.getString("salary_form"),
                        rs.getInt("salary_day"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
}
