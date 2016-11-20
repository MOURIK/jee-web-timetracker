/*
 * Conselti s.r.l.
 */
package it.conselti.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import it.conselti.domain.VisualizzaRegistrazione;
import it.conselti.provider.VisualizzaRegistrazioneProvider;

/**
 * @author onofr
 *
 */


@Repository
public interface VisualizzaRegistrazioneRepository {
	
	@Results({ 
		@Result(column="Id", property="id", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
		@Result(column="CodiceUtente", property="codiceUtente", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
        @Result(column="Cognome", property="cognome", jdbcType=JdbcType.VARCHAR, javaType=String.class),
        @Result(column="Nome", property="nome", jdbcType=JdbcType.VARCHAR, javaType=String.class),
        @Result(column="Citta", property="citta", jdbcType=JdbcType.VARCHAR, javaType=String.class),
        @Result(column="DataNascita", property="dataNascita", jdbcType=JdbcType.DATE, javaType=Date.class),
        @Result(column="Email", property="email", jdbcType=JdbcType.VARCHAR, javaType=String.class),
        @Result(column="RuoloUtente", property="ruoloUtente", jdbcType=JdbcType.VARCHAR, javaType=String.class),
        @Result(column="TotaleLavorate", property="totaleLavorate", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
        @Result(column="TotaleFerie", property="totaleFerie", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
        @Result(column="TotalePermesso", property="totalePermesso", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
        @Result(column="TotaleMalattia", property="totaleMalattia", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
        @Result(column="TotaleTrasferta", property="totaleTrasferta", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
        @Result(column="Totale", property="totale", jdbcType=JdbcType.INTEGER, javaType=Integer.class)})
	
	
	
	/**
	 * 	
	 * @param admin
	 * @return
	 */
	@SelectProvider(type = VisualizzaRegistrazioneProvider.class, method = "findRegistrazioneAdminFiltered")
	@Options(useCache = true, flushCache = true)
	public List<VisualizzaRegistrazione> findRegistrazioneAdminFiltered(@Param("admin") VisualizzaRegistrazione admin);
	
	/**
	 * 
	 * @param user
	 * @param codiceUtente
	 * @return
	 */
	@SelectProvider(type = VisualizzaRegistrazioneProvider.class, method = "findRegistrazioneUserFiltered")
	@Options(useCache = true, flushCache = true)
	public List<VisualizzaRegistrazione> findRegistrazioneUserFiltered(@Param("user") VisualizzaRegistrazione user, @Param("codiceUtente") Integer codiceUtente);
	
	/**
	 * 
	 * @param period
	 * @return
	 */
	@SelectProvider(type = VisualizzaRegistrazioneProvider.class, method = "findRegistrazioneByPeriodFiltered")
	@Options(useCache = true, flushCache = true)
	public List<VisualizzaRegistrazione> findRegistrazioneByPeriodFiltered(@Param("period") VisualizzaRegistrazione period);
	
	
	
	
	@Select("SELECT ut.Codice_utente AS CodiceUtente, an.Cognome, an.Nome, SUM(Lavorate) AS TotaleLavorate, SUM(Ferie) AS TotaleFerie, SUM(Permesso) AS TotalePermesso, SUM(Malattia) AS TotaleMalattia, SUM(Trasferta) AS TotaleTrasferta, "
			+ "(isNull(SUM(Lavorate), 0) + isNull(SUM(Ferie), 0) + isNull(SUM(Permesso), 0) + isNull(SUM(Malattia), 0) + isNull(SUM(Trasferta), 0)) AS Totale "
			+ "FROM _Anagrafica AS an INNER JOIN _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica INNER JOIN _assegna AS ass ON ut.Codice_utente = ass.Matr_utente INNER JOIN _Ruolo AS ru ON ass.Matr_ruolo = ru.CodiceRuolo "
			+ "INNER JOIN _Registrazione AS re ON ut.Codice_utente = re.Codice "
			+ "GROUP BY ut.Codice_utente, an.Cognome, an.Nome")
	@Options(useCache = true, flushCache = true)
	public List<VisualizzaRegistrazione> findAllRegistrazioneForAdminsAndSuperusers();
	
	
	
	@Select("SELECT ut.Codice_utente AS CodiceUtente, an.Cognome, an.Nome, SUM(Lavorate) AS TotaleLavorate, SUM(Ferie) AS TotaleFerie, SUM(Permesso) AS TotalePermesso, SUM(Malattia) AS TotaleMalattia, SUM(Trasferta) AS TotaleTrasferta, "
			+ "(isNull(SUM(Lavorate), 0) + isNull(SUM(Ferie), 0) + isNull(SUM(Permesso), 0) + isNull(SUM(Malattia), 0) + isNull(SUM(Trasferta), 0)) AS Totale "
			+ "FROM _Anagrafica AS an INNER JOIN _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica INNER JOIN _assegna AS ass ON ut.Codice_utente = ass.Matr_utente INNER JOIN _Ruolo AS ru ON ass.Matr_ruolo = ru.CodiceRuolo "
			+ "INNER JOIN _Registrazione AS re ON ut.Codice_utente = re.Codice "
			+ "WHERE Codice_utente = #{codiceUtente}"
			+ "GROUP BY ut.Codice_utente, an.Cognome, an.Nome")
	@Options(useCache = true, flushCache = true)
	public List<VisualizzaRegistrazione> findAllRegistrazioneForUsers(@Param("codiceUtente") Integer codiceUtente);
	

	
	/**
	 * 
	 * @param periodo
	 * @return
	 */
	@Select("SELECT ut.Codice_utente AS CodiceUtente, an.Cognome, an.Nome, SUM(Lavorate) AS TotaleLavorate, SUM(Ferie) AS TotaleFerie, SUM(Permesso) AS TotalePermesso, SUM(Malattia) AS TotaleMalattia, SUM(Trasferta) AS TotaleTrasferta, "
			+ "(isNull(SUM(Lavorate), 0) + isNull(SUM(Ferie), 0) + isNull(SUM(Permesso), 0) + isNull(SUM(Malattia), 0) + isNull(SUM(Trasferta), 0)) AS Totale "
			+ "FROM _Anagrafica AS an INNER JOIN _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica "
			+ "INNER JOIN _Registrazione AS re ON ut.Codice_utente = re.Codice "
			+ "WHERE YEAR(Data) = #{periodo.anno} AND MONTH(Data) = #{periodo.mese}"
			+ "GROUP BY ut.Codice_utente, an.Cognome, an.Nome")
	@Options(useCache = true, flushCache = true)
	public List<VisualizzaRegistrazione> findAllRegistrazionePerPeriodo(@Param("periodo") VisualizzaRegistrazione periodo);
	
	

	
	@Select("select * from _Anagrafica as a inner join _Utente as u on a.ID_anagrafica = u.ID_anagrafica inner join _Registrazione as r on u.Codice_utente = r.Codice")
	public List<VisualizzaRegistrazione> findAll();

}
