package org.omegat.gui.exttrans;

import org.omegat.util.Language;

public interface IMachineTranslation {
    String getName();
    String getTranslation(Language sLang, Language tLang, String text) throws Exception;
}
