package com.heady.explora.base;

import android.app.Application;
import android.content.Context;

import com.heady.explora.BuildConfig;
import com.heady.explora.R;
import com.heady.explora.data.component.DaggerNetComponent;
import com.heady.explora.data.component.NetComponent;
import com.heady.explora.data.module.AppModule;
import com.heady.explora.data.module.NetModule;
import com.heady.explora.util.Fonts;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


/**
 * Created by yashthakur on 26/08/18.
 */

public class ExploraApp extends Application {

    private NetComponent mNetComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (BuildConfig.DEBUG) {
            Logger.addLogAdapter(new AndroidLogAdapter());
        }

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule("https://stark-spire-93433.herokuapp.com"))
                .build();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(Fonts.PACIFICO)
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public NetComponent getNetComponent() {
        return mNetComponent;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

}
