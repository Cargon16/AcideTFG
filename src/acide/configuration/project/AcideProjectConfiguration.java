/*
 * ACIDE - A Configurable IDE
 * Official web site: http://acide.sourceforge.net
 * 
 * Copyright (C) 2007-2013  
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
 *      - Version from 0.12 to 0.16
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
package acide.configuration.project;

import acide.configuration.workbench.AcideWorkbenchConfiguration;
import acide.files.AcideFileManager;
import acide.files.project.AcideProjectFile;
import acide.gui.mainWindow.AcideMainWindow;
import acide.gui.menuBar.configurationMenu.themesMenu.AcideThemesMenu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import acide.language.AcideLanguageManager;
import acide.log.AcideLog;

import acide.resources.AcideResourceManager;

/**
 * ACIDE - A Configurable IDE project configuration.
 * 
 * @version 0.11
 * @see AcideProjectFile
 */
public class AcideProjectConfiguration {

	/**
	 * ACIDE - A Configurable IDE project configuration default path.
	 */
	public static final String DEFAULT_PATH = "./configuration/project/default.acideProject";
	/**
	 * ACIDE - A Configurable IDE project configuration unique class instance.
	 */
	private static AcideProjectConfiguration _instance;
	/**
	 * ACIDE - A Configurable IDE project configuration project name.
	 */
	private String _name;
	/**
	 * ACIDE - A Configurable IDE project configuration project path.
	 */
	private String _path;
	/**
	 * Language of the application.
	 */
	private String _languageConfiguration;
	/**
	 * Flag that indicates if the database panel is showed or not.
	 */
	private boolean _isDataBasePanelShowed;
	/**
	 * Menu configuration.
	 */
	private String _menuConfiguration;
	/**
	 * Menu new configuration.
	 */
	private String _menuNewConfiguration;
	/**
	 * Tool Bar configuration.
	 */
	private String _toolBarConfiguration;
	/**
	 * Compiler path.
	 */
	private String _compilerPath;
	/**
	 * Arguments for the compiler.
	 */
	private String _compilerArguments;
	/**
	 * Flag that indicates if the compiler compiles all the files.
	 */
	private boolean _compileAllFiles;
	/**
	 * File separator for the compiler.
	 */
	private String _fileSeparator;
	/**
	 * File extensions valid for the project.
	 */
	private String _fileExtension;
	/**
	 * Compiler path of the executable that executes the project.
	 */
	private String _executablePath;
	/**
	 * Arguments for the executable that executes the project.
	 */
	private String _executableArguments;
	/**
	 * Shell path associated to the project.
	 */
	private String _consolePanelShellPath;
	/**
	 * Shell directory associated to the project.
	 */
	private String _consolePanelShellDirectory;
	/**
	 * Indicates if the command fired has to be displayed in the console.
	 */
	private boolean _consolePanelIsEchoCommand;
	/**
	 * Shell parameters associated to the project.
	 */
	private String _consolePanelParameters;
	/**
	 * ACIDE - A Configurable IDE console panel configuration console exit
	 * command.
	 */
	private String _consolePanelExitCommand;
	/**
	 * ACIDE - A Configurable IDE console panel configuration font name.
	 */
	private String _consolePanelFontName;
	/**
	 * ACIDE - A Configurable IDE console panel configuration font style.
	 */
	private int _consolePanelFontStyle;
	/**
	 * ACIDE - A Configurable IDE console panel configuration font size.
	 */
	private int _consolePanelFontSize;
	/**
	 * ACIDE - A Configurable IDE console panel configuration foreground color.
	 */
	private Color _consolePanelForegroundColor;
	/**
	 * ACIDE - A Configurable IDE console panel configuration background color.
	 */
	private Color _consolePanelBackgroundColor;
	/**
	 * ACIDE - A Configurable IDE console panel configuration buffer size.
	 */
	private int _consolePanelBufferSize;
	/**
	 * Flag that indicates if the explorer panel is showed or not.
	 */
	private boolean _isExplorerPanelShowed;
	/**
	 * Flag that indicates if the console panel is showed or not.
	 */
	private boolean _isConsolePanelShowed;
	/**
	 * Flag that indicates if the graph panel is showed or not.
	 */
	private boolean _isGraphPanelShowed;
	/**
	 * Flag that indicates if the debug panel is showed or not.
	 */
	private boolean _isDebugPanelShowed;
	/**
	 * ACIDE - A Configurable IDE main window width.
	 */
	private int _width;
	/**
	 * ACIDE - A Configurable IDE main window height.
	 */
	private int _height;
	/**
	 * ACIDE - A Configurable IDE main window x coordinate.
	 */
	private int _xCoordinate;
	/**
	 * ACIDE - A Configurable IDE main window y coordinate.
	 */
	private int _yCoordinate;
	/**
	 * Vertical split pane divider location in the ACIDE - A Configurable IDE
	 * main window.
	 */
	private int _verticalFilesSplitPaneDividerLocation;
	/**
	 * Vertical split pane divider location in the ACIDE - A Configurable IDE
	 * main window.
	 */
	private int _verticalDataBaseSplitPaneDividerLocation;
	/**
	 * Vertical split pane divider location in the ACIDE - A Configurable IDE
	 * main window.
	 */
	private int _verticalGraphSplitPaneDividerLocation;
	/**
	 * Horizontal split pane divider location in the ACIDE - A Configurable IDE
	 * main window.
	 */
	private int _horizontalSplitPaneDividerLocation;
	/**
	 * Horizontal graph split pane divider location in the ACIDE - A Configurable IDE
	 * main window.
	 */
	private int _horizontalGraphSplitPaneDividerLocation;
	/**
	 * Flag that indicates if it is the first time that the configuration has
	 * been saved or not.
	 */
	private boolean _isFirstSave;
	/**
	 * Flag that indicates if the project configuration has been modified or
	 * not.
	 */
	private boolean _isModified;
	/**
	 * File list of the files which belongs to the project.
	 */
	private ArrayList<AcideProjectFile> _fileList;
	/**
	 * List of panels shown on the main window
	 */
	private ArrayList<String> _panelList;
	/**
	 * Int that points the position of the next parameter of configuration.
	 */
	private int _position;
	
	private boolean fileEditorIsModified;
	
	private boolean consoleIsModified;

