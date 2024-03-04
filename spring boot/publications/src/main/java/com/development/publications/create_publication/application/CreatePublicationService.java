package com.development.publications.create_publication.application;

import com.development.publications.create_publication.infrastructure.request.CreatePublicationRequest;
import com.development.publications.shared.categories.domain.CategoriesRepository;
import com.development.publications.shared.publications.domain.PublicationState;
import com.development.publications.shared.publications.domain.Publications;
import com.development.publications.shared.publications.domain.PublicationsRepository;
import com.development.publications.shared.publications_categories.domain.PublicationsCategories;
import com.development.publications.shared.publications_categories.domain.PublicationsCategoriesRepository;
import com.development.publications.shared.users.domain.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreatePublicationService {

    private final PublicationsCategoriesRepository publicationsCategoriesRepository;
    private final PublicationsRepository publicationsRepository;
    private final UsersRepository usersRepository;
    private final CategoriesRepository categoriesRepository;

    public CreatePublicationService(PublicationsCategoriesRepository publicationsCategoriesRepository, PublicationsRepository publicationsRepository, UsersRepository usersRepository, CategoriesRepository categoriesRepository) {
        this.publicationsCategoriesRepository = publicationsCategoriesRepository;
        this.publicationsRepository = publicationsRepository;
        this.usersRepository = usersRepository;
        this.categoriesRepository = categoriesRepository;
    }

    public void execute(CreatePublicationRequest request) {

        //Save publication
        var publication = new Publications();
        publication.setUser(usersRepository.getReferenceById(request.getIdUser()));
        publication.setTitle(request.getTitle());
        publication.setDescription(request.getDescription());
        publication.setTargetAmount(request.getTargetAmount());
        publication.setDeadline(request.getDeadline());
        publication.setRefundDate(request.getRefundDate());
        publication.setCurrentAmount(0);
        publication.setInterest(request.getInterest());
        publication.setState(PublicationState.ACTIVE.name());
        publication.setImage(request.getImage());
        var publicationSaved = publicationsRepository.save(publication);

        //Set publications-categories relationship
        PublicationsCategories publicationCategory;
        for (var idCategory : request.getCategories()) {
            publicationCategory = new PublicationsCategories();
            publicationCategory.setPublication(publicationSaved);
            publicationCategory.setCategory(categoriesRepository.getReferenceById(idCategory));
            publicationsCategoriesRepository.save(publicationCategory);
        }
    }
}
