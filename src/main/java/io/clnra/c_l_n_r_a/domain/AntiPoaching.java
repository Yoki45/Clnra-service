package io.clnra.c_l_n_r_a.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class AntiPoaching {

    @Id
    @Column(nullable = false, updatable = false)
    @SequenceGenerator(
            name = "primary_sequence",
            sequenceName = "primary_sequence",
            allocationSize = 1,
            initialValue = 10000
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "primary_sequence"
    )
    private Long id;

    @Column(nullable = false, columnDefinition = "UUID")
    private UUID antiPoachingLog;

    @Column
    private LocalDate date;

    @Column
    private String siteName;

    @Column
    private Integer numberOfGuards;

    @Column
    private Integer numberOfArms;

    @Column
    private String aPCoverageArea;

    @Column
    private String measureOfAPEffort;

    @Column
    private String indicatorIllegalActivities;

    @Column
    private String encounterRateIllegalActivities;

    @Column
    private String primaryIndicators;

    @Column
    private String secondaryIndicators;

    @Column
    private String aPAction;

    @ManyToMany(mappedBy = "aPAntiPoachings")
    private Set<CBORegistration> aPCBORegistrations;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
