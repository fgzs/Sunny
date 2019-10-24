package com.example.sunny.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private List<Integer> pageList = new ArrayList<>();

    public void setPaganation(Integer totalCount, Integer pageNum, Integer pageSize) {
        currentPage=pageNum;
        Integer totalPage = totalCount / pageSize;
        if (totalCount % pageSize != 0) {
            totalPage++;
        }

        pageList.add(pageNum);
        for (int i = 1; i <= 3; i++) {
            if (pageNum - i > 0) {
                pageList.add(0, pageNum - i);
            }
            if (pageNum + i <= totalPage) {
                pageList.add(pageNum + i);

            }
        }


        //展示上一页和下一页
        if (pageNum == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        if (pageNum == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }


        if (pageList.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }
        if (pageList.contains(totalPage)) {
            showEndPage = false;
        } else {
            showEndPage = true;
        }

    }

}
