package com.development.publications.shared.publications.domain;

import com.development.publications.shared.users.domain.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "PUBLICATIONS")

@Getter
@Setter
@NoArgsConstructor
public class Publications {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user", referencedColumnName = "id", nullable = false)
    private Users user;
    @Column(name = "title", nullable = false, length = 50)
    private String title;
    @Column(name = "description", nullable = false, length = 50)
    private String description;
    @Column(name = "target_amount", nullable = false)
    private Integer targetAmount;
    @Column(name = "published_date", nullable = false, columnDefinition = "DATE")
    @CreationTimestamp
    private LocalDate publishedDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "deadline", nullable = false)
    private Date deadline;
    @Temporal(TemporalType.DATE)
    @Column(name = "refund_date", nullable = false)
    private Date refundDate;
    @Column(name = "current_amount", nullable = false)
    private Integer currentAmount;
    @Column(name = "interest", nullable = false)
    private Integer interest;
    @Column(name = "state", nullable = false, length = 20)
    private String state;
    @Column(name = "image", nullable = false, length = 200)
    private String image;
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
