package model.dao;

import model.dto.PostDto;
import util.JDBCUtil;

import java.sql.ResultSet;

public class PostDao {
    private final JDBCUtil JDBC_UTIL;

    public PostDao() {
        JDBC_UTIL = new JDBCUtil();
    }

    public int create(PostDto postDto) {
        String sql = "INSERT INTO \"post\" VALUES (?, ?, DEFAULT, ?, ?, ?, DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
        int tempId = 1234;
        int rs = 0;
        System.out.println(sql);
        Object[] param = new Object[] { tempId, postDto.getMemberId(), postDto.getType(),
                postDto.getTitle(), postDto.getContent() };

        JDBC_UTIL.setSqlAndParameters(sql, param);

        try {
            rs = JDBC_UTIL.executeUpdate();
            System.out.println("insert success");
        } catch(Exception e) {
            JDBC_UTIL.rollback();
            e.printStackTrace();
        } finally {
            JDBC_UTIL.commit();
            JDBC_UTIL.close();
        }
        return rs;
    }
}