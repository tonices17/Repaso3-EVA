public class Inmueble {
    String direccion;
    String ciudad;
    String tipo;
    int id;

    public Inmueble(int id, String direccion, String ciudad, String tipo) {
        this.id = id;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.tipo = tipo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
