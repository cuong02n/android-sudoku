package com.cuong02n.sudoku2905;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class about_us_privacy_policy extends AppCompatActivity {
    Button privacy_policy;
    Button agree;
    WebView webView;
    Dialog privacypolicydialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about_us_privacy);
        privacy_policy = findViewById(R.id.button_privacy_policy);
        privacy_policy.setOnClickListener(view -> {
            privacypolicydialog = new Dialog(about_us_privacy_policy.this);
            privacypolicydialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            privacypolicydialog.setContentView(R.layout.dialog_privacy);
            privacypolicydialog.show();
            agree = privacypolicydialog.findViewById(R.id.agree);
            webView = privacypolicydialog.findViewById(R.id.webview);
            webView.loadUrl("https://sites.google.com/view/cuong02n-sudoku2905-privacy/home");
            if (privacypolicydialog.isShowing()) {
                agree.setOnClickListener(view1 -> privacypolicydialog.dismiss());
            }
        });
    }
}
