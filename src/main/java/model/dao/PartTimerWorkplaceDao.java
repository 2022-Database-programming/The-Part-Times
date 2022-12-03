package model.dao;

import model.dto.PartTimerWorkplaceDto;
import model.dto.WorkplaceDto;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

    public int remove(int memberId, int workplaceId) {
        String sql = "delete from PARTTIMER_WORKPLACE " +
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

    public PartTimerWorkplaceDto findByWorkplaceId(int memberId, int workplaceId) {
        String sql = "select * from PARTTIMER_WORKPLACE " +
                "where MEMBER_ID=? and WORKPLACE_ID=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] {memberId, workplaceId});

        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if(rs.next()) {
                return new PartTimerWorkplaceDto(
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

    public boolean isExistPartTimerWorkplace(int memberId, int workplaceId) {
        String sql = "select count(*) from EMPLOYER_WORKPLACE " +
                "where MEMBER_ID=? and WORKPLACE_ID=?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { memberId, workplaceId});

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
        String sql = "select W.* from PARTTIMER_WORKPLACE " +
                "join WORKPLACE W on PARTTIMER_WORKPLACE.WORKPLACE_ID = W.ID " +
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