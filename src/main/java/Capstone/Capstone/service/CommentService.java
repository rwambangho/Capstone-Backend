package Capstone.Capstone.service;

import Capstone.Capstone.entity.Comment;

public interface CommentService {
     Comment addComment(Comment comment);
     Comment getCommentById(Long id);
     void deleteComment(Long id);


}