package org.igorski.springkeycloak.services;

import org.igorski.springkeycloak.model.DataException;
import org.igorski.springkeycloak.model.UserDTO;
import org.igorski.springkeycloak.model.UserProfile;
import org.igorski.springkeycloak.repositories.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    private final UserProfileRepository repository;

    @Autowired
    public ProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    private boolean exists(String userId) {
        return repository.findByUserId(userId).isPresent();
    }

    public UserProfile createProfile(UserDTO user) throws DataException {
        if (exists(user.getId())) {
            throw new DataException("Profile for ID " + user.getId() + " already exists.");
        } else {
            UserProfile userProfile = new UserProfile();
            userProfile.setName(user.getName());
            userProfile.setSurname(user.getSurname());
            userProfile.setEmail(user.getEmail());
            userProfile.setUsername(user.getUsername());

            return repository.save(userProfile);
        }
    }
}
