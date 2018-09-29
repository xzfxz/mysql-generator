package com.tqg.zhao.mybatisPlugin.page;





import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
//import org.codehaus.jackson.annotate.JsonTypeInfo;
//import org.codehaus.jackson.annotate.JsonTypeInfo.Id;


//@JsonTypeInfo(use=JsonTypeInfo.Id.NONE,include=JsonTypeInfo.As.WRAPPER_OBJECT)
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class TableData<T>
{
    private static final long serialVersionUID = 20131015L;

    private int sEcho;

    private int iTotalRecords;

    private int iTotalDisplayRecords;

    private List<T> aaData;
    
    private String otherData;
    
    public TableData()
    {
    }

    public TableData(PagedResult pr, int sEcho)
    {
        this.aaData = pr.getResults();
        this.iTotalRecords = pr.getTotalSize();
        this.iTotalDisplayRecords = pr.getTotalSize();
        this.sEcho = sEcho;
    }
    
    public TableData(PagedResult pr, int sEcho ,String otherData)
    {
        this.aaData = pr.getResults();
        this.iTotalRecords = pr.getTotalSize();
        this.iTotalDisplayRecords = pr.getTotalSize();
        this.sEcho = sEcho;
        this.otherData=otherData;
    }
}