package Capstone.Capstone.servicelmpl;

import Capstone.Capstone.Service.RecruitService;
import Capstone.Capstone.entity.Recruit;
import Capstone.Capstone.repository.RecruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class RecruitServiceImpl implements RecruitService {

    private final RecruitRepository recruitRepository;

    @Autowired
    public RecruitServiceImpl(RecruitRepository recruitRepository) {
        this.recruitRepository = recruitRepository;
    }

    @Override
    public List<Recruit> selectBoardList(){

        return recruitRepository.findAll();
    }

    @Override
    public Recruit getRecruitById(Long id){
        Optional<Recruit> recruit = recruitRepository.findById(id);
        return recruit.orElse(null);
    }

    @Override
    public Recruit createRecruit(Recruit recruit){

        return recruitRepository.save(recruit);
    }

    @Override
    public void deleteRecruit(Long id){
        recruitRepository.deleteById(id);
    }

    @Override
    public Recruit updateRecruit(Long id, Recruit recruitDetails) {
        Optional<Recruit> recruitOptional = recruitRepository.findById(id);

        if (!recruitOptional.isPresent()) {
            return null;
        }

        Recruit recruit = recruitOptional.get();
        recruit.setTitle(recruitDetails.getTitle());
        recruit.setContents(recruitDetails.getContents());
        recruit.setUsername(recruitDetails.getUsername());
        recruit.setId(recruitDetails.getId());
        recruit.setStar(recruitDetails.getStar());


        recruitRepository.save(recruit);
        return recruit;
    }

    @Override
    public List<Recruit> findLatestRecruits() {
        return recruitRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Recruit> searchRecruits(LocalDate departureDate, String destination) {
        return recruitRepository.findByDepartureDateAndDestination(departureDate, destination);
    }
}
