package com.testserver.test.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "employees")
@Getter
@Setter
public class Employees {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Name")
    private String name;

    @Column(name="likes_count")
    private Integer liked;

    @Column(name = "description")
    private String description;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "job_id")
    private Long job_id;

    @Column(name = "location_id")
    private Long location_id;

//    @ManyToOne
//    @JoinColumn(name="job_id", referencedColumnName = "id", insertable = false, updatable = false, table = "employees")
//    private Job job;
//
//    @ManyToOne
//    @JoinColumn(name="location_id", referencedColumnName = "id", insertable = false, updatable = false, table = "employees")
//    private Location location;
}
