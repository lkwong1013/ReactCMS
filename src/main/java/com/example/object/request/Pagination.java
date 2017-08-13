package com.example.object.request;

import lombok.Data;

/**
 * Created by LKW on 2016/12/14.
 */

@Data
public class Pagination {

    private Integer currentPage;

    private Integer previousPage;

    private Integer nextPage;

    private Integer lastPage;

    private Integer totalPage;

    private Integer totalRecord;

    public Pagination(Integer currentPage, Integer totalRecord) {

        BaseQuery baseQuery = new BaseQuery();
        Integer pageSize = baseQuery.getPageSize();

        if (currentPage == null) {
            currentPage = 0;
        }
        this.currentPage = currentPage;
        this.totalRecord = totalRecord;
        // Calculate total page

        if (totalRecord < pageSize) {
            // total record is smaller than page size, only 1 page needed
            totalPage = 1;
        } else {
            totalPage = totalRecord / pageSize;
            if (totalRecord % pageSize > 0) {
                totalPage += 1;
            }
        }

        lastPage = totalPage;       // Get last page

        // Calculate previous page
        if (currentPage.equals(0)) {
            previousPage = 0;
        } else {
            previousPage = currentPage - 1;
        }

        // Calculate next page
        if (currentPage < lastPage && totalPage > 1) {
            nextPage = currentPage + 1;
        } else {
            nextPage = 0;
        }

    }
}
