// IMyAidlInterface.aidl
package com.example.a61555.remoteservicedemo_server;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    int basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString);
    /**
    * 需要实现的方法
    */
    String myMethod ();
}
