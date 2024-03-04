package com.development.publications.shared.publications_categories.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationsCategoriesRepository extends JpaRepository<PublicationsCategories, Long> {
}
