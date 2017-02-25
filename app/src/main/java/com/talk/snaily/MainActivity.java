package com.talk.snaily;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.talk.snaily.AboutUs.AboutUs;
import com.talk.snaily.Database.MainActivityDatabase;
import com.talk.snaily.Fragments.FragementActivity;
import com.talk.snaily.MapActivity.GoogleMapActivity;
import com.talk.snaily.NewDatabase.FormSpinnerDatepickerExample;
import com.talk.snaily.Pojo.LoginNew;
import com.talk.snaily.ShowStudent.ShowCourse;
import com.talk.snaily.TorchLight.TorchLight;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    EditText mEmail,mPassword;
    JsonParser jsonParser= new JsonParser();
    int status=0;
    String email,password,id,phone,address;
    ProgressDialog progressDialog;

    LoginNew loginNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Working on it", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        Button mLog_in_Button = (Button) findViewById(R.id.log_in_button);

        mEmail = (EditText) findViewById(R.id.user_name);
        mPassword = (EditText) findViewById(R.id.pass_word);

        final MediaPlayer mp = MediaPlayer.create(this, R.raw.login);

        mLog_in_Button.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                mp.start();

                if(mEmail.getText().toString().equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("oops!");
                    alertDialog.setMessage("E-mail field is empty");
                    alertDialog.setButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                    alertDialog.show();
                }
                else if(mPassword.getText().toString().equals("")){
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("oops!");
                    alertDialog.setMessage("Password field is empty");
                    alertDialog.setButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    //dismiss the dialog
                                }
                            });
                    alertDialog.show();
                }


                else {
                    email = mEmail.getText().toString();
                    password = mPassword.getText().toString();


                    new CheckLogin().execute();


                }
            }

        });

        Button mCreateButton = (Button) findViewById(R.id.create_acc_button);
        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);

                startActivity(intent);


            }
        });

        TextView mForgot = (TextView) findViewById(R.id.forget_id);

        mForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, ForgotPassActivity.class);
                startActivity(intent);


            }
        });


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == R.id.action_about) {
            startActivity(new Intent(MainActivity.this, AboutUs.class));
            return true;
        }
        if (id == R.id.action_torch) {
            startActivity(new Intent(MainActivity.this, TorchLight.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_login) {
            startActivity(new Intent(MainActivity.this,MainActivity.class));
        } else if (id == R.id.nav_register) {
            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));

        } else if (id == R.id.nav_showlist) {
            startActivity(new Intent(MainActivity.this,StudentListActivity.class));

        }
        else if (id == R.id.nav_studentlist) {
            startActivity(new Intent(MainActivity.this,ShowCourse.class));
        }
        else if (id == R.id.nav_fragment) {
            startActivity(new Intent(MainActivity.this,FragementActivity.class));
        }
        else if (id == R.id.nav_map) {
            startActivity(new Intent(MainActivity.this, GoogleMapActivity.class));
        }
        else if (id == R.id.nav_database) {
            startActivity(new Intent(MainActivity.this, MainActivityDatabase.class));
        }
        else if (id == R.id.nav_torch) {
            startActivity(new Intent(MainActivity.this, TorchLight.class));

        }else if (id == R.id.nav_new_database) {
            startActivity(new Intent(MainActivity.this, FormSpinnerDatepickerExample.class));
        }


    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    class CheckLogin extends AsyncTask {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog= new ProgressDialog(MainActivity.this);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading.. Please Wait");
            progressDialog.show();
        }

        @Override
        protected Object doInBackground(Object[] params) {

            HashMap<String,String> hashMap= new HashMap<>();
            hashMap.put("email",email);
            hashMap.put("password",password);

            String url="http://kinbech.6te.net/ittraining_online/api/login";
            JSONObject jsonObject = jsonParser.performPostCI(url,hashMap);

            if(jsonObject == null){
                status=1;

            }
            else {
                try{
                    if(jsonObject.getString("status").equals("success")){

                        status=2;

                        address= jsonObject.getString("address");
                        phone= jsonObject.getString("phone");
                        id= jsonObject.getString("id");
                        email=jsonObject.getString("email");

                        loginNew =new LoginNew(id,phone,email,address);


                    }
                    else{

                        status=3;
                    }

                }catch(JSONException e)
                {

                }

            }


            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);

            progressDialog.dismiss();

            if(status==1){
                Toast.makeText(MainActivity.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

            }
            else if(status== 2){


                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, DisplayActivity.class);

                intent.putExtra("login_data",loginNew);

//                intent.putExtra("snail_email", email);
//                intent.putExtra("snail_password", password);
//
//                intent.putExtra("snail_phone", phone);
//                intent.putExtra("snail_address", address);
//                intent.putExtra("snail_id", id);

                startActivity(intent);

            }
            else if(status==3){

                Toast.makeText(MainActivity.this, "Invalid Username and Password", Toast.LENGTH_SHORT).show();
            }


        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View v = getCurrentFocus();

        if (v != null &&
                (ev.getAction() == MotionEvent.ACTION_UP || ev.getAction() == MotionEvent.ACTION_MOVE) &&
                v instanceof EditText &&
                !v.getClass().getName().startsWith("android.webkit.")) {
            int scrcoords[] = new int[2];
            v.getLocationOnScreen(scrcoords);
            float x = ev.getRawX() + v.getLeft() - scrcoords[0];
            float y = ev.getRawY() + v.getTop() - scrcoords[1];

            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom())
                hideKeyboard(this);
        }
        return super.dispatchTouchEvent(ev);
    }

    public static void hideKeyboard(MainActivity activity) {
        if (activity != null && activity.getWindow() != null && activity.getWindow().getDecorView() != null) {
            InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getWindowToken(), 0);
        }
    }





}



