import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void mostrarMenu() {
        System.out.println("""
                ==============================
                             MENÚ
                ==============================
                0. Salir.
                1. Importar fichero.
                2. Mostrar inmuebles.
                3. Buscar inmueble por id.
                4. Insertar inmueble.
                5. Eliminar inmueble.
                6. Actualizar inmueble.
                7. Exportar fichero.      
                """);
    }

    public static ArrayList<Inmueble> importarFichero(ArrayList<Inmueble> listaInmuebles) {
        BufferedReader br = null;
        int id;
        String direccion;
        String ciudad;
        String tipo;
        Inmueble inmueble;

        try {
            br = new BufferedReader(new FileReader("Inmuebles.txt"));
            String linea = null;
            String[] partes;
            while ((linea = br.readLine()) != null) {
                partes = linea.split(";");
                id = Integer.parseInt(partes[0]);
                direccion = partes[1];
                ciudad = partes[2];
                tipo = partes[3];
                inmueble = new Inmueble(id,direccion,ciudad,tipo);
                listaInmuebles.add(inmueble);
            }
            System.out.println("El fichero ha sido recorrido correctamente");
        } catch (FileNotFoundException e) { // qué hacer si no se encuentra el fichero
            e.printStackTrace();
        } catch (IOException e) { // qué hacer si hay un error en la lectura del fichero
            e.printStackTrace();
        } finally {
            if (br != null)
                try {
                    br.close(); //muy importante cerrar el fichero cuando se hayan realizado las operaciones
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return listaInmuebles;
    }

    public static void escribirFichero(ArrayList<Inmueble> listaInmuebles) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("InmueblesActualizados.txt"));
            for (int i = 0; i < listaInmuebles.size(); i++) {
                bw.write("Id: " + listaInmuebles.get(i).getId() + "; dirección: " +
                        listaInmuebles.get(i).getDireccion() + "; ciudad: " + listaInmuebles.get(i).getCiudad() +
                        "; tipo: " + listaInmuebles.get(i).getTipo() + ".");
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
    public static void main(String[] args) throws InterruptedException {
        ArrayList<Inmueble> listaInmuebles = new ArrayList<>();
        Lista lista = new Lista(listaInmuebles);
        Scanner sc = new Scanner(System.in);
        boolean seguir = true;
        boolean valido;
        boolean devolucion;
        int id;
        String direccion;
        String ciudad;
        String tipo;
        String opcion = null;
        Inmueble busquedaInmueble;
        Inmueble inmueble;

        do {
            mostrarMenu();
            valido = false;
            while(!valido) {
                System.out.println("Introduce una opción: ");
                opcion = sc.nextLine();
                if (opcion.matches("[0-7]")) {
                    valido = true;
                } else {
                    System.out.println("La opción que has introducido no es válida, vuelve a intentarlo.");
                }
            }

            switch (opcion) {
                case "0":
                    System.out.println("Gracias por utilizar nuestro programa.");
                    seguir = false;
                    break;

                case "1":
                    System.out.println("VAMOS A PROCEDER A IMPORTAR LOS DATOS DEL FICHERO: ");
                    Thread.sleep(3000);
                    listaInmuebles = importarFichero(listaInmuebles);
                    break;

                case"2":
                    System.out.println("VAMOS A MOSTRAR LA LISTA DE INMUEBLES: ");
                    lista.mostrarInmueble();
                    break;

                case "3":
                    System.out.println("VAMOS A BUSCAR UN INMUEBLE EN LA LISTA: ");
                    System.out.println("Introduce el id del inmueble que quieres buscar: ");
                    id = Integer.parseInt(sc.nextLine());
                    busquedaInmueble = lista.buscarInmueble(id);
                    if (busquedaInmueble != null) {
                        lista.mostrarInmueble(busquedaInmueble);
                    } else {
                        System.out.println("No se ha encontrado el inmueble en la lista.");
                    }
                    break;

                case "4":
                    System.out.println("VAMOS A INSERTAR UN INMUEBLE EN LA LISTA: ");
                    id = listaInmuebles.get(listaInmuebles.size()-1).getId()+1;
                    System.out.println("Introduce la dirección del inmueble: ");
                    direccion = sc.nextLine();
                    System.out.println("Introduce la ciudad del inmueble: ");
                    ciudad = sc.nextLine();
                    System.out.println("Introduce el tipo de inmueble (alquiler/venta): ");
                    tipo = sc.nextLine();
                    inmueble = new Inmueble(id,direccion,ciudad,tipo);
                    devolucion = lista.insertarInmueble(inmueble);
                    if (devolucion) {
                        System.out.println("El inmueble se ha insertado con éxito.");
                    } else {
                        System.out.println("El inmueble ya se encuentra registrado en la lista.");
                    }
                    break;

                case "5":
                    System.out.println("VAMOS A ELIMINAR UN INMUEBLE: ");
                    System.out.println("Introduce el id del inmueble que quieres eliminar: ");
                    id = Integer.parseInt(sc.nextLine());
                    devolucion = lista.eliminarInmueble(id);
                    if (devolucion) {
                        System.out.println("El inmueble se ha eliminado con éxito.");
                    } else {
                        System.out.println("El inmueble no se encuentra en la lista.");
                    }
                    break;

                case "6":
                    System.out.println("VAMOS A ACTUALIZAR UN INMUEBLE: ");
                    System.out.println("Introduce el id del inmueble que quieres actualizar: ");
                    id = Integer.parseInt(sc.nextLine());
                    devolucion = lista.actualizarInmueble(id);
                    if (devolucion) {
                        System.out.println("El inmueble se ha actualizado con éxtio.");
                    } else {
                        System.out.println("El inmueble que intentas actualizar no está en la lista.");
                    }
                    break;

                case "7":
                    System.out.println("VAMOS A GENERAR UN ARCHIVO DE TEXTO CON LA LISTA DE INMUEBLES: ");
                    escribirFichero(listaInmuebles);
                    break;
            }
        }while (seguir);
    }
}