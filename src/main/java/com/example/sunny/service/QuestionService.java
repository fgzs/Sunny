package com.example.sunny.service;

import com.example.sunny.dto.PaginationDTO;
import com.example.sunny.dto.QuestionDTO;
import com.example.sunny.mapper.QuestionMapper;
import com.example.sunny.mapper.UserMapper;
import com.example.sunny.model.Question;
import com.example.sunny.model.User;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO selectByPage(Integer pageNum, Integer pageSize) {
        PaginationDTO paginationDTO = new PaginationDTO();

        PageHelper.startPage(pageNum,pageSize);
        List<Question> questionList = questionMapper.selectByPage();



        List<QuestionDTO> questionDTOList = new ArrayList<>();
        for (Question question : questionList) {
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        Integer totalCount=questionMapper.count();
        paginationDTO.setQuestionDTOList(questionDTOList);
        paginationDTO.setPaganation(totalCount,pageNum,pageSize);
        return paginationDTO;
    }
}
