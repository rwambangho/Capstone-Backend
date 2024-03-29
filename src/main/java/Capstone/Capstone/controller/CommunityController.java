package Capstone.Capstone.Controller;

import Capstone.Capstone.entity.Community;
import Capstone.Capstone.service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@RequestMapping("/community")
@Tag(name="Community API",description = "Community API입니다.")
public class CommunityController {

    private final CommunityService communityService;

    @Autowired
    public CommunityController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @GetMapping("/")
    @Tag(name="Community API")
    @Operation(summary = "모든 글 찾기",description = "모든 글을 찾아옵니다.")
    public ResponseEntity<List<Community>> getAllCommunities() {
        List<Community> communities = communityService.findAll();
        return new ResponseEntity<>(communities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Tag(name="Community API")
    @Operation(summary = "글 찾기",description = "id로 글을 찾습니다.")
    public Optional<Community> getCommunityById(@PathVariable Long id) {
        communityService.addClickCount(id);
        return communityService.findById(id);
    }

    @PostMapping("/save")
    @Tag(name="Community API")
    @Operation(summary = "글 생성",description = "글을 생성하여 저장합니다.")
    public ResponseEntity<String> createCommunity(@RequestBody Community community, @CookieValue(value = "JSESSIONID", defaultValue = "") String sessionId, HttpServletRequest request) throws IOException {

        HttpSession session = request.getSession(false);
        if (session != null && sessionId.equals(session.getId())) {
            String nickName = (String) session.getAttribute("nickName");
            community.setNickName(nickName);
            log.info("nickName={}",community.getNickName());
        } else {
            return new ResponseEntity<>("로그아웃 상태입니다.",HttpStatus.BAD_REQUEST);

        }
        if (community.getImage()!=null) {
            communityService.saveImage(community);
        }
        communityService.save(community);
            return new ResponseEntity<>( "굿", HttpStatus.CREATED);


    }

    @PutMapping("/{id}")
    @Tag(name="Community API")
    @Operation(summary = "글 수정",description = "게시판 글을 수정합니다.")
    public Community updateCommunity(@PathVariable Long id, @RequestBody Community community) {

        return communityService.update(id, community);
    }

    @DeleteMapping("/{id}")
    @Tag(name="Community API")
    @Operation(summary = "글 삭제",description = "게시판 글을 삭제합니다.")
    public ResponseEntity<String> deleteCommunity(@PathVariable Long id,HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Optional<Community> community=communityService.findById(id);
        log.info("community={}",community);
        if (community.isPresent()) {
            Community communityObject = community.get();
            String findTitle=communityObject.getTitle();
            String findNickName = communityObject.getNickName();
            log.info("findTitle={} findNickName={}",findTitle,findNickName);
            String nickName = (String) session.getAttribute("nickName");
                if (findNickName.equals(nickName)) {
                    communityService.delete(id);
                    return new ResponseEntity<>("삭제 성공",HttpStatus.OK);

                }
        }
        return new ResponseEntity<>("삭제 실패",HttpStatus.BAD_REQUEST);
    }
    @PutMapping("/addLike")
    @Tag(name="Community API")
    @Operation(summary="좋아요 추가",description="게시판 글 좋아요 개수를 1개 늘립니다.")
    public ResponseEntity<String> addLike(@RequestBody Community community){
        communityService.addLike(community);
        return new ResponseEntity<>("좋아요 성공",HttpStatus.OK);

    }
    @Tag(name="Community API")
    @Operation(summary="인기글 조회",description="게시판 글 좋아요 개수가 많은 게시글부터 5개 조회합니다.")
    @GetMapping("/PopularCommunity")
    public ResponseEntity<List<Community>> getPopularCommunity(){
        List<Community> popularCommunity=communityService.findPopularCommunity();
        return new ResponseEntity<>(popularCommunity,HttpStatus.OK);
    }
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Community>> getCommunityByTitle(@PathVariable String title){
        List<Community> community=communityService.findByTitle(title);
        return new ResponseEntity<>(community,HttpStatus.OK);
    }

}
