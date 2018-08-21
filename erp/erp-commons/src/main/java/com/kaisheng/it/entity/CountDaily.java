package com.kaisheng.it.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class CountDaily implements Serializable {
    private Integer id;

    private Integer sumnum;

    private BigDecimal summiney;

    private String dateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSumnum() {
        return sumnum;
    }

    public void setSumnum(Integer sumnum) {
        this.sumnum = sumnum;
    }

    public BigDecimal getSumminey() {
        return summiney;
    }

    public void setSumminey(BigDecimal summiney) {
        this.summiney = summiney;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        CountDaily other = (CountDaily) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSumnum() == null ? other.getSumnum() == null : this.getSumnum().equals(other.getSumnum()))
            && (this.getSumminey() == null ? other.getSumminey() == null : this.getSumminey().equals(other.getSumminey()))
            && (this.getDateTime() == null ? other.getDateTime() == null : this.getDateTime().equals(other.getDateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSumnum() == null) ? 0 : getSumnum().hashCode());
        result = prime * result + ((getSumminey() == null) ? 0 : getSumminey().hashCode());
        result = prime * result + ((getDateTime() == null) ? 0 : getDateTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", sumnum=").append(sumnum);
        sb.append(", summiney=").append(summiney);
        sb.append(", dateTime=").append(dateTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}