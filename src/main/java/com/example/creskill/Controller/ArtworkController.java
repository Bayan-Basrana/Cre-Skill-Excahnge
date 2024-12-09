package com.example.creskill.Controller;

import com.example.creskill.ApiRespose.ApiResponse;
import com.example.creskill.Model.Artwork;
import com.example.creskill.Model.Projects;
import com.example.creskill.Service.ArtworkService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/artwork")
@RequiredArgsConstructor
public class ArtworkController {

private final ArtworkService artworkService;


    @GetMapping("/get")
    public ResponseEntity get (){
        return ResponseEntity.status(200).body(artworkService.get());
    }


    @PostMapping("/add2")
    public ResponseEntity add2 (@RequestBody @Valid List<Artwork>  artworks , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
artworkService.add2(artworks);
        return ResponseEntity.status(200).body(new ApiResponse("added successfully"));
    }
    @PostMapping("/add")
    public ResponseEntity add (@RequestBody @Valid Artwork artwork , Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        artworkService.add(artwork)
        ;        return ResponseEntity.status(200).body(new ApiResponse("added successfully"));
    }

    @PutMapping("/update/{artworkId}")
    public ResponseEntity update (@PathVariable Integer artworkId , @RequestBody @Valid Artwork artwork , Errors errors ){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        artworkService.update(artworkId,artwork);
        return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
    }

    @DeleteMapping("/delete/{artworkId}")
    public ResponseEntity delete (@PathVariable Integer artworkId){
        artworkService.delete(artworkId);
        return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));
    }

@PutMapping("/like/{artworkId}")
    public ResponseEntity likeArtwork (@PathVariable Integer artworkId ){
        artworkService.likeArtwork(artworkId);
        return ResponseEntity.status(200).body(new ApiResponse("Artwork liked successfully." ));
    }

    @GetMapping("/top")
    public ResponseEntity topArtwork (){
        return ResponseEntity.status(200).body(artworkService.topArtwork());
    }

    @GetMapping("/artworkAndCountByUser/{userid}")
    public ResponseEntity getArtworkAndCountByUser (@PathVariable Integer userid){
        return ResponseEntity.status(200).body(artworkService.getArtworkAndCountByUser(userid));
    }

}
