package io.aktivator.profile;

import io.aktivator.model.UserDTO;
import io.aktivator.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class ProfileService {

    private final UserService userService;
    private final ExtendedProfileRepository extendedProfileRepository;
    private final ExternalUserService externaluserService;

    @Autowired
    ProfileService(UserService userService, ExtendedProfileRepository extendedProfileRepository,
                   ExternalUserService externalUserService) {
        this.userService = userService;
        this.extendedProfileRepository = extendedProfileRepository;
        this.externaluserService = externalUserService;
    }

    Profile getProfile() {
        UserDTO current = userService.getCurrentUser();
        Profile profile = getProfileFromUser(current);
        profile.setExtendedProfile(getExtendedProfile(current.getId()));
        return profile;
    }

    private Profile getProfileFromUser(UserDTO current) {
        Profile profile = new Profile();
        profile.setEmail(current.getEmail());
        profile.setName(current.getName());
        profile.setSurname(current.getSurname());
        profile.setUsername(current.getUsername());
        return profile;
    }

    ExtendedProfile getExtendedProfile(String ownerId) {
        return extendedProfileRepository.findByOwnerId(ownerId).orElse(new ExtendedProfile());
    }

    Profile getPublicProfile(String ownerId) {
        ExtendedProfile extendedProfile = extendedProfileRepository.findByOwnerId(ownerId).orElseThrow();
        UserDTO userDTO = externaluserService.getUser(ownerId);
        Profile profile = getProfileFromUser(userDTO);
        profile.setEmail("");
        profile.setExtendedProfile(extendedProfile);
        return profile;
    }

    public ExtendedProfile updateProfile(ProfileUpdateRequest profileUpdateRequest) {
        UserDTO currentUser = userService.getCurrentUser();
        externaluserService.editUser(profileUpdateRequest, currentUser.getId());

        ExtendedProfile extendedProfile = extendedProfileRepository.findByOwnerId(currentUser.getId())
            .orElse(new ExtendedProfile());
        extendedProfile.setStory(profileUpdateRequest.getStory());
        extendedProfile.setOwnerId(currentUser.getId());
        return extendedProfileRepository.save(extendedProfile);
    }
}
