 * 使用GirdView实现上下文菜单ContextMenu
 * 重写onCreateContextMenu和onContextItemSelected
 * 在Activity注册上下文菜单调用registerForContextMenu方法时调用onCreateContextMenu
 *                      在点击上下文菜单中的Item时调用onContextItemSelected
 * 在onCreateContextMenu方法中对ContextMenu进行构造，有动态和静态构造两种方法