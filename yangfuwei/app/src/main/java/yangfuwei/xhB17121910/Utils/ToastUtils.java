package yangfuwei.xhB17121910.Utils;

import android.widget.Toast;

import yangfuwei.xhB17121910.Common.OKApplication;

public class ToastUtils {
    public static void showToastShort(String msg) {
        Toast.makeText(OKApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void showToastShort(int res) {
        Toast.makeText(OKApplication.getContext(), OKApplication.getContext().getString(res), Toast.LENGTH_SHORT).show();
    }

}
