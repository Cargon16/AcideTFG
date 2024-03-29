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
package acide.gui.menuBar.helpMenu.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.TitledBorder;

import acide.gui.listeners.AcideWindowClosingListener;
import acide.gui.mainWindow.AcideMainWindow;
import acide.language.AcideLanguageManager;
import acide.log.AcideLog;

/**
 * ACIDE - A Configurable IDE about us window.
 * 
 * @version 0.11
 * @see JFrame
 */
public class AcideAboutUsWindow extends JFrame {

	/**
	 * ACIDE - A Configurable IDE about us window class serial version UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ACIDE - A Configurable IDE about us window image icon.
	 */
	private static final ImageIcon ICON = new ImageIcon(
			"./resources/images/icon.png");
	/**
	 * ACIDE - A Configurable IDE about us window image file.
	 */
	private static final ImageIcon IMAGE = new ImageIcon(
			"./resources/images/aboutUs.png");
	/**
	 * ACIDE - A Configurable IDE about us window panel which contains the info
	 * about the developers of the application.
	 */
	private JPanel _developersPanel;
	/**
	 * ACIDE - A Configurable IDE about us window info panel.
	 */
	private JPanel _infoPanel;
	/**
	 * ACIDE - A Configurable IDE about us window main panel.
	 */
	private JPanel _mainPanel;
	/**
	 * ACIDE - A Configurable IDE about us window button panel.
	 */
	private JPanel _buttonPanel;
	/**
	 * ACIDE - A Configurable IDE about us window image label.
	 */
	private JLabel _image;
	/**
	 * ACIDE - A Configurable IDE about us window accept button.
	 */
	private JButton _acceptButton;

	/**
	 * Creates a new ACIDE - A Configurable IDE about us window.
	 */
	public AcideAboutUsWindow() {

		super();

		// Updates the log
		AcideLog.getLog().info(
				AcideLanguageManager.getInstance().getLabels()
						.getString("s619"));

		// Builds the window components
		buildComponents();

		// Sets the listeners of the window components
		setListeners();

		// Adds the components to the window
		addComponents();

		// Sets the window configuration
		setWindowConfiguration();
	}

	/**
	 * Builds the ACIDE - A Configurable IDE about us window components.
	 */
	private void buildComponents() {

		// Creates the main panel
		_mainPanel = new JPanel(new GridBagLayout());

		// Creates the image label
		_image = new JLabel(IMAGE);

		// Creates the info panel
		_infoPanel = new JPanel(new GridBagLayout());

		// Sets the info panel border
		_infoPanel.setBorder(BorderFactory.createTitledBorder(null,
				AcideLanguageManager.getInstance().getLabels()
						.getString("s630"), TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION));

		// Creates the button panel
		_buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		// Creates the developers panel
		_developersPanel = new JPanel(new GridBagLayout());

		// Sets the developers panel border
		_developersPanel.setBorder(BorderFactory.createTitledBorder(null,
				AcideLanguageManager.getInstance().getLabels()
						.getString("s625"), TitledBorder.LEADING,
				TitledBorder.DEFAULT_POSITION));

		// Creates the accept button
		_acceptButton = new JButton(AcideLanguageManager.getInstance()
				.getLabels().getString("s177"));
	}

	/**
	 * Sets the ACIDE - A Configurable IDE about us window configuration.
	 */
	private void setWindowConfiguration() {

		// Sets the window title
		setTitle(AcideLanguageManager.getInstance().getLabels()
				.getString("s622"));

		// Sets the window icon image
		setIconImage(ICON.getImage());

		// The window is not resizable
		setResizable(false);

		// Packs all the window components
		pack();

		// Centers the window
		setLocationRelativeTo(null);

		// Displays the window
		setVisible(true);

		// Disables the main window
		AcideMainWindow.getInstance().setEnabled(false);
	}

	/**
	 * Adds the components to the ACIDE - A Configurable IDE about us window
	 * with the layout.
	 */
	private void addComponents() {

		// Sets the layout
		setLayout(new BorderLayout());

		// Adds the components to the window with the layout
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;
		constraints.weightx = 500;
		constraints.gridx = 0;
		constraints.gridy = 0;

		_infoPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s631")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;

		_infoPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s632")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;

		_infoPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s633")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;

		_infoPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s634")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;

		_infoPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s635")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 0;

		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s626")), constraints);

		constraints.insets = new Insets(5, 25, 5, 5);
		constraints.gridx = 0;
		constraints.gridy = 1;

		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s627")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;

		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s628")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;

		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s629")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;

		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s966")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;

		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s967")), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 6;

		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s1100")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 7;

		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s1101")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 8;

		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s1102")), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 9;
		
		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s1103")), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 10;
		
		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s1104")), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 11;
		
		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s1105")), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 12;
		
		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s1106")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 13;

		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s1107")), constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 14;
		
		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s1108")), constraints);

		constraints.gridx = 0;
		constraints.gridy = 15;

		_developersPanel.add(new JLabel(AcideLanguageManager.getInstance()
				.getLabels().getString("s2388")), constraints);
		
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 0;
		constraints.gridy = 0;

		// Adds the info panel to the main panel
		_mainPanel.add(_infoPanel, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;

		// Adds the developers panel to the main panel
		_mainPanel.add(_developersPanel, constraints);

		// Adds the image label to the window
		add(_image, BorderLayout.NORTH);

		// Adds the main panel to the window
		add(_mainPanel, BorderLayout.CENTER);

		// Adds the accept button to the button panel
		_buttonPanel.add(_acceptButton);

		// Adds the button panel to the window
		add(_buttonPanel, BorderLayout.SOUTH);
	}

	/**
	 * Sets the listeners of the ACIDE - A Configurable IDE about us window
	 * components.
	 */
	public void setListeners() {

		// Adds the accept button action listener
		_acceptButton.addActionListener(new AcceptButtonAction());

		// Sets the window closing listener
		addWindowListener(new AcideWindowClosingListener());
		
		// Puts the escape key in the input map of the window
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false), "EscapeKey");

		// Puts the escape key in the action map of the window
		getRootPane().getActionMap().put("EscapeKey", new EscapeKeyAction());
	}

	/**
	 * Closes the ACIDE - A Configurable IDE about us window.
	 */
	public void closeWindow() {
		
		// Enables the main window
		AcideMainWindow.getInstance().setEnabled(true);

		// Closes the window
		dispose();

		// Brings the main window to the front
		AcideMainWindow.getInstance().setAlwaysOnTop(true);

		// But not permanently
		AcideMainWindow.getInstance().setAlwaysOnTop(false);
	}
	
	/**
	 * ACIDE - A Configurable IDE about us window accept button action listener.
	 * 
	 * @version 0.11
	 * @see ActionListener
	 */
	class AcceptButtonAction implements ActionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent
		 * )
		 */
		@Override
		public void actionPerformed(ActionEvent actionEvent) {

			// Updates the log
			AcideLog.getLog().info(
					AcideLanguageManager.getInstance().getLabels()
							.getString("s621"));

			// Closes the window
			closeWindow();
		}
	}
	
	/**
	 * ACIDE - A Configurable IDE about us window escape key action.
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

			// Closes the window
			closeWindow();
		}
	}
}