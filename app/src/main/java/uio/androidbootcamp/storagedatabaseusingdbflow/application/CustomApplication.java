package uio.androidbootcamp.storagedatabaseusingdbflow.application;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowLog;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by jrodri on 7/7/17.
 */

public class CustomApplication extends Application {

        @Override
        public void onCreate() {
            super.onCreate();
            FlowManager.init(new FlowConfig.Builder(this).build());
            FlowLog.setMinimumLoggingLevel(FlowLog.Level.V);
        }
}
