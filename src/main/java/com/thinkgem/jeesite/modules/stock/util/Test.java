package com.thinkgem.jeesite.modules.stock.util;

import com.sun.tools.javac.util.List;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by zhongyi on 2017/8/31 0031.
 */
public class Test {

    public static void main(String[] args) {
        Collections.sort(new ArrayList<Comparable>(), new Comparator<Comparable>() {
            @Override
            public int compare(Comparable o1, Comparable o2) {
                return 0;
            }
        });
    }
}
