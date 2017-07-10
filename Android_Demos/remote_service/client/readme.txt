 * 远程通信服务 Client端
 * 需要拥有Server端中定义的aidl文件,使用IMyAidlInterface.Stub.asInterface()方法转换类型
 * Intent中需要设定action的name,与Server端中的action name一样
 * Intent需要显式绑定,setPackage参数为aidl的所在的包名