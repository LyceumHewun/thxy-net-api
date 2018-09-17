package cc.lyceum.api.thxy.logistics.pojo;

import java.util.HashMap;
import java.util.Map;

public class Utils {

    /**
     * 保修单专用
     */
    public static class RepairOrderDorm {
        public static Map<String, String> map = new HashMap<>();

        static {
            map.put("1 ", "1栋A");
            map.put("2 ", "1栋B");
            map.put("3 ", "1栋C");
            map.put("4 ", "2栋");
            map.put("5 ", "2栋B");
            map.put("6 ", "3栋");
            map.put("7 ", "4栋");
            map.put("8 ", "5栋");
            map.put("9 ", "6栋");
            map.put("10", "7栋A");
            map.put("11", "7栋B");
            map.put("12", "8栋");
            map.put("13", "9栋");
            map.put("14", "10栋");
            map.put("15", "11栋A");
            map.put("16", "11栋B");
            map.put("17", "12栋");
            map.put("18", "13栋");
            map.put("19", "14栋");
            map.put("20", "15栋");
            map.put("21", "16栋A");
            map.put("22", "16栋B");
            map.put("23", "16栋C");
            map.put("24", "16栋D");
            map.put("25", "17栋");
            map.put("26", "18栋");
            map.put("27", "19栋A");
            map.put("28", "19栋B");
            map.put("29", "19栋C");
            map.put("30", "20栋A");
            map.put("31", "20栋B");
            map.put("32", "21栋");
            map.put("33", "22栋");
            map.put("34", "22栋B");
            map.put("35", "23栋A");
            map.put("36", "23栋B");
            map.put("37", "28栋");
            map.put("38", "1栋教学楼");
            map.put("39", "2栋教学楼");
            map.put("40", "3栋教学楼");
            map.put("41", "4栋教学楼");
            map.put("42", "5栋教学楼");
            map.put("43", "6栋教学楼");
            map.put("44", "7栋教学楼（综合楼）");
            map.put("45", "8栋教学楼");
            map.put("46", "9栋教学楼");
            map.put("47", "10栋教学楼");
            map.put("48", "图书馆");
            map.put("49", "0栋教工宿舍");
            map.put("50", "1栋教工宿舍");
            map.put("51", "2栋教工宿舍");
            map.put("52", "3栋教工宿舍");
            map.put("53", "4栋教工宿舍");
            map.put("54", "汽修楼");
            map.put("55", "5栋教工宿舍");
            map.put("56", "6栋教工宿舍");
            map.put("57", "2饭堂职工宿舍");
            map.put("58", "1栋教授楼");
            map.put("59", "1栋饭堂");
            map.put("60", "2栋饭堂");
            map.put("61", "3栋饭堂");
            map.put("62", "后勤楼");
            map.put("63", "24栋A");
            map.put("64", "24栋B");
            map.put("65", "26栋");
            map.put("66", "27栋");
            map.put("67", "7栋教工楼");
            map.put("68", "信息楼（11栋教学楼）");
            map.put("69", "12栋教学楼");
            map.put("70", "大运动场");
            map.put("71", "小运动场");
            map.put("72", "体育馆");
        }


        public static String strToSectId(String str) {
            str = str.replaceAll("#", "栋");
            str = splitDorm(str);
            StringBuilder s = new StringBuilder();
            String finalStr = str;
            map.forEach((k, v) -> {
                if (finalStr.equals(v)) {
                    s.append(k);
                }
            });
            return s.toString();
        }

        public static String sectIdToStr(String sectid) {
            return map.get(sectid);
        }

        private static String splitDorm(String s) {
            if (!s.contains("栋")) {
                return s;
            }
            // 判断'栋'后一个字符是不是英文字母
            int index = s.indexOf("栋") + 1;
            String c = s.substring(index, index + 1);
            if (c.matches("[a-zA-Z]")) {
                return s.substring(0, index + 1);
            } else {
                return s.substring(0, index);
            }
        }

        public static String strToDormId(String str) {
            if (!str.contains("栋")) {
                return str;
            }
            str = str.substring(str.indexOf("栋") + 1, str.length());
            if (str.substring(0, 1).matches("[a-zA-Z]")) {
                return str.substring(1);
            } else {
                return str;
            }
        }
    }

    /**
     * 查电系统专用
     */
    public static class Dorm {
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
            str = splitDorm(str);
            StringBuilder s = new StringBuilder();
            String finalStr = str;
            map.forEach((k, v) -> {
                if (finalStr.equals(v)) {
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
            return sectIdToStr(sectid, false);
        }

        private static String splitDorm(String s) {
            if (!s.contains("栋")) {
                return s;
            }
            // 判断'栋'后一个字符是不是英文字母
            int index = s.indexOf("栋") + 1;
            String c = s.substring(index, index + 1);
            if (c.matches("[a-zA-Z]")) {
                return s.substring(0, index + 1);
            } else {
                return s.substring(0, index);
            }
        }

        public static String strToDormId(String str) {
            if (!str.contains("栋")) {
                return str;
            }
            str = str.substring(str.indexOf("栋") + 1, str.length());
            if (str.substring(0, 1).matches("[a-zA-Z]")) {
                return str.substring(1);
            } else {
                return str;
            }
        }
    }
}
