package cc.lyceum.api.thxy.jwgl.pojo;

import cc.lyceum.api.thxy.jwgl.ThxyJwgl;

import java.text.DecimalFormat;
import java.util.*;
import java.util.function.Function;

/**
 * @author LyceumHewun
 * @date 2018-9-6 MODIFY
 */
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

    /**
     * 个人课表和班级课表合并</br>
     * 前者优先级较后者大, 相同周次、星期和节次, 选择前者。
     * 不相同, 则合并
     *
     * @param list1 个人课表
     * @param list2 班级课表
     * @return 课程实体类集合
     */
    public static List<Curriculum> sumCurriculumList(List<Curriculum> list1, List<Curriculum> list2) {
        List<Curriculum> result = new ArrayList<>();
        // 按周次分类
        Map<String, List<Curriculum>> selfCurriculum = classified(list1, Curriculum::getZc);
        Map<String, List<Curriculum>> classCurriculum = classified(list2, Curriculum::getZc);
        // 1-22周
        for (int zc = 1; zc <= 22; zc++) {
            List<Curriculum> selfCurriculumListByZc = selfCurriculum.get("" + zc);
            List<Curriculum> classCurriculumListByZc = classCurriculum.get("" + zc);
            // 按星期分类
            Map<String, List<Curriculum>> selfCurriculumByXq = classified(selfCurriculumListByZc, Curriculum::getXq);
            Map<String, List<Curriculum>> classCurriculumByXq = classified(classCurriculumListByZc, Curriculum::getXq);
            // 星期1-5
            for (int xq = 1; xq <= 5; xq++) {
                if (null == selfCurriculumByXq && null == classCurriculumByXq) {
                    break;
                }
                List<Curriculum> selfCurriculumListByXq = null;
                if (null != selfCurriculumByXq) {
                    selfCurriculumListByXq = selfCurriculumByXq.getOrDefault("" + xq, null);
                }
                List<Curriculum> classCurriculumListByXq = null;
                if (null != classCurriculumByXq) {
                    classCurriculumListByXq = classCurriculumByXq.getOrDefault("" + xq, null);
                }
                // 按节次分类, 两节的当成第一节, 如 0304 当成 03
                Map<String, List<Curriculum>> selfCurriculumByJc = classified(selfCurriculumListByXq, c -> c.getJcdm().substring(0, 2));
                Map<String, List<Curriculum>> classCurriculumByJc = classified(classCurriculumListByXq, c -> c.getJcdm().substring(0, 2));
                // 1-11节
                for (int jc = 1; jc <= 11; jc++) {
                    if (null == selfCurriculumByJc && null == classCurriculumByJc) {
                        break;
                    }
                    String key = new DecimalFormat("00").format(jc);
                    List<Curriculum> selfCurriculumListByJc = null;
                    if (null != selfCurriculumByJc) {
                        selfCurriculumListByJc = selfCurriculumByJc.getOrDefault(key, null);
                    }
                    List<Curriculum> classCurriculumListByJc = null;
                    if (null != classCurriculumByJc) {
                        classCurriculumListByJc = classCurriculumByJc.getOrDefault(key, null);
                    }
                    if (null != selfCurriculumListByJc) {
                        result.addAll(selfCurriculumListByJc);
                    } else if (null != classCurriculumListByJc) {
                        result.addAll(classCurriculumListByJc);
                    }
                }
            }
        }
        return result;
    }

    public static List<Curriculum> sumCurriculumList(ThxyJwgl jwgl, String value, String week) {
        return sumCurriculumList(jwgl.getSelfCurriculum(value, week), jwgl.getClassCurriculum(value, week));
    }

    /**
     * 分类
     *
     * @param list     课表
     * @param function function
     * @return Map<String, List<Curriculum>>
     */
    private static Map<String, List<Curriculum>> classified(List<Curriculum> list, Function<Curriculum, String> function) {
        Map<String, List<Curriculum>> resultMap = new HashMap<>();
        if (null == list) {
            return null;
        }
        list.forEach(v -> {
            String key = function.apply(v);
            if (resultMap.containsKey(key)) {
                List<Curriculum> oldList = new ArrayList<>(resultMap.get(key));
                oldList.add(v);
                resultMap.put(key, oldList);
            } else {
                resultMap.put(key, Collections.singletonList(v));
            }
        });
        return resultMap;
    }
}
