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
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class HWC {

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

    @Column(nullable = false)
    private String hWCLog;

    @Column
    private LocalDate date;

    @Column
    private String problemSpecies;

    @Column
    private String placeIncident;

    @Column
    private String hWCType;

    @Column
    private String livestockKilled;

    @Column
    private Integer valueOfLivestock;

    @Column
    private String livestockImage;

    @Column
    private String cropsDamaged;

    @Column
    private Integer valueOfCrops;

    @Column
    private Integer infrastructureValue;

    @Column
    private Integer numberOfMales;

    @Column
    private Integer numberOfFemale;

    @Column
    private String damageConfirmed;

    @Column
    private String reported;

    @Column
    private String respondedBy;

    @Column
    private String actionTaken;

    @Column
    private String complainantName;

    @Column
    private String addressComplainant;

    @Column
    private String investigatedBy;

    @Column
    private String comments;

    @ManyToMany(mappedBy = "humanConflictHWCs")
    private Set<CBORegistration> humanConflictCBORegistrations;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
