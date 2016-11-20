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

import it.conselti.domain.Menu;

/**
 * @author onofr
 *
 */

@Repository
public interface MenuRepository {
	
	@Results({ @Result(id=true, column="idmenu", property="idMenu", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
               @Result(column="Ruolo", property="ruoloMenu", jdbcType=JdbcType.VARCHAR, javaType=String.class),
               @Result(column="Descrizione", property="descrizioneMenu", jdbcType=JdbcType.VARCHAR, javaType=String.class),
               @Result(column="Url", property="urlMenu", jdbcType=JdbcType.VARCHAR, javaType=String.class),
               @Result(column="padre", property="padreMenu", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
               @Result(column="seqOrd", property="sequenzaOrdineMenu", jdbcType=JdbcType.INTEGER, javaType=Integer.class),
               @Result(column="Icona", property="icona", jdbcType=JdbcType.VARCHAR, javaType=String.class)})
	
	
	@Select("SELECT ID_menu, Ruolo, Descrizione, Url, Sequenza_ordine, Icona FROM _Menu WHERE Padre = #{padre}")
	public Menu findMenuByPadre(@Param("padre") Integer padre);
	
	
	
	@Select("SELECT m.ID_menu AS idmenu, Ruolo, Descrizione, Url, m.Padre AS padre, m.Sequenza_ordine AS seqOrd, Icona FROM _Menu AS m ORDER BY m.Ruolo, seqOrd")
	public List<Menu> findAllMenuOrdered();
	
	
	/**
	 * 
	 * @param sequenza the order sequence.
	 * @return the object Menu.
	 */
	@Select("SELECT ID_menu, Ruolo, Descrizione, Url, Padre, Icona FROM _Menu WHERE Sequenza_ordine = #{seq}")
	public Menu findMenuBySequenzaOrdine(@Param("seq") Integer sequenza);
	
	
	
	/**
	 * 
	 * @param ruolo the role.
	 * @return a list.
	 */
	@Select("select me.ID_menu as idmenu, me.Descrizione, me.Url, me.Padre as padre, me.Sequenza_ordine as seqOrd, me.Icona from _Ruolo as ru join _possiede as po on ru.CodiceRuolo = po.CodiceRuolo join _Menu as me on po.ID_menu = me.ID_menu where ru.NomeRuolo = #{ruolo} order by ru.NomeRuolo, seqOrd")
	public List<Menu> findMenuByRuolo(@Param("ruolo") String ruolo);
	
	
	
	/**
	 * 
	 * @param descrizione the description.
	 * @return the object Menu.
	 */
	@Select("SELECT ID_menu, Ruolo, Url, Padre, Sequenza_ordine, Icona FROM _Menu WHERE Descrizione = #{descr}")
	public Menu findMenuByDescrizione(@Param("descr") String descrizione);
	
	
	
	@Insert("INSERT INTO _Menu(Ruolo, Descrizione, url, Padre, Sequenza_ordine, Icona) VALUES (#{menu.ruoloMenu}, #{menu.descrizioneMenu}, #{menu.urlMenu}, #{menu.padreMenu}, #{menu.sequenzaOrdineMenu}, )")
	@Options(useGeneratedKeys = true, keyProperty = "menu.idMenu", flushCache = true)
	public void saveMenu(@Param("menu") Menu menu) throws Exception;

	/**
	 * 
	 * @param menu the object Menu.
	 * @throws Exception if fails.
	 */
	@Update("UPDATE _Menu SET Ruolo = #{menu.ruoloMenu}, Descrizione = #{menu.descrizioneMenu}, url = #{menu.urlMenu}, Padre = #{menu.padreMenu}, Sequenza_ordine = #{menu.sequenzaOrdineMenu}, Icona = #{menu.icona} WHERE ID_menu = #{menu.idMenu}")
	@Options(useGeneratedKeys = true, keyProperty = "menu.idMenu", flushCache = true)
	public void updateMenu(@Param("menu") Menu menu) throws Exception;


	@Delete("DELETE FROM _Menu WHERE ID_menu = #{menu.idMenu}")
	public void deleteMenu(@Param("menu") Menu menu) throws Exception;


}
