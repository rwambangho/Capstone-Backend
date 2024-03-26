package Capstone.Capstone.Service;

import Capstone.Capstone.entity.Recruit;

import java.time.LocalDate;
import java.util.List;


public interface RecruitService {
    List<Recruit> selectBoardList();
    Recruit getRecruitById(Long id);

    Recruit createRecruit(Recruit recruit);

    void deleteRecruit(Long id);

    Recruit updateRecruit(Long id, Recruit recruitDetails);

    List<Recruit> findLatestRecruits();

    List<Recruit> searchRecruits(LocalDate departureDate, String destination);
}
