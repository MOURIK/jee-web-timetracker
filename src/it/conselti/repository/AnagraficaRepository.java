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

import it.conselti.domain.Anagrafica;

/**
 * @author onofr
 *
 */

@Repository
public interface AnagraficaRepository {

	@Results({ @Result(id=true, column="Id", property="id", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
		       @Result(column="NomeRuolo", property="nomeRuolo", jdbcType=JdbcType.VARCHAR, javaType=String.class),
		       @Result(column="CodiceRuolo", property="codiceRuolo", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
		       @Result(column="Matr_utente", property="matricolaUtente", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
		       @Result(column="Matr_ruolo", property="matricolaRuolo", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
		       @Result(column="Username", property="username", jdbcType=JdbcType.VARCHAR, javaType=String.class),
		       @Result(column="Password", property="password", jdbcType=JdbcType.VARCHAR, javaType=String.class),
		       @Result(column="CodiceFiscale", property="codiceFiscale", jdbcType=JdbcType.VARCHAR, javaType=String.class),
		       @Result(column="Nome", property="nome", jdbcType=JdbcType.VARCHAR, javaType=String.class),
		       @Result(column="Cognome", property="cognome", jdbcType=JdbcType.VARCHAR, javaType=String.class),
		       @Result(column="Sesso", property="sesso", jdbcType=JdbcType.VARCHAR, javaType=String.class),
		       @Result(column="Nascita", property="nascita", jdbcType=JdbcType.DATE, javaType=Date.class),
		       @Result(column="Email", property="email", jdbcType=JdbcType.VARCHAR, javaType=String.class),
		       @Result(column="Via", property="via", jdbcType=JdbcType.VARCHAR, javaType=String.class),
		       @Result(column="Cap", property="cap", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
		       @Result(column="City", property="city", jdbcType=JdbcType.VARCHAR, javaType=String.class),
		       @Result(column="Cellulare", property="cellulare", jdbcType=JdbcType.VARCHAR, javaType=String.class) })



	/**
	 * 
	 * @return a List.
	 */
	@Select("SELECT * FROM _Anagrafica ORDER BY Cognome")
	@Options(flushCache = true)
	public List<Anagrafica> findAllAnagrafica();

	
	
	@Select("SELECT ru.NomeRuolo, ass.Matr_utente, ass.Matr_ruolo, ut.Codice_utente, ut.Username, ut.Password, an.ID_anagrafica AS Id, an.Codice_fiscale AS CodiceFiscale, an.Nome, an.Cognome, an.Sesso, an.DataNascita AS Nascita, an.Email, an.Via, an.Cap, an.Citta AS City, an.Cellulare "
			+ "FROM _Anagrafica AS an INNER JOIN _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica INNER JOIN _assegna AS ass ON ut.Codice_utente = ass.Matr_utente INNER JOIN _Ruolo AS ru ON ass.Matr_ruolo = ru.CodiceRuolo")
	@Options(flushCache = true, useCache = true)
	public List<Anagrafica> findAllAnagraficaUtente();

	
	
	@Select("select Nome, Cognome from _Anagrafica order by Cognome")
	@Options(flushCache = true, useCache = true)
	public List<Anagrafica> findCognomeAndNomeAnagrafica();
	
	
	
	/**
	 * 
	 * @param codiceUtente the user code.
	 * @return a List.
	 */
	@Select("SELECT ru.NomeRuolo, ass.Matr_utente, ass.Matr_ruolo, ut.Codice_utente, ut.Username, ut.Password, an.ID_anagrafica AS Id, an.Codice_fiscale AS CodiceFiscale, an.Nome, an.Cognome, an.Sesso, an.DataNascita AS Nascita, an.Email, an.Via, an.Cap, an.Citta AS City, an.Cellulare FROM _Anagrafica AS an INNER JOIN _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica INNER JOIN _assegna AS ass ON ut.Codice_utente = ass.Matr_utente INNER JOIN _Ruolo AS ru ON ass.Matr_ruolo = ru.CodiceRuolo WHERE ut.Codice_utente = #{userCode}")
	@Options(flushCache = true, useCache = true)
	public List<Anagrafica> findAllAnagraficaByCodiceUtente(@Param("userCode") Integer codiceUtente);
	
	
	
	
	@Select("SELECT * FROM _Anagrafica WHERE ID_anagrafica = #{id}")
	@Options(flushCache = true, useCache = true)
	public Anagrafica findAllAnagraficaById(@Param("id") Integer id);
	
	

	/**
	 * 
	 * @param username the user's username.
	 * @return the String cognome.
	 */
	@Select("SELECT Cognome FROM _Anagrafica AS an INNER JOIN _Utente AS us ON an.ID_anagrafica = us.ID_anagrafica WHERE us.Username = #{username}")
	@Options(flushCache = true, useCache = true)
	public String findCognomeByUsername(@Param("username") String username);
	
	
	/**
	 * 
	 * @param username the user's username.
	 * @return the String nome.
	 */
	@Select("SELECT Nome FROM _Anagrafica AS an INNER JOIN _Utente AS us ON an.ID_anagrafica = us.ID_anagrafica WHERE us.Username = #{username}")
	@Options(flushCache = true, useCache = true)
	public String findNomeByUsername(@Param("username") String username);


	
	
	/**
	 * 
	 * @param anagrafica the user anagrafica.
	 * @throws Exception the exception.
	 */
	@Insert("INSERT INTO _Anagrafica (Codice_fiscale, Nome, Cognome, Sesso, DataNascita, Email, Via, Cap, Citta, Cellulare) VALUES (#{anagr.codiceFiscale}, #{anagr.nome}, #{anagr.cognome}, #{anagr.sesso}, #{anagr.nascita}, #{anagr.email}, #{anagr.via}, #{anagr.cap}, #{anagr.city}, #{anagr.cellulare})")
	@Options(useGeneratedKeys = true, keyProperty = "anagr.id", flushCache = true)
	public void saveAnagrafica(@Param("anagr") Anagrafica anagrafica) throws Exception;

	
	/**
	 * 
	 * @param anagrafica the user anagrafica.
	 * @throws Exception the exception.
	 */
	@Update("UPDATE _Anagrafica SET Email = #{anagr.email}, Via = #{anagr.via}, Cap = #{anagr.cap}, Citta = #{anagr.city}, Cellulare = #{anagr.cellulare} WHERE Codice_fiscale = #{anagr.codiceFiscale}")
	public void updateAnagrafica(@Param("anagr") Anagrafica anagrafica) throws Exception;

	
	/**
	 * 
	 * @param anagrafica the user anagrafica.
	 * @throws Exception the exception.
	 */
	@Delete("DELETE FROM _Anagrafica WHERE ID_anagrafica = #{anagr.id}")
	public void deleteAnagrafica(@Param("anagr") Anagrafica anagrafica) throws Exception;

	
	
	/**
	 * 
	 * @param anagrafica the user anagrafica.
	 * @throws Exception the exception.
	 */
	@Delete("DELETE anagrafica, utente FROM _Anagrafica AS anagrafica INNER JOIN _Utente AS utente ON anagrafica.ID_anagrafica = utente.ID_anagrafica WHERE anagrafica.ID_anagrafica = #{anagr.id}")
	public void deleteAnagraficaAndUtente(@Param("anagr") Anagrafica anagrafica) throws Exception;

}
