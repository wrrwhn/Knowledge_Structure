package com.yao.study.java.scope;

import com.yao.study.java.scope.other.SubProtectedScope;
import com.yao.study.java.scope.pro.ProtectedScope;
import com.yao.study.java.scope.empty.NotScope;
import com.yao.study.java.scope.other.SubNotScope;
import com.yao.study.java.scope.other.SubPrivateScope;
import com.yao.study.java.scope.other.SubProtectedScope;
import com.yao.study.java.scope.other.SubPublicScope;
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

        SubPublicScope subPublicScope = new SubPublicScope();
        SubProtectedScope subProtectedScope = new SubProtectedScope();
        SubPrivateScope subPrivateScope = new SubPrivateScope();
        SubNotScope subNotScope = new SubNotScope();

        // public 对象可用
        System.out.printf(
                publicScope.val
//                , protectedScope.val
//                , privateScope.val
//                , notScope.val
                , subPublicScope.val
//                , subProtectedScope.val
//                , subPrivateScope.val
//                , subNotScope.val
        );

    }
}
