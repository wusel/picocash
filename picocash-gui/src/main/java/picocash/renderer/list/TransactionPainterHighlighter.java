package picocash.renderer.list;

import java.awt.Component;
import org.jdesktop.swingx.JXPanel;
import org.jdesktop.swingx.decorator.AbstractHighlighter;
import org.jdesktop.swingx.decorator.ComponentAdapter;
import org.jdesktop.swingx.decorator.HighlightPredicate;
import org.jdesktop.swingx.painter.Painter;

public class TransactionPainterHighlighter extends AbstractHighlighter {

    private Painter painter;

    public TransactionPainterHighlighter(HighlightPredicate highlightPredicate, Painter painter) {
        super(highlightPredicate);
        this.painter = painter;
    }

    @Override
    protected Component doHighlight(Component component, ComponentAdapter adapter) {
        ((JXPanel) component).setBackgroundPainter(painter);
        return component;
    }
}
