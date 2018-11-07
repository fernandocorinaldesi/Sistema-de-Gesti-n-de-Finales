package ar.com.unpaz.taller.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.com.unpaz.modelo.Alumno;
import ar.com.unpaz.modelo.Final;
import ar.com.unpaz.modelo.Materia;

public class FinalDAO {

	 public List<Final> getFinales() {
		    String query = "SELECT * FROM FINALES";
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

		  }

		  public void actualizar(Final finalObj) {

		  }

		}
