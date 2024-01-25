package com.serviceproject.web.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "autoturisme1")
public class Autoturisme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marca;
    private String photoUrl;
    private String descriere;

    @CreationTimestamp
    private LocalDateTime createOn;

    @UpdateTimestamp
    private LocalDateTime updateOn;

    @ManyToOne
    @JoinColumn(name = "created_by", nullable = false)
    private UserEntity createdBy;
    @OneToMany(mappedBy = "autoturisme" , cascade = CascadeType.REMOVE)

    private List<Event> events = new ArrayList<>();

}
