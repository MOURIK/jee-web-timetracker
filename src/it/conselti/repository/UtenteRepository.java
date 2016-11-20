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
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import it.conselti.domain.Utente;

/**
 * @author onofr
 *
 */


@Repository
public interface UtenteRepository {
	
	@Results({ @Result(id=true, column="Codice_utente", property="codiceUtente", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
	           @Result(column="ID_anagrafica", property="idAnagrafica", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
	           @Result(column="UsernameUtente", property="usernameUtente", jdbcType=JdbcType.VARCHAR, javaType=String.class),
	           @Result(column="PasswordUtente", property="passwordUtente", jdbcType=JdbcType.VARCHAR, javaType=String.class),
	           @Result(column="Enabled", property="enabled", jdbcType=JdbcType.VARCHAR, javaType=String.class) })
	
	
	/**
	 * 
	 * @param username the username.
	 * @param password the password.
	 * @return the Utente object.
	 */
	@Select("SELECT Codice_utente, Username AS UsernameUtente, Password AS PasswordUtente FROM _Utente AS utente WHERE utente.Username = #{user} AND utente.Password = #{pass}")
	public Utente findUtenteByCredentials(@Param("user") String username, @Param("pass") String password);
	
	
	/**
	 * 
	 * @return the list.
	 */
	@Select("SELECT Codice_utente, ID_anagrafica, Username AS UsernameUtente, Password AS PasswordUtente FROM _Utente")
	@Options(useCache = true, flushCache = true)
	public List<Utente> findAllUtente();
	
	
	/**
	 * 
	 * @param usr the username.
	 * @return the Utente object.
	 */
	@Select("SELECT Username AS UsernameUtente, Password AS PasswordUtente, Enabled FROM _Utente AS utente WHERE utente.Username = #{usr}")
	@Options(useCache = true, flushCache = true)
	public Utente findUtenteByUsername(@Param("usr") String usr);
	
	
	
	

	@Select("OPEN SYMMETRIC KEY Psw_usrs_Key01 DECRYPTION BY CERTIFICATE Users_password_AES256 "
			+ "SELECT Username AS UsernameUtente, CONVERT(nvarchar, DecryptByKey(Password_Encrypted, 1, HashBytes('SHA1', CONVERT(varbinary, Codice_utente)))) AS PasswordUtente, Enabled "
			+ "FROM _Utente AS utente WHERE utente.Username = #{usr}")
	@Options(useCache = true, flushCache = true)
	public Utente findUtenteByUsernameDecoded(@Param("usr") String usr);
	
	
	
	
	
	/**
	 * 
	 * @param pass the password.
	 * @return the Utente object.
	 */
	@Select("SELECT Password AS PasswordUtente FROM _Utente AS utente WHERE utente.Password = #{pass}")
	public Utente findUtenteByPassword(@Param("pass") String pass);
	
	
	/**
	 * 
	 * @param utente input parameter.
	 * @throws Exception if fails.
	 */
	@Insert("INSERT INTO _Utente (ID_anagrafica, Username, Password, Enabled) VALUES (#{user.idAnagrafica}, #{user.usernameUtente}, #{user.passwordUtente}, #{user.enabled})")
	@Options(useGeneratedKeys = true, keyProperty = "user.codiceUtente", flushCache = true)
	public void saveUtente(@Param("user") Utente utente) throws Exception;

	/**
	 * 
	 * @param utente the Utente input parameter.
	 * @throws Exception if fails.
	 */
	@Update("UPDATE _Utente SET ID_anagrafica = #{user.idAnagrafica}, Username = #{user.usernameUtente}, Password = #{user.passwordUtente}, Enabled = #{user.enabled} WHERE Codice_utente = #{user.codiceUtente}")
	@Options(useGeneratedKeys = true, keyProperty = "user.codiceUtente", flushCache = true)
	public void updateUtente(@Param("user") Utente utente) throws Exception;
	
	
	@Update("UPDATE _Utente SET Password = #{utente.passwordUtente} WHERE ID_anagrafica = #{utente.idAnagrafica}")
	public void updatePasswordUtente(@Param("utente") Utente utente) throws Exception;
	
	
	/**
	 * 
	 * @param utente the Utente input parameter.
	 * @throws Exception if fails.
	 */
	@Delete("DELETE FROM _Utente WHERE ID_anagrafica = #{user.idAnagrafica}")
	public void deleteUtente(@Param("user") Utente utente) throws Exception;
	
}