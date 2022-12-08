package model.dao;

import model.dto.PartTimerWorkplaceDto;
import model.dto.WorkplaceDto;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class PartTimerWorkplaceDao {
    private final JDBCUtil JDBC_UTIL;

    private final String TABLE_NAME = "PARTTIMER_WORKTIME";
    private final String ID = "id";
    private final String MEMBER_ID = "member_id";
    private final String WORKPLACE_ID = "workplace_id";
    private final String SALARY_FORM = "salary_form";
    private final String SALARY_DAY = "salary_day";
    private final String CREATED_AT = "created_at";
    private final String UPDATED_AT = "updated_at";
    private final String ID_SEQUENCE = "parttimer_workplace_seq.nextval";

    private final String INSERT_QUERY = "insert into " + TABLE_NAME +
            " VALUES (" + ID_SEQUENCE + ", ?, ?, ?, ?, default, default)";
    private final String UPDATE_QUERY = "update " + TABLE_NAME +
            " set " + SALARY_FORM + "=?, " + SALARY_DAY + "=?, " + UPDATED_AT + "=? " +
            "where " + MEMBER_ID + "=? and " + WORKPLACE_ID + "=?";
    private String DELETE_QUERY = "delete from " + TABLE_NAME +
            "where " + MEMBER_ID + "=? and " + WORKPLACE_ID + "=?";
    private String SELECT_BY_ID_QUERY = "select * from " + TABLE_NAME +
            "where " + MEMBER_ID + "=? and " + WORKPLACE_ID + "=?";

    private final String CHECK_EXISTED_QUERY = "select count(*) from " + TABLE_NAME +
            "where MEMBER_ID=? and WORKPLACE_ID=?";    // 이거 왜 넣은거야?

    private final String FIND_ALL_QUERY = "select W.* from PARTTIMER_WORKPLACE " +
            "join WORKPLACE W on PARTTIMER_WORKPLACE.WORKPLACE_ID = W.ID " +
            "where MEMBER_ID = ?";

    public PartTimerWorkplaceDao() {
        JDBC_UTIL = new JDBCUtil();
    }

    public int create(PartTimerWorkplaceDto partTimerWorkplaceDto) {
        Object[] param = new Object[] { partTimerWorkplaceDto.getMemberId(),
                partTimerWorkplaceDto.getWorkplaceId(), partTimerWorkplaceDto.getSalaryForm(),
                partTimerWorkplaceDto.getSalaryDay()};
        try {
            JDBC_UTIL.setSqlAndParameters(INSERT_QUERY, param);
            return JDBC_UTIL.executeUpdate();
        } catch (Exception e) {
            JDBC_UTIL.rollback();
            e.printStackTrace();
        } finally {
            JDBC_UTIL.commit();
            JDBC_UTIL.close();
        }
        return 0;
    }

    public int update(PartTimerWorkplaceDto partTimerWorkplaceDto) {
        Object[] param = new Object[] { partTimerWorkplaceDto.getSalaryForm(),
                partTimerWorkplaceDto.getSalaryDay(), new Timestamp(System.currentTimeMillis()),
                partTimerWorkplaceDto.getMemberId(), partTimerWorkplaceDto.getWorkplaceId()};
        JDBC_UTIL.setSqlAndParameters(UPDATE_QUERY, param);
        try {
            return JDBC_UTIL.executeUpdate();
        } catch(Exception e) {
            JDBC_UTIL.rollback();
            e.printStackTrace();
        } finally {
            JDBC_UTIL.commit();
            JDBC_UTIL.close();
        }
        return 0;
    }

    public int remove(int memberId, int workplaceId) {
        try {
            JDBC_UTIL.setSqlAndParameters(DELETE_QUERY, new Object[] { memberId, workplaceId});
            return JDBC_UTIL.executeUpdate();
        } catch (Exception e) {
            JDBC_UTIL.rollback();
            e.printStackTrace();
        } finally {
            JDBC_UTIL.commit();
            JDBC_UTIL.close();
        }
        return 0;
    }

    public PartTimerWorkplaceDto findByWorkplaceId(int memberId, int workplaceId) {
        try {
            JDBC_UTIL.setSqlAndParameters(SELECT_BY_ID_QUERY, new Object[] {memberId, workplaceId});
            ResultSet rs = JDBC_UTIL.executeQuery();
            if(rs.next()) {
                return new PartTimerWorkplaceDto(
                        rs.getInt(ID),
                        rs.getInt(MEMBER_ID),
                        rs.getInt(WORKPLACE_ID),
                        rs.getString(SALARY_FORM),
                        rs.getInt(SALARY_DAY),
                        rs.getTimestamp(CREATED_AT),
                        rs.getTimestamp(CREATED_AT)
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_UTIL.close();
        }
        return null;
    }

    public boolean isExistPartTimerWorkplace(int memberId, int workplaceId) {
        JDBC_UTIL.setSqlAndParameters(CHECK_EXISTED_QUERY, new Object[] { memberId, workplaceId});

        try {
            ResultSet rs = JDBC_UTIL.executeQuery();
            if(rs.next()) {
                int count = rs.getInt(1);
                return (count == 1 ? true : false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_UTIL.close();
        }
        return false;
    }

    public List<PartTimerWorkplaceDto> findAllWorkplace(int memberId) {

        JDBC_UTIL.setSqlAndParameters(FIND_ALL_QUERY, new Object[] { memberId });

        try {
            ResultSet rs = JDBC_UTIL.executeQuery();
            List<PartTimerWorkplaceDto> partTimerWorkplaceDtos = new ArrayList<>();

            while(rs.next()) {
                PartTimerWorkplaceDto partTimerWorkplaceDto = new PartTimerWorkplaceDto(
                        rs.getInt(ID),
                        rs.getInt(MEMBER_ID),
                        rs.getInt(WORKPLACE_ID),
                        rs.getString(SALARY_FORM),
                        rs.getInt(SALARY_DAY),
                        rs.getTimestamp(CREATED_AT),
                        rs.getTimestamp(UPDATED_AT)
                );

                partTimerWorkplaceDtos.add(partTimerWorkplaceDto);
            }
            return partTimerWorkplaceDtos;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBC_UTIL.close();
        }
        return null;
    }
}