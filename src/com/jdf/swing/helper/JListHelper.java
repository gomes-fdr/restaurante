package com.jdf.swing.helper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JList;

/**
 * Classe auxiliar para componentes JList
 *
 * @author lossurdo
 * @since 12/03/2009
 */
@SuppressWarnings("unchecked")
public class JListHelper {

    private JList listObjLeft;
    private JList listObjRight;
    private JButton btnAllLeft;
    private JButton btnAllRight;
    private JButton btnMoveLeft;
    private JButton btnMoveRight;
    private List sourceRightList;
    private List sourceLeftList;

    /**
     * Construtor
     *
     * @param list lista padrÃ£o (esquerda)
     */
    public JListHelper(JList list) {
        this.listObjLeft = list;
    }

    /**
     * Construtor
     *
     * @param listLeft lista esquerda
     * @param listRight lista direita
     * @param allRight botÃ£o-aÃ§Ã£o move todos para direita
     * @param allLeft botÃ£o-aÃ§Ã£o move todos para esquerda
     * @param moveRight botÃ£o-aÃ§Ã£o move selecionados para direita
     * @param moveLeft botÃ£o-aÃ§Ã£o move selecionados para esquerda
     */
    public JListHelper(JList listLeft, JList listRight, JButton allRight, JButton allLeft, JButton moveRight, JButton moveLeft) {
        this.listObjLeft = listLeft;
        this.listObjRight = listRight;
        this.btnAllLeft = allLeft;
        this.btnAllRight = allRight;
        this.btnMoveLeft = moveLeft;
        this.btnMoveRight = moveRight;
    }

    /**
     * Seta a lista padrÃ£o
     *
     * @param list Esquerda
     */
    public void setModel(List list) {
        setModelLeft(list);
    }

    /**
     * Seta lista
     *
     * @param list Esquerda
     */
    public void setModelLeft(List list) {
        this.sourceLeftList = list;
        listObjLeft.setModel(new ListModelMod(list));
    }

    /**
     * Seta lista
     *
     * @param list Direita
     */
    public void setModelRight(List list) {
        this.sourceRightList = list;
        listObjRight.setModel(new ListModelMod(list));
    }

    /**
     * ObtÃ©m o objeto selecionado
     *
     * @return Esquerda
     */
    public Object getSelectedLeftObject() {
        return listObjLeft.getSelectedValue();
    }

    /**
     * ObtÃ©m o objeto selecionado
     *
     * @return Direita
     */
    public Object getSelectedRightObject() {
        return listObjRight.getSelectedValue();
    }

    /**
     * Atribui o listener de botÃ£o ao modelo
     */
    public void applyListeners() {
        // limpa duplicados
        sourceLeftList.removeAll(sourceRightList);

        btnMoveRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveSelFromLeftToRight(listObjLeft, listObjRight);
            }
        });
        btnMoveLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveSelFromRightToLeft(listObjRight, listObjLeft);
            }
        });
        btnAllRight.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveAllFromLeftToRight(listObjLeft, listObjRight);
            }
        });
        btnAllLeft.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                moveAllFromRightToLeft(listObjRight, listObjLeft);
            }
        });
    }

    /**
     * Move selecionados da esquerda para direita
     *
     * @param listLeft lista da esquerda
     * @param listRight lista da direita
     */
    private void moveSelFromLeftToRight(JList listLeft, JList listRight) {
        Object[] sel = listLeft.getSelectedValues();
        if (sel == null) {
            return;
        }
        for (Object o : sel) {
            sourceLeftList.remove(o);
            sourceRightList.add(o);
        }
        listLeft.setModel(new ListModelMod(sourceLeftList));
        listRight.setModel(new ListModelMod(sourceRightList));
    }

    /**
     * Move selecionados da direita para esquerda
     *
     * @param listRight lista da direita
     * @param listLeft lista da esquerda
     */
    private void moveSelFromRightToLeft(JList listRight, JList listLeft) {
        Object[] sel = listRight.getSelectedValues();
        if (sel == null) {
            return;
        }
        for (Object o : sel) {
            sourceRightList.remove(o);
            sourceLeftList.add(o);
        }
        listRight.setModel(new ListModelMod(sourceRightList));
        listLeft.setModel(new ListModelMod(sourceLeftList));
    }

    /**
     * Move todos os objetos da esquerda para direita
     *
     * @param listLeft lista da esquerda
     * @param listRight lista da direita
     */
    private void moveAllFromLeftToRight(JList listLeft, JList listRight) {
        sourceRightList.addAll(sourceLeftList);
        listRight.setModel(new ListModelMod(sourceRightList));
        sourceLeftList = new ArrayList();
        listLeft.setModel(new ListModelMod(sourceLeftList));
    }

    /**
     * Move todos os objetos da direita para esquerda
     *
     * @param listRight lista da direita
     * @param listLeft lista da esquerda
     */
    private void moveAllFromRightToLeft(JList listRight, JList listLeft) {
        sourceLeftList.addAll(sourceRightList);
        listLeft.setModel(new ListModelMod(sourceLeftList));
        sourceRightList = new ArrayList();
        listRight.setModel(new ListModelMod(sourceRightList));
    }

    /**
     * Retorna o objeto selecionado padrÃ£o
     *
     * @return Esquerda
     */
    public Object getSelectedObject() {
        return getSelectedLeftObject();
    }

    public List getSelectedObjects() {
        return listObjLeft.getSelectedValuesList();
    }

    public void setSelectedModel(List list) {
        List<Integer> idx = new ArrayList<>();
        for (int x = 0; x < listObjLeft.getModel().getSize(); x++) {
            Object o = listObjLeft.getModel().getElementAt(x);
            for (Object s : list) {
                if (o.equals(s)) {
                    idx.add(x);
                }
            }
        }
        int[] sel = new int[idx.size()];
        for (int i = 0; i < sel.length; i++) {
            sel[i] = idx.get(i);
        }
        listObjLeft.setSelectedIndices(sel);
    }
}
