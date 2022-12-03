package model.dao;

import model.dto.PostDto;
import util.JDBCUtil;

import java.sql.ResultSet;

public class PostDao {
    private JDBCUtil jdbcUtil;

    public PostDao() {
        jdbcUtil = new JDBCUtil();
    }

    public int create(PostDto postDto) {
        String sql = "INSERT INTO \"post\" VALUES (?, ?, DEFAULT, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
        int tempId = 1234;
        int rs = 0;
        System.out.println(sql);
        Object[] param = new Object[] { tempId, postDto.getMemberId(), postDto.getType(),
                postDto.getTitle(), postDto.getContent() };

        jdbcUtil.setSqlAndParameters(sql, param);

        try {
            rs = jdbcUtil.executeUpdate();
            System.out.println("insert success");
        } catch(Exception e) {
            jdbcUtil.rollback();
            e.printStackTrace();
        } finally {
            jdbcUtil.commit();
            jdbcUtil.close();
        }
        return rs;
    }
}