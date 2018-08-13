package cc.lyceum.api.thxy.jwgl.pojo;

import java.util.*;

public class Utils {

    private static Map<String, String> result_map = new HashMap<>();

    static {
        // 学年学期代码
        result_map.put("", "全部");
        result_map.put("202201", "2022-2023-1");
        result_map.put("202102", "2021-2022-2");
        result_map.put("202101", "2021-2022-1");
        result_map.put("202002", "2020-2021-2");
        result_map.put("202001", "2020-2021-1");
        result_map.put("201902", "2019-2020-2");
        result_map.put("201901", "2019-2020-1");
        result_map.put("201802", "2018-2019-2");
        result_map.put("201801", "2018-2019-1");
        result_map.put("201702", "2017-2018-2");
        result_map.put("201701", "2017-2018-1");
        result_map.put("20162", "2016-2017-2");
        result_map.put("20161", "2016-2017-1");
        result_map.put("20152", "2015-2016-2");
        result_map.put("20151", "2015-2016-1");
        result_map.put("20142", "2014-2015-2");
        result_map.put("20141", "2014-2015-1");
        result_map.put("20132", "2013-2014-2");
        result_map.put("20131", "2013-2014-1");
        result_map.put("20122", "2012-2013-2");
        result_map.put("20121", "2012-2013-1");
        result_map.put("20112", "2011-2012-2");
        result_map.put("20111", "2011-2012-1");
        result_map.put("20102", "2010-2011-2");
        result_map.put("20101", "2010-2011-1");
    }

    /**
     * 按学年学期降序排列分类
     */
    public static Map<String, List<ExamResults>> sort(List<ExamResults> list) {
        Map<String, List<ExamResults>> map = new TreeMap<>(Comparator.reverseOrder());
        list.forEach(examResults -> {
            String xnxqmc = examResults.getXnxqmc();
            if (map.containsKey(xnxqmc)) {
                List<ExamResults> l = map.get(xnxqmc);
                l.add(examResults);
                map.put(xnxqmc, l);
            } else {
                List<ExamResults> l = new ArrayList<>();
                l.add(examResults);
                map.put(xnxqmc, l);
            }
        });
        return map;
    }

    /**
     * 学年学期代码转成学年学期名称
     */
    public static String valueToXqxnmc(String value) {
        return result_map.getOrDefault(value, "");
    }
}
