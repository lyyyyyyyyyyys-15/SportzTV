package com.sportztv;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebChromeClient;
import android.view.WindowManager;

public class PlayerActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player);

        String url = getIntent().getStringExtra("url");
        String referer = getIntent().getStringExtra("referer");
        String origin = getIntent().getStringExtra("origin");
        String userAgent = getIntent().getStringExtra("userAgent");
        String name = getIntent().getStringExtra("name");

        webView = findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setMediaPlaybackRequiresUserGesture(false);
        settings.setUserAgentString(userAgent);
        settings.setAllowFileAccess(true);
        settings.setDomStorageEnabled(true);

        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

        String html = "<!DOCTYPE html><html><head>" +
            "<meta name='viewport' content='width=device-width, initial-scale=1'>" +
            "<style>body{margin:0;background:#000;}" +
            "video{width:100vw;height:100vh;}</style>" +
            "<script src='https://cdn.jsdelivr.net/npm/hls.js@latest'></script>" +
            "</head><body>" +
            "<video id='v' controls autoplay playsinline></video>" +
            "<script>" +
            "var video = document.getElementById('v');" +
            "var url = '" + url + "';" +
            "if(Hls.isSupported()){" +
            "  var hls = new Hls({" +
            "    xhrSetup: function(xhr, u) {" +
            "      xhr.setRequestHeader('Referer', '" + referer + "');" +
            "      xhr.setRequestHeader('Origin', '" + origin + "');" +
            "    }" +
            "  });" +
            "  hls.loadSource(url);" +
            "  hls.attachMedia(video);" +
            "  hls.on(Hls.Events.MANIFEST_PARSED, function(){video.play();});" +
            "} else if(video.canPlayType('application/vnd.apple.mpegurl')){" +
            "  video.src = url;" +
            "  video.play();" +
            "}" +
            "</script></body></html>";

        webView.loadDataWithBaseURL(referer, html, "text/html", "UTF-8", null);
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (webView != null) webView.destroy();
        super.onDestroy();
    }
}
