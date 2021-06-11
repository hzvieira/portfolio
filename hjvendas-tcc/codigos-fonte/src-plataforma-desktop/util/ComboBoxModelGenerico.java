package util;

import app.Principal;
import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class ComboBoxModelGenerico implements ComboBoxModel {

    private Class entidade;
    private List lista;
    private EntityManager em;
    private Object selecionado;

    public ComboBoxModelGenerico(Class classe) {
        this.entidade = classe;
        em = Principal.emf.createEntityManager();
        consulta();
    }

    public void consulta() {
        Query q = em.createQuery("from " + entidade.getSimpleName());
        lista = q.getResultList();
        Collections.sort(lista);
    }

    public void setSelectedItem(Object anItem) {
        selecionado = anItem;
    }

    public Object getSelectedItem() {
        return selecionado;
    }

    public int getSize() {
        return lista.size();
    }

    public Object getElementAt(int index) {
        return lista.get(index);
    }

    public void addListDataListener(ListDataListener l) {
    }

    public void removeListDataListener(ListDataListener l) {
    }
}
