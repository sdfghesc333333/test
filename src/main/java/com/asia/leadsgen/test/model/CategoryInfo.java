package com.asia.leadsgen.test.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_CATEGORY")
@Data
public class CategoryInfo {
    @Id
    @Column(name = "S_ID")
    private String id;

    @Column(name = "S_NAME")
    private String name;
}
