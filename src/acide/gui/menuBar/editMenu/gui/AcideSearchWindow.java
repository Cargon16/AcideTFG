/*
 * ACIDE - A Configurable IDE
 * Official web site: http://acide.sourceforge.net
 * 
 * Copyright (C) 2007-2014  
 * Authors:
 * 		- Fernando S�enz P�rez (Team Director).
 *      - Version from 0.1 to 0.6:
 *      	- Diego Cardiel Freire.
 *			- Juan Jos� Ortiz S�nchez.
 *          - Delf�n Rup�rez Ca�as.
 *      - Version 0.7:
 *          - Miguel Mart�n L�zaro.
 *      - Version 0.8:
 *      	- Javier Salcedo G�mez.
 *      - Version from 0.9 to 0.11:
 *      	- Pablo Guti�rrez Garc�a-Pardo.
 *      	- Elena Tejeiro P�rez de �greda.
 *      	- Andr�s Vicente del Cura.
 *      -Version from 0.12 to 0.16
 *      	- Sem�ramis Guti�rrez Quintana
 *      	- Juan Jes�s Marqu�s Ortiz
 *      	- Fernando Ord�s Lorente
 *      - Version 0.17
 *      	- Sergio Dom�nguez Fuentes
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package acide.gui.menuBar.editMenu.gui;

import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import acide.gui.mainWindow.AcideMainWindow;
import acide.gui.menuBar.editMenu.utils.AcideSearchDirection;
import acide.gui.menuBar.editMenu.utils.AcideSearchEngine;
import acide.language.AcideLanguageManager;
import acide.log.AcideLog;

import com.jidesoft.swing.AutoCompletionComboBox;

/**
 * ACIDE - A Configurable IDE search window.
 * 
 * @version 0.11
 * @see JFrame
 */
public class AcideSearchWindow extends JFrame {

	/**
	 * Para indicar si ya se ha mostrado el di�logo avisando del fin de b�squeda
	 */
	private boolean _finishedF3;

	/**
	 * ACIDE - A Configurable IDE search window search history
	 */
	private static Vector<String> _list;

	/**
	 * ACIDE - A Configurable IDE search window serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ACIDE - A Configurable IDE search/replace window image icon.
	 */
	private static final ImageIcon ICON = new ImageIcon(
			"./resources/images/icon.png");
	/**
	 * ACIDE - A Configurable IDE search window unique class instance.
	 */
	private static AcideSearchWindow _instance;
	/**
	 * ACIDE - A Configurable IDE search window button panel.
	 */
	private JPanel _buttonPanel;

	private JProgressBar barDo;
	/**
	 * ACIDE - A Configurable IDE search window direction panel.
	 */
	private JPanel _directionPanel;
	/**
	 * ACIDE - A Configurable IDE search window option panel.
	 */
	private JPanel _optionPanel;
	/**
	 * ACIDE - A Configurable IDE search window scope panel.
	 */
	private JPanel _scopePanel;
	/**
	 * ACIDE - A Configurable IDE search window search label.
	 */
	private JLabel _searchLabel;
	/**
	 * ACIDE - A Configurable IDE search window recent searchs label.
	 */
	private JLabel _recentSearchesLabel;
	/**
	 * ACIDE - A Configurable IDE search window search button.
	 */
	private JButton _searchButton;
	/**
	 * ACIDE - A Configurable IDE search window cancel button.
	 */
	private JButton _cancelButton;
	/**
	 * ACIDE - A Configurable IDE search window search text field.
	 */
	private JTextField _searchTextField;
	/**
	 * ACIDE - A Configurable IDE search window case sensitive check box.
	 */
	private JCheckBox _caseSensitiveCheckBox;
	/**
	 * ACIDE - A Configurable IDE search window regular expressions check box.
	 */
	private JCheckBox _regularExpressionsCheckBox;
	/**
	 * ACIDE - A Configurable IDE search window complete words check box.
	 */
	private JCheckBox _completeWordsCheckBox;
	/**
	 * ACIDE - A Configurable IDE search window forward radio button.
	 */
	private JRadioButton _forwardRadioButton;
	/**
	 * ACIDE - A Configurable IDE search window backward radio button.
	 */
	private JRadioButton _backwardRadioButton;
	/**
	 * ACIDE - A Configurable IDE search window both directions radio button.
	 */
	private JRadioButton _bothDirectionsRadioButton;
	/**
	 * ACIDE - A Configurable IDE search window both directions backward radio button.
	 */
	private JRadioButton _bothDirectionsBackwardsRadioButton;
	/**
	 * ACIDE - A Configurable IDE search window current document radio button.
	 */
	private JRadioButton _currentDocumentRadioButton;
	/**
	 * ACIDE - A Configurable IDE search window all documents radio button.
	 */
	private JRadioButton _allDocumentsRadioButton;
	/**
	 * ACIDE - A Configurable IDE search window selected text radio button.
	 */
	private JRadioButton _selectedTextRadioButton;
	/**
	 * ACIDE - A Configurable IDE search window search result position.
	 */
	private int _searchResultPosition;
	/**
	 * ACIDE - A Configurable IDE search window selected text for the selected
	 * text search.
	 */
	private String _selectedText;
	/**
	 * ACIDE - A Configurable IDE search window initial position for the
	 * selected text search.
	 */
	private int _intialPosition;
	/**
	 * ACIDE - A Configurable IDE search window final position for the selected
	 * text search.
	 */
	private int _finalPosition;
	/**
	 * ACIDE - A Configurable IDE search window all documents current index for
	 * the all documents search.
	 */
	private static int _allDocumentsCurrentIndex;
	/**
	 * ACIDE - A Configurable IDE search window text to search
	 */
	private static String _textToSearch;
	/**
	 * ACIDE - A Configurable IDE search window is end flag for the all
	 * documents search.
	 */
	private static boolean _isEnd = false;
	/**
	 * ACIDE - A Configurable IDE search window current position for the all
	 * documents search.
	 */
	private int _currentPosition;
	/**
	 * ACIDE - A Configurable IDE search window current document index for the
	 * all documents search.
	 */
	private int _currentDocumentIndex;
	/**
	 * ACIDE - A Configurable IDE search window all document counter for the all
	 * documents search.
	 */
	private int _allDocumentsCounter;
	/**
	 * ACIDE - A Configurable IDE search window is cycle for the all documents
	 * search.
	 */
	private static boolean _isCycle;
	/**
	 * ACIDE - A Configurable IDE search window is first for the all documents
	 * search.
	 */
	private static boolean _isFirst;

	private static int _valorBar;
	/**
	 * ACIDE - A Configurable IDE search window search engine.
	 */
	private AcideSearchEngine _searchEngine;
	/**
	 * ACIDE - A Configurable IDE search window comboBox for recent
	 * searches.
	 */
	private AutoCompletionComboBox _comboBox;
	/**
	 * ACIDE - A Configurable IDE search window comboBox for special characters
	 * .
	 */
	private JComboBox _comboBoxSpecial;

