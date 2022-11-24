
package Metodos_sql;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class Metodos_sql {
    

    public static Conexion_bd conectar = new Conexion_bd();
    
    public static PreparedStatement sentencia_preparada;
    
    public static ResultSet resultado;
    
    public static String sql;
     
    public static int resultado_numero=0;
    
    public int guardar(String nombre,String apellidos,String correo, String contrasena){
        
        int resultado=0;
        
        Connection conexion =null;
        
        String sentencia_guardar =("INSERT INTO usuarios (nombre,apellido,correo,contrasena) VALUES(?,?,?,?)");
        
        try{
            conexion = (Connection) Conexion_bd.conectar();
            sentencia_preparada = conexion.prepareStatement(sentencia_guardar);
            sentencia_preparada.setString(1,nombre); 
            sentencia_preparada.setString(2,apellidos); 
            sentencia_preparada.setString(3,correo); 
            sentencia_preparada.setString(4,contrasena); 
            resultado=sentencia_preparada.executeUpdate();
            sentencia_preparada.close();
            conexion.close();  
        }catch(Exception e){
        System.out.println(e);
        }
        
        return resultado;
    }
    
    public static String buscarNombre(String correo){
        String busqueda_nombre = null;
        Connection conexion =null;
        
        try{
            conexion = (Connection) Conexion_bd.conectar();
            String sentencia_buscar = ("SELECT nombre,apellido FROM usuarios WHERE correo = '"+correo+"'");
            
            sentencia_preparada = conexion.prepareStatement(sentencia_buscar);
            resultado = sentencia_preparada.executeQuery();  
            if(resultado.next()){
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                busqueda_nombre = (nombre +" "+apellido);
            }
            
            
        }catch(Exception e){
            System.out.println(e);
        }
        
        return busqueda_nombre;
    }
    
        public static String buscarUsuario(String correo,String contrasena){
         
         String buscar_usuario =null;
         Connection conexion = null;
          
          try{
              conexion = (Connection) Conexion_bd.conectar();
              String sentencia_usuario=("SELECT correo,contrasena FROM usuarios WHERE correo = '"+correo+"' && contrasena ='"+contrasena+"'");
              sentencia_preparada = conexion.prepareStatement(sentencia_usuario);      
              resultado = sentencia_preparada.executeQuery();
              
              if(resultado.next()){
                  buscar_usuario = "Usuario Encontrado";
              } else{
                  buscar_usuario = "Usuario NO Encontrado";
              }
              
              
          }catch( Exception e){
              System.out.println(e);
          }
         
          return buscar_usuario;
         
     }


    
}
