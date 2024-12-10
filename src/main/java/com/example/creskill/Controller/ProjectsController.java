package com.example.creskill.Controller;

import com.example.creskill.ApiRespose.ApiResponse;
import com.example.creskill.Model.Projects;
import com.example.creskill.Service.ProjectsService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/project")
@RequiredArgsConstructor
public class ProjectsController {

    private final ProjectsService projectsService;

    @GetMapping("/get")
    public ResponseEntity getProject (){
        return ResponseEntity.status(200).body(projectsService.getProjects());
    }

    @PostMapping("/add")
    public ResponseEntity add (@RequestBody @Valid Projects projects , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
projectsService.add(projects)
;        return ResponseEntity.status(200).body(new ApiResponse("added successfully"));
    }

    @PutMapping("/update/{projectId}")
    public ResponseEntity update (@PathVariable Integer projectId ,@RequestBody @Valid Projects projects , Errors errors ){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        projectsService.update(projectId,projects);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }

    @DeleteMapping("/delete/{projectId}")
    public ResponseEntity delete (@PathVariable Integer projectId){
projectsService.delete(projectId);
return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));
    }

@PutMapping("/deliverByTalent/{projectId}/{talentId}")
    public ResponseEntity deliverByTalent (@PathVariable Integer projectId ,@PathVariable Integer talentId) {
        projectsService.deliverByTalent(projectId,talentId);
        return ResponseEntity.status(200).body(new ApiResponse("project delivered successfully"));
    }


    
    @PutMapping("/completeProject/{projectId}/{projectOwnerId}")
public ResponseEntity completeProject (@PathVariable Integer projectId ,@PathVariable Integer projectOwnerId) {
        projectsService.completeProject(projectId,projectOwnerId);
    return ResponseEntity.status(200).body(new ApiResponse("project completed successfully , and points updates"));
}

@GetMapping("findProjectMatchSkills/{talentId}")
public ResponseEntity findProjectMatchSkills (@PathVariable Integer talentId) {
    List<Projects> recommendedSkill = projectsService.findProjectMatchSkills(talentId);
    return ResponseEntity.status(200).body(recommendedSkill);
}
//show project with specific budget or less
@GetMapping("/projectsLessBudget/{budget}")
public ResponseEntity getProjectsLessBudget (@PathVariable Double budget){
        return ResponseEntity.status(200).body(projectsService.getProjectsLessBudget(budget));
}

//show project with specific budget or more
    @GetMapping("/projectsMoreBudget/{budget}")
    public ResponseEntity getProjectsMoreBudget (@PathVariable Double budget){
        return ResponseEntity.status(200).body(projectsService.getProjectsMoreBudget(budget));
    }



}
