package cc.lyceum.api.thxy.network.pojo;

/**
 * 计费周期
 *
 * @author Lyceum Hewun
 * @version 1.0.0
 * @date 2018-08-01
 */
public class TollCycle {

    /**
     * 服务名称
     */
    private String serviceName;
    /**
     * 服务描述
     */
    private String description;
    /**
     * 服务后缀
     */
    private String suffix;
    /**
     * 计费周期开始时间
     */
    private String startTime;
    /**
     * 计费周期结束时间
     */
    private String endTime;

    public TollCycle() {
    }

    public TollCycle(String serviceName, String description, String suffix, String startTime, String endTime) {
        this.serviceName = serviceName;
        this.description = description;
        this.suffix = suffix;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "TollCycle{" +
                "serviceName='" + serviceName + '\'' +
                ", description='" + description + '\'' +
                ", suffix='" + suffix + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
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
}
