package Capstone.Capstone.repository;

import Capstone.Capstone.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {
    List<Community> findTop5ByOrderByLikeCountDesc();
    List<Community> findByTitle(String title);
}

