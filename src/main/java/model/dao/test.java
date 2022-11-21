package model.dao;

import model.dto.PostDto;
import model.dto.WorkplaceDto;

public class test {
    public static void main(String[] args) {
        WorkplaceDao workplaceDao = new WorkplaceDao();
        WorkplaceDto workplaceDto = workplaceDao.findByWorkplaceName("낙원타코 강남역점");
        System.out.println(workplaceDto.toString());
    }
}
