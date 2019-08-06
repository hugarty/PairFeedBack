package pairFeedBack.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pairFeedBack.dto.DetailsPairDto;
import pairFeedBack.dto.UserDto;
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

    private Long getUserId(HttpServletRequest request){
        return (Long) request.getAttribute("userId");
    }
}