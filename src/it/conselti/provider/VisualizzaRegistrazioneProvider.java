/*
 * Conselti s.r.l.
 */
package it.conselti.provider;

import java.util.Map;

import it.conselti.domain.VisualizzaRegistrazione;

/**
 * @author onofr
 *
 */

public class VisualizzaRegistrazioneProvider {

	public static String findRegistrazioneAdminFiltered(Map<?, ?> params) {
		
		VisualizzaRegistrazione visualizzaDomain = (VisualizzaRegistrazione) params.get("admin");
	
		String header = "SELECT ut.Codice_utente AS CodiceUtente, an.Cognome, an.Nome, SUM(Lavorate) AS TotaleLavorate, SUM(Ferie) AS TotaleFerie, SUM(Permesso) AS TotalePermesso, SUM(Malattia) AS TotaleMalattia, SUM(Trasferta) AS TotaleTrasferta, "
				+ "(isNull(SUM(Lavorate), 0) + isNull(SUM(Ferie), 0) + isNull(SUM(Permesso), 0) + isNull(SUM(Malattia), 0) + isNull(SUM(Trasferta), 0)) AS Totale "
				+ "FROM _Anagrafica AS an INNER JOIN _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica INNER JOIN _assegna AS ass ON ut.Codice_utente = ass.Matr_utente INNER JOIN _Ruolo AS ru ON ass.Matr_ruolo = ru.CodiceRuolo "
				+ "INNER JOIN _Registrazione AS re ON ut.Codice_utente = re.Codice ";

		String footer = " GROUP BY ut.Codice_utente, an.Cognome, an.Nome";
		
		
		StringBuilder query = new StringBuilder();
		query.append(header);
		
		if (visualizzaDomain.getCognome().isEmpty() && visualizzaDomain.getNome().isEmpty()) {
			query.append("WHERE re.Data BETWEEN #{admin.dateFrom} AND #{admin.dateTo}");
			
		} else if (!visualizzaDomain.getCognome().isEmpty() && visualizzaDomain.getNome().isEmpty()) {
			query.append("WHERE re.Data BETWEEN #{admin.dateFrom} AND #{admin.dateTo} AND an.Cognome = #{admin.cognome}");
		
		} else if (visualizzaDomain.getCognome().isEmpty() && !visualizzaDomain.getNome().isEmpty()) {
			query.append("WHERE re.Data BETWEEN #{admin.dateFrom} AND #{admin.dateTo} AND an.Nome = #{admin.nome}");
		
		} else {
			query.append("WHERE an.Cognome = #{admin.cognome} AND an.Nome = #{admin.nome} AND re.Data BETWEEN #{admin.dateFrom} AND #{admin.dateTo}");
		}
		
	
	    query.append(footer);
		
	    return query.toString();
	}
	
	
	
	
	/**
	 * 
	 * @param params the Map params.
	 * @return the String query.
	 */
	public static String findRegistrazioneUserFiltered(Map<?, ?> params) {
		
		String body = "SELECT ut.Codice_utente AS CodiceUtente, an.Cognome, an.Nome, SUM(Lavorate) AS TotaleLavorate, SUM(Ferie) AS TotaleFerie, SUM(Permesso) AS TotalePermesso, SUM(Malattia) AS TotaleMalattia, SUM(Trasferta) AS TotaleTrasferta, "
				+ "(isNull(SUM(Lavorate), 0) + isNull(SUM(Ferie), 0) + isNull(SUM(Permesso), 0) + isNull(SUM(Malattia), 0) + isNull(SUM(Trasferta), 0)) AS Totale "
				+ "FROM _Anagrafica AS an INNER JOIN _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica "
				+ "INNER JOIN _Registrazione AS re ON ut.Codice_utente = re.Codice "
				+ "WHERE re.Data BETWEEN #{user.dateFrom} AND #{user.dateTo} AND ut.Codice_utente = #{codiceUtente} "
				+ "GROUP BY ut.Codice_utente, an.Cognome, an.Nome";

		
		StringBuilder query = new StringBuilder();
		query.append(body); 
		
		return query.toString();
	}
	
	
	
	
	
