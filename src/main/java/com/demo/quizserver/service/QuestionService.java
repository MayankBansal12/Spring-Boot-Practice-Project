package com.demo.quizserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.demo.quizserver.dao.QuestionDao;
import com.demo.quizserver.model.Question;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<List<Question>> getByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<Question> getQuestion(Integer id) {
        try {
            Optional<Question> question = questionDao.findById(id);
            if (question.isPresent()) {
                return new ResponseEntity<>(question.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new Question(), HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Successfully, created the question!", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while creating!", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> deleteQuestion(Integer id) {
        try {
            Optional<Question> existingQuestion = questionDao.findById(id);
            if (existingQuestion.isPresent()) {
                Question question = existingQuestion.get();
                questionDao.delete(question);
                return new ResponseEntity<>("Question deleted successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Question doesn't exist", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while deleting!", HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question newQuestion) {
        try {
            Optional<Question> existingQuestion = questionDao.findById(id);
            if (existingQuestion.isPresent()) {
                newQuestion.setId(id);
                questionDao.save(newQuestion);
                return new ResponseEntity<>("Updated successfully!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Question is not present!", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error while updating!", HttpStatus.BAD_REQUEST);
        }
    }
}
