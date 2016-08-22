package org.omegat.plugin.machinetranslators;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.omegat.util.Preferences;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PreferencesDialog extends JDialog {

	private static final long serialVersionUID = 4133572750844334621L;
	private final JPanel contentPanel = new JPanel();
	private JTextField whereIsApertiumTB;
	private JCheckBox showUnknownCB;
	private JComboBox<String> langPairCombo;
	private JTextField workingdirTB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			PreferencesDialog dialog = new PreferencesDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public PreferencesDialog() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new FormLayout(
				new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
						FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
				new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
						FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, FormSpecs.RELATED_GAP_ROWSPEC,
						FormSpecs.DEFAULT_ROWSPEC, }));
		{
			JLabel lblLocalApertium = new JLabel("Local Apertium");
			contentPanel.add(lblLocalApertium, "2, 2, right, default");
		}
		{
			whereIsApertiumTB = new JTextField();
			contentPanel.add(whereIsApertiumTB, "4, 2, fill, default");
			whereIsApertiumTB.setColumns(10);
		}
		{
			JLabel lblShowUnknown = new JLabel("Show unknown");
			contentPanel.add(lblShowUnknown, "2, 4, right, default");
		}
		{
			showUnknownCB = new JCheckBox("");
			contentPanel.add(showUnknownCB, "4, 4, right, default");
		}
		{
			JLabel lblWorkingDirectory = new JLabel("Working directory");
			contentPanel.add(lblWorkingDirectory, "2, 6, right, default");
		}
		{
			workingdirTB = new JTextField();
			contentPanel.add(workingdirTB, "4, 6, fill, default");
			workingdirTB.setColumns(10);
		}
		{
			JLabel lblLanguagePair = new JLabel("Language pair");
			contentPanel.add(lblLanguagePair, "2, 8, right, default");
		}
		{
			langPairCombo = new JComboBox<String>();
			contentPanel.add(langPairCombo, "4, 8, fill, default");
		}
		{
			JButton btnUpdateLanguagePairs = new JButton("Update language pairs");
			btnUpdateLanguagePairs.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					initLangs();
				}
			});
			contentPanel.add(btnUpdateLanguagePairs, "4, 10");
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						save();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						exit();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		init();
	}

	void init() {
		whereIsApertiumTB.setText(Preferences.getPreference(LocalApertiumPreferences.LOCALAPERTIUM_WHEREIS));
		showUnknownCB.setSelected(
				"True".equals(Preferences.getPreference(LocalApertiumPreferences.LOCALAPERTIUM_SHOWUNKNOWN)));
		workingdirTB.setText(Preferences.getPreference(LocalApertiumPreferences.LOCALAPERTIUM_WORKINGDIR));
		initLangs();
	}

	private void initLangs() {
		LocalApertiumTranslate.setWhereIsApertium(whereIsApertiumTB.getText());
		LocalApertiumTranslate.setWorkingDir(workingdirTB.getText());
		List<String> langs = LocalApertiumTranslate.obtainLanguagesApertiumLocalInstallation();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		for (String s : langs)
			model.addElement(s);
		langPairCombo.setModel(model);

		String selected = Preferences.getPreference(LocalApertiumPreferences.LOCALAPERTIUM_LANG);
		int index = model.getIndexOf(selected);
		if (index > 0)
			langPairCombo.setSelectedIndex(index);
	}

	void save() {
		Preferences.setPreference(LocalApertiumPreferences.LOCALAPERTIUM_WHEREIS, whereIsApertiumTB.getText());
		Preferences.setPreference(LocalApertiumPreferences.LOCALAPERTIUM_SHOWUNKNOWN,
				((Boolean) showUnknownCB.isSelected()).toString());
		Preferences.setPreference(LocalApertiumPreferences.LOCALAPERTIUM_LANG,
				langPairCombo.getSelectedItem().toString());
		Preferences.setPreference(LocalApertiumPreferences.LOCALAPERTIUM_WORKINGDIR, workingdirTB.getText());
		exit();
	}

	void restore() {
		LocalApertiumPreferences.init();
	}

	void exit() {
		restore();
		this.dispose();
	}

}
