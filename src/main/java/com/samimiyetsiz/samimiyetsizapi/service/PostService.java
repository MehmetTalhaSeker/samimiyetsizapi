package com.samimiyetsiz.samimiyetsizapi.service;

import com.samimiyetsiz.samimiyetsizapi.repository.PostRepository;
import com.samimiyetsiz.samimiyetsizapi.service.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postrepository;



    @Autowired
    private VoteService voteService;

    @Autowired
    private CommentService commentService;



    public Page<PostDto> readPosts(Pageable pageable){
        Page<PostDto> page=postrepository.findAll(pageable).map(q->{
            PostDto dto=new PostDto();
            dto.setId(q.getId());
            dto.setTitle(q.getTitle());
            dto.setMemer(q.getMemer());
            dto.setPost(q.getPost());
            dto.setVoteNumber(voteService.countByPost(q));
            return dto;
        });
        return page;
    }

    public Optional<PostDto> readPosts(Long id){
        Optional<PostDto> op=postrepository.findById(id).map(q->{
            PostDto dto=new PostDto();
            dto.setMemer(q.getMemer());
            dto.setTitle(q.getTitle());
            dto.setId(q.getId());
            dto.setPost(q.getPost());
            dto.setVoteNumber(voteService.countByPost(q));
            dto.setCommentDto(commentService.readCommentsByPost(q));
            return dto;
        });
        return op;
    }
}
