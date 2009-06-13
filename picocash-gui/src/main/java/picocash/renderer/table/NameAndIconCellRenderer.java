package picocash.renderer.table;

import java.awt.Component;
import java.text.DateFormat;
import java.text.NumberFormat;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import picocash.handler.PicocashIcons;
import picocash.model.NameAndIconable;
import picocash.model.impl.Money;

/**
 *
 * @author wusel
 */
public class NameAndIconCellRenderer extends DefaultTableCellRenderer {

    public static final DateFormat DATEINSTANCE = DateFormat.getDateInstance();

    @Override
    public Component getTableCellRendererComponent(JTable arg0, Object value, boolean arg2, boolean arg3, int arg4, int arg5) {
        JLabel label = (JLabel) super.getTableCellRendererComponent(arg0, value, arg2, arg3, arg4, arg5);

        label.setIcon(PicocashIcons.getIcon(null));
        if (value == null) {
        } else if (value instanceof Long) {
            label.setText(DATEINSTANCE.format(value));
            label.setHorizontalAlignment(JLabel.CENTER);
        } else if (value instanceof Money) {
            label.setText(value.toString());
            label.setHorizontalAlignment(JLabel.TRAILING);
        } else if (value instanceof NameAndIconable) {
            NameAndIconable nameAndIconable = (NameAndIconable) value;
            label.setText(nameAndIconable.getName());
            label.setIcon(PicocashIcons.getIcon(nameAndIconable.getIcon()));
            label.setHorizontalAlignment(JLabel.LEADING);
        } else {
            throw new RuntimeException("unknown Value.class [" + value.getClass() + "]");
        }
        return label;
    }
}
