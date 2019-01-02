package cn.xiaows.gen;

import cn.xiaows.gen.util.HttpUtils;
import cn.xiaows.logs.common.AppLogger;
import cn.xiaows.logs.common.BaseLogger;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 数据生成程序
 */
@Slf4j
public class TestGenData {
    private static Random random = new Random();

    private static String[] mobile = {"13739281826", "17765135298", "13322226666", "13800008888"};
    private static String[] orgName = {"贝斯", "新志", "派啦", "焱展"};
    private static String[] sysId = {"VOC", "GOSSIP", "ROLECENTER", "SAICIO"};
    // private static FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static String appId = "sdk34734";

    private static String[] tenantIds = {"cake"};
    private static String[] appVersions = {"3.2.1", "3.2.2", "3.3.0"};
    private static String[] appChannels = {"youmeng1", "youmeng2"};
    private static String[] appPlatFroms = {"android", "ios", "pc"};

    private static String[] countrys = {"America", "China", "Japan"};
    private static String[] provinces = {"Washington", "jiangxi", "anhui", "bejing"};
    private static String[] networks = {"WiFi", "CellNetwork"};
    private static String[] carriers = {"中国移动", "中国电信", "中国联通"};
    private static String[] deviceStyles = {"IPhone6", "IPhone6 Plus", "IPhone X", "IPhone XR", "iPhone XS", "Mi8"};

    private static String[] screenSizes = {"1136*640", "960*640", "480*320"};
    private static String[] osTypes = {"8.3", "7.1.1"};
    private static String[] brands = {"三星", "华为", "Apple", "魅族", "小米", "锤子"};
    private static String[] eventIDs = {"popMenu", "autoImport", "BookStore"};
    private static Long[] EventDurationSecsS = {new Long(25), new Long(67), new Long(45)};

    public static void test() {
        ExecutorService pool = Executors.newFixedThreadPool(64);
        for (int i = 0; i < 20; i++) {
            int a = i;
            pool.execute(() -> {
                AppLogger app = new AppLogger()
                        .setAppChannel(appChannels[random.nextInt(appChannels.length)])
                        .setAppPlatFrom(appPlatFroms[random.nextInt(appPlatFroms.length)])
                        .setAppVersion(appVersions[random.nextInt(appVersions.length)])
                        .setBrand(brands[random.nextInt(brands.length)])
                        .setCarrier(carriers[random.nextInt(carriers.length)])
                        .setCountry(countrys[random.nextInt(countrys.length)])
                        .setDeviceStyle(deviceStyles[random.nextInt(deviceStyles.length)])
                        .setEventID(eventIDs[random.nextInt(eventIDs.length)])
                        .setNetwork(networks[random.nextInt(networks.length)])
                        .setOsType(osTypes[random.nextInt(osTypes.length)])
                        .setProvince(provinces[random.nextInt(provinces.length)])
                        .setScreenSize(screenSizes[random.nextInt(screenSizes.length)])
                        .setTenantId(tenantIds[random.nextInt(tenantIds.length)]);
                app.setCurrentDate(sdf.format(new Date()));

                ArrayList<BaseLogger> applogs = new ArrayList<>();
                applogs.add(app);
                String json = JSONObject.toJSONString(applogs);

                // String url = "http://172.22.1.5/kafka/user";
                String url = "http://localhost:8081/col/collect";
                int status = HttpUtils.upload(json, url);
                log.debug("Send {} log status: {}", a, status);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }
            });
        }
        pool.shutdown();
    }

    public static void main(String[] args) {
        test();
    }
}
