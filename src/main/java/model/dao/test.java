package model.dao;

import model.dto.PostDto;

public class test {
    public static void main(String[] args) {
        PostDto postDto = new PostDto(999, "EMPLOYER", "DFADF", "COCOCO");
        PostDao postDao = new PostDao();

        System.out.println("result: " + postDao.create(postDto));
    }
}
