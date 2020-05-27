package yangfuwei.xhB17121910.Common;

import android.app.Application;
import android.content.Context;

public class OKApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }
}
