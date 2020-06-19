package com.samimiyetsiz.samimiyetsizapi.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Image extends AbstractEntity {

    private byte[] image;
}
