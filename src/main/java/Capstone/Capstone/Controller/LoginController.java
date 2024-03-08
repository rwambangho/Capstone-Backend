package Capstone.Capstone.Controller;

import Capstone.Capstone.dto.Member;
import Capstone.Capstone.dto.MemberRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class LoginController {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //회원가입
    @PostMapping("/login/signUp")
    public String SignUp(@RequestBody Member member) {
        List<Member> members= memberRepository.findAll();
        for(Member such:members){
            if(member.getId().equals(such.getId()))
            {
                return "이미 있는 아이디입니다.";
            }
        }
        memberRepository.save(member);

        return "okk";
    }

    @GetMapping("/login/signUp-ok")
    public String SignUpOk(@RequestParam("id") String id) {
        List<Member> members = memberRepository.findAll();
        for (Member member : members) {
            if (id.equals(member.getId())) {
                return member.getId();
            }
        }
        return "Not Found";
    }
}
