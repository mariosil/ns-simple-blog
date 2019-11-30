package ns.demos.blog.repository;

import ns.demos.blog.entity.Comment;
import ns.demos.blog.entity.Post;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {

    List<Comment> findByPostOrderByCreatedAt(Post post);

}
