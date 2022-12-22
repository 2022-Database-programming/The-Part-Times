package model.service;

import model.dao.PartTimerWorkplaceDao;
import model.dao.WorkplaceDao;
import model.dto.MyTotalWorkTimeDto;
import model.dto.PartTimerWorkplaceDto;
import model.dto.WorkplaceDto;

import java.util.ArrayList;
import java.util.List;

public class PartTimerWorkplaceManager {
    private static PartTimerWorkplaceManager partTimerWorkplaceManager = new PartTimerWorkplaceManager();
    private PartTimerWorkplaceDao partTimerWorkplaceDao;
    private WorkplaceDao workplaceDao;

    private PartTimerWorkplaceManager() {
        try {
            partTimerWorkplaceDao = new PartTimerWorkplaceDao();
            workplaceDao = new WorkplaceDao();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static PartTimerWorkplaceManager getInstance() {
        return partTimerWorkplaceManager;
    }

    public List<String> findThisMonthWorkplaceNamesByPartTimerWorkplace(List<MyTotalWorkTimeDto> myTotalWorkTimes, int memberId) {
        List<PartTimerWorkplaceDto> partTimerWorkplaces = partTimerWorkplaceDao.findAllWorkplace(memberId);
        List<WorkplaceDto> workplaces = new ArrayList<>();

        for (int i = 0; i < myTotalWorkTimes.size(); i++) {
            if (myTotalWorkTimes.get(i).getPartTimerWorkplaceId() == partTimerWorkplaces.get(i).getId()) {
                WorkplaceDto workplaceDto = workplaceDao.findByWorkplaceId(partTimerWorkplaces.get(i).getWorkplaceId());

                System.out.println(workplaceDto);

                if (workplaceDto != null) {
                    workplaces.add(workplaceDto);
                }
            }
        }

        return initWorkplaceNames(workplaces);
    }

    public List<String> findAllWorkplaceNamesByPartTimerWorkplace(int memberId) {
        List<PartTimerWorkplaceDto> partTimerWorkplaces = partTimerWorkplaceDao.findAllWorkplace(memberId);
        List<WorkplaceDto> workplaces = new ArrayList<>();

        for (int i = 0; i < partTimerWorkplaces.size(); i++) {
            WorkplaceDto workplaceDto = workplaceDao.findByWorkplaceId(partTimerWorkplaces.get(i).getId());
            workplaces.add(workplaceDto);
        }

        return initWorkplaceNames(workplaces);
    }

    private List<String> initWorkplaceNames(List<WorkplaceDto> workplaces) {
        List<String> workplaceNames = new ArrayList<>();

        for (int i = 0; i < workplaces.size(); i++) {
            workplaceNames.add(workplaces.get(i).getWorkplaceName());
        }

        return workplaceNames;
    }

}
