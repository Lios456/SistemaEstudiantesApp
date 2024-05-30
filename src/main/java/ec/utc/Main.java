package ec.utc;

import ec.utc.conexion.Conexion;
import ec.utc.datos.EstudianteDAO;
import ec.utc.dominio.Estudiante;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static Scanner consola = new Scanner(System.in);
    private static boolean salir = false;
    public static void main(String[] args) {

        while(salir != true){
            mostrarOpciones();
            try{
                ejercutarOpcion(consola.nextInt(), new EstudianteDAO());
            }catch (Exception ex){
                System.out.println(ex.getMessage());
            }

        }
    }

    public static void mostrarOpciones(){
        System.out.println("""
                *** Sistema de Gestión de Estudiantes ***
                1.  Agregar estudiante
                2.  Listar estudiantes
                3.  Buscar estudiante
                4.  Eliminar estudiante
                5.  Salir
                """);
    }

    public static void ejercutarOpcion(int op, EstudianteDAO dao){
        switch (op){
            case 1 ->{
                System.out.println("AGREGAR ESTUDIANTE");
            }
            case 2 ->{
                System.out.println("LISTAR ESTUDIANTES");
                dao.listar().forEach(System.out::println);
            }
            case 3->{
                System.out.println("BUSCAR ESTUDIANTE");
            }
            case 4->{
                System.out.println("ELIMINAR ESTUDIANTE");
            }
            case 5->{
                System.out.println("SALIENDO...");
                salir = true;
            }
            default -> {
                System.out.println("OPCIÓN NO VÁLIDA");
            }
        }
    }
}