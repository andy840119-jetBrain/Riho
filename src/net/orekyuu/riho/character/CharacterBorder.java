package net.orekyuu.riho.character;

import com.intellij.util.ui.JBUI;
import net.orekyuu.riho.topics.RihoReactionNotifier;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CharacterBorder implements Border, RihoReactionNotifier, ActionListener {

    private final CharacterRenderer characterRenderer;
    private final Timer timer;
    private Component component;
    private final int REPAINT_DELAY = 30;

    public CharacterBorder(JComponent component) throws IOException {
        this.component = component;
        timer = new Timer(REPAINT_DELAY, this);
        timer.start();
        characterRenderer = new CharacterRenderer();
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        characterRenderer.render(c, g, x, y, width, height);
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return JBUI.emptyInsets();
    }

    @Override
    public boolean isBorderOpaque() {
        return true;
    }

    @Override
    public void reaction(Reaction reaction) {
        characterRenderer.setReaction(reaction);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        component.repaint(REPAINT_DELAY);
    }

    public void dispose() {
        timer.stop();
    }
}
