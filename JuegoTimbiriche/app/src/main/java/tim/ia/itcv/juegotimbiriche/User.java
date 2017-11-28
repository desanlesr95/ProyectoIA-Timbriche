package tim.ia.itcv.juegotimbiriche;

/**
 * Created by luis on 16/11/17.
 */

public class User {
    int imagen;
    int id;
    String nombre;

    public User(int imagen, int id, String nombre) {
        this.imagen = imagen;
        this.id = id;
        this.nombre = nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "User{" +
                "imagen=" + imagen +
                ", id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
