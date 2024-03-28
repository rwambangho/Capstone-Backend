package Capstone.Capstone.Service;
import Capstone.Capstone.entity.Community;

import java.util.List;
import java.util.Optional;


public interface CommunityService {
    List<Community> findAll();
    Optional<Community> findById(Long id);
    Community save(Community community);
    Community update(Long id, Community community);


    void delete(Long id);
}
