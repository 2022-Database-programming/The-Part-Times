package model.dao;

import model.dto.MyTotalIncomeDto;
import java.sql.SQLException;
import java.sql.*;
import util.JDBCUtil;
import java.sql.Date;

public class MyTotalIncomeDao {
	private JDBCUtil jdbcUtil = null;
	
	//MyTotalIncomeDao 생성자
	public MyTotalIncomeDao() {
		jdbcUtil = new JDBCUtil();
	}
	
	public MyTotalIncomeDto findMyTotalIncome(Date date) {
		 String sql = "SELECT * "
	                + "FROM MYTOTAL_INCOME " + " WHERE income_date_of_month=?";

	        jdbcUtil.setSqlAndParameters(sql, new Object[] {date});

	        try {
	            ResultSet resultSet = jdbcUtil.executeQuery();

	            if(resultSet.next()) {
	            	MyTotalIncomeDto myTotalIncomeDto = new MyTotalIncomeDto(
	                        resultSet.getInt("id"), 
	                        resultSet.getInt("employer_workplace_id"), 
	                        resultSet.getDate("income_date_of_month"),
	                        resultSet.getInt("income"), 
	                        resultSet.getTimestamp("created_at"), 
	                        resultSet.getTimestamp("updated_at"));
	                return myTotalIncomeDto;
	            }

	            return null;
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            jdbcUtil.close();
	        }

	        return null;
	}
	
	
}