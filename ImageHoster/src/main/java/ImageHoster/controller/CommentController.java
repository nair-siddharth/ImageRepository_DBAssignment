package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.UserService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments")
    public String createComment(@PathVariable("imageId") Integer id,
                                @PathVariable("imageTitle") String title,
                                @RequestParam("comment") String comment,
                                HttpSession session){

        System.out.println("comment = " + comment);
        Comment newComment = new Comment();
        LocalDate lDate = LocalDate.now();
        newComment.setCreatedDate(lDate);
        newComment.setComment(comment);
        newComment.setImage(imageService.getImage(id));
        newComment.setUser((User)session.getAttribute("loggeduser"));

        /*
        System.out.println("CreatedDate() = " + newComment.getCreatedDate());
        System.out.println("Username() = " + newComment.getUser().getUsername());
        System.out.println("Image().getId() = " + newComment.getImage().getId());
        System.out.println("Comment() = " + newComment.getComment());
        */

        commentService.uploadNewComment(newComment);

        return "redirect:/images";
    }

}
