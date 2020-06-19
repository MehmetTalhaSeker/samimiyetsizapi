package com.samimiyetsiz.samimiyetsizapi.service;

import com.samimiyetsiz.samimiyetsizapi.entity.Hot;
import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import com.samimiyetsiz.samimiyetsizapi.entity.Trending;
import com.samimiyetsiz.samimiyetsizapi.repository.HotRepository;
import com.samimiyetsiz.samimiyetsizapi.repository.PostRepository;
import com.samimiyetsiz.samimiyetsizapi.repository.TrendingRepository;
import com.samimiyetsiz.samimiyetsizapi.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.List;

@Service
public class TrendingService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private TrendingRepository trendingRepository;

    @Autowired
    private HotRepository hotRepository;

    @Scheduled(cron = "0 */4 * * *")
    public void showTrends() {
        List<Post> posts = postRepository.findInLastHours(Instant.now().minus(4L, ChronoUnit.HOURS));
        posts.forEach(p -> {
            Long voteCount = voteRepository.countVoteByPost(p);
            if (voteCount > 13) {
                Trending t = new Trending();
                t.setPost(p);
                t.setCreatedDate(p.getCreatedDate());
                trendingRepository.save(t);
            }
        });
    }

    @Scheduled(cron = "* * * * *")
    public void showHots() {
        System.out.println("asdsads");
        List<Post> posts = postRepository.findInLastHours(Instant.now().minus(12L, ChronoUnit.HOURS));
        posts.forEach(p -> {
            Long voteCount = voteRepository.countVoteByPost(p);
            if (voteCount > 30) {
                Hot h = new Hot();
                h.setPost(p);
                h.setCreatedDate(p.getCreatedDate());
                hotRepository.save(h);
            }
        });
    }
}
