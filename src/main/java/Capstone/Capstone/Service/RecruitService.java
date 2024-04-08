package Capstone.Capstone.Service;

import Capstone.Capstone.entity.Recruit;
import Capstone.Capstone.entity.User;

import java.time.LocalDate;
import java.util.List;


public interface RecruitService {
    List<Recruit> selectBoardList();
    Recruit getRecruitById(Long id);

    Recruit createRecruit(Recruit recruit, User user);

    void deleteRecruit(Long id);

    Recruit updateRecruit(Long id, Recruit recruitDetails);

    List<Recruit> findLatestRecruits();

    List<Recruit> searchRecruits(LocalDate departureDate, String destination);
    List<Recruit> findRecruitsByDistance(double userLat, double userLon);

    List<Recruit> findRecruitsByKeywords(List<String> keywords);

}
