package cr.ac.siua.tec.model;


public class Constancy {

    private String studentName;
    private String email;
    private String studentId;
    private String id;
    private String motive;

    public Constancy(String studentName, String email, String studentId, String id, String motive) {
        this.studentName = studentName;
        this.email = email;
        this.studentId = studentId;
        this.id = id;
        this.motive = motive;
    }

    public Constancy() {}

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getstudentId() {
        return studentId;
    }

    public void setstudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    @Override
    public String toString() {
        return "Constancy [studentName=" + this.studentName + ", email=" + this.email + ", studentId=" + this.studentId
                + ", id=" + this.id + ", motive=" + this.motive + "]";
    }
}
