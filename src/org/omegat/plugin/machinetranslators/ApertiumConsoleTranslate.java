/*
 * Copyright (C) 2016 Daniel Torregrosa
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as
 * published by the Free Software Foundation; either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA
 * 02111-1307, USA.
 */
package org.omegat.plugin.machinetranslators;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JMenuItem;

import org.omegat.core.Core;
import org.omegat.core.machinetranslators.BaseTranslate;
import org.omegat.util.Language;

/**
 * Main class of ApertiumConsole-OmegaT Provides direct access to a local
 * Apertium installation
 * 
 * @author Daniel Torregrosa
 */
public class ApertiumConsoleTranslate extends BaseTranslate {

	public ApertiumConsoleTranslate() {
		JMenuItem item = new JMenuItem("Apertium console settings...");
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new PreferencesDialog().setVisible(true);
			}
		});
		Core.getMainWindow().getMainMenu().getOptionsMenu().add(item);
		init();
	}

	@Override
	protected String getPreferenceName() {
		return "allow_apertium_console_translate_plugin";
	}

	@Override
	public String getName() {
		return "Apertium (console)";
	}

	@Override
	protected String translate(Language sLang, Language tLang, String text) throws Exception {
		return LocalApertiumTranslate.translateApertiumLocalInstallation(text);
	}

	public List<String> massTranslate(Language sLang, Language tLang, List<String> texts) throws Exception {
		return LocalApertiumTranslate.translateApertiumLocalInstallation(texts);
	}

	private void init() {
		LocalApertiumPreferences.init();
	}

}