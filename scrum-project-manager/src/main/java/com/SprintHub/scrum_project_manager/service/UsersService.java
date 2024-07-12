package com.SprintHub.scrum_project_manager.service;

import com.SprintHub.scrum_project_manager.exception.AlreadyExistsException;
import com.SprintHub.scrum_project_manager.exception.NotFoundException;
import com.SprintHub.scrum_project_manager.model.Users;
import com.SprintHub.scrum_project_manager.repository.UsersRepository;
import com.SprintHub.scrum_project_manager.util.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    public Users searchUser(String tokenUser) {
        return usersRepository.searchUser(tokenUser);
    }

    public Users getUserByEmail(String email) {
        Optional<Users> users = usersRepository.getUserByEmail(email);
        if (users.isEmpty()){
            throw new NotFoundException(Constants.USER_EMAIL_NOT_FOUND.getMessage());
        }
        return users.get();
    }

    public Users createUser(Users userCreate) {
        //userCreate.setPasswordUser(userCreate.getPasswordUser());
        return usersRepository.save(userCreate);
    }

    public Users updateUser(Users user) {
        Optional<Users> usersBd = usersRepository.getUserByEmail(user.getEmailUser());
        if (usersBd.isEmpty()) {
           throw new AlreadyExistsException(Constants.USER_EMAIL_NOT_FOUND.getMessage());
        }
        usersBd.get().setPasswordUser(user.getPasswordUser());
        return usersRepository.save(usersBd.get());
    }
}
