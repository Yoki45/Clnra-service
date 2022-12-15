package io.clnra.c_l_n_r_a.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.math.BigDecimal;
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
public class CBORegistration {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 36)
    private String cBOName;

    @Column(nullable = false)
    private Integer cBOID;

    @Column
    private String country;

    @Column(nullable = false)
    private String cBORegistered;

    @Column
    private String regNumber;

    @Column
    private Integer approxPopulation;

    @Column(nullable = false, columnDefinition = "longtext")
    private String cBOAddress;

    @Column(precision = 10, scale = 1)
    private BigDecimal gPSLatitude;

    @Column(precision = 10, scale = 2)
    private BigDecimal gPSLongitude;

    @Column(nullable = false)
    private String cBOContact;

    @Column(nullable = false)
    private String cBOConstitution;

    @OneToMany(mappedBy = "cBORegistration")
    private Set<Administrative> cBORegistrationAdministratives;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "management",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "cbomanagement_id")
    )
    private Set<CBOManagement> managementCBOManagements;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "cbopartners",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "partners_id")
    )
    private Set<Partners> cBOPartnersPartnerss;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "properties",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "cboproperties_id")
    )
    private Set<CBOProperties> propertiesCBOPropertiess;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "project",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "cboprojects_id")
    )
    private Set<CBOProjects> projectCBOProjectss;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "activities",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "cboactivities_id")
    )
    private Set<CBOActivities> activitiesCBOActivitiess;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "cbohunting",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "hunting_id")
    )
    private Set<Hunting> cBOHuntingHuntings;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "wildlifepp",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "game_count_id")
    )
    private Set<GameCount> wildlifePPGameCounts;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "cbotourism",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "tourism_id")
    )
    private Set<Tourism> cBOTourismTourisms;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "human_conflict",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "hwcid")
    )
    private Set<HWC> humanConflictHWCs;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "ap",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "anti_poaching_id")
    )
    private Set<AntiPoaching> aPAntiPoachings;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "csrcbo",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "csrid")
    )
    private Set<CSR> cSRCBOCSRs;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "cbopatrols",
            joinColumns = @JoinColumn(name = "cboregistration_id"),
            inverseJoinColumns = @JoinColumn(name = "patrols_id")
    )
    private Set<Patrols> cBOPatrolsPatrolss;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
