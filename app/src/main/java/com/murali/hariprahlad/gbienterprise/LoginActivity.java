package com.murali.hariprahlad.gbienterprise;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import controller.ConnectionDetector;

public class LoginActivity extends AppCompatActivity {
    private WebView wv1;
    ConnectionDetector cd;
    Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        cd = new ConnectionDetector(getApplicationContext());
        wv1=(WebView)findViewById(R.id.loginwebview);

        wv1.getSettings().setAppCacheMaxSize( 5 * 1024 * 1024 ); // 5MB
        wv1.getSettings().setAppCachePath( getApplicationContext().getCacheDir().getAbsolutePath() );
        wv1.getSettings().setAllowFileAccess( true );
        wv1.getSettings().setAppCacheEnabled( true );
        wv1.getSettings().setJavaScriptEnabled( true );
        wv1.getSettings().setCacheMode( WebSettings.LOAD_DEFAULT );

        Intent loginOPTION = getIntent();
        String url_login = loginOPTION.getStringExtra("loginOption");
        if(savedInstanceState==null) {
            if (cd.isNetworkOn(getApplicationContext())) {
                // Internet Connection is Present
                // make HTTP requests
                dialog = getProgressDialog();

                wv1.setWebViewClient(new MyBrowser());
                wv1.loadUrl(url_login);
                dialog.dismiss();
            } else {
                wv1.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                showAlertDialog1(LoginActivity.this, "No Internet Connection", "You don't have internet connection.", false);

            }
        }
    }
    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    public boolean showAlertDialog1(Context context, String title, String message, final Boolean status) {
        final android.support.v7.app.AlertDialog alertDialog = new android.support.v7.app.AlertDialog.Builder(context,R.style.MyAlertDialogStyle).create();
        // Setting Dialog Title
        alertDialog.setTitle(title);
        // Setting Dialog Message
        alertDialog.setMessage("If you Want connect click below");
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_POSITIVE, "Mobile Data", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {
                startActivity(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS));
                finish();
                return;

            }
        });

        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEGATIVE, "Wifi", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                finish();
                return;
            }
        });

        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "Cancel", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int id) {

                finish();
                System.exit(0);
            }
        });
        // Showing Alert Message
        alertDialog.show();
        return true;
    }
    public Dialog getProgressDialog() {
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.progress_dialog);
        //TextView tv = (TextView) dialog.findViewById(R.id.dialog_text);
        //   tv.setText(message);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }
}
