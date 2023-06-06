import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void imprimirMenu() {
        System.out.println("""
                ===================================
                                MENÚ
                ===================================
                0. Salir
                1. Importar fichero.
                2. Mostrar alumnos.
                3. Buscar alumno por nombre.
                4. Insertar un alumno.
                5. Modificar un alumno.
                6. Borrar un alumno.
                7. Exportar fichero.            
                """);
    }

    public static ArrayList<Alumno> leerFichero(ArrayList<Alumno> listaAlumnos) {

        BufferedReader br = null;
        String nombre;
        int edad;

        try {
            br = new BufferedReader(new FileReader("Alumnos.txt"));
            String linea = null;
            while ((linea = br.readLine()) != null) {
                nombre = linea.substring(0,linea.indexOf(","));
                edad = Integer.parseInt(linea.substring(linea.indexOf(",")+2));
                Alumno alumno = new Alumno(nombre,edad);
                listaAlumnos.add(alumno);
                /* Aquí trato el String línea por línea */
            }
            System.out.println("El fichero ha sido recorrido correctamente");
        } catch (
                FileNotFoundException e) { // qué hacer si no se encuentra el fichero
            e.printStackTrace();
        } catch (
                IOException e) { // qué hacer si hay un error en la lectura del fichero
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close(); //muy importante cerrar el fichero cuando se hayan realizado las operaciones
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return listaAlumnos;
    }

    public static void escribirFichero(ArrayList<Alumno> listaAlumnos) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("AlumnosFinal.txt"));
            for (int i = 0; i < listaAlumnos.size(); i++) {
                bw.write(listaAlumnos.get(i).getNombre() + ", " + listaAlumnos.get(i).getEdad());
                bw.newLine();
            }
            bw.close();
            System.out.println("El fichero ha sido recorrido correctamente");
        } catch (FileNotFoundException e) { // qué hacer si no se encuentra el fichero
            e.printStackTrace();
        } catch (IOException e) { // qué hacer si hay un error en la lectura del fichero
            e.printStackTrace();
        } finally {
            if (bw != null)
                try {
                    bw.close(); //muy importante cerrar el fichero cuando se hayan realizado las operaciones
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
    public static void main(String[] args) {
        boolean seguir = true;
        boolean valido;
        String opcion = null;
        String nombre;
        int edad;
        Scanner sc = new Scanner(System.in);
        ArrayList<Alumno> listaAlumnos = new ArrayList<>();
        Lista lista = new Lista(listaAlumnos);
        
        do {
            imprimirMenu();
            valido = false;
            while (!valido) {
                System.out.println("Introduce una opción: ");
                opcion = sc.nextLine();
                if (!opcion.matches("[0-7]")) {
                    System.out.println("El valor que has introducido no es válido. Vuelve a intentarlo.");
                } else {
                    valido = true;
                }
            }
            
            switch (opcion) {
                case "0":
                    seguir = false;
                    System.out.println("Gracias por utilizar nuestro programa.");
                    break;

                case "1":
                    System.out.println("VAMOS A IMPORTAR EL FICHERO CON LOS ALUMNOS: ");
                    listaAlumnos = leerFichero(listaAlumnos);
                    break;

                case "2":
                    System.out.println("VAMOS A MOSTRAR TODOS LOS ALUNNOS DE LA LISTA: ");
                    lista.mostrarAlumnos(listaAlumnos);
                    break;

                case "3":
                    System.out.println("VAMOS A BUSCAR UN ALUMNO POR SU NOMBRE: ");
                    System.out.println("Introduce el nombre del alumno que quieres buscar: ");
                    nombre = sc.nextLine();
                    Alumno alumnoBusqueda = lista.buscarAlumno(nombre,listaAlumnos);
                    if (alumnoBusqueda != null) {
                        System.out.println("Nombre: " + alumnoBusqueda.getNombre() + ", edad: " + alumnoBusqueda.getEdad() + ".");
                    } else {
                        System.out.println("No se ha encontrado al alumno.");
                    }
                    break;

                case "4":
                    System.out.println("VAMOS A INSERTAR UN ALUMNO: ");
                    System.out.println("Introduce el nombre del alumno que quieres insertar: ");
                    nombre = sc.nextLine();
                    System.out.println("Introduce la edad del alumno que quieres insertar: ");
                    edad = sc.nextInt();
                    Alumno alumnoIntroducir = new Alumno(nombre,edad);
                    boolean devolucion = lista.insertarAlumno(alumnoIntroducir,listaAlumnos);
                    if (devolucion) {
                        System.out.println("El alumno " + nombre + " se ha insertado con éxito.");
                    }
                    break;

                case "5":
                    System.out.println("VAMOS A MODIFICAR UN ALUMNO: ");
                    System.out.println("Dime el nombre del alumno que quieres modificar: ");
                    nombre = sc.nextLine();
                    devolucion = lista.modificarAlumno(nombre,listaAlumnos);
                    if (devolucion) {
                        System.out.println("Modificación realizada con éxito.");
                    }
                    break;

                case "6":
                    System.out.println("VAMOS A BORRAR UN ALUMNO: ");
                    System.out.println("Introduce el nombre del alumno que quieres borrar: ");
                    nombre = sc.nextLine();
                    devolucion = lista.eliminarAlumno(nombre,listaAlumnos);
                    if (devolucion) {
                        System.out.println("El alumno " + nombre + " se ha eliminado correctamente.");
                    }
                    break;

                case "7":
                    System.out.println("VAMOS A ESCRIBIR LA LISTA DE ALUMNOS EN UN FICHERO: ");
                    escribirFichero(listaAlumnos);
                    break;
            }
        }while (seguir);
    }
}