package com.samimiyetsiz.samimiyetsizapi.web.rest;

import com.samimiyetsiz.samimiyetsizapi.entity.Category;
import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import com.samimiyetsiz.samimiyetsizapi.exception.BadRequest;
import com.samimiyetsiz.samimiyetsizapi.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import static com.samimiyetsiz.samimiyetsizapi.util.PaginationUtil.generatePaginationHttpHeaders;


@RestController
@RequestMapping("api/v1/posts")
public class PostResource {

    @Autowired
    private PostRepository postRepository;

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) throws URISyntaxException {
        if (post.getId() != null) {
            throw new RuntimeException("Already have an ID");
        }
        Post c = postRepository.save(post);
        return ResponseEntity.created(new URI("v1/posts/" + c.getId())).body(c);
    }

    @GetMapping
    public ResponseEntity<List<Post>> readPosts(Pageable pageable, UriComponentsBuilder uriBuilder) {
        Page<Post> page = postRepository.findAll(pageable);
        uriBuilder.path("v1/posts/");
        HttpHeaders headers = generatePaginationHttpHeaders(uriBuilder, page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("category/{category}")
    public ResponseEntity<List<Post>> readPosts(@PathVariable Category category, Pageable pageable, UriComponentsBuilder uriBuilder) {
        Page<Post> page = postRepository.findAllByCategory(pageable, category);
        uriBuilder.path("v1/posts/");
        HttpHeaders headers = generatePaginationHttpHeaders(uriBuilder, page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> readPost(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> updatePost(@RequestBody Post post) {
        if (post.getId() == null) {
            throw new BadRequest("Invalid ID: Null");
        }
        Post c = postRepository.save(post);
        return ResponseEntity.ok().body(c);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}