package universidadgrupo8.accesoADatos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadgrupo8.entidades.Alumno;

public class AlumnoData {

    private Connection con=null;

    public AlumnoData() {
         
//        con=Conexion.getConexion();
    }
    
    public void guardarAlumno(Alumno alumno){
        
        String sql= "INSERT INTO alumno(dni, apellido, nombre, fechaNacimiento, estado)"
                +"VALUES(?, ?, ?, ?, ?)";
        
        try {
            PreparedStatement ps=con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());              // metodos set. para cargar datos en la tabla 
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));  
            ps.setBoolean(5, alumno.isActivo());  //el metodo get de atributos boolean, es (is)                           
            ps.executeUpdate();
            //aqui devuelve la clave generada para el alumno
            ResultSet rs = ps.getGeneratedKeys(); // invocando el metodo getGeneratedKeys(); 
            // rs es una matriz de una fila y una columna, por eso no utilizo while (es un solo alumno)
            if(rs.next()){
                
                alumno.setIdAlumno(rs.getInt(1)); //aqui le asignamos el keys como Id, se lo paso por rs.getInt(1) por ser un entero 
                                                  //y le paso el numero de columna donde estan los Id.
                JOptionPane.showMessageDialog(null, "Alumno agregado");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }
    
    public void modificarAlumno(Alumno alumno){
        
        String sql = "UPDATE alumno SET dni=?, apellido=?, nombre=?, fechaNacimiento=?"
                + "WHERE IdAlumno=?";
        
        try {
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4, Date.valueOf(alumno.getFechaNac()));
            ps.setInt(5, alumno.getIdAlumno());
            
            int exito = ps.executeUpdate();
            if (exito==1){
                JOptionPane.showMessageDialog(null, "Alumno modificado");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al acceder a tabla alumno");
        }
        
    }    
    public void eliminarAlumno(int Id){
        
        String sql = "UPDATE alumno SET estado=0 WHERE IdAlumno=?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Id);
            int exito = ps.executeUpdate(sql);
            if (exito==1){
                JOptionPane.showMessageDialog(null, "Alumno eliminado");
            }                        
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al acceder a la tabla alumno");  
        }                
    } 
    
    public Alumno buscarAlumno (int Id){  //buscar Alumno por id
        
        Alumno alumno=null;
                
            String sql = "SELECT dni, apellido, nombre, fechaNacimiento FROM alumno "
                    + "WHERE idAlumno=? AND estado=1";
        try {    
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, Id);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                
                alumno = new Alumno();
                alumno.setIdAlumno(Id);
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setActivo(true);             
                
            }else{                
                JOptionPane.showMessageDialog(null, "No existe el Alumno ");               
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al acceder a la tabla alumno");
        }
        return alumno;
    }   
    
    public Alumno buscarAlumnoPorDni (int dni){  //buscar Alumno por dni
        
        
            String sql = "SELECT idAlumno, dni, apellido, nombre, fechaNacimiento FROM alumno "
                    + "WHERE dni=? AND estado=1";
            Alumno alumno=null;
        try {    
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()){
                
                alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setActivo(true);             
                
            }else{                
                JOptionPane.showMessageDialog(null, "No existe el Alumno ");               
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al acceder a la tabla alumno");
        }
        return alumno;
    }   
    
    public List<Alumno> listarAlumnos(){  
         
                
        
            String sql = "SELECT idAlumno, dni, apellido, nombre, fechaNacimiento FROM alumno "
                    + "WHERE estado=1";
                        
            ArrayList<Alumno> alumnos = new ArrayList<>();
        try {    
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()){
                
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNac(rs.getDate("fechaNacimiento").toLocalDate());
                alumno.setActivo(true);             
                
                alumnos.add(alumno);
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al acceder a la tabla alumno");
        }
        return alumnos;
    } 
}