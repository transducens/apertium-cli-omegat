package org.omegat.core.machinetranslators;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.omegat.gui.exttrans.IMachineTranslation;
import org.omegat.util.Language;

public abstract class BaseTranslate implements IMachineTranslation, ActionListener {
    public void actionPerformed(ActionEvent e) {}
    public String getName() {return null;}
    public String getTranslation(Language sLang, Language tLang, String text) {return null;}
    abstract protected String getPreferenceName();
    abstract protected String translate(Language sLang, Language tLang, String text) throws Exception;
}
