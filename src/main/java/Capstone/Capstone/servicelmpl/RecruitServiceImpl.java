package Capstone.Capstone.servicelmpl;

import Capstone.Capstone.dto.RecruitDto;
import Capstone.Capstone.service.RecruitService;
import Capstone.Capstone.entity.Recruit;
import Capstone.Capstone.entity.User;
import Capstone.Capstone.repository.RecruitRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j

@Service
public class RecruitServiceImpl implements RecruitService {

    private final RecruitRepository recruitRepository;

    @Autowired
    public RecruitServiceImpl(RecruitRepository recruitRepository) {
        this.recruitRepository = recruitRepository;
    }



    @Override
    public List<Recruit> selectDriverBoardList(){

        return recruitRepository.findByIsDriverPost(true);
    }
    @Override
    public List<Recruit> selectPassengerBoardList(){

        return recruitRepository.findByIsDriverPost(false);
    }

    @Override
    public Recruit getRecruitById(Long id){
        Optional<Recruit> recruit = recruitRepository.findById(id);
        return recruit.orElse(null);
    }

    @Override
    public Recruit createRecruit(RecruitDto recruitDto){

      Recruit recruit= ConvertToEntity(recruitDto);

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
        recruit.setNickname(recruitDetails.getNickname());

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
                .sorted(Comparator.comparing(recruit -> calculateDistance(userLat, userLon, recruit.getArrivalX(), recruit.getArrivalY())))
                .collect(Collectors.toList());
    }

    @Override
    public int calculateDistance(double lat1, double lon1, double lat2, double lon2) {
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


        return (int)distance;
    }

    @Override
    public List<Recruit> findRecruitsByKeywords(List<String> keywords){
        return recruitRepository.findByKeywordsIn(keywords);
    }


    @Override
    public Recruit ConvertToEntity(RecruitDto recruitDto) {
        Recruit recruit=new Recruit();
        recruit.setContents(recruitDto.getContents());
        recruit.setDestination(recruitDto.getDestination());
        recruit.setDeparture(recruitDto.getDeparture());
        recruit.setDepartureDate(recruitDto.getDepartureDate());
        recruit.setKeywords(recruitDto.getKeywords());
        recruit.setTitle(recruitDto.getTitle());
        recruit.setMessage(recruitDto.getMessage());
        recruit.setNickname(recruitDto.getNickname());
        recruit.setParticipant(recruitDto.getParticipant());
        recruit.setMaxParticipant(recruitDto.getMaxParticipant());
        recruit.setUsers(recruitDto.getUsers());
        recruit.setIsDriverPost(recruitDto.isDriverPost());
        recruit.setArrivalX(recruitDto.getArrivalX());
        recruit.setArrivalY(recruitDto.getArrivalY());
        recruit.setDepartureY(recruitDto.getDepartureY());
        recruit.setDepartureX(recruitDto.getDepartureX());
        recruit.setDistance(recruitDto.getDistance());
        recruit.setAvgStar(recruitDto.getAvgStar());
        log.info("{}",recruitDto.getNickname());

        return recruit;
    }

    @Override
    public RecruitDto ConvertToDto(Recruit recruit) {
        RecruitDto recruitDto = new RecruitDto();
        recruitDto.setContents(recruit.getContents());
        recruitDto.setDestination(recruit.getDestination());
        recruitDto.setDeparture(recruit.getDeparture());
        recruitDto.setDepartureDate(recruit.getDepartureDate());
        recruitDto.setKeywords(recruit.getKeywords());
        recruitDto.setTitle(recruit.getTitle());
        recruitDto.setMessage(recruit.getMessage());
        recruitDto.setNickname(recruit.getNickname());
        recruitDto.setParticipant(recruit.getParticipant());
        recruitDto.setMaxParticipant(recruit.getMaxParticipant());
        recruitDto.setUsers(recruit.getUsers());
        recruitDto.setBookingUsers(recruit.getBookingUsers());
        recruitDto.setDriverPost(recruit.isDriverPost());
        recruitDto.setArrivalX(recruit.getArrivalX());
        recruitDto.setArrivalY(recruit.getArrivalY());
        recruitDto.setDepartureY(recruit.getDepartureY());
        recruitDto.setDepartureX(recruit.getDepartureX());
        recruitDto.setAvgStar(recruit.getAvgStar());

        return recruitDto;
    }

    @Override
    public void addParticipant(Long idxNum) {
        Optional<Recruit> Oprecruit=recruitRepository.findById(idxNum);
        if(Oprecruit.isPresent())
        {
            Recruit recruit=Oprecruit.get();
            recruit.setParticipant(recruit.getParticipant()+1);
        }
    }

    @Override
    public boolean addBookingList(String user,Long idxNum) {
        Optional<Recruit> Oprecruit=recruitRepository.findById(idxNum);
        if(Oprecruit.isPresent())
        {
            Recruit recruit=Oprecruit.get();
           List<String> users= recruit.getUsers();
           List<String> bookingUsers=recruit.getBookingUsers();
           if(!users.contains(user)&&!bookingUsers.contains(user))
           {
           users.add(user);
           recruit.setUsers(users);
           recruitRepository.save(recruit);
           return true;
           }
           else
           {
               return false;
           }

        }
        return false;
    }
    @Override
    public void subBookingList(String user,Long idxNum) {
        Optional<Recruit> Oprecruit=recruitRepository.findById(idxNum);
        if(Oprecruit.isPresent())
        {
            Recruit recruit=Oprecruit.get();
            List<String> users= recruit.getUsers();
            List<String> bookingUsers= recruit.getBookingUsers();
            users.remove(user);
            bookingUsers.add(user);
            recruit.setBookingUsers(bookingUsers);
            recruit.setUsers(users);
            recruitRepository.save(recruit);

        }
    }

}


