package cr.ac.siua.tec.model;


public class Inclusion extends Form{

    private String cellphone;
    private String career;
    private String enrollDay;
    private String enrollTime;
    private String courseName;
    private String courseCode;
    private int courseGroup;
    private boolean fulfillRequisites;
    private boolean hasTimeConflict;
    private int rn;
    private String enrolledCourses;
    private String justification;

    public Inclusion() {}

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career;
    }

    public String getEnrollDay() {
        return enrollDay;
    }

    public void setEnrollDay(String enrollDay) {
        this.enrollDay = enrollDay;
    }

    public String getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(String enrollTime) {
        this.enrollTime = enrollTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public int getCourseGroup() {
        return courseGroup;
    }

    public void setCourseGroup(int courseGroup) {
        this.courseGroup = courseGroup;
    }

    public boolean isFulfillRequisites() {
        return fulfillRequisites;
    }

    public void setFulfillRequisites(boolean fulfillRequisites) {
        this.fulfillRequisites = fulfillRequisites;
    }

    public boolean isHasTimeConflict() {
        return hasTimeConflict;
    }

    public void setHasTimeConflict(boolean hasTimeConflict) {
        this.hasTimeConflict = hasTimeConflict;
    }

    public String getEnrolledCourses() {
        return enrolledCourses;
    }

    public void setEnrolledCourses(String enrolledCourses) {
        this.enrolledCourses = enrolledCourses;
    }

    public int getRn() {
        return rn;
    }

    public void setRn(int rn) {
        this.rn = rn;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public String toString() {
        return "Inclusion{" +
                "cellphone='" + cellphone + '\'' +
                ", career='" + career + '\'' +
                ", enrollDay='" + enrollDay + '\'' +
                ", enrollTime='" + enrollTime + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseGroup=" + courseGroup +
                ", fulfillRequisites=" + fulfillRequisites +
                ", hasTimeConflict=" + hasTimeConflict +
                ", rn=" + rn +
                ", enrolledCourses='" + enrolledCourses + '\'' +
                ", justification='" + justification + '\'' +
                '}';
    }
}
