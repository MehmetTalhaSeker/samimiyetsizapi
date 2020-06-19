package com.samimiyetsiz.samimiyetsizapi.service;

import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import com.samimiyetsiz.samimiyetsizapi.service.dto.CommentDto;
import com.samimiyetsiz.samimiyetsizapi.entity.Comment;
import com.samimiyetsiz.samimiyetsizapi.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentDto readCommentsByPost(Post post) {
        Page<Comment> page = commentRepository.findAllByPost(post, getPageable());
        CommentDto dto = new CommentDto();
        dto.setComments(page.getContent());
        dto.setCommentcount(page.getTotalElements());
        return dto;
    }



    public Pageable getPageable() {
        return PageRequest.of(0, 3, Sort.by(Sort.Direction.ASC, "createdDate"));

    }
}
