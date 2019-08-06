package pairFeedBack.controller;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pairFeedBack.dataTransferer.dto.DetailsPairDto;
import pairFeedBack.dataTransferer.dto.UserDto;
import pairFeedBack.dataTransferer.form.PairRatingForm;
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
        return mainService.getUserDtoById(getUserId(request));
    }

    @GetMapping("/pair/{id}")
    public DetailsPairDto getPair(@PathVariable Long id, HttpServletRequest request){
        return mainService.getDetailsPairDtoById(id, getUserId(request));
    }    

    @PostMapping("/pair")
    @Transactional
    public DetailsPairDto addFeedBackToPair(@RequestBody @Valid PairRatingForm form, HttpServletRequest request){
        return mainService.addFeedBackToPair(form, getUserId(request));
    }

    private Long getUserId(HttpServletRequest request){
        return (Long) request.getAttribute("userId");
    }
}