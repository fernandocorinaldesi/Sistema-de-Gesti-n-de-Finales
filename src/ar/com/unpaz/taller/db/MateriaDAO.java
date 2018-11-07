package ar.com.unpaz.taller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ar.com.unpaz.modelo.Anio;
import ar.com.unpaz.modelo.Materia;

public class MateriaDAO {
    private static final String LISTAMATERIAS = 
            "SELECT M.ID, M.DESCRIPCION, M.ANIO " +
            "FROM MATERIA M ";
    
    
    private static final String WHEREANIO = 
            "WHERE ANIO = ? ";
    
    private static final String ORDERBYMATERIAS = 
            "ORDER BY M.DESCRIPCION";
    
    private static final String ORDERBYID = 
            "ORDER BY M.ID";
    
    private static final String INSERTARMATERIA = 
            "INSERT INTO MATERIA (ID, DESCRIPCION, ANIO) values(?,?,?)";
    
    private static final String BORRARMATERIA =
            "DELETE FROM MATERIA WHERE ID = ?";
    
    private static final String MAXMATERIA = "SELECT ISNULL(MAX(ID),0) + 1 AS MAXID FROM MATERIA ";
    
    private static final String ACTUALIZARMATERIA = "UPDATE MATERIA SET DESCRIPCION = ?, ANIO = ? WHERE ID = ?";
    

    public ArrayList<Materia> getMaterias(){
        
        ArrayList<Materia> materias = new ArrayList<Materia>();
        try {
            
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(LISTAMATERIAS + ORDERBYMATERIAS);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia m = new Materia();
                m.setIdMateria( rs.getInt("ID") );
                m.setDescripcion(rs.getString("DESCRIPCION"));
                m.setAnio(rs.getInt("ANIO"));
                materias.add(m);
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return materias;
    }

public ArrayList<Materia> getMateriasByAnio(Anio anio){
        
        ArrayList<Materia> materias = new ArrayList<Materia>();
        try {
            
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(LISTAMATERIAS + WHEREANIO + ORDERBYMATERIAS);
            ps.setInt(1, anio.getAnio());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia m = new Materia();
                m.setIdMateria( rs.getInt("ID") );
                m.setDescripcion(rs.getString("DESCRIPCION"));
                m.setAnio(rs.getInt("ANIO"));
                materias.add(m);
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return materias;
    }
   
    
    
    public ArrayList<Materia> getMateriasOrderById(){
        
        ArrayList<Materia> materias = new ArrayList<Materia>();
        try {
            
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(LISTAMATERIAS + ORDERBYID);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia m = new Materia();
                m.setIdMateria( rs.getInt("ID") );
                m.setDescripcion(rs.getString("DESCRIPCION"));
                m.setAnio(rs.getInt("ANIO"));
                materias.add(m);
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return materias;
    }
    
    public ArrayList<Materia> getMateriasByAnioOrderById(Anio anio){
        
        ArrayList<Materia> materias = new ArrayList<Materia>();
        try {
            
            Connection con = Conexion.getConexion();
            
            PreparedStatement ps = con.prepareStatement(LISTAMATERIAS + WHEREANIO + ORDERBYID);
            ps.setInt(1, anio.getAnio() );
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia m = new Materia();
                m.setIdMateria( rs.getInt("ID") );
                m.setDescripcion(rs.getString("DESCRIPCION"));
                m.setAnio(rs.getInt("ANIO"));
                materias.add(m);
            }
            
            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return materias;
    }
    
    public int insertarMateria(Materia m){
        int retorno = 0;
        Connection con = null;
        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                int maxID = this.getMaxIdMateria(con);
                      
                PreparedStatement ps = con.prepareStatement(INSERTARMATERIA );
                ps.setInt(1,maxID );
                ps.setString(2, m.getDescripcion());
                ps.setInt(3, m.getAnio());
                
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


    private int getMaxIdMateria(Connection con) throws SQLException{
        int retorno = -1;
      
        PreparedStatement ps = con.prepareStatement(MAXMATERIA );
        ResultSet rs = ps.executeQuery();
        rs.next();
        retorno = rs.getInt("MAXID");
        rs.close();
        ps.close();   
        
        return retorno;
    }
    
    
    public int BorrarMateria(Materia m){
        int retorno = 0;
        Connection con = null;

        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                      
                PreparedStatement ps = con.prepareStatement(BORRARMATERIA);
                ps.setInt(1,m.getIdMateria());
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
    
    
    public int ActualizarMateria(Materia m){
        int retorno = 0;
        Connection con = null;

        try {
                con = Conexion.getConexion();
                con.setAutoCommit(false);
                      
                PreparedStatement ps = con.prepareStatement(ACTUALIZARMATERIA );
                ps.setString(1,m.getDescripcion());
                ps.setInt(2, m.getAnio());
                ps.setInt(3,m.getIdMateria());
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
