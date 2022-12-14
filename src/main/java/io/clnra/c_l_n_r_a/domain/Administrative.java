package io.clnra.c_l_n_r_a.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import java.time.OffsetDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Administrative {

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

    @Column(nullable = false, length = 15)
    private String country;

    @Column(nullable = false, length = 5)
    private String countryCode;

    @Column(nullable = false, length = 25)
    private String countryCurrency;

    @Column(nullable = false, length = 5)
    private String currencyCode;

    @Column(length = 35)
    private String regionName;

    @Column(length = 30)
    private String province;

    @Column(length = 30)
    private String district;

    @Column(length = 30)
    private String subDistrict;

    @Column(length = 30)
    private String ward;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cboregistration_id")
    private CBORegistration cBORegistration;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

}
