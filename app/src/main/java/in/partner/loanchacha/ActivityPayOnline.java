package in.partner.loanchacha;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.DecimalFormat;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.SQLException;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;



public class ActivityPayOnline extends Activity {


    WebView txtDescription;
    // declare dbhelper object

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.pay_online);


     /*   Parse.initialize(this, getString(R.string.parse_application_id), getString(R.string.parse_client_key));
        ParseAnalytics.trackAppOpened(getIntent());
        PushService.setDefaultPushCallback(this, ActivityNotification.class);
        ParseInstallation.getCurrentInstallation().saveInBackground(); */



        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setTitle("Pay Online");
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);
        txtDescription = (WebView) findViewById(R.id.txtDescription);
      //  txtDescription.setWebChromeClient(new WebChromeClient());

       String url = getIntent().getStringExtra("URL");
      //  txtDescription.loadDataWithBaseURL("", Menu_description, "text/html", "UTF-8", "");

        txtDescription.setWebChromeClient(new WebChromeClient());
        WebSettings webSettings = txtDescription.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
       // txtDescription.setWebViewClient(new WebViewClient());

        txtDescription.loadUrl(url);

        // txtDescription.setBackgroundColor(Color.parseColor("#ffffff"));

    }





    // method to show number of order form


    // asynctask class to handle parsing json in background





    // close database before back to previous page
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
     //   super.onBackPressed();
     //   finish();
       // overridePendingTransition(R.anim.open_main, R.anim.close_next);
    }


    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        //imageLoader.clearCache();
        super.onDestroy();
    }


    @Override
    public void onConfigurationChanged(final Configuration newConfig)
    {
        // Ignore orientation change to keep activity from restarting
        super.onConfigurationChanged(newConfig);
    }


}
