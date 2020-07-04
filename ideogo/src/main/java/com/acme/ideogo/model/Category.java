package com.acme.ideogo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
public class Category extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Size(max = 30)
    @Column(unique = true)
    private String name;

    @NotNull
    @NotBlank
    @Size(max = 250)
    private String description;


    @ManyToMany(fetch = FetchType.LAZY,
                cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "categories_tags",
               joinColumns = {@JoinColumn(name = "post_id")},
               inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    @JsonIgnore
    List<Tag> tags;

}
