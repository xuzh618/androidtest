package com.xuzh.androidtest.jni;

public class Foo {

    /**
     * 使用javah 命令生成对应的native C代码
     * 
     * javah 到.class目录下，android工程都在bin/classes下
     * 执行命令：javah -d ../../jni -jni com.xuzh.androidtest.jni.Foo
     * 就会发现jni目录下有 生成对用的com_xuzh_androidtest_jni_Foo.h文件
     * 
     */
    native void method1();

    native int method2();

    native String method3();
}
