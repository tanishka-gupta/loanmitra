package in.partner.loanchacha;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class ActivityAddLead extends FragmentActivity {

    Button btnSend;
    EditText edtName,  edtPhone, edtComment;
    ScrollView sclDetail;
    ProgressBar prgLoading;
    TextView txtAlert;
    Spinner spinner;
    WebView webView;

    String Name, Phone;
    String Comment = "";
    String Result;
    String UserId, UserType;

    public static final String MyPREFERENCES = "MyPrefs";
    SharedPreferences prefs;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_lead);


        prefs = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        UserId = prefs.getString("user_id","0");
        UserType = prefs.getString("user_type","");

        //String user_type = prefs.getString("user_type","");



      /*  Parse.initialize(this, getString(R.string.parse_application_id), getString(R.string.parse_client_key));
        ParseAnalytics.trackAppOpened(getIntent());
        PushService.setDefaultPushCallback(this, ActivityNotification.class);
        ParseInstallation.getCurrentInstallation().saveInBackground(); */


        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.header)));
        bar.setTitle("Add Lead");
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setHomeButtonEnabled(true);

        edtName = (EditText) findViewById(R.id.edtName_cont);
        edtPhone = (EditText) findViewById(R.id.edtPhone_cont);
        edtComment = (EditText) findViewById(R.id.edtComment_cont);
        btnSend = (Button) findViewById(R.id.btnSend_cont);
        webView = (WebView) findViewById(R.id.webContact);


     //   webView.loadDataWithBaseURL("", Constant.Contact+"<br /><br /><br /><strong>Send us a Message:</strong>", "text/html", "UTF-8", "");

       // webView.setBackgroundColor(Color.parseColor("#ffffff"));
        webView.setVisibility(View.GONE);

        // event listener to handle send button when pressed
        btnSend.setOnClickListener(new OnClickListener() {

            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                // get data from all forms and send to server
                Name = edtName.getText().toString();
                Phone = edtPhone.getText().toString();
                Comment = edtComment.getText().toString();
                if(Name.equalsIgnoreCase("") || Phone.equalsIgnoreCase("")){
                    Toast.makeText(ActivityAddLead.this, R.string.form_alert, Toast.LENGTH_SHORT).show();
                } else {
                    new sendData().execute();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case android.R.id.home:
                // app icon in action bar clicked; go home
                this.finish();
                overridePendingTransition(R.anim.open_main, R.anim.close_next);
                return true;

            case R.id.share:
                Intent sendInt = new Intent(Intent.ACTION_SEND);
                sendInt.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
                sendInt.putExtra(Intent.EXTRA_TEXT, "Download our app:\n\""+getString(R.string.app_name)+"\" \nhttps://play.google.com/store/apps/details?id="+getPackageName());
                sendInt.setType("text/plain");
                startActivity(Intent.createChooser(sendInt, "Share"));
                return true;

            case R.id.call:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Constant.Helpline));
                startActivity(intent);

                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public class sendData extends AsyncTask<Void, Void, Void> {
        ProgressDialog dialog;

        // show progress dialog
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            dialog= ProgressDialog.show(ActivityAddLead.this, "",
                    getString(R.string.sending_alert), true);

        }

        @Override
        protected Void doInBackground(Void... params) {
            // TODO Auto-generated method stub
            // send data to server and store result to variable
            Result = getRequest(Name, Phone,  Comment);
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // TODO Auto-generated method stub
            // if finish, dismis progress dialog and show toast message
            dialog.dismiss();
            resultAlert(Result);


        }
    }

    // method to show toast message
    public void resultAlert(String HasilProses){
        // Toast.makeText(ActivityEnquiry.this,  HasilProses, Toast.LENGTH_SHORT).show();
        if(HasilProses.trim().equalsIgnoreCase("OK")){
            Toast.makeText(ActivityAddLead.this, "Lead is added", Toast.LENGTH_SHORT).show();
            edtName.setText("");
            edtPhone.setText("");
          //  Intent i = new Intent(ActivityAddLead.this, ActivityConfirmEnquiry.class);
           // startActivity(i);
           // overridePendingTransition (R.anim.open_next, R.anim.close_next);
           // finish();
        }else if(HasilProses.trim().equalsIgnoreCase("Failed")){
            Toast.makeText(ActivityAddLead.this, "An error occured while adding lead.", Toast.LENGTH_SHORT).show();

        }else{
            Log.d("HasilProses", HasilProses);
        }
    }

    // method to post data to server
    public String getRequest(String name,  String phone,  String comment){
        String result = "";

        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(Constant.AddLeadAPI);

        try{
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(7);
            nameValuePairs.add(new BasicNameValuePair("accesskey", Constant.AccessKey));
            nameValuePairs.add(new BasicNameValuePair("name", name));
            nameValuePairs.add(new BasicNameValuePair("phone", phone));
            nameValuePairs.add(new BasicNameValuePair("comment", comment));
            if(UserType.equals("LOANMITRA")) {
                nameValuePairs.add(new BasicNameValuePair("loanmitra_id", UserId));
            } else {
                nameValuePairs.add(new BasicNameValuePair("loanmitra_id", "0"));
            }

            request.setEntity(new UrlEncodedFormEntity(nameValuePairs,HTTP.UTF_8));
            HttpResponse response = client.execute(request);
            result = request(response);
        }catch(Exception ex){
            result = "Unable to connect.";
        }
        return result;
    }

    public static String request(HttpResponse response){
        String result = "";
        try{
            InputStream in = response.getEntity().getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder str = new StringBuilder();
            String line = null;
            while((line = reader.readLine()) != null){
                str.append(line + "\n");
            }
            in.close();
            result = str.toString();
        }catch(Exception ex){
            result = "Error";
        }
        return result;
    }

    // when back button pressed close database and back to previous page
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub
        super.onBackPressed();
        //dbhelper.close();
        finish();
        overridePendingTransition(R.anim.open_main, R.anim.close_next);
    }

    @Override
    public void onConfigurationChanged(final Configuration newConfig)
    {
        // Ignore orientation change to keep activity from restarting
        super.onConfigurationChanged(newConfig);
    }
}
