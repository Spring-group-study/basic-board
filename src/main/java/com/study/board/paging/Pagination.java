package com.study.board.paging;


import com.study.board.entity.Post;

import java.util.ArrayList;
import java.util.List;

public class Pagination {

    public static int currentPage;
    public int totalPostCount;
    public int totalPageCount;
    public int postCntPerPage;
    public int pageCntPerBlock;
    public int startPage;
    public int endPage;
    public int limitStart;
    public boolean existPrePage;
    public boolean existNextPage;


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



}
