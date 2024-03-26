package Capstone.Capstone.service;
import Capstone.Capstone.entity.Community;

import java.util.List;
import java.util.Optional;


public interface CommunityService {
    List<Community> findAll();
    Optional<Community> findById(Long id);
    Optional<Community> findByTitle(String title);

    Community save(Community community);
    Community update(Long id, Community community);

    Community saveImage(Community community);

    void delete(long id);

    void addLike(Community community) ;

    List<Community> findPopularCommunity();

    void addClickCount(long id);
}
