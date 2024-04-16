package com.demo.quizserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.quizserver.dao.QuestionDao;
import com.demo.quizserver.model.Question;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionDao questionDao;

    public List<Question> getAllQuestions() {
        return questionDao.findAll();
    }

    public Question getQuestion(Integer id) {
        Optional<Question> question = questionDao.findById(id);
        if (question.isPresent()) {
            return question.get();
        } else {
            return new Question();
        }
    }

    public String addQuestion(Question question) {
        questionDao.save(question);
        return "Successfully, created the question!";
    }

    public List<Question> getByCategory(String category) {
        return questionDao.findByCategory(category);
    }

    public String deleteQuestion(Integer id) {
        Optional<Question> existingQuestion = questionDao.findById(id);
        if (existingQuestion.isPresent()) {
            Question question = existingQuestion.get();
            questionDao.delete(question);
            return "Question deleted successfully!";
        } else {
            return "Question doesn't exist";
        }
    }

    public String updateQuestion(Integer id, Question newQuestion) {
        Optional<Question> existingQuestion = questionDao.findById(id);
        if (existingQuestion.isPresent()) {
            newQuestion.setId(id);
            questionDao.save(newQuestion);
            return "Updated successfully!";
        } else {
            return "Question is not present!";
        }
    }
}
