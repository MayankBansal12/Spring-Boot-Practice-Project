package com.demo.quizserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("question")
// Handles Requests For route /question
public class QuizController {

    // /question/allQuestions
    @GetMapping("allQuestions")
    public String getQuestions() {
        return "Hey! All the questions are here!";
    }
}
