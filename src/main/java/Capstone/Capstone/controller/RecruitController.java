package Capstone.Capstone.controller;

import Capstone.Capstone.dto.RecruitDto;
import Capstone.Capstone.dto.DistanceDto;
import Capstone.Capstone.service.RecruitService;
import Capstone.Capstone.service.UserService;
import Capstone.Capstone.entity.Recruit;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@Tag(name="RECRUIT API", description = "RECRUIT API입니다")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @Autowired
    private UserService userService;

    @GetMapping("/recruits/driver")
    @Tag(name="RECRUIT API")
    @Operation(summary = "모집 글 목록보기",description = "모집 글을 불러옵니다.")
    public ResponseEntity<List<Recruit>> selectDriverBoardList() {
        List<Recruit> recruits = recruitService.selectDriverBoardList();
        return new ResponseEntity<>(recruits, HttpStatus.OK);
    }

    @GetMapping("/recruits/passenger")
    @Tag(name="RECRUIT API")
    @Operation(summary = "모집 글 목록보기",description = "모집 글을 불러옵니다.")
    public ResponseEntity<List<Recruit>> selectPassengerBoardList() {
        List<Recruit> recruits = recruitService.selectPassengerBoardList();
        return new ResponseEntity<>(recruits, HttpStatus.OK);
    }

    @GetMapping("/recruits/{id}")
    @Tag(name="RECRUIT API")
    @Operation(summary = "모집 글 찾기", description = "ID로 글을 찾아옵니다.")
    public ResponseEntity<Recruit> getRecruitById(@PathVariable Long id) {
        Recruit recruit = recruitService.getRecruitById(id);
        if (recruit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recruit, HttpStatus.OK);
    }


    @PostMapping("/recruits")
    @Tag(name="RECRUIT API")
    @Operation(summary = "모집 글 생성",description = "모집 글을 생성합니다.")
    public ResponseEntity<Recruit> createRecruit(@RequestBody RecruitDto recruitDto) {

            int distance=recruitService.calculateDistance(recruitDto.getDepartureX(), recruitDto.getDepartureY(), recruitDto.getArrivalX(), recruitDto.getArrivalY());
            log.info("distance={}",distance);
            recruitDto.setDistance(distance);
            Recruit createdRecruit = recruitService.createRecruit(recruitDto);
            log.info("recruit={}",createdRecruit);

            return new ResponseEntity<>(createdRecruit, HttpStatus.CREATED);





    }

    @DeleteMapping("/{id}")
    @Tag(name="RECRUIT API")
    @Operation(summary = "모집 글 삭제",description = "모집 글을 삭제합니다.")
    public void deleteRecruit(@PathVariable Long id){
        recruitService.deleteRecruit(id);
    }


    @PutMapping("/recruits/{id}")
    @Tag(name="RECRUIT API")
    @Operation(summary = "모집 글 수정", description = "ID로 지정한 모집 글을 수정합니다.")
    public ResponseEntity<Recruit> updateRecruit(@PathVariable Long id, @RequestBody Recruit recruitDetails) {
        Recruit updatedRecruit = recruitService.updateRecruit(id, recruitDetails);
        if (updatedRecruit == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(updatedRecruit, HttpStatus.OK);
    }

    @GetMapping("/recruits/latest")
    @Tag(name="RECRUIT API")
    @Operation(summary = "최신 모집 글 목록보기", description = "최신 모집 글을 불러옵니다.")
    public ResponseEntity<List<Recruit>> getLatestRecruits() {
        List<Recruit> latestRecruits = recruitService.findLatestRecruits();
        return new ResponseEntity<>(latestRecruits, HttpStatus.OK);
    }

    @GetMapping("/recruits/search")
    @Tag(name="RECRUIT API")
    @Operation(summary = "모집 글 검색", description = "출발일자와 목적지로 모집 글을 검색합니다.")
    public ResponseEntity<List<Recruit>> searchRecruits(
            @RequestParam LocalDate departureDate,
            @RequestParam String destination) {
        List<Recruit> recruits = recruitService.searchRecruits(departureDate, destination);
        return new ResponseEntity<>(recruits, HttpStatus.OK);
    }

    @GetMapping("/by-distance")
    @Tag(name="RECRUIT API")
    @Operation(summary = "거리 순 모집 글 목록보기", description = "거리순 모집 글을 불러옵니다.")
    public ResponseEntity<List<Recruit>> getRecruitsByDistance(@RequestParam double latitude, @RequestParam double longitude) {
        List<Recruit> recruits = recruitService.findRecruitsByDistance(latitude, longitude);
        return new ResponseEntity<>(recruits, HttpStatus.OK);
    }

    @GetMapping("/recruits/Keywords")
    @Tag(name="RECRUIT API")
    @Operation(summary = "키워드로 모집 글 검색하기", description = "키워드로 모집 글을 검색합니다.")
    public ResponseEntity<List<Recruit>> searchRecruitsByKeywords(@RequestParam List<String> keywords){
        List<Recruit> recruits = recruitService.findRecruitsByKeywords(keywords);
        return new ResponseEntity<>(recruits, HttpStatus.OK);
    }

    @PostMapping("/recruits/booking")
    public ResponseEntity<Boolean> Booking(@RequestParam String nickname,@RequestParam long idxNum){
            boolean result;
          result= recruitService.addBookingList(nickname,idxNum);
           return new ResponseEntity<>(result,HttpStatus.OK);



    }

    @PostMapping("/recruits/addBooking")
    public ResponseEntity<Boolean> adddBooking(@RequestParam String nickname,@RequestParam long idxNum) {
        if (recruitService.getRecruitById(idxNum).getParticipant() < recruitService.getRecruitById(idxNum).getMaxParticipant()) {
            recruitService.addParticipant(idxNum);
            recruitService.subBookingList(nickname, idxNum);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return  new ResponseEntity<>(false,HttpStatus.OK);
    }

    @PostMapping("recruits/distance")
    public ResponseEntity<Integer> calculate(@RequestBody DistanceDto distanceDto){
        int distance= recruitService.calculateDistance(distanceDto.getDepartureX(), distanceDto.getDeparturey(), distanceDto.getArrivalX(), distanceDto.getArrivaly());
        log.info("distance={}",distance);
        return new ResponseEntity<>(distance,HttpStatus.OK);
    }


}
