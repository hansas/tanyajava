/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tanyajava.controller;

import com.tanyajava.model.Answer;
import com.tanyajava.model.Question;
import com.tanyajava.model.User;
import com.tanyajava.model.UserPreference;
import com.tanyajava.service.AnswerService;
import com.tanyajava.service.QuestionService;
import com.tanyajava.service.UserService;
import com.tanyajava.ui.form.AnswerForm;
import com.tanyajava.ui.form.QuestionForm;
import java.util.TimeZone;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author ifnu
 */
@Controller
public class AnswerController {

    @Autowired
    private QuestionService questionService;
    
    @Autowired
    private UserService userService;
    
    @Autowired 
    private AnswerService answerService;

    @RequestMapping(value = "/answer/{id}", method = RequestMethod.GET)
    public String answer(@PathVariable Long id, Model model) {
        Question q = questionService.getQuestion(id);
        model.addAttribute(q);
        //check if user already login or not
        Boolean login = System.currentTimeMillis() % 2 == 0 ? Boolean.TRUE : Boolean.FALSE;
        model.addAttribute("login", login);
        return "/answer";
    }

}
