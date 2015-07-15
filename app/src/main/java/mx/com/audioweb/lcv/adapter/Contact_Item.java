package mx.com.audioweb.lcv.adapter;

/**
 * Created by Juan Acosta on 8/20/2014.
 */
public class Contact_Item {

    private String Nombre;
    private String Telefono;
    private String Correo;

    public Contact_Item() {

    }

    public Contact_Item(String Nombre, String Telefono) {
        this.Nombre = Nombre;
        this.Telefono = Telefono;
    }

    public Contact_Item(String Nombre, String Telefono, String Correo) {
        this.Nombre = Nombre;
        this.Telefono = Telefono;
        this.Correo = Correo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String telefono) {
        Telefono = telefono;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }


}
