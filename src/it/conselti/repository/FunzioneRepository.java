/*
 * Conselti s.r.l.
 */
package it.conselti.repository;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import it.conselti.domain.Funzione;

/**
 * @author onofr
 *
 */

@Repository
public interface FunzioneRepository {
	
	@Results({ @Result(id=true, column="CodiceFunzione", property="codiceFunzione"),
		       @Result(column="Cognome", property="surname"),
		       @Result(column="NomeRuolo", property="roleName"),
               @Result(column="NomeFunzione", property="nomeFunzione"),
               @Result(column="Descrizione", property="descrizione") })
	
	
	@Select("SELECT * FROM _Funzione")
	public List<Funzione> findAllFunzione();

	@Select("SELECT an.Cognome, an.Nome, ru.CodiceRuolo, ru.NomeRuolo, fu.CodiceFunzione, fu.NomeFunzione, fu.Descrizione FROM _Anagrafica AS an INNER JOIN _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica INNER JOIN _assegna AS ass ON ut.Codice_utente = ass.Matr_utente INNER JOIN _Ruolo AS ru ON ass.Matr_ruolo = ru.CodiceRuolo INNER JOIN _definisce AS de ON ru.CodiceRuolo = de.Codice_ruolo INNER JOIN _Funzione AS fu ON de.Codice_funzione = fu.CodiceFunzione WHERE ut.Codice_utente = #{code} ORDER By fu.Descrizione ")
	@Options(useCache = true)
	public Funzione findFunzioneByCodiceUtente(@Param("code") Integer codiceUtente);


	@Select("select distinct an.Cognome, ru.NomeRuolo, ru.Descrizione, fu.NomeFunzione, fu.Descrizione from _Anagrafica as an join _Utente as ut on an.ID_anagrafica = ut.ID_anagrafica join _assegna as ass on ut.Codice_utente = ass.Matr_utente join _Ruolo as ru on ass.Matr_ruolo = ru.CodiceRuolo join _definisce as de on ru.CodiceRuolo = de.Codice_ruolo join _Funzione as fu on de.Codice_funzione = fu.CodiceFunzione where ru.NomeRuolo = #{role.roleName}")
	@Options(useCache = true, flushCache = true)
	public List<Funzione> findFunzioneByRuoloUtente(@Param("role") String nomeRuolo);


	@Insert("INSERT INTO _Funzione (NomeFunzione, Descrizione) VALUES (#{funz.nomeFunzione},#{funz.Descrizione})")
	@Options(useGeneratedKeys = true, keyProperty = "funz.codiceFunzione", flushCache = true)
	public void saveFunzione(@Param("funz") Funzione funzione) throws Exception;

	@Update("UPDATE _Funzione SET NomeFunzione = #{funz.nomeFunzione}, Descrizione = #{funz.descrizione} WHERE CodiceFunzione = #{funz.codiceFunzione}")
	@Options(useGeneratedKeys = true, keyProperty = "funz.codiceFunzione", flushCache = true)
	public void updateFunzione(@Param("funz") Funzione funzione) throws Exception;

	@Delete("DELETE FROM _Funzione WHERE CodiceFunzione = #{funz.codiceFunzione}")
	public void deleteFunzione(@Param("funz") Funzione funzione) throws Exception;

	
}
