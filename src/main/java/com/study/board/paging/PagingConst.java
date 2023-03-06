package com.study.board.paging;

/**
 *  페이징 할 때, 바뀔수 있는 상수를 따로 관리하도록 클래스를 따로 빼버림
 */
public class PagingConst {
    private final int POST_CNT_PER_PAGE=10;     //한 페이지당 게시글 갯수
    private final int PAGE_CNT_PER_BLOCK = 5;   //한 블럭당 페이지 갯수

    public int getPOST_CNT_PER_PAGE() {
        return POST_CNT_PER_PAGE;
    }

    public int getPAGE_CNT_PER_BLOCK() {
        return PAGE_CNT_PER_BLOCK;
    }
}
