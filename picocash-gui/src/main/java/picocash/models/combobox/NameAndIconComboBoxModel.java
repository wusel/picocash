/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package picocash.models.combobox;

import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import picocash.model.NameAndIconable;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author wusel
 */
public class NameAndIconComboBoxModel extends DefaultComboBoxModel{

    private static final Log log = LogFactory.getLog(NameAndIconComboBoxModel.class);

    private Vector<NameAndIconable> elements;
    
    public NameAndIconComboBoxModel(List elements){
        this.elements = new Vector<NameAndIconable>(elements);
    }

    @Override
    public Object getElementAt(int index) {
        return this.elements.get(index);
    }

    @Override
    public int getSize() {
        return elements.size();
    }

    public Vector<NameAndIconable> getElements() {
        return elements;
    }

    @Override
    public int getIndexOf(Object anObject) {
        return elements.indexOf(anObject);
    }
}
