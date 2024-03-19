package Capstone.Capstone.Controller;

import Capstone.Capstone.Service.RecruitService;
import Capstone.Capstone.dto.Recruit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    @GetMapping("/recruits")
    public String selectBoardList(Model model) {
        List<Recruit> recruits = recruitService.selectBoardList();
        model.addAttribute("recruits", recruits);
        return "recruitList";
    }

    @GetMapping("/recruits/{id}")
    public String getRecruitById(@PathVariable Long id, Model model) {
        Recruit recruit = recruitService.getRecruitById(id);
        model.addAttribute("recruit", recruit);
        return "recruitDetail";
    }

    @GetMapping("/recruits/new")
    public String createPostForm(Model model) {
        model.addAttribute("recruit", new Recruit());
        return "recruitForm";
    }

    @PostMapping("/recruits")
    public String createPost(@ModelAttribute Recruit recruit) {
        recruitService.createRecruit(recruit);
        return "redirect:/recruits";
    }
}