	/**
	 * 
	 * @param params the Map params.
	 * @return the String query.
	 */
	public static String findRegistrazioneByPeriodFiltered(Map<?, ?> params) {
		VisualizzaRegistrazione visualizzaDomain = (VisualizzaRegistrazione) params.get("period");
		
		String header = "SELECT ut.Codice_utente AS CodiceUtente, an.Cognome, an.Nome, SUM(Lavorate) AS TotaleLavorate, SUM(Ferie) AS TotaleFerie, SUM(Permesso) AS TotalePermesso, SUM(Malattia) AS TotaleMalattia, SUM(Trasferta) AS TotaleTrasferta, "
				+ "(isNull(SUM(Lavorate), 0) + isNull(SUM(Ferie), 0) + isNull(SUM(Permesso), 0) + isNull(SUM(Malattia), 0) + isNull(SUM(Trasferta), 0)) AS Totale "
				+ "FROM _Anagrafica AS an INNER JOIN _Utente AS ut ON an.ID_anagrafica = ut.ID_anagrafica "
				+ "INNER JOIN _Registrazione AS re ON ut.Codice_utente = re.Codice ";
		
		String footer = " GROUP BY ut.Codice_utente, an.Cognome, an.Nome";
		
		
		StringBuilder query = new StringBuilder();
		query.append(header); 
		
		if (visualizzaDomain.getCognome().isEmpty() && visualizzaDomain.getNome().isEmpty() && 
				(visualizzaDomain.getMese() == null || visualizzaDomain.getMese().intValue() == 0)) {
			
			query.append("WHERE YEAR(Data) = #{period.anno}");
		
		
		} else if (visualizzaDomain.getCognome().isEmpty() && visualizzaDomain.getNome().isEmpty() && 
				(visualizzaDomain.getMese() != null || visualizzaDomain.getMese().intValue() > 0)) {
			
			query.append("WHERE YEAR(Data) = #{period.anno} AND MONTH(Data) = #{period.mese}");
		
			
	    } else if (visualizzaDomain.getCognome().isEmpty() && !visualizzaDomain.getNome().isEmpty() && 
	    		(visualizzaDomain.getMese() == null || visualizzaDomain.getMese().intValue() == 0)) {
	    	
	    	query.append("WHERE YEAR(Data) = #{period.anno} AND an.Nome = #{period.nome}");
	    
	    	
	    } else if (visualizzaDomain.getCognome().isEmpty() && !visualizzaDomain.getNome().isEmpty() && 
	    		(visualizzaDomain.getMese() != null || visualizzaDomain.getMese().intValue() > 0)) {
	    	
	    	query.append("WHERE YEAR(Data) = #{period.anno} AND an.Nome = #{period.nome} AND MONTH(Data) = #{period.mese}");
	    	
	    	
	    	
	    } else if (!visualizzaDomain.getCognome().isEmpty() && visualizzaDomain.getNome().isEmpty() && 
	    		(visualizzaDomain.getMese() == null || visualizzaDomain.getMese().intValue() == 0)) {
	    	
	    	query.append("WHERE YEAR(Data) = #{period.anno} AND an.Cognome = #{period.cognome}");	
	    	
	    	

	    } else if (!visualizzaDomain.getCognome().isEmpty() && visualizzaDomain.getNome().isEmpty() && 
	    		(visualizzaDomain.getMese() != null || visualizzaDomain.getMese().intValue() > 0)) {
	    	
	    	query.append("WHERE YEAR(Data) = #{period.anno} AND an.Cognome = #{period.cognome} AND MONTH(Data) = #{period.mese}");	
	    

	    } else if (!visualizzaDomain.getCognome().isEmpty() && !visualizzaDomain.getNome().isEmpty() && 
	    		(visualizzaDomain.getMese() == null || visualizzaDomain.getMese().intValue() == 0)) {
	    	
	    	query.append("WHERE YEAR(Data) = #{period.anno} AND an.Cognome = #{period.cognome} AND an.Nome = #{period.nome}");		
	    
	    	
		} else {
			query.append("WHERE an.Cognome = #{period.cognome} AND an.Nome = #{period.nome} AND MONTH(Data) = #{period.mese} AND YEAR(Data) = #{period.anno}");
		}
	
	 
	    query.append(footer);
		
		return query.toString();
		
	}
	
	
}
