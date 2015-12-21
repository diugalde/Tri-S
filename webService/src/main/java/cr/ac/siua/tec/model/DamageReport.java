package cr.ac.siua.tec.model;


public class DamageReport extends Form {

    private String labId;
    private String detail;
    private String deviceId;

    public DamageReport() {}

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "DamageReport{" +
                "labId='" + labId + '\'' +
                ", detail='" + detail + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
