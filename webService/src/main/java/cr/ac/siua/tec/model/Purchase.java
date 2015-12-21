package cr.ac.siua.tec.model;


public class Purchase {

    private String officialName;
    private String email;
    private String id;
    private String detail;
    private String justification;

    public Purchase() {}

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return "Purchase{" +
                "officialName='" + officialName + '\'' +
                ", email='" + email + '\'' +
                ", id='" + id + '\'' +
                ", detail='" + detail + '\'' +
                ", justification='" + justification + '\'' +
                '}';
    }
}
