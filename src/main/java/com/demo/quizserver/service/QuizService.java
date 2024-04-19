package com.demo.quizserver.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.demo.quizserver.model.Quiz;
import com.demo.quizserver.model.Response;
import com.demo.quizserver.model.Question;
import com.demo.quizserver.dao.QuizDao;
import com.demo.quizserver.dao.QuestionDao;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    private QuizDao quizDao;
    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<Quiz> getQuiz(Integer id) {
        try {
            Optional<Quiz> quiz = quizDao.findById(id);
            if (quiz.isPresent()) {
                return new ResponseEntity<>(quiz.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Quiz(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Quiz(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> createQuiz(String title, String category, int numQues) {
        try {
            List<Question> questions = questionDao.findRandomQuesByCategory(category, numQues);
            Quiz quiz = new Quiz();
            quiz.setQuestions(questions);
            quiz.setTitle(title);
            quizDao.save(quiz);
            return new ResponseEntity<>("Successfully created the quiz!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error creating quiz!", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> calculateResult(Integer id, List<Response> responses) {
        try {
            Quiz quiz = quizDao.findById(id).get();
            List<Question> questions = quiz.getQuestions();
            int result = 0;
            int idx = 0;
            for (Response response : responses) {
                if (response.getUserResponse().equals(questions.get(idx).getRightAnswer())) {
                    result++;
                }

                idx++;
            }
            return new ResponseEntity<>("Here are the results: " + result, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while calculating result!", HttpStatus.BAD_REQUEST);
        }
    }

}
