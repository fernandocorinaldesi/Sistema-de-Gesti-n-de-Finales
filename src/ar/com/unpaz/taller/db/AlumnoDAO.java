package ar.com.unpaz.taller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.com.unpaz.modelo.Alumno;


public class AlumnoDAO {
	  private static final String LISTAALUMNOS = 
	            "SELECT A.DNI,A.NOMBRE,A.APELLIDO,A.EMAIL FROM ALUMNO A ";
	  //  private static final String WHEREANIO = 
	         //   "WHERE ANIO = ? ";
	    
	    private static final String ORDERBYAPELLIDO = 
	            "ORDER BY A.APELLIDO";
	    
	    private static final String ORDERBYNOMBRE = 
	            "ORDER BY A.NOMBRE";
	    private static final String ORDERBYDNI = 
	            "ORDER BY A.DNI";
	    
	    private static final String INSERTARALUMNO = 
	            "INSERT INTO ALUMNO (DNI,NOMBRE,APELLIDO,EMAIL) values(?,?,?,?) ";
	    
	    private static final String BORRARALUMNO =
	            "DELETE FROM ALUMNO WHERE DNI = ? ";
	    
	    private static final String MAXALUMNO = "SELECT ISNULL(MAX(DNI),0) + 1 AS MAXDNI FROM ALUMNO ";
	    
	    private static final String ACTUALIZARALUMNO = "UPDATE ALUMNO SET NOMBRE = ?, APELLIDO = ?, EMAIL = ? WHERE DNI = ?";
	  
	    

	    public ArrayList<Alumno> getAlumnos(){
	        
	        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
	        try {
	            
	            Connection con = Conexion.getConexion();
	            
	            PreparedStatement ps = con.prepareStatement(LISTAALUMNOS);
	            ResultSet rs = ps.executeQuery();
	            
	            while(rs.next()){
	                Alumno a = new Alumno();
	                a.setDni( rs.getInt("DNI") );
	                a.setNombre(rs.getString("NOMBRE"));
	                a.setApellido(rs.getString("APELLIDO"));
	                a.setEmail(rs.getString("EMAIL"));
	                alumnos.add(a);
	            }
	            
	            rs.close();
	            ps.close();
	            con.close();
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        
	        return alumnos;
	    }
  public ArrayList<Alumno> getAlumnoOrderByNombre(){
	        
	        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
	        try {
	            
	            Connection con = Conexion.getConexion();
	            
	            PreparedStatement ps = con.prepareStatement(LISTAALUMNOS + ORDERBYNOMBRE);
	            ResultSet rs = ps.executeQuery();
	            
	            while(rs.next()){
	                Alumno a = new Alumno();
	                a.setDni( rs.getInt("DNI") );
	                a.setNombre(rs.getString("NOMBRE"));
	                a.setApellido(rs.getString("APELLIDO"));
	                a.setEmail(rs.getString("EMAIL"));
	                alumnos.add(a);
	            }
	            
	            rs.close();
	            ps.close();
	            con.close();
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        
	        return alumnos;
	    }


	   
	    
	    
	    public ArrayList<Alumno> getAlumnoOrderByDNI(){
	        
	        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
	        try {
	            
	            Connection con = Conexion.getConexion();
	            
	            PreparedStatement ps = con.prepareStatement(LISTAALUMNOS + ORDERBYDNI);
	            ResultSet rs = ps.executeQuery();
	            
	            while(rs.next()){
	                Alumno a = new Alumno();
	                a.setDni( rs.getInt("DNI") );
	                a.setApellido(rs.getString("APELLIDO"));
	                a.setNombre(rs.getString("NOMBRE"));
	                a.setEmail(rs.getString("EMAIL"));
	                alumnos.add(a);
	            }
	            
	            rs.close();
	            ps.close();
	            con.close();
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        
	        return alumnos;
	    }
  public ArrayList<Alumno> getAlumnoOrderByApellido(){
	        
	        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
	        try {
	            
	            Connection con = Conexion.getConexion();
	            
	            PreparedStatement ps = con.prepareStatement(LISTAALUMNOS + ORDERBYAPELLIDO);
	            ResultSet rs = ps.executeQuery();
	            
	            while(rs.next()){
	                Alumno a = new Alumno();
	                a.setDni( rs.getInt("DNI") );
	                a.setApellido(rs.getString("APELLIDO"));
	                a.setNombre(rs.getString("NOMBRE"));
	                a.setEmail(rs.getString("EMAIL"));
	                alumnos.add(a);
	            }
	            
	            rs.close();
	            ps.close();
	            con.close();
	            
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	        
	        return alumnos;
	    }
	    
	
	    
	    public int insertarAlumno(Alumno a){
	        int retorno = 0;
	        Connection con = null;
	        try {
	                con = Conexion.getConexion();
	                con.setAutoCommit(false);
	                int maxDNI = this.getMaxDNIAlumno(con);
	                      
	                PreparedStatement ps = con.prepareStatement(INSERTARALUMNO );
	                ps.setInt(1,a.getDni() );
	                ps.setString(2, a.getNombre());
	                ps.setString(3, a.getApellido());
	                ps.setString(4, a.getEmail());
	                
	                retorno = ps.executeUpdate();
	                con.commit();
	                con.setAutoCommit(true);
	                ps.close();
	                con.close();

	        } catch (SQLException ex) {
	            try {
	                ex.printStackTrace();
	                con.rollback();
	                con.setAutoCommit(true);
	                con.close();
	            } catch (SQLException ex1) {
	                ex1.printStackTrace();
	            }
	        }
	        return retorno;
	    }


	    private int getMaxDNIAlumno(Connection con) throws SQLException{
	        int retorno = -1;
	      
	        PreparedStatement ps = con.prepareStatement(MAXALUMNO );
	        ResultSet rs = ps.executeQuery();
	        rs.next();
	        retorno = rs.getInt("maxDNI");
	        rs.close();
	        ps.close();   
	        
	        return retorno;
	    }
	    
	    
	    public int BorrarAlumno(Alumno a){
	        int retorno = 0;
	        Connection con = null;

	        try {
	                con = Conexion.getConexion();
	                con.setAutoCommit(false);
	                      
	                PreparedStatement ps = con.prepareStatement(BORRARALUMNO);
	                ps.setInt(1,a.getDni());
	                retorno = ps.executeUpdate();
	                con.commit();
	                con.setAutoCommit(true);
	                ps.close();
	                con.close();

	        } catch (SQLException ex) {
	            try {
	                ex.printStackTrace();
	                con.rollback();
	                con.setAutoCommit(true);
	                con.close();
	            } catch (SQLException ex1) {
	               ex1.printStackTrace();
	            }
	        }

	        return retorno;
	    }
	    
	    
	    public int ActualizarAlumno(Alumno a){
	        int retorno = 0;
	        Connection con = null;

	        try {
	                con = Conexion.getConexion();
	                con.setAutoCommit(false);
	                      
	                PreparedStatement ps = con.prepareStatement(ACTUALIZARALUMNO );
	                ps.setInt(1,a.getDni());
	                ps.setString(2,a.getNombre());
	                ps.setString(3, a.getApellido());
	                ps.setString(4,a.getEmail());
	                retorno = ps.executeUpdate();
	                con.commit();
	                con.setAutoCommit(true);
	                ps.close();
	                con.close();

	        } catch (SQLException ex) {
	            try {
	                ex.printStackTrace();
	                con.rollback();
	                con.setAutoCommit(true);
	                con.close();
	            } catch (SQLException ex1) {
	                ex1.printStackTrace();
	            }
	        }
	        
	        
	        return retorno;
	        }
	    

}
