package com.yao.study.designPatterns.proxy.dynamic;

/**
 * Created by Yao on 2015/5/7.
 */
public interface IPersonBean {

    public String getName();
    public void setName(String name);
    public String getGender();
    public void setGender(String gender);

    public String getInterests();
    public void setInterests(String interests);
    public int getHotOrNotRating();
    public void setHotOrNotRating(int rating);
}
