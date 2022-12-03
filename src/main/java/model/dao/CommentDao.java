package model.dao;

import model.dto.CommentDto;
import util.JDBCUtil;
import java.sql.SQLException;

public class CommentDao {
    private final String TABLE_NAME = "REPLY";
    private JDBCUtil jdbcUtil = null;

    public CommentDao() {
        jdbcUtil = new JDBCUtil();
    }

    //댓글 추가
    public int create(CommentDto commentDto) throws SQLException{
        //System.out.println("insert start");
        String sql = "INSERT INTO " + TABLE_NAME + " VALUES (?, ?, ?, ?, ?, default, default, ?, ?)";

        Object[] param = new Object[] { (int) (Math.random() * 1000), commentDto.getPostId(), null, commentDto.getIsAnonymous(),
                commentDto.getContent(), commentDto.getCreatedAt(), commentDto.getUpdatedAt()};

        System.out.println(commentDto);
        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡstart");
            int rs = jdbcUtil.executeUpdate();
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡfinish");
            return rs;

        } catch(Exception e) {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡsqlError");
            jdbcUtil.rollback();
            e.printStackTrace();

        } finally {
            System.out.println("ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡsuccess");
            System.out.println("insert finish");
            jdbcUtil.commit();
            jdbcUtil.close();
        }

        return 0;
    }


}

