package com.weimi.wmmess.business.shimu.bean.problem;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

/**
 * Create by chhyu
 * on 2019/5/27
 * Describle:
 */
@Entity
public class ProblemItemResbean  {

    @Id
    private Long id;

    @Property(nameInDb = "problemItemResbeanId")
    private Long problemItemResbeanId;

    private int index;

    private String describe;

    private String reason;

    private String solution;

    private String completeDate;

    private String person;

    @Generated(hash = 917935663)
    public ProblemItemResbean(Long id, Long problemItemResbeanId, int index,
            String describe, String reason, String solution, String completeDate,
            String person) {
        this.id = id;
        this.problemItemResbeanId = problemItemResbeanId;
        this.index = index;
        this.describe = describe;
        this.reason = reason;
        this.solution = solution;
        this.completeDate = completeDate;
        this.person = person;
    }

    @Generated(hash = 1155446875)
    public ProblemItemResbean() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProblemItemResbeanId() {
        return this.problemItemResbeanId;
    }

    public void setProblemItemResbeanId(Long problemItemResbeanId) {
        this.problemItemResbeanId = problemItemResbeanId;
    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getDescribe() {
        return this.describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSolution() {
        return this.solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getCompleteDate() {
        return this.completeDate;
    }

    public void setCompleteDate(String completeDate) {
        this.completeDate = completeDate;
    }

    public String getPerson() {
        return this.person;
    }

    public void setPerson(String person) {
        this.person = person;
    }


}
