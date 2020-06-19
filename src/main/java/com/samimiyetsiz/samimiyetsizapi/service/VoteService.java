package com.samimiyetsiz.samimiyetsizapi.service;

import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import com.samimiyetsiz.samimiyetsizapi.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    Long countByPost(Post post) {
        Long upcount = voteRepository.countByPostAndUp(post, true);
        Long downcount = voteRepository.countByPostAndUp(post, false);
        return upcount - downcount;
    }

}
