package com.yao.utils.custom;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.FilenameUtils;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2014/11/14.
 */
public class StringUtils {

    public static void main(String[] args) throws UnsupportedEncodingException {


        String binaries = Integer.toBinaryString(0);
        if (binaries.length() < 3) {
            binaries = "000".substring(0, 3 - binaries.length()) + binaries;
        }

    }

    public static void test4Cron() throws UnsupportedEncodingException {
        String infos = new String("15880777595,姚清居;13911994702,索零浇;13601099192,张书泰;15843138678,闫肃;13651090952,藤锦玉;18600696136,苏宝艳;18600696136,张艳红;13501192324,尉刚;13811225255,张淼;13801054032,王宇鹏;18511057223, 陈世成;13901376568,张梅;13601206971,张浩;18515490429,张女士;13699137701,闫新雅;18910842280,周健;13501003006,赵飞;18600010325,冯克奎;18210576125,顾明杰;13581673698,侯俊智;18612723608,王淑静;13691086436,吴红;13366558930,赵爽秋;18001281016,裴超".getBytes(), "UTF-8");
        for (String info : infos.split(";")) {
            for (String var : info.split(","))
                System.out.print("\t" + var);
            System.out.println();
        }
    }


    public static String replace(String oriStr, int idx, char cVal) {
        return oriStr.substring(0, idx) + cVal + oriStr.substring(idx + 1);
    }

    /**
     * 判断文本是否为空
     * 1.输入为null
     * 2.输入移除空格后长度为0
     *
     * @param input
     * @return
     */
    public static boolean isEmpty(String input) {
        boolean result = false;
        if (null == input || input.trim().isEmpty()) {
            result = true;
        }
        return result;
    }

    /**
     * 快速定位输入字符串通配符并对位进行替换
     *
     * @param key   字符串输入
     * @param valus 替换的相关对象
     * @return 替换完成后的结构
     */
    public static String format(String key, Object... valus) {
        return String.format(key, valus);
    }

    public static void filenameReplace() {

        String vedioFilePath = "/data/cdn/dev/doc/8a883e59-2d82-4bf1-b5a3-67740e292edf/res/media/media11.mp4";
        String realExtension = FilenameUtils.getExtension("media11.mp4");
        String dstVedioFilePath = vedioFilePath.replace("." + realExtension, ".mp4");
        System.out.println(dstVedioFilePath);
    }

    public static void watermarkResize() {
        String size = "";
        String info = "960 540";

        if (org.apache.commons.lang3.StringUtils.isNotBlank(info) || 2 != info.split(" ").length) {
            System.out.println("wrong");
        } else {
            String[] params = info.split(" ");
            int width = Integer.parseInt(params[0]) / 3;
            int height = Integer.parseInt(params[0]) / 3;
            size = new StringBuffer("[")
                    .append(width).append("x").append(height)
                    .append("<]").toString();
        }
        System.out.println(size);
    }

    public static void jsonParse() {

        String json =
                "{'success': true, 'room': {'roomId': 'jzf243856e3949455894c5ce48bfee6163','title': '测试多贝云01','startTime': '2014-12-04 15:51:00','endTime': '2014-12-04 18:51:00','validEndTime': '2099-01-01 23:59','video': true,'hostCode': 'jzk38iygsd'}}";
//                "{\"error\": \"app_sign_error\",\"success\": false}";
        Map<String, Object> map = new HashMap<>();
        map = new Gson().fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
        System.out.println(map.get("room"));
    }

    public static void toJson() {

        Map<String, Object> map = new HashMap<>();
        map.put("success", false);
        map.put("error", "app_sign_error");
        String json = new Gson().toJson(map);
        System.out.println(json);
        System.out.println("{\"error\": \"app_sign_error\",\"success\": false}");

        map = new HashMap<>();
        map = new Gson().fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());
        System.out.println(map.size());
    }
}

