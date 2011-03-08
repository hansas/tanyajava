/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tanyajava.service.impl;

import com.tanyajava.dao.QuestionDao;
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

    @Transactional
    public void save(Question question) {
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

}