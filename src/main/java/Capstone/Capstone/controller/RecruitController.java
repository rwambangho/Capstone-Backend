package Capstone.Capstone.Controller;

import Capstone.Capstone.Service.RecruitService;
import Capstone.Capstone.entity.Recruit;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@Tag(name="RECRUIT API", description = "RECRUIT API입니다")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @GetMapping("/recruits")
    @Tag(name="RECRUIT API")
    @Operation(summary = "모집 글 목록보기",description = "모집 글을 불러옵니다.")
    public ResponseEntity<List<Recruit>> selectBoardList() {
        List<Recruit> recruits = recruitService.selectBoardList();
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

    @GetMapping("/recruits/new")
    @Tag(name="RECRUIT API")
    @Operation(summary = "모집 글 생성",description = "모집 글을 생성합니다.")
    public String createRecruitForm(Model model) {
        model.addAttribute("recruit", new Recruit());
        return "recruitForm";
    }

    @PostMapping("/recruits")
    @Tag(name="RECRUIT API")
    @Operation(summary = "모집 글 생성",description = "모집 글을 생성합니다.")
    public Recruit createRecruit(@RequestBody Recruit recruit) {
        return recruitService.createRecruit(recruit);

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




}
