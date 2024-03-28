package Capstone.Capstone.Controller;

import Capstone.Capstone.entity.Community;
import Capstone.Capstone.Service.CommunityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return communityService.findById(id);
    }

    @PostMapping("/")
    @Tag(name="Community API")
    @Operation(summary = "회원가입",description = "User 정보를 저장합니다.")
    public Community createCommunity(@RequestBody Community community) {
        return communityService.save(community);
    }

    @PutMapping("/{id}")
    @Tag(name="Community API")
    @Operation(summary = "회원가입",description = "User 정보를 저장합니다.")
    public Community updateCommunity(@PathVariable Long id, @RequestBody Community community) {
        return communityService.update(id, community);
    }

    @DeleteMapping("/{id}")
    @Tag(name="Community API")
    @Operation(summary = "회원가입",description = "User 정보를 저장합니다.")
    public void deleteCommunity(@PathVariable Long id) {
        communityService.delete(id);
    }
}
