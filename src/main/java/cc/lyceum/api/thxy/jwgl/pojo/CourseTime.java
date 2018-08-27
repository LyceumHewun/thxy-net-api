package cc.lyceum.api.thxy.jwgl.pojo;

import java.text.SimpleDateFormat;
import java.util.*;

public class CourseTime {
    /**
     * 现在Calendar时间
     */
    private Calendar now;
    /**
     * 现在的时间
     */
    private String nowTime;
    /**
     * 现在时间段名称
     */
    private String nowCourseName;
    /**
     * 现在离下课的时间
     */
    private String nowCourseFinishJetLag;
    /**
     * 下一节课时间
     */
    private String nextTime;
    /**
     * 下一节课名称
     */
    private String nextCourseName;
    /**
     * 离下一节课的时差
     */
    private String nextCourseJetLag;
    /**
     * 下一节课程详细
     */
    private Curriculum nextCurriculum;

    @Override
    public String toString() {
        return "CourseTime{" +
                "nowTime='" + nowTime + '\'' +
                ", nowCourseName='" + nowCourseName + '\'' +
                ", nowCourseFinishJetLag='" + nowCourseFinishJetLag + '\'' +
                ", nextTime='" + nextTime + '\'' +
                ", nextCourseName='" + nextCourseName + '\'' +
                ", nextCourseJetLag='" + nextCourseJetLag + '\'' +
                ", nextCurriculum=" + nextCurriculum +
                '}';
    }

    public Calendar getNow() {
        return now;
    }

    public void setNow(Calendar now) {
        this.now = now;
    }

    public String getNowTime() {
        return nowTime;
    }

    public void setNowTime(String nowTime) {
        this.nowTime = nowTime;
    }

    public String getNowCourseName() {
        return nowCourseName;
    }

    public void setNowCourseName(String nowCourseName) {
        this.nowCourseName = nowCourseName;
    }

    public String getNowCourseFinishJetLag() {
        return nowCourseFinishJetLag;
    }

    public void setNowCourseFinishJetLag(String nowCourseFinishJetLag) {
        this.nowCourseFinishJetLag = nowCourseFinishJetLag;
    }

    public String getNextTime() {
        return nextTime;
    }

    public void setNextTime(String nextTime) {
        this.nextTime = nextTime;
    }

    public String getNextCourseName() {
        return nextCourseName;
    }

    public void setNextCourseName(String nextCourseName) {
        this.nextCourseName = nextCourseName;
    }

    public String getNextCourseJetLag() {
        return nextCourseJetLag;
    }

    public void setNextCourseJetLag(String nextCourseJetLag) {
        this.nextCourseJetLag = nextCourseJetLag;
    }

    public Curriculum getNextCurriculum() {
        return nextCurriculum;
    }

    public void setNextCurriculum(Curriculum nextCurriculum) {
        this.nextCurriculum = nextCurriculum;
    }

    public static abstract class Utils {
        /**
         * 天河学院作息时间安排表
         */
        public static List<Calendar> calendarList = Arrays.asList(
                createCalendar(0, 0),
                createCalendar(6, 30),
                createCalendar(8, 20),
                createCalendar(9, 5),
                createCalendar(9, 15),
                createCalendar(10, 0),
                createCalendar(10, 20),
                createCalendar(11, 5),
                createCalendar(11, 15),
                createCalendar(12, 0),
                createCalendar(14, 30),
                createCalendar(15, 15),
                createCalendar(15, 25),
                createCalendar(16, 10),
                createCalendar(16, 25),
                createCalendar(17, 10),
                createCalendar(17, 15),
                createCalendar(18, 0),
                createCalendar(19, 30),
                createCalendar(20, 15),
                createCalendar(20, 25),
                createCalendar(21, 10),
                createCalendar(24, 0)
        );
        public static List<String> courseNameList = Arrays.asList(
                "凌晨",
                "早餐时间",
                "第1节",
                "第12节课间",
                "第2节",
                "第23节课间",
                "第3节",
                "第34节课间",
                "第4节",
                "午休时间",
                "第5节",
                "第56节课间",
                "第6节",
                "第67节课间",
                "第7节",
                "第78节课间",
                "第8节",
                "晚餐时间",
                "第9节",
                "第910节课间",
                "第10节",
                "今天的课上完了",
                "今天的课上完了"
        );

        public static Calendar createCalendar(int hour, int minute) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            return calendar;
        }

        public static CourseTime getCourseTimeByTodayCurriculumList(List<Curriculum> curriculumList) {
            CourseTime courseTime = getNowCourseTime();
            // 根据今天课程安排划定区间
            List<Integer> courseInterval = new ArrayList<>();
            courseInterval.add(0);
            for (Curriculum c : curriculumList) {
                String[] jcdm = c.getJcdm2().split(",");
                for (String s : jcdm) {
                    String str = "第" + Integer.valueOf(s) + "节";
                    courseInterval.add(courseNameList.indexOf(str));
                }
            }
            courseInterval.add(21);
            Calendar now = courseTime.getNow();
            int i = 0;
            // 根据新区间获取下一节课详细
            for (; i < courseInterval.size() - 1; i++) {
                Calendar start = calendarList.get(courseInterval.get(i));
                Calendar end = calendarList.get(courseInterval.get(i + 1));
                if (start.before(now) && end.after(now)) {
                    courseTime.setNextTime(getTime(end));
                    courseTime.setNextCourseName(courseNameList.get(courseInterval.get(i + 1)));
                    courseTime.setNextCourseJetLag(getJetLag(now, end));
                    break;
                }
            }
            // 根据i找课程信息
            try {
                courseTime.setNextCurriculum(curriculumList.get((i + 2) / 2 - 1));
            } catch (Exception e) {
                if (e.getMessage().contains("IndexOutOfBoundsException")) {
                    courseTime.setNextCurriculum(null);
                }
            }
            return courseTime;
        }

        public static CourseTime getNowCourseTime() {
            CourseTime courseTime = new CourseTime();
            Calendar now = Calendar.getInstance();
            courseTime.setNow(now);
            courseTime.setNowTime(getTime(now));
            for (int i = 0; i < courseNameList.size() - 1; i++) {
                Calendar start = calendarList.get(i);
                Calendar end = calendarList.get(i + 1);
                if (start.before(now) && end.after(now)) {
                    String nowtCourseName = courseNameList.get(i);
                    courseTime.setNowCourseName(nowtCourseName);
                    if (nowtCourseName.endsWith("节")) {
                        // 如果是课, 则找下课时间
                        courseTime.setNowCourseFinishJetLag(getJetLag(now, end));
                    }
                    return courseTime;
                }
            }
            throw new NullPointerException("现在的时间不在设定的区间内");
        }

        public static String getTime(Calendar calendar) {
            return new SimpleDateFormat("HH:mm").format(calendar.getTime());
        }

        public static String getJetLag(Calendar c1, Calendar c2) {
            long l1;
            long l2;
            if (c1.getTimeInMillis() > c2.getTimeInMillis()) {
                l1 = c1.getTime().getTime();
                l2 = c2.getTime().getTime();
            } else {
                l1 = c2.getTime().getTime();
                l2 = c1.getTime().getTime();
            }
            Date date = new Date(l1 - l2);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H小时mm分ss秒");
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0:00"));
            String jetLag = simpleDateFormat.format(date);
            if (jetLag.startsWith("0小时0分")) {
                jetLag = jetLag.replaceAll("0小时0分", "");
            }
            if (jetLag.startsWith("0小时")) {
                jetLag = jetLag.replaceAll("0小时", "");
            }
            return jetLag;
        }
    }
}
