package com.Profiling.Profiling.api;


import com.Profiling.Profiling.model.ProfileTag;
import com.Profiling.Profiling.repository.ProfileTagRepository;
import com.Profiling.Profiling.service.ClickIncrement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class ProfieTagEndpoint {



    @Autowired
    public ProfileTagRepository profileTagRepository;

    @Autowired
    public ClickIncrement clickIncrement;

    public Optional<ProfileTag> ExistingProfileTag;

    @PostMapping("/ProfileTag")
    public ProfileTag saveProfileTag (@RequestBody ProfileTag profileTag){
        if(  profileTagRepository.findById(profileTag.getClick()).isPresent() ){
            ExistingProfileTag = profileTagRepository.findById(profileTag.getClick());
            clickIncrement.incrementClickTag(ExistingProfileTag.get());
            profileTagRepository.save(ExistingProfileTag.get());


            return ExistingProfileTag.get();
        }else {
            clickIncrement.incrementClickTag(profileTag);
            profileTagRepository.save(profileTag);
            return profileTag;
        }
    }


}
