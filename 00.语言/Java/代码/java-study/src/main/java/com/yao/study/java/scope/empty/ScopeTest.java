package com.yao.study.java.scope.empty;

import com.yao.study.java.scope.pro.ProtectedScope;
import com.yao.study.java.scope.pri.PrivateScope;
import com.yao.study.java.scope.pro.ProtectedScope;
import com.yao.study.java.scope.pub.PublicScope;

/**
 * Creator: Yao
 * Date:    2016/5/17
 * For:
 * Other:
 */
public class ScopeTest {

    public static void main(String[] args) {

        PublicScope publicScope = new PublicScope();
        ProtectedScope protectedScope = new ProtectedScope();
        PrivateScope privateScope = new PrivateScope();
        NotScope notScope = new NotScope();

        // 当前包下可用
        System.out.printf(
            publicScope.val
//            ,protectedScope.val
//            ,privateScope.val
            ,notScope.val
        );

    }
}
