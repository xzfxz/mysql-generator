package com.tqg.zhao.mybatisPlugin.page;

import java.io.Serializable;

public class PageInfo implements Serializable {

    private static final long serialVersionUID = 587754556498974978L;

    //pagesize ，每一页显示多少
    private int showCount = 10;
    //总页数
    private int totalPage;
    //总记录数
    private int totalResult;
    //当前页数
    private int currentPage;
    //当前显示到的ID, 在mysql limit 中就是第一个参数.
    private int currentResult;
    private String sortField;
    private String order;

    private int skip;

    public int getShowCount() {
        return showCount;
    }

    public void setShowCount(int showCount) {
        this.showCount = showCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(int totalResult) {
        this.totalResult = totalResult;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage <= 0) {
            currentPage = 1;
        }
        this.currentPage = currentPage;
    }

    public int getCurrentResult() {
        return currentResult;
    }

    public void setCurrentResult(int currentResult) {
        this.currentResult = currentResult;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getSkip() {
        return (currentPage - 1) * showCount;
    }

    public void setSkip(int skip) {
        this.skip = skip;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"showCount\":")
                .append(showCount);
        sb.append(",\"totalPage\":")
                .append(totalPage);
        sb.append(",\"totalResult\":")
                .append(totalResult);
        sb.append(",\"currentPage\":")
                .append(currentPage);
        sb.append(",\"currentResult\":")
                .append(currentResult);
        sb.append(",\"sortField\":\"")
                .append(sortField).append('\"');
        sb.append(",\"order\":\"")
                .append(order).append('\"');
        sb.append(",\"skip\":")
                .append(skip);
        sb.append('}');
        return sb.toString();
    }
}