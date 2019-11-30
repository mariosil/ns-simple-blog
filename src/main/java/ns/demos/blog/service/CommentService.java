package ns.demos.blog.service;

import ns.demos.blog.entity.Comment;
import ns.demos.blog.exception.BlogException;
import ns.demos.blog.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("commentService")
public class CommentService implements EntityService<Comment> {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAll() {
        return (List<Comment>) commentRepository.findAll();
    }

    @Override
    public Comment getById(long id) throws BlogException {
        Optional<Comment> comment = commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new BlogException("Cannot find comment with id " + id);
        }
    }

    @Override
    public void save(Comment comment) {
        commentRepository.save(comment);
    }

    @Override
    public void delete(Comment comment) {
        commentRepository.delete(comment);
    }
}
