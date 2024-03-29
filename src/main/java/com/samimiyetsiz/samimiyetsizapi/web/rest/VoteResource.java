package com.samimiyetsiz.samimiyetsizapi.web.rest;
import com.samimiyetsiz.samimiyetsizapi.entity.Vote;
import com.samimiyetsiz.samimiyetsizapi.exception.BadRequest;
import com.samimiyetsiz.samimiyetsizapi.repository.VoteRepository;
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
@RequestMapping("api/v1/votes")
public class VoteResource {

    @Autowired
    private VoteRepository voteRepository;

    @PostMapping
    public ResponseEntity<Vote> createVote(@RequestBody Vote vote) throws URISyntaxException {
        if (vote.getId() != null) {
            throw new RuntimeException("Already have an ID");
        }

        Vote c = voteRepository.save(vote);
        return ResponseEntity.created(new URI("v1/votes/" + c.getId())).body(c);
    }

    @GetMapping
    public ResponseEntity<List<Vote>> readVotes(Pageable pageable, UriComponentsBuilder uriBuilder) {
        Page<Vote> page = voteRepository.findAll(pageable);
        uriBuilder.path("v1/votes/");
        HttpHeaders headers = generatePaginationHttpHeaders(uriBuilder, page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("{id}")
    public ResponseEntity<Vote> readVote(@PathVariable Long id) {
        Optional<Vote> vote = voteRepository.findById(id);
        return vote.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public ResponseEntity<Vote> updateVote(@RequestBody Vote vote) {
        if (vote.getId() == null) {
            throw new BadRequest("Invalid ID: Null");
        }
        Vote c = voteRepository.save(vote);
        return ResponseEntity.ok().body(c);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteVote(@PathVariable Long id) {
        voteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}