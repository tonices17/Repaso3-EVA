import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.Scanner;

public class Lista {
    ArrayList<Inmueble> listaInmuebles;

    public Lista(ArrayList<Inmueble> listaInmuebles) {
        this.listaInmuebles = listaInmuebles;
    }

    public ArrayList<Inmueble> getListaInmuebles() {
        return listaInmuebles;
    }

    public void setListaInmuebles(ArrayList<Inmueble> listaInmuebles) {
        this.listaInmuebles = listaInmuebles;
    }

    public void mostrarInmueble () {
        if (listaInmuebles.isEmpty()) {
            System.out.println("La lista está vacía.");
        } else {
            for (int i = 0; i < listaInmuebles.size(); i++) {
                System.out.println("Id: " + listaInmuebles.get(i).getId() + "; dirección: " +
                        listaInmuebles.get(i).getDireccion() + "; ciudad: " + listaInmuebles.get(i).getCiudad() +
                        "; tipo: " + listaInmuebles.get(i).getTipo() + ".");
            }
        }
    }
    public Inmueble buscarInmueble(int id) {
        Inmueble devolver = null;
        if (listaInmuebles.isEmpty()) {
            System.out.println("La lista está vacía.");
        } else {
            for (int i = 0; i < listaInmuebles.size(); i++) {
                if(listaInmuebles.get(i).getId() == id) {
                    devolver = listaInmuebles.get(i);
                }
            }
        }
        return devolver;
    }

    public boolean insertarInmueble(Inmueble inmueble) {
        boolean devolver;
        Inmueble busquedaInmueble = buscarInmueble(inmueble.getId());
        if (busquedaInmueble == null) {
            listaInmuebles.add(inmueble);
            devolver = true;
        } else {
            devolver = false;
        }
        return devolver;
    }

    public boolean eliminarInmueble(int id) {
        boolean devolver;
        Inmueble busquedaInmueble = buscarInmueble(id);
        if (busquedaInmueble != null) {
            listaInmuebles.remove(busquedaInmueble);
            devolver = true;
        } else {
            devolver = false;
        }
        return devolver;
    }

    public boolean actualizarInmueble(int id) {
        boolean devolver;
        String direccion;
        String ciudad;
        String tipo;
        Scanner sc = new Scanner(System.in);
        Inmueble busquedaInmueble = buscarInmueble(id);
        if (busquedaInmueble != null) {
            System.out.println("Introduce la nueva dirección: ");
            direccion = sc.nextLine();
            System.out.println("Introduce la nueva ciudad: ");
            ciudad = sc.nextLine();
            System.out.println("Introduce el tipo de inmueble (alquiler/venta): ");
            tipo = sc.nextLine();
            Inmueble actualizaInmueble = new Inmueble(busquedaInmueble.getId(),direccion,ciudad,tipo);
            listaInmuebles.set(busquedaInmueble.getId()-1,actualizaInmueble);
            devolver = true;
        } else {
            devolver = false;
        }
        return devolver;
    }

    public void mostrarInmueble(Inmueble inmueble) {
        System.out.println("Id: " + inmueble.getId() + "; dirección: " +
                inmueble.getDireccion() + "; ciudad: " + inmueble.getCiudad() +
                "; tipo: " + inmueble.getTipo() + ".");
    }
}
