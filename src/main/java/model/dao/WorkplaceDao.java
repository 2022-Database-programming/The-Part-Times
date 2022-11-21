package model.dao;

import model.dto.WorkplaceDto;
import util.JDBCUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkplaceDao {
    private JDBCUtil jdbcUtil;

    public WorkplaceDao() {
        jdbcUtil = new JDBCUtil();
    }

    /**
     * workplace 이름 검색하여 WorkplaceDto 반환
     */
    public WorkplaceDto findByWorkplaceName(String workplaceName) {
        String sql = "select * from WORKPLACE " +
                "where WORKPLACE_NAME = ?";
        jdbcUtil.setSqlAndParameters(sql, new Object[] { workplaceName });
        WorkplaceDto workplaceDto = null;
        try {
            ResultSet rs = jdbcUtil.executeQuery();
            if(rs.next()) {
                workplaceDto = new WorkplaceDto(
                        rs.getInt("ID"),
                        workplaceName,
                        rs.getString("ADDRESS"),
                        rs.getString("PHONE_NUMBER"),
                        rs.getString("BUSINESS_NUMBER"),
                        rs.getTimestamp("CREATED_AT"),
                        rs.getTimestamp("UPDATED_AT")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            jdbcUtil.close();
        }
        return workplaceDto;
    }

}
