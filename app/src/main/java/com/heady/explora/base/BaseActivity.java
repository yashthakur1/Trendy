package com.heady.explora.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.heady.explora.R;

import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by yashThakur on 08/03/16.
 */

public class BaseActivity extends AppCompatActivity {

    public static final String RUPEES = "\u20B9 ";
    public static final int HIDE = -1;
    public static final int INVISIBLE = 0;
    private static final String TAG = "BaseActivity";
    protected Context context;
    protected Gson gson;
    protected RelativeLayout baseLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        gson = new Gson();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        baseLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.base_layout, null);
        setContentView(baseLayout);
        ViewStub stub = (ViewStub) baseLayout.findViewById(R.id.container);
        stub.setLayoutResource(layoutResID);
        stub.inflate();
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void shortSnack(String message) {
        Snackbar snackbar = Snackbar.make(baseLayout, message, Snackbar.LENGTH_SHORT);
        View snackView = snackbar.getView();
        snackbar.show();

        TextView tv = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
    }

    public void longSnack(String message) {
        Snackbar snackbar = Snackbar.make(baseLayout, message, Snackbar.LENGTH_LONG);
        View snackView = snackbar.getView();
        snackbar.show();

        TextView tv = (TextView) snackView.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
    }

    public int color(int color) {
        return ContextCompat.getColor(context, color);
    }

    public int dpToPx(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, getResources().getDisplayMetrics());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}

