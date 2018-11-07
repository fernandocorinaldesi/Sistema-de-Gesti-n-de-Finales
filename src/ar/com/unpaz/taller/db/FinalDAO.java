package ar.com.unpaz.taller.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.unpaz.modelo.Alumno;
import ar.com.unpaz.modelo.Final;
import ar.com.unpaz.modelo.Materia;

public class FinalDAO {
	private static final String MAXID = "SELECT ISNULL(MAX(ID),0) + 1 AS MAXID FROM FINALES ";
	private static final String query = "SELECT * FROM FINALES";
	
	 public List<Final> getFinales() {
		   
		    Connection conexion = Conexion.getConexion();
		    List<Final> todos = new ArrayList<>();
		    try (PreparedStatement st = conexion.prepareStatement(query);
		        ResultSet resultSet = st.executeQuery()) {
		      while (resultSet.next()) {
		        int id = resultSet.getInt("ID");
		        Alumno alumno = buscarAlumnoPorDNI(resultSet.getInt("DNI"));
		        Materia materia = buscarMateriaPorId(resultSet.getInt("ID_MATERIA"));
		        double nota = resultSet.getDouble("NOTA");
		        LocalDate fecha = resultSet.getDate("FECHA_FINAL").toLocalDate();
		        Final finalObj = new Final(id, alumno, materia, fecha, nota);
		        todos.add(finalObj);
		      }
		    } catch (Exception ex) {
		      ex.printStackTrace();
		    }
		    return todos;
		  }
		  
		  private Alumno buscarAlumnoPorDNI(int dni) {
		    String query = "SELECT * FROM ALUMNO WHERE DNI=?";
		    Connection conexion = Conexion.getConexion();
		    try (PreparedStatement st = conexion.prepareStatement(query)) {
		      st.setInt(1, dni);
		      try (ResultSet resultSet = st.executeQuery()) {
		        if (resultSet.next()) {
		          Alumno alumno = new Alumno();
		          alumno.setDni(dni);
		          alumno.setNombre(resultSet.getString("NOMBRE"));
		          alumno.setApellido(resultSet.getString("APELLIDO"));
		          alumno.setEmail(resultSet.getString("EMAIL"));
		          return alumno;
		        }
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    }    
		    return null;
		  }
		    
		  private Materia buscarMateriaPorId( int id) {
		    String query = "SELECT * FROM MATERIA WHERE ID=?";
		    Connection conexion = Conexion.getConexion();
		    try (PreparedStatement st = conexion.prepareStatement(query)) {
		      st.setInt(1, id);
		      try (ResultSet resultSet = st.executeQuery()) {
		        if (resultSet.next()) {
		          Materia materia = new Materia();
		          materia.setIdMateria(id);
		          materia.setDescripcion(resultSet.getString("DESCRIPCION"));
		          materia.setAnio(resultSet.getInt("ANIO"));
		          return materia;
		        }
		      }
		    } catch (Exception e) {
		      e.printStackTrace();
		    }    
		    return null;
		  }
		  
		  public void eliminar(Final finalObj) {
			    String query = "DELETE FROM FINALES WHERE ID=?";
			    Connection conexion = Conexion.getConexion();
			    try (PreparedStatement st = conexion.prepareStatement(query)) {
			      st.setInt(1, finalObj.getId());
			      st.executeUpdate();
			    } catch (Exception e) {
			      e.printStackTrace();
			    }
			  }

		  public void agregar(Final finalObj) {
			  String query = "INSERT INTO FINALES (ID, DNI, ID_MATERIA, NOTA, FECHA_FINAL) values(?,?,?,?,?)";
			    Connection conexion = Conexion.getConexion();
			    try (PreparedStatement st = conexion.prepareStatement(query)) {
			     int maxid = this.getMaxIDFinales(conexion);
				  st.setInt(1,maxid);
			      st.setInt(2, finalObj.getAlumno().getDni());
			      st.setInt(3, finalObj.getMateria().getIdMateria());
			      st.setDouble(4, finalObj.getNota());
			      st.setDate(5, Date.valueOf(finalObj.getFecha()));
			      st.executeUpdate();
			    } catch (Exception e) {
			      e.printStackTrace();
			    }

		  }
		  private int getMaxIDFinales(Connection con) throws SQLException{
		        int retorno = -1;
		      
		        PreparedStatement ps = con.prepareStatement(MAXID);
		        ResultSet rs = ps.executeQuery();
		        rs.next();
		        retorno = rs.getInt("MAXID");
		        rs.close();
		        ps.close();   
		        
		        return retorno;
		    }

		  public void actualizar(Final finalObj) {
			  String query = "UPDATE FINALES SET DNI=?,ID_MATERIA=?," + "NOTA=?,FECHA_FINAL=? WHERE ID=?";
			    Connection conexion = Conexion.getConexion();
			   
			    
			    try (PreparedStatement st = conexion.prepareStatement(query)) {
			     
			      st.setInt(1, finalObj.getAlumno().getDni());
			      st.setDouble(2, finalObj.getMateria().getIdMateria());
			      st.setDouble(3, finalObj.getNota());
			      st.setDate(4, Date.valueOf(finalObj.getFecha()));
			      st.setInt(5, finalObj.getId());
			     
			      st.executeUpdate();
			    } catch (Exception e) {
			      e.printStackTrace();
			    }

		  }
		

		}
