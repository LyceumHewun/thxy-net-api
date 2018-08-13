package cc.lyceum.api.thxy.logistics.pojo;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    public abstract static class Dorm {
        public static Map<String, String> map = new HashMap<>();

        static {
            map.put("0", "0栋");
            map.put("100", "1栋A");
            map.put("101", "1栋B");
            map.put("102", "1栋C");
            map.put("2", "2栋");
            map.put("29", "2栋B");
            map.put("3", "3栋");
            map.put("4", "4栋");
            map.put("5", "5栋");
            map.put("6", "6栋");
            map.put("7", "7栋");
            map.put("8", "8栋");
            map.put("9", "9栋");
            map.put("10", "10栋");
            map.put("11", "11栋A");
            map.put("110", "11栋B");
            map.put("12", "12栋");
            map.put("13", "13栋");
            map.put("14", "14栋");
            map.put("15", "15栋");
            map.put("16", "16栋A");
            map.put("161", "16栋B");
            map.put("162", "16栋C");
            map.put("163", "16栋D");
            map.put("17", "17栋");
            map.put("18", "18栋");
            map.put("190", "19栋A");
            map.put("191", "19栋B");
            map.put("192", "19栋C");
            map.put("200", "20栋A");
            map.put("201", "20栋B");
            map.put("21", "21栋");
            map.put("22", "22栋");
            map.put("23", "23栋");
            map.put("230", "23栋B");
            map.put("241", "24栋A");
            map.put("242", "24栋B");
            map.put("28", "28栋");
            map.put("38", "1号员工");
            map.put("20", "2号员工");
            map.put("31", "1栋教工");
            map.put("32", "2栋教工");
            map.put("33", "3栋教工");
            map.put("34", "4栋教工");
            map.put("30", "5栋教工");
            map.put("36", "6栋教工");
            map.put("39", "3栋教学楼");
            map.put("109", "10栋教学楼");
            map.put("37", "教授楼");
            map.put("35", "汽训楼");
            map.put("40", "图书馆");
            map.put("41", "新区配电");
        }

        public static String strToSectId(String str) {
            str = str.replaceAll("#", "栋");
            StringBuilder s = new StringBuilder();
            String finalStr = str;
            map.forEach((k, v) -> {
                if (finalStr.contains(v)) {
                    s.append(k);
                }
            });
            return s.toString();
        }

        public static String sectIdToStr(String sectid, boolean b) {
            String str = map.get(sectid);
            return b ? str.replaceAll("栋", "#") : str;
        }

        public static String sectIdToStr(String sectid) {
            return sectIdToStr(sectid, true);
        }
    }
}
