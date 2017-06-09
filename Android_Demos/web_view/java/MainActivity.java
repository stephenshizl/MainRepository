package com.example.a61555.webviewdemo;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private String url = "http://www.imooc.com/";
    private String summary = "<html><body>You scored <b>192</b> points.</body></html>";
    private WebView webView;
    private ProgressDialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webView);
        //openWebView1();
        //openWebView2();
        //openWebView3();
        openWebView4();
    }
    /* 1. Basic usage */
    private void openWebView1() {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    /* 2. Simplest usage */
    private void openWebView2() {
        webView.loadUrl(url);//加载地址
    }
    /* 3. load data */
    private void openWebView3() {
        webView.loadData(summary, "text/html", "UTF-8");
    }
    /* 4. 覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebVIew中打开 */
    private void openWebView4() {
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("tel:")) {
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(url));
                    startActivity(intent);
                }else if(url.startsWith("http:") || url.startsWith("https:")) {
                    view.loadUrl(url);
                }
                //返回值是true的时候控制网页在WebView中去打开，
                // 如果为false调用系统浏览器或第三方浏览器去打开
                return true;
            }
            //WebViewClient帮助WebView去处理一些页面控制和请求通知
        });
        WebSettings settings = webView.getSettings();
        //启用javascript
        settings.setJavaScriptEnabled(true);
        //WebView加载页面优先使用缓存加载
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        //设置页面加载Progerss Dialog
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                //newProgress 1-100之间的整数
                if (newProgress == 100) {
                    //网页加载完毕，关闭ProgressDialog
                    closeDialog();
                } else {
                    //网页正在加载,打开ProgressDialog
                    openDialog(newProgress);
                }
            }

            private void closeDialog() {
                if (dialog != null && dialog.isShowing()) {
                    dialog.dismiss();
                    dialog = null;
                }
            }

            private void openDialog(int newProgress) {
                if (dialog == null) {
                    dialog = new ProgressDialog(MainActivity.this);
                    dialog.setTitle("Loading……");//设置Dialog标题
                    dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);//设置Dialog样式
                    dialog.setIndeterminate(false);//打开显示进度详细数据
                    dialog.setProgress(newProgress);//Progress进度
                    dialog.show();//打开Dialog显示的开关
                } else {
                    dialog.setProgress(newProgress);
                }
            }
        });
    }
    //改写物理按键返回的逻辑
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event){

        if(keyCode== KeyEvent.KEYCODE_BACK) {
            //Toast.makeText(this, webView.getUrl(), Toast.LENGTH_SHORT).show();
            if (webView.canGoBack()) {
                webView.goBack();//返回上一页面
                return true;
            } else {
                System.exit(0);//退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
