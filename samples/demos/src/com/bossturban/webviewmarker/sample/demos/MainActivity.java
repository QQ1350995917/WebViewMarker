package com.bossturban.webviewmarker.sample.demos;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.bossturban.webviewmarker.TextSelectionSupport;

public class MainActivity extends Activity {
    private TextSelectionSupport mTextSelectionSupport;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        MyWebView myWebView = new MyWebView(this);
        myWebView.setLayoutParams(layoutParams);
        this.setContentView(myWebView);

        mTextSelectionSupport = TextSelectionSupport.support(this, myWebView);
        mTextSelectionSupport.setSelectionListener(new TextSelectionSupport.SelectionListener() {
            @Override
            public void startSelection() {
            }
            @Override
            public void selectionChanged(String text) {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void endSelection() {
            }
        });
        myWebView.setWebViewClient(new WebViewClient() {
            public void onScaleChanged(WebView view, float oldScale, float newScale) {
                mTextSelectionSupport.onScaleChanged(oldScale, newScale);
            }
        });
        myWebView.loadUrl("file:///android_asset/content.html");
    }


    private class MyWebView extends WebView{
        public MyWebView(Context context) {
            super(context);
        }

        @Override
        public ActionMode startActionMode(ActionMode.Callback callback) {
            ViewParent parent = getParent();
            if (parent == null) {
                return null;
            }
            return null;
        }
    }
}
