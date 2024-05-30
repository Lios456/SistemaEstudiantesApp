package ec.utc.datos;

import ec.utc.conexion.Conexion;
import ec.utc.dominio.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static ec.utc.conexion.Conexion.getConexion;

public class EstudianteDAO {
    //DAO - Data Access Object
    private Estudiante estudiantee = new Estudiante();
    public List<Estudiante> listar(){
        List<Estudiante> estudiantes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        String sql = "SELECT * FROM estudiantes order by id_estudiante";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Estudiante estudiante = new Estudiante();
                estudiante.setIdEstudiante(rs.getInt("id_estudiante"));
                estudiante.setNombre(rs.getString("nombre"));
                estudiante.setApellido(rs.getString("apellido"));
                estudiante.setEmail(rs.getString("email"));
                estudiante.setTelefono(rs.getString("telefono"));
                estudiantes.add(estudiante);
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            try{
                con.close();
            }catch (Exception e){
                System.out.println("Error al cerrar" + e.getMessage());
            }
        }
        return estudiantes;
    }
    //findById
    public boolean buscar(Estudiante e){
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConexion();
        String sql = "SELECT * FROM estudiantes WHERE id_estudiante = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,e.getIdEstudiante());
            rs = ps.executeQuery();
            if(rs.next()){
                estudiantee.setNombre(rs.getString("nombre"));
                estudiantee.setApellido(rs.getString("apellido"));
                estudiantee.setTelefono(rs.getString("telefono"));
                estudiantee.setEmail(rs.getString("email"));
                return true;
            }
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception ex){
                System.out.println("Error al cerrar" + ex.getMessage());
            }
        }
        return false;
    }

    public boolean agregarEstudiante(Estudiante e){
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "INSERT INTO estudiantes(nombre, apellido, telefono, email) VALUES" +
                "(?,?,?,?)";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setString(2, e.getApellido());
            ps.setString(3, e.getTelefono());
            ps.setString(4, e.getEmail());
            ps.execute();
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        finally {
            try{
                con.close();
            }catch (Exception ex){
                System.out.println("Error al cerrar" + ex.getMessage());
            }
        }
        return false;
    }

    public boolean modificar(Estudiante es){
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "UPDATE estudiantes SET nombre=?, apellido=?, telefono=?, email=? WHERE id_estudiante = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, es.getNombre());
            ps.setString(2, es.getApellido());
            ps.setString(3, es.getTelefono());
            ps.setString(4, es.getEmail());
            ps.setInt(5,es.getIdEstudiante());
            ps.execute();
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            try{
                con.close();
            }catch (Exception ex){
                System.out.println("Error al cerrar" + ex.getMessage());
            }
        }
        return false;

    }

    public boolean eliminar(Estudiante es){
        PreparedStatement ps;
        Connection con = Conexion.getConexion();
        String sql = "DELETE FROM estudiantes WHERE id_estudiante = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1,es.getIdEstudiante());
            ps.execute();
            return true;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }finally {
            try{
                con.close();
            }catch (Exception ex){
                System.out.println("Error al cerrar" + ex.getMessage());
            }
        }
        return false;
    }
}
