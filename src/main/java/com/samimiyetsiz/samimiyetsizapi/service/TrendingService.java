package com.samimiyetsiz.samimiyetsizapi.service;

import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import com.samimiyetsiz.samimiyetsizapi.entity.Trending;
import com.samimiyetsiz.samimiyetsizapi.repository.PostRepository;
import com.samimiyetsiz.samimiyetsizapi.repository.TrendingRepository;
import com.samimiyetsiz.samimiyetsizapi.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class TrendingService {


}