package pl.karol.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.karol.model.Comment;
import pl.karol.model.Post;
import pl.karol.repository.PostRepository;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class PostController {

    private PostRepository postRepository;

    @Autowired
    public PostController(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @GetMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    @GetMapping(path = "/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Integer id){
       if(id > postRepository.findAll().size() || id <= 0){
           return ResponseEntity.notFound().build();
       } else {
           return ResponseEntity.ok(postRepository.getOne(id));
       }
    }

    @PostMapping(path = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post savePost(@Valid @RequestBody Post post, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new Post("Error!", "Post nie został zapisany! Wypełnij dane postu ponownie");
        } else {
            List<Comment> comments = post.getComments();
            for(Comment comment: comments){
                comment.setPost(post);
            }
            postRepository.save(post);
            return post;
        }
    }

    @PutMapping(path = "/posts/{id}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post, @PathVariable Integer id){
        return postRepository.findById(id)
                .map(entity -> {
                    entity.setTitle(post.getTitle());
                    entity.setDescription(post.getDescription());
                    entity.setComments(post.getComments());
                    entity.setTags(post.getTags());
                    entity.getComments().forEach(comment -> comment.setPost(entity));
                    Post updated = postRepository.save(entity);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path = "/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Integer id){
        return postRepository.findById(id)
                .map(entity -> {
                    postRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
