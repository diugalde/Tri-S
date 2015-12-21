package cr.ac.siua.tec.model;


public class Equipment extends LoanForm{

    private String detail;

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "detail='" + detail + '\'' +
                '}';
    }
}
