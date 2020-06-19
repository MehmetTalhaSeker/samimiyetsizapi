package com.samimiyetsiz.samimiyetsizapi.web.rest;


import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import com.samimiyetsiz.samimiyetsizapi.entity.Trending;
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
@RequestMapping("api/v1/Trending")
public class TrendingResource {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private TrendingRepository trendingRepository;

    @GetMapping
    @Scheduled(cron = "0 */4 * * *")
    public Page<Post> checkTrend(Pageable pageable) {

        Page<Post> posts = postRepository.findAllWithCreationDateTimeInLastFourHours(pageable);
        posts.map(p -> {
            Long voteCount = voteRepository.countVoteByPost(p);
            if (voteCount > 13) {
                Trending trending = new Trending();
                trending.setCreatedDate(Instant.now());
                trendingRepository.save(trending);
                trending.setPost(p);
            }
            return p;
        });
    return posts;}







}


