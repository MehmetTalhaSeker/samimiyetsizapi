package com.samimiyetsiz.samimiyetsizapi.web.rest;
import com.samimiyetsiz.samimiyetsizapi.entity.Memer;
import com.samimiyetsiz.samimiyetsizapi.exception.BadRequest;
import com.samimiyetsiz.samimiyetsizapi.repository.MemerRepository;
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
@RequestMapping("api/v1/memers")
public class MemerResource {

    @Autowired
    private MemerRepository memerRepository;

    @PostMapping
    public ResponseEntity<Memer> createMemer(@RequestBody Memer memer) throws URISyntaxException {
        if (memer.getId() != null) {
            throw new RuntimeException("Already have an ID");
        }

        Memer c = memerRepository.save(memer);
        return ResponseEntity.created(new URI("v1/memers/" + c.getId())).body(c);
    }

    @GetMapping
    public ResponseEntity<List<Memer>> readMemers(Pageable pageable, UriComponentsBuilder uriBuilder) {
        Page<Memer> page = memerRepository.findAll(pageable);
        uriBuilder.path("v1/memers/");
        HttpHeaders headers = generatePaginationHttpHeaders(uriBuilder, page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("{id}")
    public ResponseEntity<Memer> readMemer(@PathVariable Long id) {
        Optional<Memer> memer = memerRepository.findById(id);
        return memer.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("{id}")
    public ResponseEntity<Memer> updateMemer(@RequestBody Memer memer) {
        if (memer.getId() == null) {
            throw new BadRequest("Invalid ID: Null");
        }
        Memer c = memerRepository.save(memer);
        return ResponseEntity.ok().body(c);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMemer(@PathVariable Long id) {
        memerRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}