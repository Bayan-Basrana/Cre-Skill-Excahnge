package com.example.creskill.Service;

import com.example.creskill.ApiRespose.ApiException;
import com.example.creskill.Model.ProjectOwner;
import com.example.creskill.Model.Talent;
import com.example.creskill.Repostory.ProjectOwnerRepository;
import com.example.creskill.Repostory.ProjectsRepository;
import com.example.creskill.Repostory.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectOwnerService {

    private final ProjectOwnerRepository projectOwnerRepository;
    private final ProjectsRepository projectsRepository;
    private final RatingRepository ratingRepository;

    public List<ProjectOwner> getOwners() {
        return projectOwnerRepository.findAll();
    }

    public void add2 (List<ProjectOwner> owners){
        projectOwnerRepository.saveAll(owners);
    }
    public void add(ProjectOwner projectOwner) {
        projectOwnerRepository.save(projectOwner);
    }

    public void update(Integer ownerId, ProjectOwner projectOwner) {
        ProjectOwner old = projectOwnerRepository.findProjectOwnerByOwnerId(ownerId);

        if (old == null) {
            throw new ApiException("owner id not found");
        }
        old.setName(projectOwner.getName());
        old.setEmail(projectOwner.getEmail());
        old.setPassword(projectOwner.getPassword());

        projectOwnerRepository.save(old);
    }


    public void delete(Integer ownerId) {
        ProjectOwner projectOwner = projectOwnerRepository.findProjectOwnerByOwnerId(ownerId);
        if (projectOwner == null) {
            throw new ApiException("owner id not found");
        }
        projectOwnerRepository.delete(projectOwner);
    }


    public void checkAndVerifyOwner (Integer ownerId){
        ProjectOwner projectOwner= projectOwnerRepository.findProjectOwnerByOwnerId(ownerId);
        if (projectOwner==null){
            throw new ApiException("owner id not found");
        }
        Integer completedProject = projectsRepository.countCompletedProjectForOwner(ownerId);
        if (completedProject<2 ) {
            throw new ApiException("contention not met,must complete at least 2 projects");
        }
        if (projectOwner.getPoints()<100 ) {
            throw new ApiException("contention not met, points must be grater than 100");
        }
        Double avrgForUser=ratingRepository.findAverageForUser(ownerId);
        if (avrgForUser<4.0 || avrgForUser==null){
            throw new ApiException("contention not met, rating must be at lest 4");
        }
            projectOwner.setIsVerified(true);
            projectOwnerRepository.save(projectOwner);

    }
}
