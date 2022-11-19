package model.dao;


import model.dto.MyTotalWorkTimeDto;
import util.JDBCUtil;

public class MyTotalWorkTimeDao {
    private JDBCUtil jdbcUtil = null;

    public MyTotalWorkTimeDao() {
        jdbcUtil = new JDBCUtil();
    }

    public MyTotalWorkTimeDto findMyTotalWorkTimeById() {

    }

}
