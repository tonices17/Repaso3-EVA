import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void escribirFichero(ArrayList<Asignatura> listaAsignaturas) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("Asignaturas.txt"));
            bw.write("ASIGNATURA|NOTA");
            bw.newLine();
            for (int i = 0; i < listaAsignaturas.size(); i++) {
                bw.write(listaAsignaturas.get(i).getAsignatura() + "|" + listaAsignaturas.get(i).getNota());
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

    public static int leerFichero() {
        BufferedReader br = null;
        int notaTotal = 0;

        try {
            br = new BufferedReader(new FileReader("Asignaturas.txt"));
            String linea = null;
            String[] partes;
            while ((linea = br.readLine()) != null) {
                partes = linea.split("\\|");
                try {
                    notaTotal = notaTotal + Integer.parseInt(partes[1]);
                } catch (NumberFormatException e){

                }
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
        return notaTotal;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Asignatura> listaAsignaturas = new ArrayList<>();
        String nombre;
        int nota;
        int num_asignaturas;
        double nota_media;
        Asignatura asignatura;

        System.out.println("En primer lugar, va a tener que introducir la nota que ha sacado en las asignaturas del curso.");
        System.out.println("Cuantas asignaturas vas a introducir: ");
        num_asignaturas = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < num_asignaturas; i++) {
            System.out.println("Introduce el nombre de la asignatura: ");
            nombre = sc.nextLine();
            System.out.println("Introduce la nota de la asignatura " + nombre + ": ");
            nota = Integer.parseInt(sc.nextLine());
            asignatura = new Asignatura(nombre,nota);
            listaAsignaturas.add(asignatura);
        }

        System.out.println("Vamos a crear un archivo de texto con tus asignaturas y sus respectivas notas.");
        escribirFichero(listaAsignaturas);

        System.out.println("Por último, vamos a calcular la nota media de todas las asignaturas.");
        nota_media = leerFichero()/num_asignaturas;
        System.out.println("La nota media de todas tus asignaturas es: " + nota_media);
    }
}