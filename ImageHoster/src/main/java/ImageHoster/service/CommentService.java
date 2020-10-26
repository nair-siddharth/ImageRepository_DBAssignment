package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void uploadNewComment(Comment newComment){
        Comment cmt = commentRepository.uploadComment(newComment);
        System.out.println("New Comment Id = " + cmt.getId());
    }
}
