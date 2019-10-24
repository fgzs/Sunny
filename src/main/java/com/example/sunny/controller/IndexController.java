package com.example.sunny.controller;

import com.example.sunny.dto.PaginationDTO;
import com.example.sunny.mapper.UserMapper;
import com.example.sunny.model.User;
import com.example.sunny.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer pageNum,
                        @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {
        Cookie[] cookies = request.getCookies();


        System.out.println("__________"+pageNum);
        //判断用户是否在线
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }



        PaginationDTO paginationDTOList = questionService.selectByPage(pageNum,pageSize);
        System.out.println(paginationDTOList.getCurrentPage());


        model.addAttribute("paginationDTOList", paginationDTOList);
        return "index";

    }
}