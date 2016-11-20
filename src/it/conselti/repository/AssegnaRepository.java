/*
 * Conselti s.r.l.
 */
package it.conselti.repository;


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

import it.conselti.domain.Assegna;

/**
 * @author onofr
 *
 */

@Repository
public interface AssegnaRepository {

	@Results({ @Result(column="Matr_utente", property="matricolaUtente", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
	           @Result(column="Matr_ruolo", property="matricolaRuolo", jdbcType=JdbcType.INTEGER, javaType=Integer.class) })
	
	
	/**
	 * 
	 * @param user the username.
	 * @param psw the password.
	 * @return the Assegna object.
	 */
	@Select("SELECT Matr_utente, Matr_ruolo FROM _assegna AS ass INNER JOIN _Utente AS ut ON ut.Codice_utente = ass.Matr_utente WHERE ut.Username = #{usr} and ut.Password = #{psw}")
	@Options(flushCache = true, useCache = true)
	public Assegna findAllAssegnaByUserAndPsw(@Param("usr") String user, @Param("psw") String psw);

	/**
	 * 
	 * @param assegna the object Assegna.
	 * @throws Exception if fails.
	 */
	@Insert("INSERT INTO _assegna (Matr_utente, Matr_ruolo) VALUES (#{assign.matricolaUtente}, #{assign.matricolaRuolo})")
	@Options(useGeneratedKeys = false, keyProperty = "assign.matricolaUtente", flushCache = true)
	public void saveAssegna(@Param("assign") Assegna assegna) throws Exception;

	/**
	 * 
	 * @param assegna the object Assegna.
	 * @throws Exception if fails.
	 */
	@Update("UPDATE _assegna SET Matr_utente = #{assegna.matricolaUtente}, Matr_ruolo = #{assegna.matricolaRuolo} WHERE Matr_utente = #{assegna.matricolaUtente}")
	@Options(flushCache = true, useCache = true)
	public void updateAssegna(@Param("assegna") Assegna assegna) throws Exception;

	/**
	 * 
	 * @param assegna the object Assegna.
	 * @throws Exception if fails.
	 */
	@Delete("DELETE FROM _assegna WHERE Matr_utente = #{assegna.matricolaUtente}")
	public void deleteAssegna(@Param("assegna") Assegna assegna) throws Exception;

}
