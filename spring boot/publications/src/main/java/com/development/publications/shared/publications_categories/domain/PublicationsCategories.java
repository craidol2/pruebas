package com.development.publications.shared.publications_categories.domain;

import com.development.publications.shared.categories.domain.Categories;
import com.development.publications.shared.publications.domain.Publications;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "PUBLICATIONS_CATEGORIES")

@Getter
@Setter
@NoArgsConstructor
public class PublicationsCategories {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_publication", referencedColumnName = "id", nullable = false)
    private Publications publication;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category", referencedColumnName = "id", nullable = false)
    private Categories category;
    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(name = "updated_at", columnDefinition = "TIMESTAMP")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
