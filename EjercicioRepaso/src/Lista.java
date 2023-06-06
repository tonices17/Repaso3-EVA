import java.util.ArrayList;
import java.util.Scanner;

public class Lista {
    ArrayList<Alumno> listaAlumnos;

    public Lista(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public ArrayList<Alumno> getListaAlumnos() {
        return listaAlumnos;
    }

    public void setListaAlumnos(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    public void mostrarAlumnos(ArrayList<Alumno> listaAlumnos) {
        if (listaAlumnos.isEmpty()) {
            System.out.println("No hay alumnos en la lista.");
        } else {
            for (int i = 0; i < listaAlumnos.size(); i++) {
                System.out.println("Nombre: " + listaAlumnos.get(i).getNombre() + ", edad: " + listaAlumnos.get(i).getEdad() + ".");
            }
        }
    }

    public Alumno buscarAlumno(String nombre, ArrayList<Alumno> listaAlumnos) {
        if (listaAlumnos.isEmpty()) {
            System.out.println("No hay alumnos en la lista.");
        } else {
            for (int i = 0; i < listaAlumnos.size(); i++) {
                if (listaAlumnos.get(i).getNombre().contains(nombre)) {
                    return listaAlumnos.get(i);
                }
            }
        }
        return null;
    }

    public boolean insertarAlumno(Alumno alumno, ArrayList<Alumno> listaAlumnos) {
        boolean devolver;
        if (listaAlumnos.isEmpty()) {
            listaAlumnos.add(alumno);
            devolver = true;
        } else {
            Alumno alumnoBusqueda = buscarAlumno(alumno.getNombre(), listaAlumnos);
            if (alumnoBusqueda == null) {
                listaAlumnos.add(alumno);
                devolver = true;
            } else {
                System.out.println("El alumno " + alumno.getNombre() + " ya se encuentra en la lista.");
                devolver = false;
            }
        }
        return devolver;
    }

    public boolean modificarAlumno(String nombre, ArrayList<Alumno> listaAlumnos) {
        Scanner sc = new Scanner(System.in);
        boolean devolver;
        String nuevoNombre;
        int nuevaEdad;
        Alumno alumnoBusqueda = buscarAlumno(nombre,listaAlumnos);
        Alumno alumnoModificado;
        if(alumnoBusqueda != null) {
            System.out.println("Alumno " + nombre + " encontrado. Introduce sus nuevos datos: ");
            System.out.println("Nombre nuevo: ");
            nuevoNombre = sc.nextLine();
            System.out.println("Edad nueva: ");
            nuevaEdad = sc.nextInt();
            alumnoModificado = new Alumno(nuevoNombre,nuevaEdad);
            listaAlumnos.set(listaAlumnos.indexOf(alumnoBusqueda),alumnoModificado);
            devolver = true;
        } else {
            System.out.println("El alumno que intentas modificar, no se encuentra en la lista.");
            devolver = false;
        }
        return devolver;
    }

    public boolean eliminarAlumno(String nombre, ArrayList<Alumno> listaAlumnos) {
        boolean devolver;
        Alumno alumnoBusqueda = buscarAlumno(nombre,listaAlumnos);
        if(alumnoBusqueda != null) {
            listaAlumnos.remove(alumnoBusqueda);
            devolver = true;
        } else {
            System.out.println("El alumno que estás intentando borrar no se encuentra en la lista.");
            devolver = false;
        }
        return devolver;
    }
}
