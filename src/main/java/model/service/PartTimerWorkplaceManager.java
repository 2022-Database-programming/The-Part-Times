package model.service;

import model.dao.PartTimerWorkplaceDao;
import model.dto.PartTimerWorkplaceDto;

import java.util.List;

public class PartTimerWorkplaceManager {
    private static PartTimerWorkplaceManager PART_TIMER_WORKPLACE_MANAGER = new PartTimerWorkplaceManager();
    private PartTimerWorkplaceDao partTimerWorkplaceDao;

    private PartTimerWorkplaceManager() {
        try {
            partTimerWorkplaceDao = new PartTimerWorkplaceDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PartTimerWorkplaceManager getInstance() {
        return PART_TIMER_WORKPLACE_MANAGER;
    }

    public List<PartTimerWorkplaceDto> findAllPartTimerWorkplace(int memberId) {
        return partTimerWorkplaceDao.findAllWorkplace(memberId);
    }
}
