package org.omegat.plugin.machinetranslators;

import org.omegat.util.Preferences;

public class LocalApertiumPreferences {

	public static String LOCALAPERTIUM_WHEREIS = "localapertium_whereis";
	public static String LOCALAPERTIUM_SHOWUNKNOWN = "localapertium_showunknown";
	public static String LOCALAPERTIUM_WORKINGDIR = "localapertium_workingdir";
	public static String LOCALAPERTIUM_LANG = "localapertium_lang";


	public static void populate() {
		if (!Preferences.existsPreference(LOCALAPERTIUM_WHEREIS))
			Preferences.setPreference(LOCALAPERTIUM_WHEREIS, "apertium");
		if (!Preferences.existsPreference(LOCALAPERTIUM_SHOWUNKNOWN))
			Preferences.setPreference(LOCALAPERTIUM_SHOWUNKNOWN, "False");
		if (!Preferences.existsPreference(LOCALAPERTIUM_LANG))
			Preferences.setPreference(LOCALAPERTIUM_LANG, "es-en");
		if (!Preferences.existsPreference(LOCALAPERTIUM_WORKINGDIR))
			Preferences.setPreference(LOCALAPERTIUM_WORKINGDIR, "");

	}

	public static void init() {
		populate();

		LocalApertiumTranslate.setWhereIsApertium(Preferences.getPreference(LOCALAPERTIUM_WHEREIS));
		LocalApertiumTranslate.setLocalApertiumShowUnknown("True".equals(Preferences.getPreference(LOCALAPERTIUM_SHOWUNKNOWN)));
		LocalApertiumTranslate.setPair(Preferences.getPreference(LOCALAPERTIUM_LANG));
		LocalApertiumTranslate.setWorkingDir(Preferences.getPreference(LOCALAPERTIUM_WORKINGDIR));
	}
}
