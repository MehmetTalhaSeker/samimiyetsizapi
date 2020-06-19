package com.samimiyetsiz.samimiyetsizapi.service.dto;

import com.samimiyetsiz.samimiyetsizapi.entity.Category;
import com.samimiyetsiz.samimiyetsizapi.entity.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class TrendingDto {


    private PostDto postDto;

    private Post post;

    private Instant createdDate;

    @Enumerated(EnumType.STRING)
    private Category category;



}

