package Capstone.Capstone.Controller;

import Capstone.Capstone.dto.Member;
import Capstone.Capstone.dto.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class LoginController {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    //회원가입
    @PostMapping("/login/signUp")
    public String SignUp(@RequestBody Member member) {
        List<Member> members = memberRepository.findAll();
        for (Member such : members) {
            if (member.getId().equals(such.getId())) {
                return "fail";
            }
        }
        memberRepository.save(member);

        return "ok";
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

    @PostMapping("/login/login")
    public String Login(@RequestBody Member member) {
       if (memberRepository.findById(member.getId()) != null) {
            Member search = memberRepository.findById(member.getId());
            if(search.getPw().equals(member.getPw()))
            {
            return "ok";
            }


        }
        log.info("id={} pw={}",member.getId(),member.getPw());
        return "fail";
    }
}