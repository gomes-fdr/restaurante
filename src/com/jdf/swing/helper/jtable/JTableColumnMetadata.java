package com.jdf.swing.helper.jtable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation responsÃ¡vel pela marcaÃ§Ã£o de metadados para exibiÃ§Ã£o em
 * componentes JTable e JComboBox
 *
 * @author lossurdo
 * @since 22/03/2009
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JTableColumnMetadata {

    /**
     * Nome do cabeÃ§alho da coluna
     *
     * @return
     */
    public String name();

    /**
     * Tamanho em pixels para a coluna
     *
     * @return
     */
    public int size() default 0;

    /**
     * Formato em que os dados serÃ£o exibidos
     *
     * @return
     */
    public ColumnMetadataFormat format() default ColumnMetadataFormat.DEFAULT;

    /**
     * Alinhamento em que os dados serÃ£o exibidos
     *
     * @return
     */
    public ColumnMetadataAlign align() default ColumnMetadataAlign.LEFT;
}
