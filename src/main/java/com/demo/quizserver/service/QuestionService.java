package com.demo.quizserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.quizserver.dao.QuestionDao;
import com.demo.quizserver.model.Question;

import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public String addQuestion(Question question) {
        System.out.println("Here's the question rec: " + question);
        questionDao.save(question);
        return "Successfully, created the question!";
    }

    public List<Question> getByCategory(String category) {
        return questionDao.findByCategory(category);
    }
}
