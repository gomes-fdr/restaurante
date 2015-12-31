package com.jdf.security;

/**
 * Interface implementada pelos objetos de tela para controle de seguranÃ§a
 * @author rafael-lossurdo
 * @since 22/04/2009
 */
public interface JDFSecurity {
	/**
	 * Verifica permissÃ£o do usuÃ¡rio logado
	 * @param object Objeto a ser verificado
	 * @param action AÃ§Ã£o a ser verificada
	 */
	public void verifyPermission(String object, String action);
}
