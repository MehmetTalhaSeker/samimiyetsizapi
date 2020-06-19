package com.samimiyetsiz.samimiyetsizapi.repository;

import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import com.samimiyetsiz.samimiyetsizapi.entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {

    Long countByPostAndUp(Post post, boolean up);

    Long countVoteByPost(Post post);


}
