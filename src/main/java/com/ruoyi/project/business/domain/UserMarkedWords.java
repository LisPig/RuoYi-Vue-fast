package com.ruoyi.project.business.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 用户自标记单词对象 UserMarkedWords
 * 
 * @author ruoyi
 * @date 2024-07-17
 */
public class UserMarkedWords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long Id;

    /** 用户id */
    private Long userId;

    /** 自标记单词 */
    @Excel(name = "自标记单词")
    private String markedWords;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updatedAt;


    public void setId(Long Id)
    {
        this.Id = Id;
    }

    public Long getId()
    {
        return Id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setMarkedWords(String markedWords) 
    {
        this.markedWords = markedWords;
    }

    public String getMarkedWords() 
    {
        return markedWords;
    }
    public void setUpdatedAt(Date updatedAt) 
    {
        this.updatedAt = updatedAt;
    }

    public Date getUpdatedAt() 
    {
        return updatedAt;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("markedWords", getMarkedWords())
            .append("updatedAt", getUpdatedAt())
            .toString();
    }
}
