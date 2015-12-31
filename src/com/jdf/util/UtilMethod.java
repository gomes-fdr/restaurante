package com.jdf.util;

import java.lang.reflect.Method;

/**
 * UtilitÃÂ¡rio para tratar de reflexÃÂ£o em classes
 * @author lossurdo
 * @since 15/03/2009
 */
public final class UtilMethod {
	
	/**
	 * Executa a aÃÂ§ÃÂ£o sem passagem de parÃÂ¢metros
	 * @param object instÃÂ¢ncia do objeto que contÃÂ©m o mÃÂ©todo a ser executado
	 * @param method mÃÂ©todo a ser executado
	 */
	
	public static synchronized Object executeMethod(Object object, String method) {
		try {
			Class<?> clazz = object.getClass();
			Method m = clazz.getDeclaredMethod(method, null);
			m.setAccessible(true);
			return m.invoke(object, null);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
