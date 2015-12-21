package cr.ac.siua.tec.model;


public class Constancy extends Form{

    private String id;
    private String motive;

    public Constancy() {}

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Constancy{" +
                "id='" + id + '\'' +
                ", motive='" + motive + '\'' +
                '}';
    }
}
