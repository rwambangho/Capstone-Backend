package Capstone.Capstone.Service;

import Capstone.Capstone.dto.Recruit;

import java.util.List;

public interface RecruitService {
    List<Recruit> selectBoardList();
    Recruit getRecruitById(Long id);
    void createRecruit(Recruit recruit);
}
