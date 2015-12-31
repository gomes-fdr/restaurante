package com.jdf.swing.helper;

import javax.swing.ImageIcon;

import com.jdf.swing.iface.IconPackBase64;

/**
 * Interface padrÃ£o para componentes que contenham Ã­cones
 * @author lossurdo
 * @since 16/04/2009
 */
interface IconSets {

	/**
	 * Atribui icone ao objeto 
	 * @param icon Enum contendo a referÃªncia Base64 do icone
	 */
	public abstract void setIcon(IconPackBase64 icon);

	/**
	 * Atribui icone ao objeto 
	 * @param icon Path para o icone
	 */
	public abstract void setIcon(String icon);

	/**
	 * Atribui icone ao objeto 
	 * @param icon Imagem
	 */
	public abstract void setIcon(ImageIcon icon);

}