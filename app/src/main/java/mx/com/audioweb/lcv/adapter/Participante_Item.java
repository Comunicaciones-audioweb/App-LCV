package mx.com.audioweb.lcv.adapter;

/**
 * Created by Juan Acosta on 8/21/2014.
 */
public class Participante_Item {

    private String Name, Id, Time;

    public Participante_Item(){
    }

    public Participante_Item(String Id, String Time) {
        this.Id = Id;
        this.Time = Time;
    }

    public Participante_Item(String Id, String Time, String Name) {
        this.Name = Name;
        this.Id = Id;
        this.Time = Time;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public String getTime() {
        String[] tiempo = Time.split(" ");
        return tiempo[1];
    }

    public void setTime(String Time) {
        this.Time = Time;
    }



}
