package Capstone.Capstone.servicelmpl;


import Capstone.Capstone.entity.Community;
import Capstone.Capstone.repository.CommunityRepository;
import Capstone.Capstone.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommunityServiceLmpl implements CommunityService {

    private final CommunityRepository communityRepository;

    @Autowired
    public CommunityServiceLmpl(CommunityRepository communityRepository) {
        this.communityRepository = communityRepository;
    }

    @Override
    public List<Community> findAll() {
        return communityRepository.findAll();
    }

    @Override
    public Optional<Community> findById(Long id) {
        return communityRepository.findById(id);
    }

    @Override
    public List<Community> findByTitle(String title) {
        return communityRepository.findByTitle(title);
    }

    @Override
    public Community save(Community community) {
        LocalDateTime now = LocalDateTime.now();
        community.setTime(now);
        community.setLikeCount(0L);
        return communityRepository.save(community);
    }

    @Override
    public Community saveImage(Community community) {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "image_" + timeStamp + ".jpg"; // 파일 확장자에 맞게 변경

        // 이미지 데이터(dataURL) 추출
        String imageData = community.getImage();

        // dataURL에서 실제 데이터 부분만 분리 (콤마 이후의 부분)
        String base64Image = imageData.split(",")[1];

        // base64로 인코딩된 데이터를 디코딩하여 바이너리 데이터로 변환
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        String imageurl="/Users/kimseungzzang/ideaProjects/capstone-frontend/src/postImage/"+fileName;
        community.setImage(imageurl);
        // 저장할 파일 경로 지정
        File outputFile = new File(imageurl);

        try (OutputStream outputStream = new FileOutputStream(outputFile)) {
            outputStream.write(imageBytes);
        }

        catch (Exception e) {
            e.printStackTrace();

        }
        return community;
    }

    @Override
    public Community update(Long id, Community community) {
        Optional<Community> optionalCommunity = communityRepository.findById(id);
        if (optionalCommunity.isPresent()) {
            Community existingCommunity = optionalCommunity.get();
            existingCommunity.setTitle(community.getTitle());
            existingCommunity.setContent(community.getContent());
            existingCommunity.setNickName(community.getNickName());
            existingCommunity.setTime(community.getTime());
            return communityRepository.save(existingCommunity);
        } else {
            return null; // 또는 적절한 예외 처리
        }
    }

    @Override
    public void delete(long id) {

        communityRepository.deleteById(id);
    }
    @Override
    public void addLike(Community community){
       Optional<Community> findCommunity=communityRepository.findById(community.getId());
       if(findCommunity.isPresent())
       {
           long LikeCount=findCommunity.get().getLikeCount();
           findCommunity.get().setLikeCount(LikeCount+1);
           communityRepository.save(findCommunity.get());

       }
    }

    @Override
    public List<Community> findPopularCommunity() {
        return communityRepository.findTop5ByOrderByLikeCountDesc();
    }

    @Override
    public void addClickCount(long id) {
        Optional<Community> community= communityRepository.findById(id);
        if(community.isPresent())
        {
          Community ClickCommunity=community.get();
           long presentCount= ClickCommunity.getClickCount();
           presentCount++;
            ClickCommunity.setClickCount(presentCount);
        }
    }



}

