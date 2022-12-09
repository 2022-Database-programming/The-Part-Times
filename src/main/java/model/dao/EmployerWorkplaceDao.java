package model.dao;

import model.dto.EmployerWorkplaceDto;
import model.dto.WorkplaceDto;
import util.JDBCUtil;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
                "where MEMBER_ID=? and WORKPLACE_ID=?";
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

    public boolean isExistEmployerWorkplace(int memberId, int workplaceId) {
        String sql = "select count(*) from PARTTIMER_WORKPLACE " +
                "where MEMBER_ID=? and WORKPLACE_ID=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId, workplaceId });

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if(rs.next()) {
                int count = rs.getInt(1);
                return (count == 1 ? true : false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return false;
    }

    public List<WorkplaceDto> findAllWorkplace(int memberId) {
        String sql = "select W.* from EMPLOYER_WORKPLACE " +
                "join WORKPLACE W on EMPLOYER_WORKPLACE.WORKPLACE_ID = W.ID " +
                "where MEMBER_ID = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId });

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            List<WorkplaceDto> memberList = new ArrayList<>();

            while(rs.next()) {
                WorkplaceDto workplaceDto = new WorkplaceDto(
                        rs.getInt("id"),
                        rs.getString("workplace_name"),
                        rs.getString("address"),
                        rs.getString("phone_number"),
                        rs.getString("business_number"),
                        rs.getTimestamp("created_at"),
                        rs.getTimestamp("updated_at")
                );
                memberList.add(workplaceDto);
            }
            return memberList;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return null;
    }
}