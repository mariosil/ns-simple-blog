package ns.demos.blog.service;

import ns.demos.blog.entity.Post;
import ns.demos.blog.exception.BlogException;
import ns.demos.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("postService")
public class PostService implements EntityService<Post> {

    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findByOrderByCreatedAtDesc();
    }

    @Override
    public Post getById(long postId) throws BlogException {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new BlogException("Cannot find post with id " + postId));;
        return post;
    }

    @Override
    public void save(Post post) {
        this.postRepository.save(post);
    }

    @Override
    public void delete(Post post) {
        this.postRepository.delete(post);
    }

}
