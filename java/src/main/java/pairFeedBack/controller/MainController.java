package pairFeedBack.controller;

import java.net.URI;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import pairFeedBack.dataTransferer.dto.DetailsPairDto;
import pairFeedBack.dataTransferer.dto.UserDto;
import pairFeedBack.dataTransferer.form.PairAddForm;
import pairFeedBack.dataTransferer.form.PairRatingForm;
import pairFeedBack.dataTransferer.form.PairUpdateForm;
import pairFeedBack.service.MainService;

@RestController
@RequestMapping("/me")
public class MainController {

    @Autowired
    MainService mainService;

    @GetMapping("/api/test")
    public ResponseEntity<String> nothing() {
        return ResponseEntity.ok("Api rest");
    }

    @GetMapping
    public UserDto usersWithPairs(HttpServletRequest request) {
        return mainService.getUserDtoById(request);
    }

    @GetMapping("/pair/{id}")
    public DetailsPairDto getPair(@PathVariable Long id, HttpServletRequest request){
        return mainService.getDetailsPairDtoById(id, request);
    }    

    @PostMapping("/pair")
    @Transactional
    public ResponseEntity<DetailsPairDto> addPair(@RequestBody @Valid PairAddForm form, HttpServletRequest request){
        DetailsPairDto dto = mainService.addPair(form, request);
        URI uri = UriComponentsBuilder.newInstance().path("/me/pair/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PatchMapping("/pair")
    @Transactional
    public ResponseEntity<DetailsPairDto> addFeedbackToPair(@RequestBody @Valid PairRatingForm form, HttpServletRequest request){
        DetailsPairDto dto = mainService.addFeedbackToPair(form, request);
        URI uri = UriComponentsBuilder.newInstance().path("/me/pair/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PatchMapping("/pair/att")
    @Transactional
    public ResponseEntity<DetailsPairDto> updatePair(@RequestBody @Valid PairUpdateForm form, HttpServletRequest request){
        DetailsPairDto dto = mainService.updatePair(form, request);
        URI uri = UriComponentsBuilder.newInstance().path("/me/pair/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
    
    @DeleteMapping("/pair/{id}")
    @Transactional
    public ResponseEntity<DetailsPairDto> deletePair(@PathVariable Long id, HttpServletRequest request){
        mainService.deletePair(id, request);
        return ResponseEntity.ok().build();
    }
}