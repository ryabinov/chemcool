package com.chemcool.school.braingames.twoofoureight.web.api.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.chemcool.school.braingames.twoofoureight.domain.UserScore;
import com.chemcool.school.braingames.twoofoureight.service.UserScoreService;

import java.util.Map;

@Controller
@RequestMapping("/game")
@RequiredArgsConstructor
public class TwoOFourEightController {

    private final UserScoreService userScoreService;

    @GetMapping()
    public String viewPage() {
        return "index";
    }

}
