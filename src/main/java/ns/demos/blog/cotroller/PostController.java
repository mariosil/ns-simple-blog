package ns.demos.blog.cotroller;

import ns.demos.blog.entity.Post;
import ns.demos.blog.exception.BlogException;
import ns.demos.blog.repository.CommentRepository;
import ns.demos.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/posts")
public class PostController {

    // TODO: 11/28/19 Create a PostServiceService interface
    private PostService postService;
    private CommentRepository commentRepository;

    @Autowired
    public PostController(PostService postService, CommentRepository commentRepository) {
        this.postService = postService;
        this.commentRepository = commentRepository;
    }

    @GetMapping("index")
    public String postsIndex(Model model) {
        model.addAttribute("posts", postService.getAll());
        return "posts/index";
    }

    @GetMapping("show/{id}")
    public String showPost(@PathVariable("id") long postId, Model model) throws BlogException {
        Post post = postService.getById(postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", commentRepository.findByPostOrderByCreatedAt(post));
        return "posts/show";
    }


    @GetMapping("new")
    public String newPost(Post post, Model model) {
        model.addAttribute("post", post);
        return "posts/new";
    }
    @PostMapping("save")
    public String savePost(@Valid Post post, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "posts/new";
        }
        postService.save(post);
        return "redirect:/posts/index";
    }


    @GetMapping("edit/{id}")
    public String editPost(@PathVariable("id") long postId, Model model) throws BlogException {
        model.addAttribute("post", postService.getById(postId));
        return "posts/edit";
    }
    @PostMapping("update/{id}")
    public String updatePost(@PathVariable("id") long postId, @Valid Post post, BindingResult result, Model model) throws BlogException {
        if (result.hasErrors()) {
            return "posts/edit";
        }
        Post updatable = postService.getById(postId);
        updatable.setTitle(post.getTitle());
        updatable.setAuthor(post.getAuthor());
        updatable.setContent(post.getContent());
        postService.save(updatable);
        model.addAttribute("posts", postService.getAll());
        return "posts/index";
    }


    @GetMapping("delete/{id}")
    public String deletePost(@PathVariable("id") long postId, Model model) throws BlogException {
        Post post = postService.getById(postId);
        postService.delete(post);
        model.addAttribute("posts", postService.getAll());
        return "posts/index";
    }
}
