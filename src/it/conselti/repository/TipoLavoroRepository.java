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

import it.conselti.domain.TipoLavoro;

/**
 * @author onofr
 *
 */


@Repository
public interface TipoLavoroRepository {
	
	@Results({ @Result(id=true, column="ID_tipo_lavoro", property="idTipoLavoro", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
               @Result(column="Descrizione", property="descrizione", jdbcType=JdbcType.VARCHAR, javaType=String.class) })
	
	
	/**
	 * 
	 * @return
	 */
	@Select("SELECT ID_tipo_lavoro, Descrizione FROM _Tipo_lavoro")
	public List<TipoLavoro> findAllTipoLavoro();
	
	
	/**
	 * 
	 * @return
	 */
	@Select("SELECT * FROM _Tipo_lavoro WHERE Descrizione = #{descrizione}")
	public TipoLavoro findTipoLavoroByDescrizione(@Param("descrizione") String descrizione);
	
	
	/**
	 * 
	 * @param lavoro
	 * @throws Exception
	 */
	@Insert("INSERT INTO _Tipo_lavoro (Descrizione) VALUES (#{lavoro.descrizione})")
	@Options(useGeneratedKeys = true, keyProperty = "lavoro.idTipoLavoro", flushCache = true)
	public void saveTipoLavoro(@Param("lavoro") TipoLavoro lavoro) throws Exception;
	
	/**
	 * 
	 * @param lavoro
	 * @throws Exception
	 */
	@Update("UPDATE _Tipo_lavoro SET Descrizione = #{lavoro.descrizione} WHERE ID_tipo_lavoro = #{lavoro.idTipoLavoro}")
	@Options(useGeneratedKeys = true, keyProperty = "lavoro.idTipoLavoro", flushCache = true)
	public void updateTipoLavoro(@Param("lavoro") TipoLavoro lavoro) throws Exception;
	
	/**
	 * 
	 * @param lavoro
	 * @throws Exception
	 */
	@Delete("DELETE FROM _Tipo_lavoro WHERE ID_tipo_lavoro = #{lavoro.idTipoLavoro}")
	public void deleteTipoLavoro(@Param("lavoro") TipoLavoro lavoro) throws Exception;


}
