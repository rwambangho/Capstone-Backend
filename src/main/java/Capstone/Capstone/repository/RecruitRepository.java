package Capstone.Capstone.repository;

import Capstone.Capstone.entity.Recruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecruitRepository extends JpaRepository<Recruit, Long> {
    List<Recruit> findAllByOrderByCreatedAtDesc(); //최신 모집글 찾기 쿼리 메서드
    List<Recruit> findByDepartureDateAndDestination(LocalDate departureDate, String destination); //출발일자, 목적지, 출발 위치에 기반한 검색을 수행
}
