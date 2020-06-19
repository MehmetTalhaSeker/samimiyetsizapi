package com.samimiyetsiz.samimiyetsizapi.service.dto;

import com.samimiyetsiz.samimiyetsizapi.entity.Category;
import com.samimiyetsiz.samimiyetsizapi.entity.Memer;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode
public class PostDto {

    private Long id;
    private String title;
    private String post;
    private Memer memer;
    private CommentDto commentDto;
    private Long voteNumber;
    private Category category;





}
