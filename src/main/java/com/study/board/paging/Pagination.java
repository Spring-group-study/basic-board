package com.study.board.paging;

public class Pagination {

    private int currentPage;
    private int totalPostCount;
    private int totalPageCount;
    private int postCntPerPage;
    private int pageCntPerBlock;
    private int startPage;
    private int endPage;
    private int limitStart;
    private boolean existPrePage;
    private boolean existNextPage;

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
}
