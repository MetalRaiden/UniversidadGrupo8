
package universidadgrupo8.accesoADatos;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class Conexion {
    
    private static final String URL = "jdbc:mariadb://localhost/";
    private static final String DB = "universidadulp";
    private static final String USUARIO = "root";
    private static final String PASS = "";
    private static Connection connection;
    
    private Conexion (){}
    
    
    public static Connection getconexion(){
        
        if(connection == null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(URL+DB, USUARIO, PASS);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar los Driver");
        }   catch (SQLException ex) {
              JOptionPane.showMessageDialog(null, "Error al conectarse a la Base de Datos");  
            }
    }
        return connection;
}
}

