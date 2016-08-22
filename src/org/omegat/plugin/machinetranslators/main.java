package org.omegat.plugin.machinetranslators;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.omegat.gui.exttrans.IMachineTranslation;
import org.omegat.util.Language;

public class main {

	public static void main(String[] args) {

		// IMachineTranslation im = new ApertiumConsoleTranslate();
		// try {
		// Method[] m1 = im.getClass().getDeclaredMethods();
		// for (Method m2: m1)
		// {
		// System.out.println(m2);
		// }
		// System.out.println(">>");
		// Method m = im.getClass().getDeclaredMethod("massTranslate",
		// Language.class, Language.class,
		// List.class);
		// System.out.println(m);
		// } catch (NoSuchMethodException | SecurityException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// LocalApertiumTranslate.setLocalApertiumShowUnknown(true);
		// LocalApertiumTranslate.setWhereIsApertium("apertium");
		// LocalApertiumTranslate.setPair("en-es");

		// PreferencesDialog pd = new PreferencesDialog();
		// pd.setVisible(true);
		// for (String s :
		// LocalApertiumTranslate.obtainLanguagesApertiumLocalInstallation())
		// {
		// System.out.println(s);
		// }
		LocalApertiumTranslate.setWhereIsApertium("apertium");
		LocalApertiumTranslate.setLocalApertiumShowUnknown(true);
		LocalApertiumTranslate.setPair("es-en");
		LocalApertiumTranslate.setWorkingDir("/home/eltorre/Desktop/apertium/apertium-en-es/");
//		LocalApertiumTranslate.setWorkingDir("");

		LocalApertiumTranslate.init();
		System.out.println(LocalApertiumTranslate.translateApertiumLocalInstallation("Mi casa"));
		System.out.println(LocalApertiumTranslate.translateApertiumLocalInstallation("Rosa"));
		System.out.println(LocalApertiumTranslate.translateApertiumLocalInstallation("Rosa roja"));
		System.out.println(LocalApertiumTranslate.translateApertiumLocalInstallation("rosa"));
		System.out.println(LocalApertiumTranslate.translateApertiumLocalInstallation("rosa roja"));

		// List<String> in, out;
		//
		// in = new ArrayList<String>();
		// in.add("Mi casa");
		// in.add("Rosa");
		// in.add("Rosa roja");
		// in.add("rosa");
		// in.add("rosa roja");
		// out = LocalApertiumTranslate.translateApertiumLocalInstallation(in);
		//
		// for (String s : out)
		// {
		// System.out.println(s);
		// }

	}

}
