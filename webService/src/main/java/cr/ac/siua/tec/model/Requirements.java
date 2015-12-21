package cr.ac.siua.tec.model;


public class Requirements extends Form{

    private String campus;
    private String courseName;
    private String courseCode;
    private String requisiteCourses;
    private String requisiteType;
    private String justification;

    public Requirements() {}

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
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

    public String getRequisiteCourses() {
        return requisiteCourses;
    }

    public void setRequisiteCourses(String requisiteCourses) {
        this.requisiteCourses = requisiteCourses;
    }

    public String getRequisiteType() {
        return requisiteType;
    }

    public void setRequisiteType(String requisiteType) {
        this.requisiteType = requisiteType;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    @Override
    public String toString() {
        return "Requirements{" +
                "campus='" + campus + '\'' +
                ", courseName='" + courseName + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", requisiteCourses='" + requisiteCourses + '\'' +
                ", requisiteType='" + requisiteType + '\'' +
                ", justification='" + justification + '\'' +
                '}';
    }
}
