package com.samimiyetsiz.samimiyetsizapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Trending extends AbstractEntity {

    @ManyToOne
    private Post post;

}
