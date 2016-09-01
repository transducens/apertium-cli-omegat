package org.omegat.plugin.machinetranslators;

import java.awt.BorderLayout;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.omegat.util.Preferences;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;

public class PreferencesDialog extends JDialog {

	private static final long serialVersionUID = 4133572750844334621L;
	private final JPanel contentPanel = new JPanel();
	private JTextField whereIsApertiumTB;
	private JCheckBox showUnknownCB;
	private JComboBox<String> langPairCombo;
	private JTextField workingdirTB;
	private JButton okButton;

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

	public void warn() {
		okButton.setEnabled(false);
		okButton.setToolTipText("Please, use the Update language pairs button and select one.");
		langPairCombo.setModel(new DefaultComboBoxModel<String>());
		langPairCombo.setEnabled(false);
		langPairCombo.setToolTipText("Please, use the Update language pairs button and select one.");
	}
	
	public void clearWarn()
	{
		okButton.setEnabled(true);
		okButton.setToolTipText("");
		langPairCombo.setEnabled(true);
		langPairCombo.setToolTipText("");
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
			whereIsApertiumTB.getDocument().addDocumentListener(new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					warn();
				}

				public void removeUpdate(DocumentEvent e) {
					warn();
				}

				public void insertUpdate(DocumentEvent e) {
					warn();
				}
			});
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
			JPanel panel = new JPanel();
			contentPanel.add(panel, "4, 6, fill, top");
			panel.setLayout(new FormLayout(
					new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow(10)"),
							FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"), },
					new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));
			{
				workingdirTB = new JTextField();
				panel.add(workingdirTB, "2, 2");
				workingdirTB.setColumns(10);
				workingdirTB.getDocument().addDocumentListener(new DocumentListener() {
					public void changedUpdate(DocumentEvent e) {
						warn();
					}

					public void removeUpdate(DocumentEvent e) {
						warn();
					}

					public void insertUpdate(DocumentEvent e) {
						warn();
					}
				});
			}
			{
				JButton button = new JButton("...");
				button.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JFileChooser fd = new JFileChooser();
						fd.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
						if (!"".equals(workingdirTB.getText())) {
							fd.setCurrentDirectory(new File(workingdirTB.getText()));
							System.out.println("PATH " + workingdirTB.getText());
						}
						if (fd.showOpenDialog(
								SwingUtilities.getWindowAncestor(contentPanel)) == JFileChooser.APPROVE_OPTION) {
							System.out.println(
									fd.getSelectedFile().getAbsolutePath() + " " + fd.getCurrentDirectory().getPath());
							workingdirTB.setText(fd.getSelectedFile().getAbsolutePath());
						}
					}
				});
				panel.add(button, "4, 2");
			}
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
				okButton = new JButton("OK");
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
		clearWarn();
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