	/**
	 * Creates a new project configuration.
	 */
	public AcideProjectConfiguration() {
		
		// The project has not been modified
		_isModified = false;
		
		// It is not the first time the project has been saved
		_isFirstSave = false;
		
		// Creates the file list
		_fileList = new ArrayList<>();
		
		// Creates the panels list
		_panelList = new ArrayList<>();
		
		fileEditorIsModified = false;
		
		consoleIsModified = false;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration unique class
	 * instance.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration unique class
	 *         instance.
	 */
	public static AcideProjectConfiguration getInstance() {

		if (_instance == null)
			_instance = new AcideProjectConfiguration();
		return _instance;
	}

	/**
	 * Loads the project configuration from the file content given as a
	 * parameter.
	 * 
	 * @param filePath
	 *            configuration file path which contains all the project
	 *            configuration to load.
	 */
	public void load(String filePath) {

		try {

			// Deletes all the files associated to the project
			AcideProjectConfiguration.getInstance().removeFiles();

			String fileContent = null;

			// Loads its content
			fileContent = AcideFileManager.getInstance().load(filePath);

			// If it can't find the file
			if (fileContent == null) {

				// Loads the default file
				fileContent = AcideFileManager.getInstance().load(
						AcideProjectConfiguration.DEFAULT_PATH);

				// Updates the ACIDE - A Configurable IDE project configuration
				AcideResourceManager.getInstance().setProperty(
						"projectConfiguration",
						AcideProjectConfiguration.DEFAULT_PATH);

				// Displays an error message
				JOptionPane.showMessageDialog(null, AcideLanguageManager
						.getInstance().getLabels().getString("s960")
						+ filePath
						+ AcideLanguageManager.getInstance().getLabels()
								.getString("s959"));
			}

			_position = 0;

			// Avoids errors in the conversions
			fileContent = fileContent.replaceAll("\r\n", "\n");
			
			// Gets the project name 1
			_name = getProperty(fileContent);

			// Gets the project path 2
			_path = getProperty(fileContent);

			// Gets the compiler path 3
			_compilerPath = getProperty(fileContent);

			// Gets the compiler arguments 4
			_compilerArguments = getProperty(fileContent);

			// Gets the compile all files 5
			_compileAllFiles = Boolean.parseBoolean(getProperty(fileContent));

			// Gets the file separator 6
			_fileSeparator = getProperty(fileContent);

			// Gets the file extension 7
			_fileExtension = getProperty(fileContent);

			// Gets the executable path 8
			_executablePath = getProperty(fileContent);

			// Gets the executable arguments 9
			_executableArguments = getProperty(fileContent);

			// Gets the console panel shell path 10
			_consolePanelShellPath = getProperty(fileContent);

			// Gets the console panel shell directory 11
			_consolePanelShellDirectory = getProperty(fileContent);

			// Gets the console panel exit command 12
			_consolePanelExitCommand = getProperty(fileContent);

			// Gets the console panel is echo command 13
			_consolePanelIsEchoCommand = Boolean.parseBoolean(getProperty(fileContent));
			
			// Gets the console panel parameters 14
			_consolePanelParameters = getProperty(fileContent);

			// Gets the console panel foreground color 15			
			_consolePanelForegroundColor = new Color(
					Integer.parseInt(getProperty(fileContent)));

			// Gets the console panel background color 16		
			_consolePanelBackgroundColor = new Color(
					Integer.parseInt(getProperty(fileContent)));

			// Gets the console panel font name 17			
			_consolePanelFontName = getProperty(fileContent);

			// Gets the console panel font style 18			
			_consolePanelFontStyle = Integer.parseInt(getProperty(fileContent));

			// Gets the console panel font size 19			
			_consolePanelFontSize = Integer.parseInt(getProperty(fileContent));

			// Gets the console panel buffer size 20			
			_consolePanelBufferSize = Integer.parseInt(getProperty(fileContent));

			// Gets the is explorer panel showed flag 21			
			_isExplorerPanelShowed = Boolean.parseBoolean(getProperty(fileContent));

			// Gets the is console panel showed flag 22			
			_isConsolePanelShowed = Boolean.parseBoolean(getProperty(fileContent));
			
			// Gets the is data base panel showed flag 23			
			_isDataBasePanelShowed = Boolean.parseBoolean(getProperty(fileContent));

			// gets the is graph panel showed flag 24
			_isGraphPanelShowed = Boolean.parseBoolean(getProperty(fileContent));

			// gets the is debug panel showed flag 26
			_isDebugPanelShowed = Boolean.parseBoolean(getProperty(fileContent));
			
			// Gets the ACIDE - A Configurable IDE main window width 27
			_width = Integer.parseInt(getProperty(fileContent));

			// Gets the ACIDE - A Configurable IDE main window height 28
			_height = Integer.parseInt(getProperty(fileContent));

			// Gets the ACIDE - A Configurable IDE main window x coordinate 29
			_xCoordinate = Integer.parseInt(getProperty(fileContent));

			// Gets the ACIDE - A Configurable IDE main window y coordinate 30
			_yCoordinate = Integer.parseInt(getProperty(fileContent));

			// Gets the ACIDE - A Configurable IDE main window vertical split 31
			// pane divider location
			_verticalFilesSplitPaneDividerLocation = Integer.parseInt(getProperty(fileContent));

			// Gets the ACIDE - A Configurable IDE main window vertical split 32
			// pane divider location	
			_verticalDataBaseSplitPaneDividerLocation = Integer.parseInt(getProperty(fileContent));
			
			// Gets the ACIDE - A Configurable IDE main window vertical split 33
			// pane divider location
			_verticalGraphSplitPaneDividerLocation = Integer.parseInt(getProperty(fileContent));

			// Gets the ACIDE - A Configurable IDE main window horizontal split 34
			// pane divider location
			
			_horizontalSplitPaneDividerLocation = Integer.parseInt(getProperty(fileContent));
			
			// Gets the ACIDE - A Configurable IDE main window horizontal split 35
			// pane divider location
			
			_horizontalGraphSplitPaneDividerLocation = Integer.parseInt(getProperty(fileContent));

			// Gets the language configuration 36
			
			_languageConfiguration = getProperty(fileContent);
			
			// Gets the menu configuration 37
			
			_menuConfiguration = getProperty(fileContent);
			
			// Gets the menu new configuration 38
			
			_menuNewConfiguration = getProperty(fileContent);

			// Gets the tool bar configuration 39
			
			_toolBarConfiguration = getProperty(fileContent);
			
			// Gets the panels from the panels list, they are kept in order
			for (int i = 0; i < 6; i++) {
				
				_panelList.add(getProperty(fileContent));
			}

			// Gets the number of files of the project 46
			
			String numFiles = getProperty(fileContent);
			

			boolean isCompilableFile;
			boolean isMainFile;
			String name;
			String path;
			String parent;
			boolean isDirectory;
			boolean isOpened;

			// Clears the file list
			_fileList.clear();

			for (int index = 0; index < Integer.parseInt(numFiles); index++) {

				// Creates the ACIDE - A Configurable IDE file
				AcideProjectFile file = new AcideProjectFile();

				// Gets the absolute path 38
				path = getProperty(fileContent);

 
				// Gets the name 39
				name = getProperty(fileContent);


				// Gets the parent 40
				parent = getProperty(fileContent);


				// Gets the is directory flag 41
				isDirectory = Boolean.parseBoolean(getProperty(fileContent));


				// Gets the is compilable flag 42
				isCompilableFile = Boolean.parseBoolean(getProperty(fileContent));


				// Gets the is main flag 43
				isMainFile = Boolean.parseBoolean(getProperty(fileContent));

				// Gets the is opened flag 44
				isOpened = Boolean.parseBoolean(getProperty(fileContent));


				// Updates the ACIDE - A Configurable IDE file with the info

				// Sets the is main flag
				file.setIsMainFile(isMainFile);

				// Sets the is compilable flag
				file.setIsCompilableFile(isCompilableFile);

				// Sets the absolute path
				file.setAbsolutePath(path);

				// Sets the parent
				file.setParent(parent);

				// Sets the name
				file.setName(name);

				// Sets the is directory flag
				file.setIsDirectory(isDirectory);

				// Sets the is opened flag
				file.setIsOpened(isOpened);

				// Always add the folders as they do not exist
				if (file.isDirectory())
					// Adds the file to the list
					_fileList.add(file);
				else {

					// Checks if exists
					File externalFile = new File(file.getAbsolutePath());
					if (externalFile.exists()) {

						// Adds the file to the list
						_fileList.add(file);
					} else {

						// Displays an error message
						JOptionPane.showMessageDialog(null,
								AcideLanguageManager.getInstance().getLabels()
										.getString("s970")
										+ path
										+ AcideLanguageManager.getInstance()
												.getLabels().getString("s971"),
								"Error", JOptionPane.ERROR_MESSAGE);

						// The configuration has been modified
						_isModified = true;
					}
				}
			}
			
			/*
			 * If it has been modified because one of the files does not exist,
			 * saves the project configuration automatically to preserve its
			 * configuration.
			 */
			if(_isModified)
				
				// Saves the project automatically
				AcideFileManager.getInstance().write(_path, save());
			
		} catch (Exception exception) {

			// Displays an error message
			JOptionPane.showMessageDialog(
					null,
					AcideLanguageManager.getInstance().getLabels()
							.getString("s2015")
							+ filePath
							+ " "
							+ AcideLanguageManager.getInstance().getLabels()
									.getString("s2016"), "Error",
					JOptionPane.ERROR_MESSAGE);

			if (!AcideWorkbenchConfiguration.getInstance().isWorkbenchLoaded()) {

				// Sets the default name
				_name = "";

				// Sets the default path
				_path = DEFAULT_PATH;

				// Sets the default compiler path
				_compilerPath = "";

				// Sets the default compiler arguments
				_compilerArguments = "";

				// Sets the default compile all files flag
				_compileAllFiles = false;

				// Sets the default file extension
				_fileExtension = "";

				// Sets the default file separator
				_fileSeparator = "";

				// Sets the default file executable path
				_executablePath = "";

				// Sets the default executable arguments
				_executableArguments = "";

				// Sets the default console panel shell path
				_consolePanelShellPath = "";

				// Sets the default console panel shell directory
				_consolePanelShellDirectory = "";

				// Sets the default console panel is echo command
				_consolePanelIsEchoCommand = false;

				// Sets the default console panel exit command
				_consolePanelExitCommand = "";

				// Sets the default console panel font name
				_consolePanelFontName = "Monospaced";

				// Sets the default console panel font style
				_consolePanelFontStyle = Font.PLAIN;

				// Sets the default console panel font size
				_consolePanelFontSize = 12;

				// Sets the default console panel background color
				_consolePanelBackgroundColor = Color.WHITE;

				// Sets the default console panel foreground color
				_consolePanelForegroundColor = Color.BLACK;

				// Sets the default console panel buffer size
				_consolePanelBufferSize = 512;

				// Sets the default language configuration
				_languageConfiguration = "spanish";

				// Sets the default tool bar configuration
				_toolBarConfiguration = "./configuration/toolbar/default.toolbarConfig";

				// Sets the default menu configuration
				_menuConfiguration = "./configuration/menu/defaultAllOn.menuConfig";
				
				// Sets the default menu new configuration
				_menuNewConfiguration = "./configuration/menu/defaultAllOn.xml";

				// Gets the screen dimensions
				Dimension screenDimentsion = Toolkit.getDefaultToolkit()
						.getScreenSize();

				// Shows the console panel
				_isConsolePanelShowed = true;

				// Shows the explorer panel
				_isExplorerPanelShowed = true;
				
				//Sets the default database panel showed
				_isDataBasePanelShowed = true;
				
				// Shows the graph panel
				_isGraphPanelShowed = true;
				
				// Shows the debug panel
				_isDebugPanelShowed = true;

				// Sets the width by default
				_width = 800;

				// Sets the height by default
				_height = 600;

				// Sets the x coordinate by default
				_xCoordinate = (screenDimentsion.width - _width) / 2;

				// Sets the y coordinate by default
				_yCoordinate = (screenDimentsion.height - _height) / 2;

				// Sets the horizontal split pane divider location by default
				_horizontalSplitPaneDividerLocation = 350;
				
				// Sets the horizontal graph split pane divider location by default
				_horizontalGraphSplitPaneDividerLocation = 350;

				// Sets the vertical split pane divider location by default
				_verticalFilesSplitPaneDividerLocation = 150;
				
				// Sets the vertical split pane divider location by default
				_verticalDataBaseSplitPaneDividerLocation = 150;
				
				// Sets the vertical split pane divider location by default
				_verticalGraphSplitPaneDividerLocation = 500;
				
				// Adds the name of the panels to the panels list
				_panelList.add("AcideExplorerPanel");
				_panelList.add("AcideFileEditor");
				_panelList.add("AcideDataBasePanel");
				_panelList.add("AcideConsolePanel");
				_panelList.add("AcideGraphPanel");
				_panelList.add("AcideDebugPanel");

				// It is not modified
				_isModified = false;

				// It is not the first time that it is saved
				_isFirstSave = false;

				// Clears the list of files associated to the project
				removeFiles();
			} else {

				// Closes the current project
				AcideMainWindow.getInstance().getMenu().getProjectMenu()
						.closeProject();
			}
		}
	}
	
	/**
	 * Saves the project configuration in a string.
	 * 
	 * @return the file content with the project configuration to be saved.
	 */
	public String save() {

		// Builds the file content
		String fileContent = "";

		// Adds the name
		fileContent = fileContent + _name + "\n";

		// Adds the path
		fileContent = fileContent + _path + "\n";

		// Adds the compiler path
		fileContent = fileContent + _compilerPath + "\n";

		// Adds the compiler arguments
		fileContent = fileContent + _compilerArguments + "\n";

		// Adds the compile all files
		fileContent = fileContent + _compileAllFiles + "\n";

		// Adds the file separator
		fileContent = fileContent + _fileSeparator + "\n";

		// Adds the file extension
		fileContent = fileContent + _fileExtension + "\n";
		
		// Adds the executable path
		fileContent = fileContent + _executablePath + "\n";

		// Adds the executable arguments
		fileContent = fileContent + _executableArguments + "\n";

		// Adds the console panel shell path
		fileContent = fileContent + _consolePanelShellPath + "\n";

		// Adds the console panel shell directory
		fileContent = fileContent + _consolePanelShellDirectory + "\n";

		// Adds the console panel exit command
		fileContent = fileContent + _consolePanelExitCommand + "\n";

		// Adds the console panel is echo command
		fileContent = fileContent + _consolePanelIsEchoCommand + "\n";

		// Adds the console panel parameters
		fileContent = fileContent + _consolePanelParameters + "\n";

		// Adds the console panel foreground color
		fileContent = fileContent
				+ Integer.toString(_consolePanelForegroundColor.getRGB())
				+ "\n";

		// Adds the console panel background color
		fileContent = fileContent
				+ Integer.toString(_consolePanelBackgroundColor.getRGB())
				+ "\n";

		// Adds the console panel font name
		fileContent = fileContent + _consolePanelFontName + "\n";

		// Adds the console panel font style
		fileContent = fileContent + _consolePanelFontStyle + "\n";

		// Adds the console panel font size
		fileContent = fileContent + _consolePanelFontSize + "\n";

		// Adds the console panel buffer size
		fileContent = fileContent + _consolePanelBufferSize + "\n";

		// Adds the is explorer panel showed configuration
		fileContent = fileContent + _isExplorerPanelShowed + "\n";

		// Adds the is console panel showed configuration
		fileContent = fileContent + _isConsolePanelShowed + "\n";
		
		// Adds the is data base panel showed configuration
		fileContent = fileContent + _isDataBasePanelShowed + "\n";
		
		// Adds the is graph panel showed configuration
		fileContent = fileContent + _isGraphPanelShowed + "\n";
		
		// Adds the is graph panel showed configuration
		fileContent = fileContent + _isDebugPanelShowed + "\n";

		// Adds the width
		fileContent = fileContent + _width + "\n";

		// Adds the height
		fileContent = fileContent + _height + "\n";

		// Adds the x coordinate
		fileContent = fileContent + _xCoordinate + "\n";

		// Adds the y coordinate
		fileContent = fileContent + _yCoordinate + "\n";

		// Adds the vertical split pane divider location
		fileContent = fileContent + AcideMainWindow.getInstance().getVerticalFilesSplitPane().getDividerLocation() + "\n";
		
		// Adds the vertical split pane divider location
		fileContent = fileContent + AcideMainWindow.getInstance().getVerticalDataBaseSplitPane().getDividerLocation() + "\n";
		
		// Adds the vertical split pane divider location
		fileContent = fileContent + AcideMainWindow.getInstance().getVerticalSplitPane()
				.getDividerLocation() + "\n";

		// Adds the horizontal split pane divider location
		fileContent = fileContent + AcideMainWindow.getInstance().getHorizontalSplitPane()
				.getDividerLocation() + "\n";
		
		// Adds the horizontal graph split pane divider location
		fileContent = fileContent + AcideMainWindow.getInstance()
		.getHorizontalGraphSplitPane().getDividerLocation() + "\n";

		// Adds the language configuration
		fileContent = fileContent + _languageConfiguration + "\n";
		
		// Adds the menu configuration
		fileContent = fileContent + _menuConfiguration + "\n";
		
		// Adds the menu new configuration
		fileContent = fileContent + _menuNewConfiguration + "\n";

		// Adds the tool bar configuration
		fileContent = fileContent + _toolBarConfiguration + "\n";
		
		// Adds the panels kept in the panel list
		for (int i = 0; i < _panelList.size() && i < 6; i++) {
			fileContent = fileContent + _panelList.get(i) + "\n";
		}

		// Adds the number of files associated
		fileContent = fileContent + _fileList.size() + "\n";
		
		int tam = AcideMainWindow.getInstance().getFileEditorManager().getNumberOfFileEditorPanels();
		for (int index = 0; index < tam; index++) {
			String tab = AcideMainWindow.getInstance().getFileEditorManager().getFileEditorPanelAt(index).getAbsolutePath();
			// Gets the ACIDE - A Configurable file from the list
			int i =0;
			try {
			AcideProjectFile file = (AcideProjectFile) _fileList.get(i);
			while(file.getAbsolutePath() != tab && i < _fileList.size()) {
				file = (AcideProjectFile) _fileList.get(++i);
			}
			//AcideProjectFile file = (AcideProjectFile) _fileList.get(index);

			// Adds its information
			fileContent = fileContent + file.getAbsolutePath() + "\n"
					+ file.getName() + "\n" + file.getParent() + "\n"
					+ file.isDirectory() + "\n" + file.isCompilableFile()
					+ "\n" + file.isMainFile() + "\n" + file.isOpened() + "\n";
			}catch(Exception e) {}
		}
		/*for (int index = 0; index < _fileList.size(); index++) {

			// Gets the ACIDE - A Configurable file from the list
			AcideProjectFile file = (AcideProjectFile) _fileList.get(index);

			// Adds its information
			fileContent = fileContent + file.getAbsolutePath() + "\n"
					+ file.getName() + "\n" + file.getParent() + "\n"
					+ file.isDirectory() + "\n" + file.isCompilableFile()
					+ "\n" + file.isMainFile() + "\n" + file.isOpened() + "\n";
		}*/

		// Returns the file content
		return fileContent;
	}

	/**
	 * <p>
	 * Updates the name of the project in the files which depends on the root
	 * node with the name of the project.
	 * </p>
	 * <p>
	 * When a project has been saved with other name, it changes the parent
	 * nodes which name matches with the old name for the new one.
	 * </p>
	 * 
	 * @param previousProjectName
	 *            previousProjectName previous project name to be renamed.
	 * @param newProjectName
	 *            newProjectName previous project name to be set.
	 */
	public void updateFileListProjectConfiguration(String previousProjectName,
			String newProjectName) {

		for (int index = 0; index < _fileList.size(); index++) {

			// Gets the ACIDE - A Configurable file from the list
			AcideProjectFile file = (AcideProjectFile) _fileList.get(index);

			// If the node depended on the node with the previous project name
			if (file.getParent().matches(previousProjectName))

				// Updates its parent
				file.setParent(newProjectName);
		}
	}

	/**
	 * Returns true if the current project is
	 * "./configuration/project/default.acideProject" or the name is "".
	 * 
	 * @return true if it has the default project and false in other case.
	 */
	public boolean isDefaultProject() {

		String projectConfiguration = null;
		try {

			// Gets the ACIDE - A Configurable IDE project configuration
			projectConfiguration = AcideResourceManager.getInstance()
					.getProperty("projectConfiguration");
		} catch (Exception exception) {

			// Updates the Log
			AcideLog.getLog().error(exception.getMessage());
			exception.printStackTrace();
		}

		return projectConfiguration.matches(DEFAULT_PATH)
				&& AcideProjectConfiguration.getInstance().getName().equals("");
	}
	
	/**
	 * Returns the file from the file list which absolute path matches with the
	 * path given as a parameter.
	 * 
	 * @param absolutePath
	 *            absolute path to compare with.
	 * 
	 * @return the file from the file list which absolute path matches with the
	 *         path given as a parameter.
	 */
	public AcideProjectFile getFileAt(String absolutePath) {

		for (int index = 0; index < _fileList.size(); index++)
			if (_fileList.get(index).getAbsolutePath().equals(absolutePath))
				return _fileList.get(index);

		return null;
	}

	/**
	 * Returns the index of the file from the file list which absolute path
	 * matches with the path given as a parameter.
	 * 
	 * @param filePath
	 *            absolute path to compare with.
	 * 
	 * @return the index of the file from the file list. If it is not at the
	 *         list then returns -1.
	 */
	public int getIndexOfFile(String filePath) {

		for (int index = 0; index < _fileList.size(); index++)
			if (_fileList.get(index).getAbsolutePath().equals(filePath))
				return index;
		return -1;
	}

	/**
	 * Asks for saving the project configuration to the user.
	 * 
	 * @return false if the cancel option has been selected and true in other
	 *         case.
	 */
	public boolean askForSavingProjectConfiguration() {

		// Are the project configuration modified
		if (AcideProjectConfiguration.getInstance().isModified()) {

			// Ask the user to save the configuration
			int returnValue = JOptionPane.showConfirmDialog(
					null,
					AcideLanguageManager.getInstance().getLabels()
							.getString("s657"), AcideLanguageManager
							.getInstance().getLabels().getString("s953"),
					JOptionPane.YES_NO_CANCEL_OPTION);

			// If it is not the cancel or the closed option
			if (returnValue != JOptionPane.CANCEL_OPTION
					&& returnValue != JOptionPane.CLOSED_OPTION) {

				// If it is yes
				if (returnValue == JOptionPane.YES_OPTION) {

					// If it is not the default project
					if (!AcideProjectConfiguration.getInstance()
							.isDefaultProject()) {

						// Enables the menu
						AcideMainWindow.getInstance().getMenu()
								.getProjectMenu().getSaveProjectMenuItem()
								.setEnabled(true);

						// Saves the project
						AcideMainWindow.getInstance().getMenu()
								.getProjectMenu().getSaveProjectMenuItem()
								.doClick();
						saveThemesConfiguration();
					}
				}
			} else
				return false;
		}

		return true;
	}
	public void saveThemesConfiguration() {
		
		String console = Integer.toString(AcideMainWindow.getInstance().getConsolePanel().getTextPane().getBackground().getRGB());
		String databse = Integer.toString(AcideMainWindow.getInstance().getDataBasePanel().getBackgroundColor().getRGB());
		AcideResourceManager.getInstance().setProperty("explorerPanel.backgroundColor",Integer.toString(AcideMainWindow.getInstance().getExplorerPanel().getBackgroundColor().getRGB()));
		AcideResourceManager.getInstance().setProperty("explorerPanel.foregroundColor",Integer.toString(AcideMainWindow.getInstance().getExplorerPanel().getForegroundColor().getRGB()));
		AcideResourceManager.getInstance().setProperty("databasePanel.backgroundColor",Integer.toString(AcideMainWindow.getInstance().getDataBasePanel().getBackgroundColor().getRGB()));
		AcideResourceManager.getInstance().setProperty("databasePanel.foregroundColor",Integer.toString(AcideMainWindow.getInstance().getDataBasePanel().getForegroundColor().getRGB()));
		AcideResourceManager.getInstance().setProperty("consolePanel.foregroundColor",Integer.toString(AcideMainWindow.getInstance().getConsolePanel().getTextPane().getForeground().getRGB()));
		AcideResourceManager.getInstance().setProperty("consolePanel.backgroundColor",Integer.toString(AcideMainWindow.getInstance().getConsolePanel().getTextPane().getBackground().getRGB()));
		AcideWorkbenchConfiguration.getInstance().getFileEditorConfiguration().setBackgroundColor(AcideMainWindow.getInstance().getExplorerPanel().getBackgroundColor());
		AcideWorkbenchConfiguration.getInstance().getFileEditorConfiguration().setForegroundColor(AcideMainWindow.getInstance().getExplorerPanel().getForegroundColor());
		AcideResourceManager.getInstance().setProperty("themeApplied", AcideThemesMenu.activeTheme);
	}
	/**
	 * Returns the ACIDE - A Configurable IDE project configuration project
	 * name.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration project
	 *         name.
	 */
	public String getName() {
		return _name;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration project
	 * path.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration project
	 *         path.
	 */
	public String getProjectPath() {
		return _path;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration file list
	 * size.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration file list
	 *         size.
	 */
	public int getFileListSize() {
		return _fileList.size();
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration compiler
	 * path.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration compiler
	 *         path.
	 */
	public String getCompilerPath() {
		return _compilerPath;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration compiler
	 * arguments.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration compiler
	 *         arguments.
	 */
	public String getCompilerArguments() {
		return _compilerArguments;
	}

	/**
	 * Returns the file from a list in the position given as a parameter.
	 * 
	 * @param index
	 *            position of the file.
	 * @return the file from a list in the position given as a parameter.
	 * @see AcideProjectFile
	 */
	public AcideProjectFile getFileAt(int index) {
		return _fileList.get(index);
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * project name.
	 * 
	 * @param name
	 *            new value to set.
	 */
	public void setName(String name) {
		_name = name;
	}

	/**
	 * Sets a new value to the the ACIDE - A Configurable IDE project
	 * configuration project path.
	 * 
	 * @param path
	 *            new value to set.
	 */
	public void setProjectPath(String path) {
		_path = path;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * compiler path.
	 * 
	 * @param compilerPath
	 *            new value to set.
	 */
	public void setCompilerPath(String compilerPath) {
		_compilerPath = compilerPath;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * compiler arguments.
	 * 
	 * @param compilerArguments
	 *            new value to set.
	 */
	public void setCompilerArguments(String compilerArguments) {
		_compilerArguments = compilerArguments;
	}

	/**
	 * Adds a file to the ACIDE - A Configurable IDE project configuration file
	 * list.
	 * 
	 * @param projectFile
	 *            new file to add.
	 */
	public void addFile(AcideProjectFile projectFile) {
		_fileList.add(projectFile);
	}

	/**
	 * Returns the number of files from the ACIDE - A Configurable IDE project
	 * configuration file list.
	 * 
	 * @return the number of files from the ACIDE - A Configurable IDE project
	 *         configuration file list.
	 */
	public int getNumberOfFilesFromList() {
		return _fileList.size();
	}

	/**
	 * Removes all the files from the list.
	 */
	public void removeFiles() {
		_fileList.clear();
	}

	/**
	 * Removes a file at the position of the list given as a parameter.
	 * 
	 * @param position
	 *            position of the file to remove.
	 */
	public void removeFileAt(int position) {
		_fileList.remove(position);
	}

	/**
	 * Removes a file from the list which matches with the one given as a
	 * parameter.
	 * 
	 * @param name
	 *            file name to be removed.
	 */
	public void removeFileAt(String name) {
		_fileList.remove(name);
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration compile all
	 * files flag.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration compile all
	 *         files flag.
	 */
	public boolean getCompileAllFiles() {
		return _compileAllFiles;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * compile all files flag.
	 * 
	 * @param compileAllFiles
	 *            new value to set.
	 */
	public void setCompileAllFiles(boolean compileAllFiles) {
		_compileAllFiles = compileAllFiles;
	}

	/**
	 * Returns the the ACIDE - A Configurable IDE project configuration file
	 * separator.
	 * 
	 * @return the the ACIDE - A Configurable IDE project configuration file
	 *         separator.
	 */
	public String getFileSeparator() {
		return _fileSeparator;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * file separator.
	 * 
	 * @param fileSeparator
	 *            new value to set.
	 */
	public void setFileSeparator(String fileSeparator) {
		_fileSeparator = fileSeparator;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration file
	 * extension.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration file
	 *         extension.
	 */
	public String getFileExtension() {
		return _fileExtension;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * file extension.
	 * 
	 * @param fileExtension
	 *            new value to set.
	 */
	public void setFileExtension(String fileExtension) {
		_fileExtension = fileExtension;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration is first
	 * save flag.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration is first
	 *         save flag.
	 */
	public boolean isFirstSave() {
		return _isFirstSave;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * is first save flag.
	 * 
	 * @param firstSave
	 *            new value to set.
	 */
	public void setIsFirstSave(boolean firstSave) {
		_isFirstSave = firstSave;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration menu
	 * configuration.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration menu
	 *         configuration.
	 */
	public String getMenuConfiguration() {
		return _menuConfiguration;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * menu configuration.
	 * 
	 * @param menuConfiguration
	 *            new value to set.
	 */
	public void setMenuConfiguration(String menuConfiguration) {
		_menuConfiguration = menuConfiguration;
	}
	
	/**
	 * Returns the ACIDE - A Configurable IDE project configuration menu
	 * new configuration.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration menu new
	 *         configuration.
	 */
	public String getMenuNewConfiguration() {
		return _menuNewConfiguration;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * menu new configuration.
	 * 
	 * @param menuNewConfiguration
	 *            new value to set.
	 */
	public void setMenuNewConfiguration(String menuNewConfiguration) {
		_menuNewConfiguration = menuNewConfiguration;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration tool bar
	 * configuration.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration tool bar
	 *         configuration.
	 */
	public String getToolBarConfiguration() {
		return _toolBarConfiguration;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * tool bar configuration.
	 * 
	 * @param toolBarConfiguration
	 *            new value to set.
	 */
	public void setToolBarConfiguration(String toolBarConfiguration) {
		_toolBarConfiguration = toolBarConfiguration;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration language
	 * configuration.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration language
	 *         configuration.
	 */
	public String getLanguageConfiguration() {
		return _languageConfiguration;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * language configuration.
	 * 
	 * @param languageConfiguration
	 *            new value to set.
	 */
	public void setLanguageConfiguration(String languageConfiguration) {
		_languageConfiguration = languageConfiguration;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration is project
	 * modified flag.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration is project
	 *         modified flag.
	 */
	public boolean isModified() {
		return _isModified;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * is project modified flag.
	 * 
	 * @param isProjectModified
	 *            new value to set.
	 */
	public void setIsModified(boolean isProjectModified) {

		// Stores the flag
		_isModified = isProjectModified;

		// Updates the save project in the menu bar tool bar
		AcideMainWindow.getInstance().getToolBarPanel().getMenuBarToolBar()
				.updateSaveProjectButtonState(isProjectModified);
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration shell path.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration shell path.
	 */
	public String getShellPath() {
		return _consolePanelShellPath;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration shell
	 * directory.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration shell
	 *         directory.
	 */
	public String getShellDirectory() {
		return _consolePanelShellDirectory;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * shell path.
	 * 
	 * @param shellPath
	 *            new value to set.
	 */
	public void setShellPath(String shellPath) {
		_consolePanelShellPath = shellPath;
	}
	
	/**
	 * Returns the ACIDE - A Configurable IDE project configuration shell
	 * parameters.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration shell
	 *         parameters.
	 */
	public String getShellParameters() {
		return _consolePanelParameters;
	}
	
	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * shell path.
	 * 
	 * @param shellParameters
	 *            new value to set.
	 */
	public void setShellParameters(String shellParameters) {
		_consolePanelParameters = shellParameters;
	}
	

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE console panel
	 * configuration font name.
	 * 
	 * @param fontName
	 *            new value to set.
	 */
	public void setFontName(String fontName) {
		_consolePanelFontName = fontName;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE console panel configuration font
	 * name.
	 * 
	 * @return the ACIDE - A Configurable IDE console panel configuration font
	 *         name.
	 */
	public String getFontName() {
		return _consolePanelFontName;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE console panel
	 * configuration font size.
	 * 
	 * @param fontSize
	 *            new value to set.
	 */
	public void setFontSize(int fontSize) {
		_consolePanelFontSize = fontSize;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE console panel configuration font
	 * size.
	 * 
	 * @return the ACIDE - A Configurable IDE console panel configuration font
	 *         size.
	 */
	public int getFontSize() {
		return _consolePanelFontSize;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE console panel configuration font
	 * style.
	 * 
	 * @return the ACIDE - A Configurable IDE console panel configuration font
	 *         style.
	 */
	public int getFontStyle() {
		return _consolePanelFontStyle;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE console panel
	 * configuration font style.
	 * 
	 * @param fontStyle
	 *            new value to set.
	 */
	public void setFontStyle(int fontStyle) {
		_consolePanelFontStyle = fontStyle;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE console panel configuration
	 * foreground color.
	 * 
	 * @return the ACIDE - A Configurable IDE console panel configuration
	 *         foreground color.
	 */
	public Color getForegroundColor() {
		return _consolePanelForegroundColor;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE console panel
	 * configuration foreground color.
	 * 
	 * @param foregroundColor
	 *            new value to set.
	 */
	public void setForegroundColor(Color foregroundColor) {
		_consolePanelForegroundColor = foregroundColor;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE console panel configuration
	 * background color.
	 * 
	 * @return the ACIDE - A Configurable IDE console panel configuration
	 *         background color.
	 */
	public Color getBackgroundColor() {
		return _consolePanelBackgroundColor;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE console panel
	 * configuration background color.
	 * 
	 * @param backgroundColor
	 *            new value to set.
	 */
	public void setBackgroundColor(Color backgroundColor) {
		_consolePanelBackgroundColor = backgroundColor;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE console panel configuration is
	 * echo command flag value.
	 * 
	 * @return the ACIDE - A Configurable IDE console panel configuration is
	 *         echo command flag value.
	 */
	public boolean getIsEchoCommand() {
		return _consolePanelIsEchoCommand;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE console panel
	 * configuration is echo command flag.
	 * 
	 * @param isEchoCommand
	 *            new value to set.
	 */
	public void setIsEchoCommand(boolean isEchoCommand) {
		_consolePanelIsEchoCommand = isEchoCommand;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE console panel configuration exit
	 * command.
	 * 
	 * @return the ACIDE - A Configurable IDE console panel configuration exit
	 *         command.
	 */
	public String getExitCommand() {
		return _consolePanelExitCommand;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE console panel
	 * configuration exit command.
	 * 
	 * @param exitCommand
	 *            new value to set.
	 */
	public void setExitCommand(String exitCommand) {
		_consolePanelExitCommand = exitCommand;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE console panel
	 * configuration shell directory.
	 * 
	 * @param shellDirectory
	 *            new value to set.
	 */
	public void setShellDirectory(String shellDirectory) {
		_consolePanelShellDirectory = shellDirectory;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE window configuration window
	 * height.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration window
	 *         height.
	 */
	public int getWindowHeight() {
		return _height;
	}

	/**
	 * Sets a new value for the ACIDE - A Configurable IDE window configuration
	 * window height.
	 * 
	 * @param height
	 *            new value to set.
	 */
	public void setWindowHeight(int height) {
		_height = height;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE window configuration window x
	 * coordinate.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration window x
	 *         coordinate.
	 */
	public int getXCoordinate() {
		return _xCoordinate;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE window configuration
	 * window x coordinate.
	 * 
	 * @param xCoordinate
	 *            new value to set.
	 */
	public void setXCoordinate(int xCoordinate) {
		_xCoordinate = xCoordinate;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE window configuration window y
	 * coordinate.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration window y
	 *         coordinate.
	 */
	public int getYCoordinate() {
		return _yCoordinate;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE window configuration y
	 * coordinate of the window.
	 * 
	 * @param yCoordinate
	 *            new value to set.
	 */
	public void setYCoordinate(int yCoordinate) {
		_yCoordinate = yCoordinate;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE window configuration is console
	 * panel showed flag.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration is console
	 *         panel showed flag.
	 */
	public boolean isConsolePanelShowed() {
		return _isConsolePanelShowed;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE window configuration
	 * is console panel showed flag.
	 * 
	 * @param isConsolePanelShowed
	 *            new value to set.
	 */
	public void setIsConsolePanelShowed(boolean isConsolePanelShowed) {
		_isConsolePanelShowed = isConsolePanelShowed;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE window configuration is explorer
	 * panel showed flag.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration is explorer
	 *         panel showed flag.
	 */
	public boolean isExplorerPanelShowed() {
		return _isExplorerPanelShowed;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE window configuration
	 * is explorer panel showed flag.
	 * 
	 * @param isExplorerPanelShowed
	 *            new value to set.
	 */
	public void setIsExplorerPanelShowed(boolean isExplorerPanelShowed) {
		_isExplorerPanelShowed = isExplorerPanelShowed;
	}
	
	/**
	 * Returns the ACIDE - A Configurable IDE window configuration is data
	 * base panel showed flag.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration is data
	 *         base panel showed flag.
	 */
	public boolean isDatabasePanelShowed(){
		return _isDataBasePanelShowed;
	}
	
	
	/**
	 * Sets a new value to the ACIDE - A Configurable IDE window configuration
	 * is data base panel showed flag.
	 * 
	 * @param isDataBasePanelShowed
	 *            new value to set.
	 */
	public void setIsDatabasePanelShowed(boolean isDataBasePanelShowed){
		_isDataBasePanelShowed = isDataBasePanelShowed;
	}
	
	/**
	 * Returns the ACIDE - A Configurable IDE window configuration is graph
	 * panel showed flag.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration is graph
	 *         panel showed flag.
	 */
	public boolean isGraphPanelShowed() {
		return _isGraphPanelShowed;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE window configuration
	 * is Graph panel showed flag.
	 * 
	 * @param isGraphPanelShowed
	 *            new value to set.
	 */
	public void setIsGraphPanelShowed(boolean isGraphPanelShowed) {
		_isGraphPanelShowed = isGraphPanelShowed;
	}
	
	/**
	 * Returns the ACIDE - A Configurable IDE window configuration is debug
	 * panel showed flag.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration is debug
	 *         panel showed flag.
	 */
	public boolean isDebugPanelShowed() {
		return _isDebugPanelShowed;
	}
	
	/**
	 * Sets a new value to the ACIDE - A Configurable IDE window configuration
	 * is debug panel showed flag.
	 * 
	 * @param isDebugPanelShowed
	 *            new value to set.
	 */
	public void setIsDebugPanelShowed(boolean isDebugPanelShowed) {
		_isDebugPanelShowed = isDebugPanelShowed;
	}
	
	/**
	 * Returns the ACIDE - A Configurable IDE window configuration window width.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration window width.
	 */
	public int getWindowWidth() {
		return _width;
	}

	/**
	 * Sets a new value for the ACIDE - A Configurable IDE window configuration
	 * window width.
	 * 
	 * @param width
	 *            new value to set.
	 */
	public void setWindowWidth(int width) {
		_width = width;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE window configuration horizontal
	 * split pane divider location.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration horizontal
	 *         split pane divider location.
	 */
	public int getHorizontalSplitPanelDividerLocation() {
		return _horizontalSplitPaneDividerLocation;
	}

	/**
	 * Sets a new value for the ACIDE - A Configurable IDE window configuration
	 * horizontal split pane divider location.
	 * 
	 * @param horizontalSplitPaneDividerLocation
	 *            new value to set.
	 */
	public void setHorizontalSplitPaneDividerLocation(
			int horizontalSplitPaneDividerLocation) {
		_horizontalSplitPaneDividerLocation = horizontalSplitPaneDividerLocation;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE window configuration vertical
	 * split pane divider location.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration vertical
	 *         files split pane divider location.
	 */
	public int getVerticalFilesSplitPaneDividerLocation() {
		return _verticalFilesSplitPaneDividerLocation;
	}

	/**
	 * Sets a new value for the ACIDE - A Configurable IDE window configuration
	 * vertical files split pane divider location.
	 * 
	 * @param verticalFilesSplitPaneDividerLocation SplitPaneDividerLocation
	 *            new value to set.
	 */
	public void setVerticalFilesSplitPaneDividerLocation(
			int verticalFilesSplitPaneDividerLocation) {
		_verticalFilesSplitPaneDividerLocation = verticalFilesSplitPaneDividerLocation;
	}
	
	/**
	 * Returns the ACIDE - A Configurable IDE window configuration vertical
	 * database split pane divider location.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration vertical
	 *         database split pane divider location.
	 */
	public int getVerticalDataBaseSplitPaneDividerLocation() {
		return _verticalDataBaseSplitPaneDividerLocation;
	}

	/**
	 * Sets a new value for the ACIDE - A Configurable IDE window configuration
	 * vertical database split pane divider location.
	 * 
	 * @param verticalDataBaseSplitPaneDividerLocation
	 *            new value to set.
	 */
	public void setVerticalDataBaseSplitPaneDividerLocation(
			int verticalDataBaseSplitPaneDividerLocation) {
		_verticalDataBaseSplitPaneDividerLocation = verticalDataBaseSplitPaneDividerLocation;
	}
	
	/**
	 * Returns the ACIDE - A Configurable IDE window configuration vertical
	 * graph split pane divider location.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration vertical
	 *         graph split pane divider location.
	 */
	public int getVerticalGraphSplitPaneDividerLocation() {
		return _verticalGraphSplitPaneDividerLocation;
	}

	/**
	 * Sets a new value for the ACIDE - A Configurable IDE window configuration
	 * vertical graph split pane divider location.
	 * 
	 * @param verticalGraphSplitPaneDividerLocation
	 *            new value to set.
	 */
	public void setVerticalGraphSplitPaneDividerLocation(
			int verticalGraphSplitPaneDividerLocation) {
		_verticalGraphSplitPaneDividerLocation = verticalGraphSplitPaneDividerLocation;
	}
	
	/**
	 * Returns the ACIDE - A Configurable IDE window configuration horizontal
	 * graph split pane divider location.
	 * 
	 * @return the ACIDE - A Configurable IDE window configuration horizontal
	 *         graph split pane divider location.
	 */
	public int getHorizontalGraphSplitPaneDividerLocation() {
		return _horizontalGraphSplitPaneDividerLocation;
	}

	/**
	 * Sets a new value for the ACIDE - A Configurable IDE window configuration
	 * horizontal graph split pane divider location.
	 * 
	 * @param horizontalGraphSplitPaneDividerLocation
	 *            new value to set.
	 */
	public void setHorizontalGraphSplitPaneDividerLocation(
			int horizontalGraphSplitPaneDividerLocation) {
		_horizontalGraphSplitPaneDividerLocation = horizontalGraphSplitPaneDividerLocation;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE console panel configuration buffer
	 * size.
	 * 
	 * @return the ACIDE - A Configurable IDE console panel configuration buffer
	 *         size.
	 */
	public int getBufferSize() {
		return _consolePanelBufferSize;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE console panel
	 * configuration buffer size.
	 * 
	 * @param consolePanelBufferSize
	 *            new value to set.
	 */
	public void setBufferSize(int consolePanelBufferSize) {
		_consolePanelBufferSize = consolePanelBufferSize;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration executable
	 * path.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration executable
	 *         path.
	 */
	public String getExecutablePath() {
		return _executablePath;
	}

	/**
	 * Returns the ACIDE - A Configurable IDE project configuration executable
	 * arguments.
	 * 
	 * @return the ACIDE - A Configurable IDE project configuration executable
	 *         arguments.
	 */
	public String getExecutableArguments() {
		return _executableArguments;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * executable path.
	 * 
	 * @param executablePath
	 *            new value to set.
	 */
	public void setExecutablePath(String executablePath) {
		_executablePath = executablePath;
	}

	/**
	 * Sets a new value to the ACIDE - A Configurable IDE project configuration
	 * executable arguments.
	 * 
	 * @param executableArguments
	 *            new value to set.
	 */
	public void setExecutableArguments(String executableArguments) {
		_executableArguments = executableArguments;
	}
	
	/**
	 * Gets the ACIDE - A Configurable IDE array list of the panels contained
	 * in the main window
	 * 
	 * @return
	 * 		list of panels in order of appearance
	 */
	public ArrayList<String> getPanelList() {
		return _panelList;
	}
	
	/**
	 * Sets the ACIDE - A Configurable IDE list of panels
	 * 
	 * @param panelList
	 * 		list of panels shown in the main window
	 */
	public void setPanelList(ArrayList<String> panelList) {
		_panelList = panelList;
	}
	
	
	private String getProperty(String fileContent){
		int finalPosition = fileContent.indexOf("\n", _position);
		String property = fileContent.substring(_position, finalPosition);
		_position = finalPosition + 1;
		return property;
	}

	public boolean isFileEditorIsModified() {
		return fileEditorIsModified;
	}

	public void setFileEditorIsModified(boolean fileEditorIsModified) {
		this.fileEditorIsModified = fileEditorIsModified;
	}

	public boolean isConsoleIsModified() {
		return consoleIsModified;
	}

	public void setConsoleIsModified(boolean consoleIsModified) {
		this.consoleIsModified = consoleIsModified;
	}
	
	

}