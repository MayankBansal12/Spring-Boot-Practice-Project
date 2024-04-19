package com.demo.quizserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.quizserver.model.Quiz;
import com.demo.quizserver.model.Response;
import com.demo.quizserver.service.QuizService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    // /quiz/:id -> Get the quiz with that id
    @GetMapping("{id}")
    public ResponseEntity<Quiz> getQuiz(@PathVariable String id) {
        return quizService.getQuiz(Integer.parseInt(id));
    }

    // /create?title&category&numQues -> Create quiz for that category and numQues
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String title, @RequestParam String category,
            @RequestParam String numQues) {
        return quizService.createQuiz(title, category, Integer.parseInt(numQues));
    }

    // /submit/:id -> For calculating the user's results
    @PostMapping("submit/{id}")
    public ResponseEntity<String> submitQuiz(@PathVariable String id, @RequestBody List<Response> responses) {
        return quizService.calculateResult(Integer.parseInt(id), responses);
    }

}