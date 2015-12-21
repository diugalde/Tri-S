package cr.ac.siua.tec.model;


public class Assistance extends Form {

    private String id;
    private String assistanceType;
    private String assistanceActivities;
    private String career;
    private String officialName;
    private int courseScore;
    private int studentAverageScore;
    private int assistanceTotalHours;
    private String bankName;
    private String clientAccountNumber;

    public Assistance() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAssistanceType() {
        return assistanceType;
    }

    public void setAssistanceType(String assistanceType) {
        this.assistanceType = assistanceType;
    }

    public String getAssistanceActivities() {
        return assistanceActivities;
    }

    public void setAssistanceActivities(String assistanceActivities) {
        this.assistanceActivities = assistanceActivities;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public int getCourseScore() {
        return courseScore;
    }

    public void setCourseScore(int courseScore) {
        this.courseScore = courseScore;
    }

    public int getStudentAverageScore() {
        return studentAverageScore;
    }

    public void setStudentAverageScore(int studentAverageScore) {
        this.studentAverageScore = studentAverageScore;
    }

    public int getAssistanceTotalHours() {
        return assistanceTotalHours;
    }

    public void setAssistanceTotalHours(int assistanceTotalHours) {
        this.assistanceTotalHours = assistanceTotalHours;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getClientAccountNumber() {
        return clientAccountNumber;
    }

    public void setClientAccountNumber(String clientAccountNumber) {
        this.clientAccountNumber = clientAccountNumber;
    }

    @Override
    public String toString() {
        return "Assistance{" +
                "id='" + id + '\'' +
                ", assistanceType='" + assistanceType + '\'' +
                ", assistanceActivities='" + assistanceActivities + '\'' +
                ", career='" + career + '\'' +
                ", officialName='" + officialName + '\'' +
                ", courseScore=" + courseScore +
                ", studentAverageScore=" + studentAverageScore +
                ", assistanceTotalHours=" + assistanceTotalHours +
                ", bankName='" + bankName + '\'' +
                ", clientAccountNumber='" + clientAccountNumber + '\'' +
                '}';
    }
}
