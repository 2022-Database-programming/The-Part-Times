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

	
	public int insertOrUpdate(MyTotalIncomeDto myTotalIncomeDto) throws SQLException {
		int result = -1;
		
		if(findMyWorkPlaceTotalIncome(myTotalIncomeDto.getIncomeDateOfMonth(), myTotalIncomeDto.getEmployerWorkplaceId()) != null) {
			result = updateMyTotalIncome(myTotalIncomeDto);
		} else {
			result = insertMyTotalIncome(myTotalIncomeDto);
		}
		return result;
	}
	
	
	//해당 근무지 월별 총수익 추가
	public int insertMyTotalIncome(MyTotalIncomeDto myTotalIncomeDto) throws SQLException {
		String sql = "INSERT INTO mytotal_income (id, employer_workplace_id, income_date_of_month, income) VALUES (mytotal_income_seq.nextval, ?, ?, ?)";
		Object[] param = new Object[] {myTotalIncomeDto.getEmployerWorkplaceId(), myTotalIncomeDto.getIncomeDateOfMonth(),
						myTotalIncomeDto.getIncome()};
	
		jdbcUtil.setSqlAndParameters(sql, param);
		
		try {
			int result = jdbcUtil.executeUpdate();
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		} finally {
			jdbcUtil.commit();
			jdbcUtil.close();
		}
		
		return 0;
	}
	
	public int updateMyTotalIncome(MyTotalIncomeDto myTotalIncomeDto) throws SQLException {
		String sql = "UPDATE mytotal_income "
					+ "SET income=?, updated_at=?"
					+ "WHERE employer_workplace_id=? and income_date_of_month=?";
		
		Object[] param = new Object[] {myTotalIncomeDto.getIncome(), new Timestamp(System.currentTimeMillis()), myTotalIncomeDto.getEmployerWorkplaceId(), myTotalIncomeDto.getIncomeDateOfMonth()};				
		jdbcUtil.setSqlAndParameters(sql, param);	// JDBCUtil에 update문과 매개 변수 설정
		
		try {				
			int result = jdbcUtil.executeUpdate();	// update 문 실행
			return result;
		} catch (Exception ex) {
			jdbcUtil.rollback();
			ex.printStackTrace();
		}
		finally {
			jdbcUtil.commit();
			jdbcUtil.close();	// resource 반환
		}		
		return 0;
	
	}
	
	//근무지별 수익
	public MyTotalIncomeDto findMyWorkPlaceTotalIncome(Date date, int employerWorkplaceId) {
		 String sql = "SELECT * FROM MYTOTAL_INCOME " + "WHERE employer_workplace_id=? and income_date_of_month=?";

	        jdbcUtil.setSqlAndParameters(sql, new Object[] {employerWorkplaceId, date});

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