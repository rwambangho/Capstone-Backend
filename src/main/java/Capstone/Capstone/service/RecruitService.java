package Capstone.Capstone.service;

import Capstone.Capstone.entity.Recruit;
import Capstone.Capstone.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface RecruitService {
    List<Recruit> selectBoardList();
    Recruit getRecruitById(Long id);

    Recruit createRecruit(Recruit recruit, User user);

    void deleteRecruit(Long id);

    Recruit updateRecruit(Long id, Recruit recruitDetails);

    List<Recruit> findLatestRecruits();

    List<Recruit> searchRecruits(LocalDate departureDate, String destination);
    List<Recruit> findRecruitsByDistance(double userLat, double userLon);

}