	/**
	 * Returns the ACIDE - A Configurable IDE search window unique class
	 * instance.
	 * 
	 * @return the ACIDE - A Configurable IDE search window unique class
	 *         instance.
	 */
	public static AcideSearchWindow getInstance() {
		if (_instance == null)
			_instance = new AcideSearchWindow();
		return _instance;
	}

	/**
	 * Initializes the ACIDE - A Configurable IDE search window.
	 */
	public void initialize() {
		_instance = null;
		_list = getList();
	}

	/**
	 * Creates a new ACIDE - A Configurable IDE search window
	 */
	public AcideSearchWindow() {

		_isCycle = false;
		_currentPosition = -2;
		_currentDocumentIndex = -1;
		_intialPosition = -1;
		_selectedText = null;
		_searchResultPosition = -1;
		_isFirst = true;
		_searchEngine = AcideSearchEngine.getInstance();
		_finishedF3 = false;

		// Initializes the components
		buildComponents();

		// Adds the components to the window
		addComponents();

		// Sets the components' listeners
		setListeners();

		// Sets the window configuration
		setWindowConfiguration();
	}



	public Vector<String> getList() {
		if (_list == null){
			_list = new Vector<String>();
		}
		return _list;
	}


	public void setList(Vector<String> _list) {
		AcideSearchWindow._list = _list;
	}

	public void setValueBar(int i){
		barDo.setValue(i);
	}

	public void setProgress(int i){
		setValueBar(i); 
		barDo.repaint(); 
	}

	/**
	 * Builds the ACIDE - A Configurable IDE search/replace window components.
	 */
	private void buildComponents() {

		// Builds the search/replace text fields
		buildSearchReplaceTextFields();

		// Builds the direction panel
		buildDirectionPanel();

		// Builds the options panel
		buildOptionsPanel();

		// Builds the scope panel
		buildScopePanel();

		barDo = new JProgressBar(0, 100);

		String[] comboBoxStrings = new String[3];

		comboBoxStrings[0] = AcideLanguageManager.getInstance().getLabels().getString("s2216");

		comboBoxStrings[1] = AcideLanguageManager.getInstance().getLabels().getString("s2217");

		comboBoxStrings[2] = AcideLanguageManager.getInstance().getLabels().getString("s2218");

		_comboBoxSpecial = new JComboBox(comboBoxStrings);

		_comboBoxSpecial.setSelectedIndex(0);


		// Builds the button panel
		buildButtonPanel();
	}

	public boolean isAcabadoF3() {
		return _finishedF3;
	}

	public void setAcabadoF3(boolean acabadoF3) {
		this._finishedF3 = acabadoF3;
	}

	/**
	 * Sets the ACIDE - A Configurable IDE search window configuration.
	 */
	private void setWindowConfiguration() {

		// Sets the search window title
		setTitle(AcideLanguageManager.getInstance().getLabels()
				.getString("s556"));

		// Sets the window icon image
		setIconImage(ICON.getImage());

		// The window is not resizable
		setResizable(false);

		// Packs the window components
		pack();

		// Always at the front
		setAlwaysOnTop(true);

		// Centers the window
		setLocationRelativeTo(null);
	}

	/**
	 * Adds the components to the window.
	 */
	public void addComponents() {

		// Sets the layout
		setLayout(new GridBagLayout());

		// Adds the components to the window with the layout
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 1;

		// Adds the search label to the window
		add(_searchLabel, constraints);

//		constraints.gridx = 1;
//		constraints.fill = GridBagConstraints.HORIZONTAL;
//		constraints.anchor = GridBagConstraints.EAST;
//		add(_comboBoxSpecial, constraints);


		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridy = 1;
		constraints.gridx = 0;
		constraints.gridwidth = 2;

		// Adds the search text field to the window
		add(_searchTextField, constraints);

		constraints.gridy = 2;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.NONE;
		constraints.gridwidth = 1;

		//Adds the recent searches label to the window
		add(_recentSearchesLabel, constraints);
		
		constraints.gridx = 1;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.EAST;
		add(_comboBoxSpecial, constraints);

		constraints.gridy = 3;
		constraints.gridx = 0;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.gridwidth = 2;
		// Adds the recent searches combo box to the window
		add(_comboBox, constraints);

		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.gridy = 4;
		constraints.gridx = 0;
		constraints.gridwidth = 2;

		// Adds the option panel to the window
		add(_optionPanel, constraints);

		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 1;

		// Adds the scope panel to the window
		add(_scopePanel, constraints);

		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.anchor = GridBagConstraints.EAST;
		constraints.gridx = 1;
		constraints.gridy = 5;

		// Adds the direction panel to the window
		add(_directionPanel, constraints);

		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 2;
		constraints.anchor = GridBagConstraints.WEST;


		add(barDo, constraints);

		constraints.anchor = GridBagConstraints.LINE_END;
		constraints.gridx = 0;
		constraints.gridy = 7;
		constraints.gridwidth = 2;

		// Adds the button panel to the window
		add(_buttonPanel, constraints);

		// Validates the changes in the search/replace window
		validate();
	}

	/**
	 * Creates the search labels and text fields.
	 */
	private void buildSearchReplaceTextFields() {

		// Creates the search label
		_searchLabel = new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s557"), JLabel.CENTER);

		// Creates the search text field
		//_searchTextField = new JSuggestField(this, getList());
		_searchTextField = new JTextField();

		_comboBox = new AutoCompletionComboBox(getList());

		// Creates the recent searches label
		_recentSearchesLabel = new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s2168"), JLabel.CENTER);

	}

	/**
	 * Creates the button panel and its components.
	 */
	private void buildButtonPanel() {

		// Creates the button panel
		_buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		// Creates the search button
		_searchButton = new JButton(AcideLanguageManager.getInstance()
				.getLabels().getString("s556"));

		// Creates the cancel button
		_cancelButton = new JButton(AcideLanguageManager.getInstance()
				.getLabels().getString("s42"));

		// Adds the search button to the button panel
		_buttonPanel.add(_searchButton);

		// Adds the cancel button to the button panel
		_buttonPanel.add(_cancelButton);

	}

	/**
	 * Creates the scope panel and its components.
	 */
	private void buildScopePanel() {

		// Creates the scope panel
		_scopePanel = new JPanel(new GridLayout(0, 1));

		// Sets the scope panel border
		_scopePanel.setBorder(BorderFactory.createTitledBorder(null,
				AcideLanguageManager.getInstance().getLabels()
				.getString("s563"), TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION));

		// Creates the current document radio button
		_currentDocumentRadioButton = new JRadioButton(AcideLanguageManager
				.getInstance().getLabels().getString("s565"), true);

		// Creates the all documents radio button
		_allDocumentsRadioButton = new JRadioButton(AcideLanguageManager
				.getInstance().getLabels().getString("s566"), false);

		// Creates the selected text radio button
		_selectedTextRadioButton = new JRadioButton(AcideLanguageManager
				.getInstance().getLabels().getString("s564"), false);

		// Adds the selected text radio button to the scope panel
		_scopePanel.add(_selectedTextRadioButton);

		// Adds the current document radio button to the scope panel
		_scopePanel.add(_currentDocumentRadioButton);

		// Adds the all documents radio button to the scope panel
		_scopePanel.add(_allDocumentsRadioButton);

		// Creates a button group
		ButtonGroup buttonGroup = new ButtonGroup();

		// Adds the selected text radio button to the button group
		buttonGroup.add(_selectedTextRadioButton);

		// Adds the current document radio button to the button group
		buttonGroup.add(_currentDocumentRadioButton);

		// Adds the all documents radio button to the button group
		buttonGroup.add(_allDocumentsRadioButton);
	}

