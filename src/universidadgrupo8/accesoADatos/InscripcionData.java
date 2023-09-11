
package universidadgrupo8.accesoADatos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import universidadgrupo8.entidades.Inscripcion;
import universidadgrupo8.entidades.Materia;

public class InscripcionData {
    private Connection con = null;
    
    
    public InscripcionData(){
    con = Conexion.getconexion();           
}
    
    MateriaData matData;
    AlumnoData aluData;
    
    public void guardarInscripciones(Inscripcion insc){
        String sql="INSERT INTO inscripcion(idAlumno, idMateria)"
                + " VALUES(?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla de inscripcion");
        }
    }
    
    
    public List<Materia> obtenerMateriasCursadas(int id){
        List<Materia> materias = new ArrayList<Materia>();
        try {
        String sql = "SELECT inscripcion.idMateria, nombre, año FROM inscripcion,"
                + "materia.WHERE inscripcion.idMateria = Materia.idMateria\n" +
                "AND inscripcion.idAlumno = ?;";
                
        
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            Materia materia;
            while(rs.next()){
                materia = new Materia();
                materia.setIdMateria(rs.getInt("idMateria"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnioMateria(rs.getInt("año"));
                materias.add(materia);
            }
            ps.close();
        } catch (SQLException ex) { 
            JOptionPane.showMessageDialog(null, "Error al obtener Inscripciones."+ex.getMessage());
        }
        return materias;
        
    }
}
