package com.login.common.helper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zhang on 2018/8/25.
 */
public class ResultPage<T>  implements Serializable {
    private int count;
    private String msg;
    private int code;
    private List<T> data = new ArrayList<T>();

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