	/**
	 * Creates the options panel and its components.
	 */
	private void buildOptionsPanel() {

		// Creates the option panel
		_optionPanel = new JPanel(new GridBagLayout());

		// Sets the option panel border
		_optionPanel.setBorder(BorderFactory.createTitledBorder(null,
				AcideLanguageManager.getInstance().getLabels()
				.getString("s559"), TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION));

		// Creates the case sensitive check box
		_caseSensitiveCheckBox = new JCheckBox(AcideLanguageManager
				.getInstance().getLabels().getString("s560"), false);

		// Creates the regular expressions check box
		_regularExpressionsCheckBox = new JCheckBox(AcideLanguageManager
				.getInstance().getLabels().getString("s561"), false);

		// Creates the complete words check box
		_completeWordsCheckBox = new JCheckBox(AcideLanguageManager
				.getInstance().getLabels().getString("s562"), false);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill = GridBagConstraints.BOTH;
		constraints.gridx = 0;
		constraints.gridy = 0;

		// Adds the case sensitive check box to the option panel
		_optionPanel.add(_caseSensitiveCheckBox, constraints);

		constraints.gridy = 1;

		// Adds the regular expressions check box to the option panel
		_optionPanel.add(_regularExpressionsCheckBox, constraints);

		constraints.gridy = 2;

		// Adds the complete words check box to the option panel
		_optionPanel.add(_completeWordsCheckBox, constraints);
	}

	/**
	 * Creates the direction panel and its components.
	 */
	private void buildDirectionPanel() {

		// Creates the direction panel
		_directionPanel = new JPanel(new GridLayout(0, 1));

		// Sets the direction panel border
		_directionPanel.setBorder(BorderFactory.createTitledBorder(null,
				AcideLanguageManager.getInstance().getLabels()
				.getString("s567"), TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION));

		// Creates the forward radio button
		_forwardRadioButton = new JRadioButton(AcideLanguageManager
				.getInstance().getLabels().getString("s568"), true);

		// Creates the backward radio button
		_backwardRadioButton = new JRadioButton(AcideLanguageManager
				.getInstance().getLabels().getString("s569"), false);

		// Creates the both directions radio button
		_bothDirectionsRadioButton = new JRadioButton(AcideLanguageManager
				.getInstance().getLabels().getString("s570"), false);

		// Creates the both backwards directions radio button
		_bothDirectionsBackwardsRadioButton = new JRadioButton(AcideLanguageManager
				.getInstance().getLabels().getString("s570"), false);

		// Adds the forward radio button to the direction panel
		_directionPanel.add(_forwardRadioButton);

		// Adds the backward radio button to the direction panel
		_directionPanel.add(_backwardRadioButton);

		// Adds the both directions radio button to the direction panel
		_directionPanel.add(_bothDirectionsRadioButton);

		// Creates a button group
		ButtonGroup buttonGroup = new ButtonGroup();

		// Adds the forward radio button to the button group
		buttonGroup.add(_forwardRadioButton);

		// Adds the backward radio button to the button group
		buttonGroup.add(_backwardRadioButton);

		// Adds the both directions radio button to the button group
		buttonGroup.add(_bothDirectionsRadioButton);

		// Adds the both backwards directions radio button to the button group
		buttonGroup.add(_bothDirectionsBackwardsRadioButton);
	}

	/**
	 * Sets the listeners of the window components.
	 */
	@SuppressWarnings("rawtypes")
	private void setListeners() {

		// Sets the search button action listener
		_searchButton.addActionListener(new SearchButtonAction());

		// When the enter key is pressed the executes the search button action
		_searchTextField.registerKeyboardAction(new SearchButtonAction(),
				"EnterKey", KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		// Sets the cancel button action listener
		_cancelButton.addActionListener(new CancelButtonAction());

		// Puts the escape key in the input map of the window
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false),
				"EscapeKey");

		// Puts the escape key in the action map of the window
		getRootPane().getActionMap().put("EscapeKey", new EscapeKeyAction());

