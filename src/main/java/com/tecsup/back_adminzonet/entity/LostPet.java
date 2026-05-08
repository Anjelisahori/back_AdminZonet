package com.tecsup.back_adminzonet.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "lost_pets")
@Data
public class LostPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT") // Por si la descripción es muy larga
    private String description;

    @Column(name = "last_seen_location")
    private String lastSeenLocation;

    @Column(name = "last_seen_latitude")
    private Double lastSeenLatitude;

    @Column(name = "last_seen_longitude")
    private Double lastSeenLongitude;

    @Column(name = "report_date")
    private LocalDateTime reportDate;

    @Column(name = "pet_id")
    private Long petId;

    @Column(name = "hours_lost")
    private Integer hoursLost;

    // Cambiado de byte[] a Boolean para que el Front lo entienda fácil
    @Column(name = "found")
    private Boolean found;
}