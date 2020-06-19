package com.samimiyetsiz.samimiyetsizapi.web.rest;


import com.samimiyetsiz.samimiyetsizapi.entity.Hot;
import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import com.samimiyetsiz.samimiyetsizapi.entity.Trending;
import com.samimiyetsiz.samimiyetsizapi.repository.HotRepository;
import com.samimiyetsiz.samimiyetsizapi.repository.PostRepository;
import com.samimiyetsiz.samimiyetsizapi.repository.TrendingRepository;
import com.samimiyetsiz.samimiyetsizapi.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("api/v1/Hot")
public class HotResource {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private HotRepository hotRepository;

    @GetMapping
    @Scheduled(cron = "0 */12 * * *")
    public Page<Post> checkHots(Pageable pageable) {

        Page<Post> posts = postRepository.findAllWithCreationDateTimeInLastTwelveHours(pageable);
        posts.map(p -> {
            Long voteCount =  voteRepository.countVoteByPost(p);
            if (voteCount > 30) {
                Hot hot = new Hot();
                hot.setCreatedDate(Instant.now());
                hotRepository.save(hot);
                hot.setPost(p);
            }
            return p;
        });
    return posts;}


}


