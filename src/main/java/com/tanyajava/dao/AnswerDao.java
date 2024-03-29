/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.tanyajava.dao;

import com.tanyajava.dao.base.BaseDaoHibernate;
import com.tanyajava.model.Answer;
import com.tanyajava.model.Question;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author ifnu
 */
@Repository
public class AnswerDao extends BaseDaoHibernate<Answer>{

    public List<Answer> getAnswer(Question question, int start, int num) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Answer a where a.question=:question ")
                .setEntity("question", question)
                .setFirstResult(start)
                .setMaxResults(num)
                .list();
    }


}
