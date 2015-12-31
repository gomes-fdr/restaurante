package com.jdf.swing.helper;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 * Classe auxiliar para criaÃ§Ã£o do objeto combobox
 *
 * @author lossurdo
 * @since 01/03/2009
 */
public class JComboBoxHelper {

    private JComboBox combobox;

    public JComboBoxHelper(JComboBox combobox) {
        this.combobox = combobox;
        combobox.removeAllItems();
    }

    public void setModel(String... values) {
        combobox.setModel(new DefaultComboBoxModel(values));
    }

    public void setModel(List list) {
        for(Object o : list) {
            combobox.addItem(o);
        }
    }

    public Object getSelectedObject() {
        return combobox.getSelectedItem();
    }
}
