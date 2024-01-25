package com.serviceproject.web.dto;

import com.serviceproject.web.models.UserEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class Autoturismedto {
    private Long id;
    @NotEmpty(message = "Marca trebuie sa fie completata obligatoriu")
    private String marca;
    @NotEmpty(message = "Completeaza campul de poza")
    private String photoUrl;
    @NotEmpty(message = "Completeaza descrierea")
    private String descriere;
    private UserEntity createdBy;
    private LocalDateTime createOn;
    private LocalDateTime updateOn;
    private List<Eventdto> events;

}
