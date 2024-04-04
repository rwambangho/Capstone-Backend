package Capstone.Capstone.servicelmpl;
import Capstone.Capstone.entity.Comment;
import Capstone.Capstone.entity.Community;
import Capstone.Capstone.repository.CommentRepository;
import Capstone.Capstone.repository.CommunityRepository;
import Capstone.Capstone.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;
    private final CommunityRepository communityRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, CommunityRepository communityRepository) {
        this.commentRepository = commentRepository;
        this.communityRepository=communityRepository;

    }

    @Override
    public Comment addComment(Comment comment) {
            Optional<Community> OptionalCommunity = communityRepository.findById(comment.getCommunity().getId());
            if (OptionalCommunity.isPresent()) {
                Community findCommunity = OptionalCommunity.get();
                findCommunity.getComments().add(comment);
                comment.setCommunity(findCommunity);
            }

           return commentRepository.save(comment);


    }

    @Override
    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
