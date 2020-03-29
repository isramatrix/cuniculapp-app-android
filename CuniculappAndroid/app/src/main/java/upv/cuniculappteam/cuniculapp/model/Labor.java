package upv.cuniculappteam.cuniculapp.model;

public class Labor extends Traceable {

    public static final Creator<Labor> CREATOR = new TraceableCreator<>(Labor.class);

    public String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
