package com.samimiyetsiz.samimiyetsizapi.service.dto;

import com.samimiyetsiz.samimiyetsizapi.entity.Comment;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class CommentDto {

    List<Comment> comments = new ArrayList<Comment>();
    Long commentcount;
}