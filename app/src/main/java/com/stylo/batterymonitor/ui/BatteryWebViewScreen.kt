package com.stylo.batterymonitor.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@androidx.compose.runtime.Composable
fun BatteryWebViewScreen(
    modifier: Modifier = Modifier,
    vm: BatteryViewModel,
) {
    var webView by remember { mutableStateOf<WebView?>(null) }

    AndroidView(
        modifier = modifier,
        factory = { context ->
            WebView(context).apply {
                settings.javaScriptEnabled = true
                settings.domStorageEnabled = true
                settings.allowFileAccess = true
                settings.allowContentAccess = true
                webViewClient = WebViewClient()
                loadUrl("file:///android_asset/abateri_3d.html")
                webView = this
            }
        },
        update = { view ->
            webView = view
        }
    )

    DisposableEffect(Unit) {
        onDispose {
            webView?.apply {
                stopLoading()
                destroy()
            }
        }
    }
}
