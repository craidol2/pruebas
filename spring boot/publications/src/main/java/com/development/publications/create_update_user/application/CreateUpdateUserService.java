package com.development.publications.create_update_user.application;

import com.development.publications.create_update_user.infrastructure.request.CreateUpdateUserRequest;
import com.development.publications.shared.users.domain.Users;
import com.development.publications.shared.users.domain.UsersRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateUpdateUserService {

    private final UsersRepository usersRepository;

    public CreateUpdateUserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    public void execute(CreateUpdateUserRequest request) {
        var userInDb = usersRepository.findById(request.getId()).orElse(null);
        if (userInDb == null) {
            var user = new Users();
            user.setId(request.getId());
            user.setName(request.getName());
            user.setEmail(request.getEmail());
            usersRepository.save(user);
        } else {
            userInDb.setName(request.getName());
            userInDb.setEmail(request.getEmail());
            usersRepository.save(userInDb);
        }
    }
}
