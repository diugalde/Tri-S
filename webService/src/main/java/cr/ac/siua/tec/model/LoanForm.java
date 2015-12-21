package cr.ac.siua.tec.model;


public class LoanForm extends Form {

    private String id;
    private String justification;
    private String useDate;
    private String initialUseTime;
    private String finalUseTime;

    public LoanForm() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public String getInitialUseTime() {
        return initialUseTime;
    }

    public void setInitialUseTime(String initialUseTime) {
        this.initialUseTime = initialUseTime;
    }

    public String getFinalUseTime() {
        return finalUseTime;
    }

    public void setFinalUseTime(String finalUseTime) {
        this.finalUseTime = finalUseTime;
    }

    @Override
    public String toString() {
        return "LoanForm{" +
                "id='" + id + '\'' +
                ", justification='" + justification + '\'' +
                ", useDate='" + useDate + '\'' +
                ", initialUseTime='" + initialUseTime + '\'' +
                ", finalUseTime='" + finalUseTime + '\'' +
                '}';
    }
}
