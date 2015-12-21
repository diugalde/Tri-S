package cr.ac.siua.tec.model;

public class Workstation extends LoanForm {

    private String labId;

    public Workstation() {}

    public String getLabId() {
        return labId;
    }

    public void setLabId(String labId) {
        this.labId = labId;
    }

    @Override
    public String toString() {
        return "Workstation{" +
                "labId='" + labId + '\'' +
                '}';
    }
}
