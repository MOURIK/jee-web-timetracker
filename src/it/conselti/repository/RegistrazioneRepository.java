/*
 * Conselti s.r.l.
 */
package it.conselti.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import it.conselti.domain.Registrazione;
import it.conselti.domain.VisualizzaRegistrazione;

/**
 * @author onofr
 *
 */


@Repository
public interface RegistrazioneRepository {
	
	@Results({ @Result(id=true, column="Id", property="id", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
               @Result(column="Codice", property="codice", jdbcType=JdbcType.INTEGER,javaType=Integer.class),
               @Result(column="Cognome", property="cognome", jdbcType=JdbcType.VARCHAR,javaType=String.class),
               @Result(column="Nome", property="nome", jdbcType=JdbcType.VARCHAR,javaType=String.class),
               @Result(column="Data", property="data", jdbcType=JdbcType.DATE, javaType=Date.class),
               @Result(column="Lavorate", property="lavorate", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
               @Result(column="Ferie", property="ferie", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
               @Result(column="Permesso", property="permesso", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
               @Result(column="Malattia", property="malattia", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
               @Result(column="Trasferta", property="trasferta", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
               @Result(column="Note", property="note", jdbcType=JdbcType.VARCHAR, javaType=String.class) })
	
	
	/**
	 * 
	 * @return the populated list.
	 */
	@Select("SELECT Id, Cognome, Nome, Codice, Data, Lavorate, Ferie, Permesso, Malattia, Trasferta, Note "
			+ "FROM _Anagrafica AS an JOIN _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica JOIN _Registrazione AS re ON ut.Codice_utente = re.Codice"
			+ " ORDER BY Data DESC")
	@Options(useCache = true, flushCache = true)
	public List<Registrazione> findAllRegistrazione();

	
	/**
	 * 
	 * @param codice the code.
	 * @return the populated list.
	 */
	@Select("SELECT Id, Cognome, Nome, Data, Lavorate, Ferie, Permesso, Malattia, Trasferta, Note "
			+ "FROM _Anagrafica AS an join _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica JOIN _Registrazione AS re ON ut.Codice_utente = re.Codice "
			+ "WHERE re.Codice = #{userCode} ORDER BY Data DESC")
	@Options(useCache = true, flushCache = true)
	public List<Registrazione> findAllRegistrazioneByUserCode(@Param("userCode") Integer codice);
	
	
	
	
	
	@Select("SELECT Id, Cognome, Nome, Data, Lavorate, Ferie, Permesso, Malattia, Trasferta, Note "
			+ "FROM _Anagrafica AS an join _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica JOIN _Registrazione AS re ON ut.Codice_utente = re.Codice "
			+ "WHERE re.Codice = #{usercode} AND MONTH(Data) = #{registrazione.mese} AND YEAR(Data) = #{registrazione.anno}"
			+ "ORDER BY Data DESC")
	@Options(useCache = true, flushCache = true)
	public List<Registrazione> findAllRegistrazioneByYearAndMonth(@Param("usercode") Integer codice, @Param("registrazione") VisualizzaRegistrazione visual);
	
	
	
	
	@Select("SELECT Id, Cognome, Nome, Data, Lavorate, Ferie, Permesso, Malattia, Trasferta, Note "
			+ "FROM _Anagrafica AS an join _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica JOIN _Registrazione AS re ON ut.Codice_utente = re.Codice "
			+ "WHERE re.Codice = #{usercode} AND Data BETWEEN #{visualizza.dateFrom} AND #{visualizza.dateTo}"
			+ "ORDER BY Data DESC")
	@Options(useCache = true, flushCache = true)
	public List<Registrazione> findAllRegistrazioneByFromAndTo(@Param("usercode") Integer codice, @Param("visualizza") VisualizzaRegistrazione visual);
	
	
	
	
	/**
	 * 
	 * @param registr the user's registration.
	 * @throws Exception if fails.
	 */
	@Insert("INSERT INTO _Registrazione(Codice, Data, Lavorate, Ferie, Permesso, Malattia, Trasferta, Note) VALUES (#{reg.codice}, #{reg.data}, #{reg.lavorate}, #{reg.ferie}, #{reg.permesso}, #{reg.malattia}, #{reg.trasferta}, #{reg.note})")
	@Options(useGeneratedKeys = true, keyProperty = "reg.id", flushCache = true)
	public void saveRegistrazione(@Param("reg") Registrazione registr) throws Exception;

	
	/**
	 * 
	 * @param registr the user's registration.
	 * @throws Exception if fails.
	 */
	@Update("UPDATE _Registrazione SET Codice= #{reg.codice}, Data = #{reg.data}, Lavorate = #{reg.lavorate}, Ferie = #{reg.ferie}, Permesso = #{reg.permesso}, Malattia = #{reg.malattia}, Trasferta = #{reg.trasferta},  Note = #{reg.note} WHERE Id = #{reg.id}")
	@Options(useGeneratedKeys = true, keyProperty = "reg.id", flushCache = true)
	public void updateRegistrazione(@Param("reg") Registrazione registr) throws Exception;

	
	/**
	 * 
	 * @param registr the user's registration.
	 * @throws Exception if fails.
	 */
	@Delete("DELETE FROM _Registrazione WHERE Id = #{reg.id}")
	public void deleteRegistrazione(@Param("reg") Registrazione registr) throws Exception;

}
