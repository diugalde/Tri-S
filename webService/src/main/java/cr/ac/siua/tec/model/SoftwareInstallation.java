package cr.ac.siua.tec.model;


public class SoftwareInstallation extends Form{

    private String id;
    private String labId;
    private String detail;
    private String justification;

    public SoftwareInstallation() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public String toString() {
        return "SoftwareInstallation{" +
                "id='" + id + '\'' +
                ", labId='" + labId + '\'' +
                ", detail='" + detail + '\'' +
                ", justification='" + justification + '\'' +
                '}';
    }
}
