 * 使用AsyncTask实现多线通信
 * AsyncTask是一个抽象泛型类
 * abstract class AsyncTask<Params, Progress, Result>
 *     Params：开始异步任务执行时传入的参数类型；
 *     Progress：异步任务执行过程中，返回下载进度值的类型；
 *     Result：异步任务执行完成后，返回的结果类型；
 * 如果不想有参数，可以使用 Void