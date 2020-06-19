package com.samimiyetsiz.samimiyetsizapi.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Post extends AbstractEntity {

    @NotBlank(message = "Title cannot be empty.")
    private String title;
    @Column(nullable = false)
    private String imgUrl;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Memer memer;
    @OneToMany
    private List<Vote> votes;
    @Column(nullable = false)
    private String post;






}