		_comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_searchTextField.setText((String)((JComboBox)e.getSource()).getSelectedItem());
			}
		});

		_comboBoxSpecial.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String aux = (String)((JComboBox) e.getSource()).getSelectedItem();
				String special = AcideLanguageManager
						.getInstance().getLabels().getString("s2216");
				String t = AcideLanguageManager
						.getInstance().getLabels().getString("s2217");
				String p = AcideLanguageManager
						.getInstance().getLabels().getString("s2218");
				if (!aux.equals(special)){
					if (aux.equals(t)){
						_searchTextField.setText(_searchTextField.getText() + "^t");
						_searchTextField.requestFocus();
					}
					if (aux.equals(p)){
						_searchTextField.setText(_searchTextField.getText() + "^p");
						_searchTextField.requestFocus();
					}
					_comboBoxSpecial.setSelectedIndex(0);
				}

				
			}
		});
	}

	/**
	 * Initializes the ACIDE - A Configurable IDE search window variables.
	 */
	public void initializeVariables() {

		// Sets the current position as -2
		_currentPosition = -2;

		// Sets the is cycle as false
		_isCycle = false;

		// Sets the is end as false
		_isEnd = false;

		// Sets search engine temporal position as -2
		_searchEngine.setTemporalPosition(-2);

		// Sets the search engine is cycle as false
		_searchEngine.setIsCycle(false);

		// Sets the is cycle as false
		_isCycle = false;

		// Sets the selected text as null
		_selectedText = null;

		// Sets the is first as true
		_isFirst = true;

		_valorBar = 0;

		_finishedF3 = false;

		setProgress(0);
		validate();
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window search button.
	 * 
	 * @return the ACIDE - A Configurable IDE search window search button.
	 */
	public JButton getSearchButton() {
		return _searchButton;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window is cycle flag for
	 * the all documents search.
	 * 
	 * @return the ACIDE - A Configurable IDE search window is cycle flag for
	 *         the all documents search.
	 */
	public boolean getIsCycle() {
		return _isCycle;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE search window is cycle
	 * flag for the all documents search.
	 * 
	 * @param isCycle
	 *            new value to set.
	 */
	public void setIsCycle(boolean isCycle) {
		_isCycle = isCycle;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window is end flag for the
	 * all documents search.
	 * 
	 * @return the ACIDE - A Configurable IDE search window is end flag for the
	 *         all documents search.
	 */
	public boolean getIsEnd() {
		return _isEnd;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE search window is end
	 * flag for the all documents search.
	 * 
	 * @param isEnd
	 *            new value to set.
	 */
	public void setIsEnd(boolean isEnd) {
		_isEnd = isEnd;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE search window is first
	 * flag for the all documents search.
	 * 
	 * @param isFirst
	 *            new value to set.
	 */
	public static void setIsFirst(boolean isFirst) {
		_isFirst = isFirst;
	}


	/**
	 * Returns the ACIDE - A Configurable IDE search window current position for
	 * the all documents search.
	 * 
	 * @return the ACIDE - A Configurable IDE search window current position for
	 *         the all documents search.
	 */
	public int getCurrentPosition() {
		return _currentPosition;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE search window current
	 * position for the all documents search.
	 * 
	 * @param currentPosition
	 *            new value to set.
	 */
	public void setCurrentPosition(int currentPosition) {
		_currentPosition = currentPosition;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window current document
	 * radio button.
	 * 
	 * @return the ACIDE - A Configurable IDE search window current document
	 *         radio button.
	 */
	public JRadioButton getCurrentDocumentRadioButton() {
		return _currentDocumentRadioButton;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE search window current
	 * document radio button state.
	 * 
	 * @param isSelected
	 *            new value to set.
	 */
	public void setCurrentDocumentRadioButton(boolean isSelected) {
		_currentDocumentRadioButton.setSelected(isSelected);
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window both directions
	 * radio button.
	 * 
	 * @return the ACIDE - A Configurable IDE search window both directions
	 *         radio button.
	 */
	public JRadioButton getBothDirectionsRadioButton() {
		return _bothDirectionsRadioButton;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window both backwards directions
	 * radio button.
	 * 
	 * @return the ACIDE - A Configurable IDE search window both backwards directions
	 *         radio button.
	 */
	public JRadioButton getBothDirectionsBackwardsRadioButton() {
		return _bothDirectionsBackwardsRadioButton;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window selected text radio
	 * button.
	 * 
	 * @return the ACIDE - A Configurable IDE search window selected text radio
	 *         button.
	 */
	public JRadioButton getSelectedTextRadioButton() {
		return _selectedTextRadioButton;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window search text field.
	 * 
	 * @return the ACIDE - A Configurable IDE search window search text field.
	 */
	public JTextField getSearchTextField() {
		return _searchTextField;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE search window forward
	 * radio button state.
	 * 
	 * @param isSelected
	 *            new value to set.
	 */
	public void setForwardRadioButton(boolean isSelected) {
		_forwardRadioButton.setSelected(isSelected);
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window selected text.
	 * 
	 * @return the ACIDE - A Configurable IDE search window selected text.
	 */
	public String getSelectedText() {
		return _selectedText;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE search window selected
	 * text.
	 * 
	 * @param selectedText
	 *            new value to set.
	 */
	public void setSelectedText(String selectedText) {
		_selectedText = selectedText;
	}

	/**
	 * Sets the ACIDE - A Configurable IDE search window always on the front.
	 * 
	 * @param isOnTop
	 *            new value to set.
	 */
	public void setOnTop(boolean isOnTop) {
		setAlwaysOnTop(isOnTop);
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window search engine.
	 * 
	 * @return the ACIDE - A Configurable IDE search window search engine.
	 */
	public AcideSearchEngine getSearchEngine() {
		return _searchEngine;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE search window search
	 * engine.
	 * 
	 * @param searchEngine
	 *            new value to set.
	 */
	public void setSearchEngine(AcideSearchEngine searchEngine) {
		_searchEngine = searchEngine;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window backward radio
	 * button.
	 * 
	 * @return the ACIDE - A Configurable IDE search window backward radio
	 *         button.
	 */
	public JRadioButton getBackwardRadioButton() {
		return _backwardRadioButton;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE search window forward radio
	 * button.
	 * 
	 * @return the ACIDE - A Configurable IDE search window forward radio
	 *         button.
	 */
	public JRadioButton getForwardRadioButton() {
		return _forwardRadioButton;
	}

	public void searchAction(){

		// Puts the wait cursor
		AcideSearchWindow.getInstance().setCursor(
				Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		AcideMainWindow.getInstance().setCursor(
				Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));

		_textToSearch = _searchTextField.getText();

		if (!getList().contains(_textToSearch)){
			getList().add(_textToSearch);
		}

		_textToSearch = _textToSearch.replaceAll("\\^p","\n");
		_textToSearch = _textToSearch.replaceAll("\\^t", "\t");

		// Gets the search direction
		AcideSearchDirection direction = AcideSearchDirection.FORWARD;
		if (_forwardRadioButton.isSelected())
			direction = AcideSearchDirection.FORWARD;
		if (_backwardRadioButton.isSelected())
			direction = AcideSearchDirection.BACKWARD;
		if (_bothDirectionsRadioButton.isSelected())
			direction = AcideSearchDirection.BOTH;
		if (_bothDirectionsBackwardsRadioButton.isSelected())
			direction = AcideSearchDirection.BOTHBACK;

		// If the complete words check box is selected
		if (_completeWordsCheckBox.isSelected())

			// Sets the regular expressions check box as not
			// selected
			_regularExpressionsCheckBox.setSelected(false);

		// If the search text field is empty
		if (_textToSearch.equals("")) {

			AcideSearchWindow.getInstance().setOnTop(false);

			// Displays a message
			JOptionPane.showMessageDialog(null, AcideLanguageManager
					.getInstance().getLabels().getString("s585"));

			AcideSearchWindow.getInstance().setOnTop(true);

			// Updates the status message in the status bar in the ACIDE - A
			// Configurable main window
			AcideMainWindow
			.getInstance()
			.getStatusBar()
			.setStatusMessage(
					AcideLanguageManager.getInstance().getLabels()
					.getString("s585"));
		}

		// Gets the selected file editor panel index
		int selectedFileEditorPanelIndex = AcideMainWindow.getInstance()
				.getFileEditorManager().getSelectedFileEditorPanelIndex();

		AcideMainWindow.getInstance().getFileEditorManager()
		.getFileEditorPanelAt(selectedFileEditorPanelIndex)
		.getActiveTextEditionArea().requestFocusInWindow();

		// If it is current document
		if (_currentDocumentRadioButton.isSelected()) {
			//TODO B�squeda en currentDocument
			_allDocumentsCurrentIndex = -1;
			_allDocumentsCounter = 0;
			_searchResultPosition = -1;
			_selectedText = null;

			// Gets the selected file editor panel index
			selectedFileEditorPanelIndex = AcideMainWindow.getInstance()
					.getFileEditorManager()
					.getSelectedFileEditorPanelIndex();

			// Brings the main window to foreground
			//AcideMainWindow.getInstance().setAlwaysOnTop(true);

			// Updates the status message in the status bar in the
			// ACIDE - A Configurable IDE main window
			AcideMainWindow
			.getInstance()
			.getStatusBar()
			.setStatusMessage(
					AcideLanguageManager.getInstance()
					.getLabels().getString("s2144")
					+ " "
					+ AcideMainWindow.getInstance().getFileEditorManager()
					.getTabbedPane().getTitleAt(selectedFileEditorPanelIndex));

			// Brings the main window to foreground
			//AcideMainWindow.getInstance().setAlwaysOnTop(false);

			/*AcideSearchInformationWindow.getInstance().setLabel(AcideLanguageManager.getInstance()
									.getLabels().getString("s2144")
									+ " "
									+ AcideMainWindow.getInstance().getFileEditorManager()
									.getTabbedPane().getTitleAt(selectedFileEditorPanelIndex) + "...");
			AcideSearchInformationWindow.getInstance().setAlwaysOnTop(true);*/

			// The result position is the caret position
			_searchResultPosition = AcideMainWindow.getInstance()
					.getFileEditorManager()
					.getFileEditorPanelAt(selectedFileEditorPanelIndex)
					.getActiveTextEditionArea().getCaretPosition();

			// If it is backwards or both backwards
			if ((direction == AcideSearchDirection.BACKWARD) || (direction == AcideSearchDirection.BOTHBACK))

				// The result position is the selection start
				_searchResultPosition = AcideMainWindow.getInstance()
				.getFileEditorManager()
				.getFileEditorPanelAt(selectedFileEditorPanelIndex)
				.getActiveTextEditionArea().getSelectionStart();

			// Gets the selected file editor panel index
			selectedFileEditorPanelIndex = AcideMainWindow.getInstance()
					.getFileEditorManager()
					.getSelectedFileEditorPanelIndex();

			// Performs the search
			_searchResultPosition = _searchEngine.search(
					_searchResultPosition,
					_textToSearch,
					AcideMainWindow
					.getInstance()
					.getFileEditorManager()
					.getFileEditorPanelAt(
							selectedFileEditorPanelIndex)
							.getTextEditionAreaContent(),
							_caseSensitiveCheckBox.isSelected(),
							_regularExpressionsCheckBox.isSelected(),
							_completeWordsCheckBox.isSelected(), direction);

			//AcideSearchInformationWindow.getInstance().setVisible(false);


			// If the regular expression is wrong
			if (_searchResultPosition == -2){

				// Puts the default cursor
				AcideSearchWindow.getInstance().setCursor(
						Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

				AcideMainWindow.getInstance().setCursor(
						Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

				return;
			}

			// If it found something out
			if (_searchResultPosition != -1) {

				AcideSearchWindow.getInstance()
				.setProgress(100);
				validate();

				_finishedF3 = false;

				// If regular expressions
				if (_regularExpressionsCheckBox.isSelected()) {

					// Shows the search in the file editor
					AcideMainWindow
					.getInstance()
					.getFileEditorManager()
					.getFileEditorPanelAt(
							AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.getSelectedFileEditorPanelIndex())
							.selectText(
									_searchResultPosition,
									_searchEngine.getRegularExpresion()
									.length());

					// Updates the log
					AcideLog.getLog().info(
							AcideLanguageManager.getInstance().getLabels()
							.getString("s577")
							+ " "
							+ _searchEngine.getRegularExpresion()
							+ " "
							+ AcideLanguageManager.getInstance()
							.getLabels().getString("s574"));

					// Updates the status message in the status bar in the
					// ACIDE - A Configurable IDE main window
					AcideMainWindow
					.getInstance()
					.getStatusBar()
					.setStatusMessage(
							AcideLanguageManager.getInstance()
							.getLabels().getString("s577")
							+ " "
							+ _searchEngine
							.getRegularExpresion()
							+ " "
							+ AcideLanguageManager
							.getInstance()
							.getLabels()
							.getString("s574"));
				} else {
					// Shows the search in the file editor
					AcideMainWindow
					.getInstance()
					.getFileEditorManager()
					.getFileEditorPanelAt(
							AcideMainWindow.getInstance()
							.getFileEditorManager()
							.getSelectedFileEditorPanelIndex())
							.selectText(_searchResultPosition,
									_textToSearch.length());

					// Updates the log
					AcideLog.getLog().info(
							AcideLanguageManager.getInstance().getLabels()
							.getString("s583")
							+ " "
							+ _textToSearch
							+ " "
							+ AcideLanguageManager.getInstance()
							.getLabels().getString("s574"));

					// Updates the status message in the status bar in the ACIDE
					// - A Configurable main window
					AcideMainWindow
					.getInstance()
					.getStatusBar()
					.setStatusMessage(
							AcideLanguageManager.getInstance()
							.getLabels().getString("s583")
							+ " "
							+ _textToSearch
							+ " "
							+ AcideLanguageManager
							.getInstance().getLabels()
							.getString("s574"));
				}

			}

			else {

				AcideSearchWindow.getInstance()
				.setProgress(100);
				validate();

				if (!_finishedF3){
					_finishedF3 = true;
					// Updates the log
					AcideLog.getLog().info(
							AcideLanguageManager.getInstance().getLabels()
							.getString("s573"));

					AcideSearchWindow.getInstance().setOnTop(false);

					// Displays a message
					JOptionPane.showMessageDialog(null, AcideLanguageManager
							.getInstance().getLabels().getString("s573"));

					AcideSearchWindow.getInstance().setOnTop(true);

					// Updates the status message in the status bar in the ACIDE
					// - A Configurable IDE main window
					AcideMainWindow
					.getInstance()
					.getStatusBar()
					.setStatusMessage(
							AcideLanguageManager.getInstance()
							.getLabels().getString("s573"));
				}
			}
		}
		// Selected text search
		if (_selectedTextRadioButton.isSelected()) {

			_allDocumentsCounter = 0;
			_allDocumentsCurrentIndex = -1;

			// Gets the selected file editor panel index
			selectedFileEditorPanelIndex = AcideMainWindow.getInstance()
					.getFileEditorManager()
					.getSelectedFileEditorPanelIndex();

			// Brings the main window to foreground
			//AcideMainWindow.getInstance().setAlwaysOnTop(true);

			// Updates the status message in the status bar in the
			// ACIDE - A Configurable IDE main window
			AcideMainWindow
			.getInstance()
			.getStatusBar()
			.setStatusMessage(
					AcideLanguageManager.getInstance()
					.getLabels().getString("s2145")
					+ " "
					+ AcideMainWindow.getInstance().getFileEditorManager()
					.getTabbedPane().getTitleAt(selectedFileEditorPanelIndex));

			// Brings the main window to foreground
			//AcideMainWindow.getInstance().setAlwaysOnTop(false);

			/*AcideSearchInformationWindow.getInstance().setLabel(
					AcideLanguageManager.getInstance()
					.getLabels().getString("s2145")
					+ " "
					+ AcideMainWindow.getInstance().getFileEditorManager()
					.getTabbedPane().getTitleAt(selectedFileEditorPanelIndex) + "...");
			AcideSearchInformationWindow.getInstance().setAlwaysOnTop(true);
			 */
			// If there is no selected text
			if (_selectedText == null) {

				// Gets the selected text from the active text edition area
				_selectedText = AcideMainWindow.getInstance()
						.getFileEditorManager()
						.getFileEditorPanelAt(selectedFileEditorPanelIndex)
						.getActiveTextEditionArea().getSelectedText();

				// The initial position is the selection start
				_intialPosition = AcideMainWindow.getInstance()
						.getFileEditorManager()
						.getFileEditorPanelAt(selectedFileEditorPanelIndex)
						.getActiveTextEditionArea().getSelectionStart();

				// The final position is the selection end
				_finalPosition = AcideMainWindow.getInstance()
						.getFileEditorManager()
						.getFileEditorPanelAt(selectedFileEditorPanelIndex)
						.getActiveTextEditionArea().getSelectionEnd();

				_searchResultPosition = 0;

				// If it is backwards
				if (direction == AcideSearchDirection.BACKWARD)

					// The result position is the final position
					_searchResultPosition = _finalPosition;

				// If the regular expressions are selected and the direction
				// is not backwards
				if ((_regularExpressionsCheckBox.isSelected())
						&& (direction != AcideSearchDirection.BACKWARD))

					// The result position is the initial position
					_searchResultPosition = _intialPosition;

			} else {

				// Calculates the result position
				_searchResultPosition = AcideMainWindow.getInstance()
						.getFileEditorManager()
						.getFileEditorPanelAt(selectedFileEditorPanelIndex)
						.getActiveTextEditionArea().getCaretPosition()
						- _intialPosition;

				// If it is backwards
				if (direction == AcideSearchDirection.BACKWARD) {

					// Calculates the result position
					_searchResultPosition = AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.getFileEditorPanelAt(
									selectedFileEditorPanelIndex)
									.getActiveTextEditionArea().getSelectionStart()
									- _intialPosition;
				}
			}

			// If there is no selected text
			if (_selectedText == null) {

				AcideSearchWindow.getInstance().setOnTop(false);

				// Displays a message
				JOptionPane.showMessageDialog(null, AcideLanguageManager
						.getInstance().getLabels().getString("s616"));

				AcideSearchWindow.getInstance().setOnTop(true);

				// Updates the status message in the ACIDE - A Configurable
				// IDE status bar
				AcideMainWindow
				.getInstance()
				.getStatusBar()
				.setStatusMessage(
						AcideLanguageManager.getInstance()
						.getLabels().getString("s616"));
			} else {

				// Performs the search
				_searchResultPosition = _searchEngine.search(
						_searchResultPosition, _textToSearch,
						_selectedText, _caseSensitiveCheckBox.isSelected(),
						_regularExpressionsCheckBox.isSelected(),
						_completeWordsCheckBox.isSelected(), direction);

				//AcideSearchInformationWindow.getInstance().setVisible(false);

				// If the regular expression is wrong
				if (_searchResultPosition == -2){

					// Puts the default cursor
					AcideSearchWindow.getInstance().setCursor(
							Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

					AcideMainWindow.getInstance().setCursor(
							Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

					return;
				}

				// If it found something out
				if (_searchResultPosition != -1) {

					AcideSearchWindow.getInstance()
					.setProgress(100);
					validate();

					// Shows the search in the file editor
					AcideMainWindow
					.getInstance()
					.getFileEditorManager()
					.getFileEditorPanelAt(
							AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.getSelectedFileEditorPanelIndex())
							.selectText(
									_searchResultPosition + _intialPosition,
									_textToSearch.length());

					// Updates the log
					AcideLog.getLog().info(
							AcideLanguageManager.getInstance().getLabels()
							.getString("s583")
							+ " "
							+ _textToSearch
							+ " "
							+ AcideLanguageManager.getInstance()
							.getLabels().getString("s574"));

					// Updates the status message in the ACIDE - A
					// Configurable IDE status bar
					AcideMainWindow
					.getInstance()
					.getStatusBar()
					.setStatusMessage(
							AcideLanguageManager.getInstance()
							.getLabels().getString("s583")
							+ " "
							+ _textToSearch
							+ " "
							+ AcideLanguageManager
							.getInstance()
							.getLabels()
							.getString("s574"));

					// If the regular expressions are checked
					if (_regularExpressionsCheckBox.isSelected()) {

						// Shows the search in the file editor
						AcideMainWindow
						.getInstance()
						.getFileEditorManager()
						.getFileEditorPanelAt(
								AcideMainWindow
								.getInstance()
								.getFileEditorManager()
								.getSelectedFileEditorPanelIndex())
								.selectText(
										_searchResultPosition
										+ _intialPosition,
										_searchEngine.getRegularExpresion()
										.length());

						// Updates the log
						AcideLog.getLog().info(
								AcideLanguageManager.getInstance()
								.getLabels().getString("s329")
								+ " "
								+ _searchEngine
								.getRegularExpresion()
								+ " "
								+ AcideLanguageManager
								.getInstance().getLabels()
								.getString("s574"));

						// Updates the status message in the ACIDE - A
						// Configurable IDE status bar
						AcideMainWindow
						.getInstance()
						.getStatusBar()
						.setStatusMessage(
								AcideLanguageManager.getInstance()
								.getLabels()
								.getString("s329")
								+ " "
								+ _textToSearch
								+ " "
								+ AcideLanguageManager
								.getInstance()
								.getLabels()
								.getString("s574"));
					}
				} else {

					AcideSearchWindow.getInstance()
					.setProgress(100);
					validate();

					// There is no selected text
					_selectedText = null;
					// Updates the log
					AcideLog.getLog().info(
							AcideLanguageManager.getInstance().getLabels()
							.getString("s573"));

					AcideSearchWindow.getInstance().setOnTop(false);

					// Displays a message
					JOptionPane.showMessageDialog(null,
							AcideLanguageManager.getInstance().getLabels()
							.getString("s573"));

					AcideSearchWindow.getInstance().setOnTop(true);

					// Updates the status bar message in the ACIDE - A
					// Configurable IDE status bar
					AcideMainWindow
					.getInstance()
					.getStatusBar()
					.setStatusMessage(
							AcideLanguageManager.getInstance()
							.getLabels().getString("s573"));

					AcideSearchWindow.getInstance().setOnTop(false);

					// Shows a confirm dialog
					int returnValue = JOptionPane.showConfirmDialog(null,
							AcideLanguageManager.getInstance().getLabels()
							.getString("s575"));

					AcideSearchWindow.getInstance().setOnTop(true);

					// If it is ok
					if (returnValue == JOptionPane.OK_OPTION) {

						// Selects the current document radio button
						_currentDocumentRadioButton.setSelected(true);

						// If it is backwards
						if (direction != AcideSearchDirection.BACKWARD)

							// The result position is the final position
							_searchResultPosition = _finalPosition;
						else

							// The result position is the initial position
							_searchResultPosition = _intialPosition;

						// Performs the search button action
						_searchButton.doClick();
					}
				}
			}
		}

		// All documents search
		if (_allDocumentsRadioButton.isSelected()) {
			//TODO b�squeda en todos los documentos
			// There is no selected text
			_selectedText = null;

			// Gets the number of file editor panels
			selectedFileEditorPanelIndex = AcideMainWindow.getInstance()
					.getFileEditorManager().getNumberOfFileEditorPanels();

			if (!_isCycle && _currentPosition == -2) {

				// Updates the all documents current index
				_allDocumentsCurrentIndex = AcideMainWindow.getInstance()
						.getFileEditorManager()
						.getSelectedFileEditorPanelIndex();

				// Updates the current document
				_currentDocumentIndex = _allDocumentsCurrentIndex;

				// Gets the current position
				_currentPosition = AcideMainWindow.getInstance()
						.getFileEditorManager()
						.getFileEditorPanelAt(_allDocumentsCurrentIndex)
						.getActiveTextEditionArea().getCaretPosition();

				// Brings the main window to foreground
				//AcideMainWindow.getInstance().setAlwaysOnTop(true);

				// Updates the status message in the status bar in the
				// ACIDE - A Configurable IDE main window
				AcideMainWindow
				.getInstance()
				.getStatusBar()
				.setStatusMessage(
						AcideLanguageManager.getInstance()
						.getLabels().getString("s2144")
						+ " "
						+ AcideMainWindow.getInstance().getFileEditorManager()
						.getTabbedPane().getTitleAt(_allDocumentsCurrentIndex));
				// Brings the main window to foreground
				//AcideMainWindow.getInstance().setAlwaysOnTop(false);

				/*AcideSearchInformationWindow.getInstance().setLabel(
						AcideLanguageManager.getInstance()
						.getLabels().getString("s2144")
						+ " "
						+ AcideMainWindow.getInstance().getFileEditorManager()
						.getTabbedPane().getTitleAt(_allDocumentsCurrentIndex) + "...");
				AcideSearchInformationWindow.getInstance().setAlwaysOnTop(true);*/

				AcideSearchWindow.getInstance()
				.setProgress((_valorBar/selectedFileEditorPanelIndex)* 100);
				validate();
			}

			// If it is not the end
			if (!_isEnd) {

				// If the direction is forwards
				if (direction == AcideSearchDirection.FORWARD)

					// The result position is the caret position
					_searchResultPosition = AcideMainWindow
					.getInstance()
					.getFileEditorManager()
					.getFileEditorPanelAt(_allDocumentsCurrentIndex)
					.getActiveTextEditionArea().getCaretPosition();

				// If the direction is backwards
				if (direction == AcideSearchDirection.BACKWARD)

					// The result position is the selection start
					_searchResultPosition = AcideMainWindow
					.getInstance()
					.getFileEditorManager()
					.getFileEditorPanelAt(_allDocumentsCurrentIndex)
					.getActiveTextEditionArea().getSelectionStart();

				// If the direction is both
				if (direction == AcideSearchDirection.BOTH) {

					// The result position is the caret position
					_searchResultPosition = AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.getFileEditorPanelAt(_allDocumentsCurrentIndex)
							.getActiveTextEditionArea().getCaretPosition();
				}

				// Updates the direction
				AcideSearchDirection auxiliarDirection = direction;

				// If it is both
				if (direction == AcideSearchDirection.BOTH)

					// Now it is forward
					auxiliarDirection = AcideSearchDirection.FORWARD;

				// Performs the search
				_searchResultPosition = _searchEngine.search(
						_searchResultPosition,
						_textToSearch,
						AcideMainWindow
						.getInstance()
						.getFileEditorManager()
						.getFileEditorPanelAt(
								_allDocumentsCurrentIndex)
								.getTextEditionAreaContent(),
								_caseSensitiveCheckBox.isSelected(),
								_regularExpressionsCheckBox.isSelected(),
								_completeWordsCheckBox.isSelected(),
								auxiliarDirection);

				//AcideSearchInformationWindow.getInstance().setVisible(false);

				// If the regular expression is wrong
				if (_searchResultPosition == -2){

					// Puts the default cursor
					AcideSearchWindow.getInstance().setCursor(
							Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

					AcideMainWindow.getInstance().setCursor(
							Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

					return;
				}

				// Decides if it is the end
				if ((_isCycle)
						&& (_allDocumentsCurrentIndex == _currentDocumentIndex)
						&& (_searchResultPosition >= _currentPosition)){

					// End of the all documents search
					_isEnd = true;
					AcideSearchWindow.getInstance()
					.setProgress(100);
					validate();}

				else if ((_isCycle)
						&& (_allDocumentsCurrentIndex == _currentDocumentIndex)
						&& (_searchResultPosition == -1)){

					// End of the all documents search
					_isEnd = true;
					AcideSearchWindow.getInstance()
					.setProgress(100);
					validate();
				}

				// If it found something out
				if (_searchResultPosition != -1) {

					// Updates the all documents counter
					_allDocumentsCounter++;
					AcideSearchWindow.getInstance()
					.setProgress(100);
					validate();

					// If the regular expressions are not selected
					if (!_regularExpressionsCheckBox.isSelected()) {

						// Brings the main window to foreground
						AcideMainWindow.getInstance().setAlwaysOnTop(true);

						// Shows the search in the file editor
						AcideMainWindow
						.getInstance()
						.getFileEditorManager()
						.getFileEditorPanelAt(
								_allDocumentsCurrentIndex)
								.selectText(_searchResultPosition,
										_textToSearch.length());

						// Brings the main window to background
						AcideMainWindow.getInstance().setAlwaysOnTop(false);

						// Updates the log
						AcideLog.getLog().info(
								AcideLanguageManager.getInstance()
								.getLabels().getString("s583")
								+ " "
								+ _textToSearch
								+ " "
								+ AcideLanguageManager
								.getInstance().getLabels()
								.getString("s574"));

						// Updates the status message in the ACIDE - A
						// Configurable IDE status bar
						AcideMainWindow
						.getInstance()
						.getStatusBar()
						.setStatusMessage(
								AcideLanguageManager.getInstance()
								.getLabels()
								.getString("s583")
								+ " "
								+ _textToSearch
								+ " "
								+ AcideLanguageManager
								.getInstance()
								.getLabels()
								.getString("s574"));
					} else {

						// Brings the main window to foreground
						AcideMainWindow.getInstance().setAlwaysOnTop(true);

						// Shows the search in the file editor
						AcideMainWindow
						.getInstance()
						.getFileEditorManager()
						.getFileEditorPanelAt(
								_allDocumentsCurrentIndex)
								.selectText(
										_searchResultPosition,
										_searchEngine.getRegularExpresion()
										.length());

						// Brings the main window to background
						AcideMainWindow.getInstance().setAlwaysOnTop(false);

						// Updates the log
						AcideLog.getLog().info(
								AcideLanguageManager.getInstance()
								.getLabels().getString("s577")
								+ " "
								+ _searchEngine
								.getRegularExpresion()
								+ AcideLanguageManager
								.getInstance().getLabels()
								.getString("s577"));

						// Updates the status message in the ACIDE - A
						// Configurable IDE status bar
						AcideMainWindow
						.getInstance()
						.getStatusBar()
						.setStatusMessage(
								AcideLanguageManager.getInstance()
								.getLabels()
								.getString("s577")
								+ " "
								+ _searchEngine
								.getRegularExpresion()
								+ AcideLanguageManager
								.getInstance()
								.getLabels()
								.getString("s577"));
					}
				} else {
					// Updates the log
					AcideLog.getLog().info(
							AcideLanguageManager.getInstance().getLabels()
							.getString("s573"));

					// Updates the status message in the ACIDE - A
					// Configurable IDE status bar
					AcideMainWindow
					.getInstance()
					.getStatusBar()
					.setStatusMessage(
							AcideLanguageManager.getInstance()
							.getLabels().getString("s573"));

					// Places the caret position in the first position of
					// the document
					AcideMainWindow
					.getInstance()
					.getFileEditorManager()
					.getFileEditorPanelAt(_allDocumentsCurrentIndex)
					.getActiveTextEditionArea().setCaretPosition(0);

					// Updates the all documents current index
					if (_forwardRadioButton.isSelected())
						_allDocumentsCurrentIndex++;
					else if (_backwardRadioButton.isSelected())
						_allDocumentsCurrentIndex--;
					else
						_allDocumentsCurrentIndex++;
					_valorBar++;
					AcideSearchWindow.getInstance()
					.setProgress((_valorBar/selectedFileEditorPanelIndex)*100);
					validate();
					// If it is forwards
					if (direction == AcideSearchDirection.FORWARD) {

						if (_allDocumentsCurrentIndex >= selectedFileEditorPanelIndex) {

							// End of the all documents search
							_isEnd = true;
							AcideSearchWindow.getInstance()
							.setProgress(100);
							validate();
						} else {

							// Updates the selected editor panel
							AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.setSelectedFileEditorPanelAt(
									_allDocumentsCurrentIndex);

							// Sets the caret position at the first position
							AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.getFileEditorPanelAt(
									_allDocumentsCurrentIndex)
									.getActiveTextEditionArea()
									.setCaretPosition(0);

							// Performs the search button action
							_searchButton.doClick();
						}
					}
					if (direction == AcideSearchDirection.BACKWARD) {
						if (_allDocumentsCurrentIndex < 0) {

							// End of the all documents search
							_isEnd = true;
							AcideSearchWindow.getInstance()
							.setProgress(100);
							validate();
						} else {

							// Updates the selected editor panel
							AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.setSelectedFileEditorPanelAt(
									_allDocumentsCurrentIndex);

							// Sets the caret position at the last position
							AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.getFileEditorPanelAt(
									_allDocumentsCurrentIndex)
									.getActiveTextEditionArea()
									.setCaretPosition(
											AcideMainWindow
											.getInstance()
											.getFileEditorManager()
											.getFileEditorPanelAt(
													_allDocumentsCurrentIndex)
													.getActiveTextEditionArea()
													.getText().length() - 1);

							// Performs the search button action
							_searchButton.doClick();
						}
					}

					// If the direction is cyclic
					if (direction == AcideSearchDirection.BOTH) {

						// If there is a cycle
						if (_allDocumentsCurrentIndex >= selectedFileEditorPanelIndex) {

							// Starts in the first file editor panel
							_allDocumentsCurrentIndex = 0;

							// There is a cycle
							_isCycle = true;

							// Updates the selected editor panel
							AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.setSelectedFileEditorPanelAt(
									_allDocumentsCurrentIndex);

							// Sets the caret position at the first position
							AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.getFileEditorPanelAt(
									_allDocumentsCurrentIndex)
									.getActiveTextEditionArea()
									.setCaretPosition(0);

							// Performs the search button action
							_searchButton.doClick();

						} else {

							// Updates the selected editor panel
							AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.setSelectedFileEditorPanelAt(
									_allDocumentsCurrentIndex);

							// Sets the caret position at the first position
							AcideMainWindow
							.getInstance()
							.getFileEditorManager()
							.getFileEditorPanelAt(
									_allDocumentsCurrentIndex)
									.getActiveTextEditionArea()
									.setCaretPosition(0);

							// Performs the search button action
							_searchButton.doClick();
						}
					}
				}
			}

			// If it is the end and is the first
			if ((_isEnd) && (_isFirst)) {

				// If there are no matches
				if (_allDocumentsCounter == 0) {

					AcideSearchWindow.getInstance().setOnTop(false);

					// Displays a message
					JOptionPane.showMessageDialog(null,
							AcideLanguageManager.getInstance().getLabels()
							.getString("s576"));

					AcideSearchWindow.getInstance().setOnTop(true);

					// Updates the status message in the ACIDE - A
					// Configurable IDE status bar
					AcideMainWindow
					.getInstance()
					.getStatusBar()
					.setStatusMessage(
							AcideLanguageManager.getInstance()
							.getLabels().getString("s576"));
				} else {

					AcideSearchWindow.getInstance().setOnTop(false);

					// Displays a message
					JOptionPane.showMessageDialog(null,
							AcideLanguageManager.getInstance().getLabels()
							.getString("s586"));

					AcideSearchWindow.getInstance().setOnTop(true);

					// Updates the status message in the ACIDE - A
					// Configurable IDE status bar
					AcideMainWindow
					.getInstance()
					.getStatusBar()
					.setStatusMessage(
							AcideLanguageManager.getInstance()
							.getLabels().getString("s586"));
				}

				// This is not the first
				_isFirst = false;
			}
		}

		// Puts the default cursor
		AcideSearchWindow.getInstance().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

		AcideMainWindow.getInstance().setCursor(
				Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

	/**
	 * ACIDE - A Configurable IDE search window search button action listener.
	 * 
	 * @version 0.11
	 * @see ActionListener
	 */
	class SearchButtonAction implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent actionEvent) {
			searchAction();
		}
	}

	/**
	 * ACIDE - A Configurable IDE search window cancel button action listener.
	 * 
	 * @version 0.11
	 * @see ActionListener
	 */
	class CancelButtonAction implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			// Puts the default cursor
			AcideSearchWindow.getInstance().setCursor(
					Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			AcideMainWindow.getInstance().setCursor(
					Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			// Closes the window
			AcideSearchWindow.getInstance().dispose();
		}
	}

	/**
	 * ACIDE - A Configurable IDE search window escape key action.
	 * 
	 * @version 0.11
	 * @see AbstractAction
	 */
	class EscapeKeyAction extends AbstractAction {

		/**
		 * Escape key action serial version UID.
		 */
		private static final long serialVersionUID = 1L;

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			// Puts the default cursor
			AcideSearchWindow.getInstance().setCursor(
					Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			AcideMainWindow.getInstance().setCursor(
					Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

			// Closes the window
			AcideReplaceWindow.getInstance().dispose();
		}
	}
}
