package yangfuwei.xhB17121910.Utils;

import android.text.TextUtils;

import com.qiniu.android.common.FixedZone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.util.Auth;

public class QiniuUtils {

    private static String ACESSKEY = "CmhlZJc6URbiNXmDk01MYUNYpS5zWh3QA8jfnLI1";
    private static String SECRETKEY = "gsK1LSnaKW2QdBYs6gkQuoU4ABpKtCNx2890Xm3a";
    private static String BUCKETNAME = "ourkitchen";
    private static String token;
    public static String DOMAIN = "http://qaxi238bw.bkt.clouddn.com/";

    public static String getToken() {
        if (TextUtils.isEmpty(token)) {
            Auth auth = Auth.create(ACESSKEY,SECRETKEY);
            token = auth.uploadToken(BUCKETNAME);
        }
        return token;
    }

    public static Configuration getDefaultConfigurations() {
        Configuration config = new Configuration.Builder()
                .chunkSize(512 * 1024)        // 分片上传时，每片的大小。 默认256K
                .putThreshhold(1024 * 1024)   // 启用分片上传阀值。默认512K
                .connectTimeout(10)           // 链接超时。默认10秒
                .useHttps(true)               // 是否使用https上传域名
                .responseTimeout(60)          // 服务器响应超时。默认60秒
                .zone(FixedZone.zone0)        // 设置区域，指定不同区域的上传域名、备用域名、备用IP。
                .build();
        return config;
    }
}
