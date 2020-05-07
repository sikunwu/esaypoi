package com.wu.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.wu.util.json.JsonReadable;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Random;
import java.util.function.Predicate;

/**
 * @author zps
 * @date 2018/9/10
 */
@JsonInclude(/*JsonInclude.Include.NON_NULL*/)
public class BaseModel<T> extends JsonReadable implements Serializable {

    private static final long serialVersionUID = 1L;

    protected T id;
    private static Random random = new Random();
    private int randomHashCode;

    public BaseModel(T id) {
        this.id = id;
    }

    public BaseModel() {
    }

    public void setId(T id) {
        this.id = id;
    }
    public T getId() {
        return id;
    }

    // 由于每个Bean的主键不固定为id字段,需要交由子类实现，默认为true
    @JsonIgnore
    public Predicate getPredicate() {
        return (b) -> true;
    }
    // public Predicate<BaseModel> predicate = (b) -> true;

    public boolean withPrimaryKey() {
        return getPredicate().test(this);
    }

    @JsonIgnore
    public boolean getIsNewRecord() {
        return getId() == null || StringUtils.isBlank(getId().toString());
    }

    @Override
    public boolean equals(Object obj) {
        return id != null && obj instanceof BaseModel && ((BaseModel) obj).getId().equals(id);
    }

    @Override
    public int hashCode() {
        if (id == null) {
            if (randomHashCode == 0) {
                randomHashCode = random.nextInt();
            }
            return randomHashCode;
        }
        return id.hashCode();
    }
}
