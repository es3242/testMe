package com.study.board.ga;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class dodo { //테이블

    @Id//Id
    private int doid;

    private String doname;

    private int doint;



}