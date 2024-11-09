package tech.nuqta.wellnest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.nuqta.wellnest.common.CurrentUser;
import tech.nuqta.wellnest.common.ResponseMessage;
import tech.nuqta.wellnest.dto.ProfileDto;
import tech.nuqta.wellnest.entity.Profile;
import tech.nuqta.wellnest.repository.ProfileRepository;
import tech.nuqta.wellnest.repository.UserRepository;
import tech.nuqta.wellnest.request.CreateProfileRequest;

/***
 * Implementation of the UserService interface that provides methods for interacting with user entities.
 * It is responsible for updating, deleting, retrieving, and managing user data.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final ProfileRepository profileRepository;


    public ResponseMessage createProfile(CreateProfileRequest request) {
        var currentUser = CurrentUser.getCurrentUser();
        Profile newProfile = toProfileEntity(request);
        newProfile.setUser(currentUser);
        profileRepository.save(newProfile);

        return new ResponseMessage(toProfileDto(newProfile), "Profile created successfully");
    }

    private Profile toProfileEntity(CreateProfileRequest request) {
        Profile profile = new Profile();
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setGender(request.getGender());
        profile.setHeight(request.getHeight());
        profile.setWeight(request.getWeight());
        profile.setDateOfBirth(request.getDateOfBirth());
        profile.setDietaryPreferences(request.getDietaryPreferences());
        profile.setGoals(request.getGoals());
        return profile;
    }

    private ProfileDto toProfileDto(Profile profile) {
        return new ProfileDto(
                profile.getId(),
                profile.getUser().getId(),
                profile.getFirstName(),
                profile.getLastName(),
                profile.getDateOfBirth(),
                profile.getGender(),
                profile.getHeight(),
                profile.getWeight(),
                profile.getDietaryPreferences(),
                profile.getGoals()
        );
    }
}