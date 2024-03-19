package Capstone.Capstone.Service;

import Capstone.Capstone.dto.Recruit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecruitServiceImpl implements RecruitService {
    private List<Recruit> recruits = new ArrayList<>();

    @Override
    public List<Recruit> selectBoardList(){

        return recruits;
    }

    @Override
    public Recruit getRecruitById(Long id){
        for(Recruit recruit: recruits){
            if(recruit.getId().equals(id)){
                return recruit;
            }
        }
        return null;
    }

    @Override
    public void createRecruit(Recruit recruit){
        recruits.add(recruit);
    }
}
