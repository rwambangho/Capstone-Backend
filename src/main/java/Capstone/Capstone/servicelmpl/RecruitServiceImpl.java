package Capstone.Capstone.servicelmpl;

import Capstone.Capstone.service.RecruitService;
import Capstone.Capstone.entity.Recruit;
import Capstone.Capstone.entity.User;
import Capstone.Capstone.repository.RecruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public Recruit createRecruit(Recruit recruit, User user){
        if (user.isDriver() && (user.getDriverLicense() == null || user.getDriverLicense().isEmpty())) {
            throw new IllegalStateException("운전면허증 등록이 필요합니다.");
        } //사용자가 운전자 모드인 경우 운전면허증이 등록되어 있는지 확인하기
        recruit.setDriverPost(user.isDriver());
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
    @Override
    public List<Recruit> findRecruitsByDistance(double userLat, double userLon) {
        List<Recruit> allRecruits = recruitRepository.findAll();
        return allRecruits.stream()
                .sorted(Comparator.comparing(recruit -> calculateDistance(userLat, userLon, recruit.getLatitude(), recruit.getLongitude())))
                .collect(Collectors.toList());
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        double distance;
        double radius = 6371; // 지구 반지름(km)
        double toRadian = Math.PI / 180;

        double deltaLatitude = Math.abs(lat1 - lat2) * toRadian;
        double deltaLongitude = Math.abs(lon1 - lon2) * toRadian;

        double sinDeltaLat = Math.sin(deltaLatitude / 2);
        double sinDeltaLng = Math.sin(deltaLongitude / 2);
        double squareRoot = Math.sqrt(
                sinDeltaLat * sinDeltaLat +
                        Math.cos(lat1 * toRadian) * Math.cos(lat2 * toRadian) * sinDeltaLng * sinDeltaLng);

        distance = 2 * radius * Math.asin(squareRoot);

        return distance;
    }

    @Override
    public List<Recruit> findRecruitsByKeywords(List<String> keywords){
        return recruitRepository.findByKeywordsIn(keywords);
    }
}


