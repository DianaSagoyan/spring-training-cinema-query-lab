package com.cydeo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor   //BREAK TILL 12:10
@Data
public class Genre extends BaseEntity {

    private String name;
}
