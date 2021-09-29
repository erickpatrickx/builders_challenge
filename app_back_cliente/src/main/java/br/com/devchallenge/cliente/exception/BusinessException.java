package br.com.devchallenge.cliente.exception;

import java.util.List;

/**
 * Classe de erros negociais gerais
 * @author erick.oliveira
 *
 */
public class BusinessException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -832579351885457504L;

	List<String> erros;
	
	public BusinessException() {
	    super();
	 }
	public BusinessException(String msg) {
	    super(msg);
	 }
	public BusinessException(List<String> erros) {
	    super();
		this.erros = erros;
	 }
	public List<String> getErros() {
		return erros;
	}
	public void setErros(List<String> erros) {
		this.erros = erros;
	}

	
	
	
}
