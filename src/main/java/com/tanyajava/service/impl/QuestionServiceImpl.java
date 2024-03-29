/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tanyajava.service.impl;

import com.tanyajava.dao.QuestionDao;
import com.tanyajava.dao.UserDao;
import com.tanyajava.model.Question;
import com.tanyajava.model.Tag;
import com.tanyajava.service.QuestionService;
import java.util.List;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ifnu
 */
@Service
@Transactional(readOnly=true)
public class QuestionServiceImpl implements QuestionService{

    @Autowired private QuestionDao questionDao;
    @Autowired private UserDao userDao;

    @Transactional(readOnly=false)
    public void save(Question question) {
        if(question.getUser()!=null && question.getUser().getId() == null){
            userDao.save(question.getUser());
        }
        questionDao.save(question);
    }

    @Transactional
    public void delete(Question question) {
        questionDao.delete(question);
    }

    public Question getQuestion(Long id) {
        Question q = questionDao.findById(id);
        Hibernate.initialize(q.getTags());
        Hibernate.initialize(q.getAnswers());
        Hibernate.initialize(q.getUser().getBadges());
        if(q.getAnswer() != null){
            q.getAnswers().remove(q.getAnswer());
        }
        return q;
    }

    public List<Question> getQuestion(int start, int num) {
        return questionDao.getQuestion(start,num);
    }

    public List<Question> getQuestion(String keyword, int start, int num) {
        return questionDao.getQuestion(keyword,start,num);
    }

    public List<Question> getQuestion(Tag tag, int start, int num) {
        return questionDao.getQuestion(tag,start,num);
    }

    public Question getSimpleQuestion(Long id) {
       return questionDao.findById(id);
    }

}
