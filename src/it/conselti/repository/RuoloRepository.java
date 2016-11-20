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

import it.conselti.domain.Ruolo;

/**
 * @author onofr
 *
 */


@Repository
public interface RuoloRepository {
	
	@Results({ @Result(id=true, column="CodiceRuolo", property="codiceRuolo"),
               @Result(column="NomeRuolo", property="nomeRuolo"),
               @Result(column="Descrizione", property="descrizioneRuolo")})
   
	
	
	/**
	 * 
	 * @return
	 */
	@Select("SELECT * FROM _Ruolo")
	public List<Ruolo> findAllRuolo();
	
	
	
	/**
	 * 
	 * @param nome
	 * @return
	 */
	@Select("SELECT * FROM _Ruolo WHERE NomeRuolo = #{name}")
	public Ruolo findRuoloByName(@Param("name") String nome);
	
	
	
	/**
	 * 
	 * @param codice
	 * @return
	 */
	@Select("SELECT NomeRuolo FROM _Ruolo WHERE CodiceRuolo = #{code}")
	public Ruolo findRuoloByCode(@Param("code") Integer codice);
	
	
	
	@Select("select * from _Ruolo where Descrizione = #{description}")
	public Ruolo findRuoloByDescription(@Param("description") String description);
	
	
	
	/**
	 * 
	 * @param username
	 * @return
	 */
	@Select("SELECT ru.NomeRuolo FROM _Utente AS ut INNER JOIN _assegna AS ass ON ut.Codice_utente = ass.Matr_utente INNER JOIN _Ruolo AS ru ON ass.Matr_ruolo = ru.CodiceRuolo WHERE ass.Matr_ruolo = ru.CodiceRuolo AND ut.Username = #{usr}")
	@Options(useCache = true, flushCache = true)
	public List<String> findRuoloByUsername(@Param("usr") String username);
	
	
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	@Select("SELECT ru.NomeRuolo AS NomeRuolo FROM _Utente AS ut INNER JOIN _assegna AS ass ON ut.Codice_utente = ass.Matr_utente INNER JOIN _Ruolo AS ru ON ass.Matr_ruolo = ru.CodiceRuolo WHERE ut.Username = #{user} AND ut.Password = #{pass}")
	public String findRuoloByUsernameAndPassword(@Param("user") String username, @Param("pass") String password);
	
	
	@Select("SELECT ru.Descrizione FROM _Utente AS ut INNER JOIN _assegna AS ass ON ut.Codice_utente = ass.Matr_utente INNER JOIN _Ruolo AS ru ON ass.Matr_ruolo = ru.CodiceRuolo WHERE ut.Username = #{user} AND ut.Password = #{pass}")
	public String findDescrizioneRuoloByUsernameAndPassword(@Param("user") String username, @Param("pass") String password);

	
	/**
	 * 
	 * @param ruolo
	 * @throws Exception
	 */
	@Insert("INSERT INTO _Ruolo (NomeRuolo) VALUES (#{role.nomeRuolo})")
	@Options(useGeneratedKeys = true, keyProperty = "role.codiceRuolo", flushCache = true)
	public void saveRuolo(@Param("role") Ruolo ruolo) throws Exception;

	
	/**
	 * 
	 * @param ruolo
	 * @throws Exception
	 */
	@Update("UPDATE _Ruolo SET NomeRuolo = #{role.nomeRuolo} WHERE CodiceRuolo = #{role.codiceRuolo}")
	@Options(useGeneratedKeys = true, keyProperty = "role.codiceRuolo", flushCache = true)
	public void updateRuolo(@Param("role") Ruolo ruolo) throws Exception;

	
	/**
	 * 
	 * @param ruolo
	 * @throws Exception
	 */
	@Delete("DELETE FROM _Ruolo WHERE CodiceRuolo = #{role.codiceRuolo}")
	public void deleteRuolo(@Param("role") Ruolo ruolo) throws Exception;


}
