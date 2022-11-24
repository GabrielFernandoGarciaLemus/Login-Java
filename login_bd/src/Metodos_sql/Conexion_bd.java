
package Metodos_sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexion_bd {
    
    private static final String URL  = "jdbc:mysql://localhost:3306/login_bd";
    private static final String Driver = "com.mysql.jdbc.Driver";
    private static final String Usuario = "root";
    private static final String Pass = "";
    private static Connection  conex = null;
    public static Connection conectar(){
        try{
            if(conex ==null){
                Class.forName(Driver);
                conex=DriverManager.getConnection(URL, Usuario , Pass);
            }
        } catch (ClassNotFoundException | SQLException e){
            System.out.println(e);
        }
    return conex;
}
        public static void pruebaConex() {
        if (Conexion_bd.conectar() != null) {
            System.out.println("Conexión a Base de Datos, ha sido correcta");
        } else {
            System.out.println("No se ha podido conectar a la base de datos");
        }

    }
}
