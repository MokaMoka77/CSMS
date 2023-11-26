package com.khoabug.coffeshop.common.paging;

import com.khoabug.coffeshop.common.services.RequestMappingService;
import jakarta.servlet.http.HttpServletRequest;

public class Pageable {

    private int page;
    private int size;
    private int totalItem;
    private Sorter sorter;

    public static Pageable of(Sorter sorter, HttpServletRequest request, int totalItem) {
        Pageable pageable = RequestMappingService.toModel(Pageable.class, request);
        pageable.setSorter(sorter);
        pageable.setTotalItem(totalItem);
        return pageable;
    }

    public int getPage() {
        return page;
    }

    public int getOffset() {
        if (page != -1 && size != -1) {
            return (page - 1) * size;
        }
        return -1;
    }

    public int getSize() {
        return size;
    }

    public Sorter getSorter() {
        return (sorter != null) ? sorter : null;
    }

    public static Sorter getSorterOr(HttpServletRequest request, Sorter sorter) {
        Sorter requestSort = Sorter.of(request);
        return requestSort != null ? requestSort : sorter;
    }

    public int getTotalItem() {
        return totalItem;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setTotalItem(int totalItem) {
        this.totalItem = totalItem;
    }

    public void setSorter(Sorter sorter) {
        this.sorter = sorter;
    }

    public int getTotalPage() {
        return (int) Math.ceil((double) totalItem / size);
    }

}
