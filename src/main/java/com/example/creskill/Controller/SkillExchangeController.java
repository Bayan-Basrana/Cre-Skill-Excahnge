package com.example.creskill.Controller;

import com.example.creskill.ApiRespose.ApiResponse;
import com.example.creskill.Model.Projects;
import com.example.creskill.Model.SkillExchange;
import com.example.creskill.Service.SkillExchangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/skillExchange")
@RequiredArgsConstructor
public class SkillExchangeController {
    private final SkillExchangeService skillExchangeService;


    @GetMapping("/get")
    public ResponseEntity get (){
        return ResponseEntity.status(200).body(skillExchangeService.getSkillExchange());
    }

    @PostMapping("/addRequest")
    public ResponseEntity addRequest (@RequestBody @Valid SkillExchange request , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        skillExchangeService.addRequest(request);
        ;        return ResponseEntity.status(200).body(new ApiResponse("added successfully"));
    }


    @PutMapping("/update/{skillExchangeId}")
    public ResponseEntity update (@PathVariable Integer skillExchangeId ,@RequestBody @Valid SkillExchange skillExchange , Errors errors ){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        skillExchangeService.update(skillExchangeId,skillExchange);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }


    @DeleteMapping("/delete/{skillExchangeId}")
    public ResponseEntity delete (@PathVariable Integer skillExchangeId){
        skillExchangeService.delete(skillExchangeId);
        return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));
    }

    @GetMapping("/getAvailable")
    public ResponseEntity findAvailableExchange (){
        return ResponseEntity.status(200).body(skillExchangeService.findAvailableExchange());
    }

    @PutMapping("/startSkillExchange/{skillExchangeId}/{partnerId}")
    public ResponseEntity startSkillExchange (@PathVariable Integer skillExchangeId, @PathVariable Integer partnerId){
        skillExchangeService.startSkillExchange(skillExchangeId,partnerId);
        return ResponseEntity.status(200).body(new ApiResponse("enjoy ..."));
    }


    @PutMapping("/completeSkillExchange/{skillExchangeId}/{userId}")
    public ResponseEntity completeSkillExchange (@PathVariable Integer skillExchangeId, @PathVariable Integer userId){
        skillExchangeService.completeSkillExchange(skillExchangeId,userId);
        return ResponseEntity.status(200).body(new ApiResponse("completed skill exchange successfully, thank you. "));
    }

    @GetMapping("/randomNewSkillExchange/{partnerId}")
    public ResponseEntity randomNewSkillExchange (@PathVariable Integer partnerId){
        return ResponseEntity.status(200).body( skillExchangeService.randomNewSkillExchange(partnerId));
    }

}
