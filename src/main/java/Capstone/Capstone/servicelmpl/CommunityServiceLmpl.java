package Capstone.Capstone.servicelmpl;


import Capstone.Capstone.entity.Community;
import Capstone.Capstone.repository.CommunityRepository;
import Capstone.Capstone.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceLmpl implements CommunityService {

    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityServiceLmpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    @Override
    public Optional<Community> findById(Long id) {
        return communityRepository.findById(id);
    }

    @Override
    public Community save(Community community) {
        return communityRepository.save(community);
    }

    @Override
    public Community update(Long id, Community community) {
        Optional<Community> optionalCommunity = communityRepository.findById(id);
        if (optionalCommunity.isPresent()) {
            Community existingCommunity = optionalCommunity.get();
            existingCommunity.setTitle(community.getTitle());
            existingCommunity.setContent(community.getContent());
            existingCommunity.setName(community.getName());
            existingCommunity.setTime(community.getTime());
            existingCommunity.setStartLocation(community.getStartLocation());
            existingCommunity.setEndLocation(community.getEndLocation());
            return communityRepository.save(existingCommunity);
        } else {
            return null; // 또는 적절한 예외 처리
        }
    }

    @Override
    public void delete(Long id) {
        communityRepository.deleteById(id);
    }
}

