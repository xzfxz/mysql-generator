package com.tqg.zhao.mybatisPlugin.page;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PagedResult<T>
{
    private static final long serialVersionUID = 20131015L;
    private List<T> results;
    private int totalSize;

    public PagedResult()
    {
    }

    public PagedResult(List<T> results, int totalSize)
    {
        this.results = results;
        this.totalSize = totalSize;
    }

    public List<T> getResults() {
        return this.results;
    }

    public int getTotalSize() {
        return this.totalSize;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public void setTotalSize(int totalSize) {
        this.totalSize = totalSize;
    }
}