package com.study.board.paging;


import com.study.board.entity.Post;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

    public static int currentPage;      //현재페이지 (param으로 받음)
    public int totalPostCount;          //총 게시글 갯수
    public int totalPageCount;          //총 페이지 갯수
    public int postCntPerPage;          //한 페이지당 게시글 갯수
    public int pageCntPerBlock;         //한 블럭당 페이지 갯수
    public int startPage;               //블럭에서의 시작페이지
    public int endPage;                 //블럭에서의 마지막 페이지
    public int limitStart;              //쿼리문에서 limit 숫자
    public boolean existPrePage;        //이전블럭 존재여부
    public boolean existNextPage;       //다음블럭 존재여부


    public Pagination(int totalPostCount, int currentPage, int postCntPerPage, int pageCntPerBlock) {
        if (totalPostCount > 0) {
            this.totalPostCount = totalPostCount;
        }
        this.currentPage = currentPage;
        this.postCntPerPage = postCntPerPage;
        this.pageCntPerBlock = pageCntPerBlock;
        calculation();
    }

    private void calculation() {

        totalPageCount = ((totalPostCount - 1) / postCntPerPage) + 1;

        startPage = ((currentPage -1)/ pageCntPerBlock)* pageCntPerBlock +1;

        endPage = startPage+ pageCntPerBlock -1>totalPageCount?totalPageCount:startPage+ pageCntPerBlock -1;

        limitStart = (currentPage - 1) * postCntPerPage;

        existPrePage = (startPage != 1);

        existNextPage = (endPage * postCntPerPage) < totalPostCount;

    }



    public List<Integer> pagesToList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= totalPageCount; i++) {
            list.add(i);
        }
        return list;
    }

    public List<Integer> pagesInCurrentBlock() {
        List<Integer> list = new ArrayList<>();
        for (int i = startPage; i <=endPage; i++) {
            list.add(i);
        }
        return list;
    }
/*
    public List<Boolean> preNextBoolean() {
        List<Boolean> list = new ArrayList<>();
        list.add(existPrePage);
        list.add(existNextPage);
        return list;
    }

    public List<Integer> preNextStartPage(){
        List<Integer> list = new ArrayList<>();
        int prePageStart = startPage - pageCntPerBlock;
        int nextPageStart = endPage + 1;
        list.add(prePageStart);
        list.add(nextPageStart);
        return list;
    }
*/



}
