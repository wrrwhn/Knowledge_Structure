package com.yao.utils.custom;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class GSonUtils {

    public static void main(String[] args) {
        parse();
    }

    public static void parse() {
        String json = "{\"success\":true,\"room\":{\"roomId\":\"jz33a2cd94a0804e198ca0a6b8285d7b12\",\"title\":\"dby.jar\",\"startTime\":\"2016-07-14 14:38:00\",\"endTime\":\"2016-07-14 16:38:00\",\"validEndTime\":\"2099-01-01 23:59\",\"video\":false,\"hostCode\":\"jzfp92j2py\",\"coverImgUrl\":null,\"convertStatus\":0,\"courseType\":2,\"vod\":false,\"weixinLiving\":false}}";
        Gson gson = new Gson();

        Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>() {
        }.getType());

        LinkedTreeMap room = (LinkedTreeMap) map.get("room");

        json = gson.toJson(room);
        System.out.println(json);

        Room r = gson.fromJson(json, new TypeToken<Room>() {
        }.getType());
        System.out.println(r.getRoomId());
    }

    public static void typeParse(){
        String json= "{\"roomId\":\"jz33a2cd94a0804e198ca0a6b8285d7b12\",\"title\":\"dby.jar\",\"startTime\":\"2016-07-14 14:38:00\",\"endTime\":\"2016-07-14 16:38:00\",\"validEndTime\":\"2099-01-01 23:59\",\"video\":false,\"hostCode\":\"jzfp92j2py\",\"convertStatus\":0.0,\"courseType\":2.0,\"vod\":false,\"weixinLiving\":false}";

    }

    public class Room {

        private String roomId;
        private String title;
        private String startTime;
        private String endTime;
        private String validEndTime;
        private boolean video;
        private String hostCode;
        private String coverImgUrl;
        private int convertStatus;
        private int courseType;
        private boolean vod;
        private boolean weixinLiving;

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getValidEndTime() {
            return validEndTime;
        }

        public void setValidEndTime(String validEndTime) {
            this.validEndTime = validEndTime;
        }

        public boolean isVideo() {
            return video;
        }

        public void setVideo(boolean video) {
            this.video = video;
        }

        public String getHostCode() {
            return hostCode;
        }

        public void setHostCode(String hostCode) {
            this.hostCode = hostCode;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public int getConvertStatus() {
            return convertStatus;
        }

        public void setConvertStatus(int convertStatus) {
            this.convertStatus = convertStatus;
        }

        public int getCourseType() {
            return courseType;
        }

        public void setCourseType(int courseType) {
            this.courseType = courseType;
        }

        public boolean isVod() {
            return vod;
        }

        public void setVod(boolean vod) {
            this.vod = vod;
        }

        public boolean isWeixinLiving() {
            return weixinLiving;
        }

        public void setWeixinLiving(boolean weixinLiving) {
            this.weixinLiving = weixinLiving;
        }
    }
}
