package io.clnra.c_l_n_r_a.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.SequenceGenerator;
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
public class Hunting {

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

    @Column(nullable = false, unique = true, columnDefinition = "UUID")
    private UUID huntingLog;

    @Column(nullable = false)
    private String animalName;

    @Column(nullable = false, name = "\"year\"")
    private Integer year;

    @Column
    private Integer allocatedQuota;

    @Column
    private Long trophyFee;

    @Column
    private Integer numberHunted;

    @Column
    private Integer fixedQuota;

    @Column
    private Integer optionalQuota;

    @ManyToMany(mappedBy = "cBOHuntingHuntings")
    private Set<CBORegistration> cBOHuntingCBORegistrations;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
