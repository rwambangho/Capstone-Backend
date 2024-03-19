package Capstone.Capstone.repository;

import Capstone.Capstone.entity.Community;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommunityRepository extends JpaRepository<Community,Long> {

}

