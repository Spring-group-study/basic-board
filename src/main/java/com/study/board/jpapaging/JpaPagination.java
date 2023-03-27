package com.study.board.jpapaging;


import java.util.ArrayList;
import java.util.List;

public class JpaPagination {

    public static int currentPage;      //현재페이지 (param으로 받음)
    public int totalPostCount;          //총 게시글 갯수
    public int totalPageCount;          //총 페이지 갯수
    public int postCntPerPage=JpaPagingConst.POST_CNT_PER_PAGE;          //한 페이지당 게시글 갯수
    public int pageCntPerBlock=JpaPagingConst.PAGE_CNT_PER_BLOCK;         //한 블럭당 페이지 갯수
    public int startPage;               //블럭에서의 시작페이지
    public int endPage;                 //블럭에서의 마지막 페이지
    public int prePageStartPage;        //이전블럭 존재여부
    public int nextPageStartPage;       //다음블럭 존재여부


    public JpaPagination(int totalPostCount, int currentPage) {
        if (totalPostCount > 0) {
            this.totalPostCount = totalPostCount;
        }
        this.currentPage = currentPage;
        calculation();
    }

    private void calculation() {

        totalPageCount = ((totalPostCount - 1) / postCntPerPage) + 1;

        startPage = ((currentPage -1)/ pageCntPerBlock)* pageCntPerBlock +1;

        endPage = startPage+ pageCntPerBlock -1>totalPageCount?totalPageCount:startPage+ pageCntPerBlock -1;

        prePageStartPage=(startPage==1)?1:startPage-5;

        nextPageStartPage = (endPage == totalPageCount) ? endPage : endPage + 1;
    }

    public List<Integer> pagesInCurrentBlock() {
        List<Integer> list = new ArrayList<>();
        for (int i = startPage; i <=endPage; i++) {
            list.add(i);
        }
        return list;
    }

}
