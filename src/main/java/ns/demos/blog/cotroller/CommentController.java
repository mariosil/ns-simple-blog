package ns.demos.blog.cotroller;

import ns.demos.blog.entity.Comment;
import ns.demos.blog.entity.Post;
import ns.demos.blog.exception.BlogException;
import ns.demos.blog.service.CommentService;
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
@RequestMapping("/posts/{id}/comments")
public class CommentController {

    private CommentService commentService;
    private PostService postService;

    @Autowired
    public CommentController(CommentService commentService, PostService postService) {
        this.commentService = commentService;
        this.postService = postService;
    }

    @GetMapping("new")
    public String newComment(@PathVariable("id") long postId, Comment comment, Model model) throws BlogException {
        model.addAttribute("post", postService.getById(postId));
        model.addAttribute("comment", comment);
        return "comments/new";
    }

    @PostMapping("save")
    public String saveComment(@PathVariable("id") long postId, @Valid Comment comment, BindingResult result, Model model) throws BlogException {
        if(result.hasErrors()) {
            return "comments/new";
        }
        Post post = postService.getById(postId);
        comment.setPost(post);
        commentService.save(comment);
        return "redirect:/posts/show/" + postId;
    }
}
