package com.rose.note.util;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Page<T> {
    private Integer pageNum; //current page
    private Integer pageSize;
    private long totalCount;

    private Integer totalPages;
    private Integer prePage;
    private Integer nextPage;

    private Integer startNavPage; // cur page - 5
    private Integer endNavPage; // cur page + 4

    private List<T> dataList; //data of cur page

    public Page(Integer pageNum, Integer pageSize, long totalCount) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.totalCount = totalCount;

        this.totalPages = (int) Math.ceil(totalCount / (pageSize * 1.0));
        this.prePage = pageNum - 1 < 1 ? 1 : pageNum - 1;
        this.nextPage = pageNum + 1 > totalPages ? totalPages : pageNum + 1;

        this.startNavPage = pageNum - 5;
        this.endNavPage = pageNum + 4;

        if (this.startNavPage < 1) {
            this.startNavPage = 1;
            this.endNavPage = this.startNavPage + 9 > totalPages ? totalPages : this.startNavPage + 9;
        }

        if (this.endNavPage > totalPages) {
            this.endNavPage = totalPages;
            this.startNavPage = this.endNavPage - 9 < 1 ? 1 : this.endNavPage - 9;
        }

    }
}
