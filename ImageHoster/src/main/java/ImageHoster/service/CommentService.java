package ImageHoster.service;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    // persists new comment in DB
    public void uploadNewComment(Comment newComment){
        Comment cmt = commentRepository.uploadComment(newComment);
        System.out.println("New Comment Id = " + cmt.getId());
    }

    // returns all comments related to a particular image
    public List getAllImageComments(Integer imageId){
        return commentRepository.getAllImageComments(imageId);
    }
}
