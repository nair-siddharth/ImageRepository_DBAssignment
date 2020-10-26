package ImageHoster.repository;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {

    //Get an instance of EntityManagerFactory from persistence unit with name as 'imageHoster'
    @PersistenceUnit(unitName = "imageHoster")
    private EntityManagerFactory emf;

    // Push new comment object into DB
    public Comment uploadComment(Comment newComment){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            em.persist(newComment);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
        }
        return newComment;
    }

    // returns all comments related to the Image with id = ImageId
    public List getAllImageComments(Integer imageId){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> query = em.createQuery(
                "SELECT c from Comment c where c.image = "+imageId, Comment.class);
        List<Comment> resultList = query.getResultList();

        return resultList;
    }
}
