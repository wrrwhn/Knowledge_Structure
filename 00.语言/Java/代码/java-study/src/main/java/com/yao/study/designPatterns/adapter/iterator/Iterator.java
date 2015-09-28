package com.yao.study.designPatterns.adapter.iterator;

/**
 * Created by Yao on 2015/4/1.
 */
public interface Iterator {
    Boolean hasNext();
    Object next();
    Object remove();
}
