package acide.gui.menuBar.configurationMenu.themesMenu;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/*
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import acide.configuration.menu.AcideInsertedItem;
import acide.configuration.menu.AcideInsertedItemListener;
import acide.configuration.menu.AcideInsertedMenu;
import acide.configuration.menu.AcideMenuConfiguration;
import acide.configuration.menu.AcideMenuItemConfiguration;
import acide.configuration.menu.AcideMenuItemsConfiguration;
import acide.configuration.menu.AcideMenuObjectConfiguration;
import acide.configuration.menu.AcideMenuSubmenuConfiguration;
import acide.configuration.project.AcideProjectConfiguration;
import acide.gui.mainWindow.AcideMainWindow;
import acide.gui.menuBar.configurationMenu.AcideConfigurationMenu;
import acide.language.AcideLanguageManager;
import acide.log.AcideLog;
import acide.resources.AcideResourceManager;
import acide.utils.IconsUtils;

/**
 * ACIDE - A Configurable IDE tool bar menu.
 * 
 * @version 0.11
 * @see JMenu
 */
public class AcideThemesMenu extends JMenu {

	/**
	 * ACIDE - A Configurable IDE tool bar menu tool bar menu class serial version
	 * UID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ACIDE - A Configurable IDE themes menu name.
	 */
	public final static String THEME_MENU_NAME = "Themes";
	/**
	 * ACIDE - A Configurable IDE toolbar menu name.
	 */
	public final static String THEMES_CONFIGURATION = "Themes configuration";
	/**
	 * ACIDE - A Configurable IDE theme menu new theme menu item.
	 */
	private JMenuItem themesConfiguration;
	/**
	 * ACIDE - A Configurable IDE theme menu new theme menu item has been inserted.
	 */
	private boolean _themesConfigurationInserted;
	/**
	 * ACIDE - A Configurable IDE theme menu configuration menu.
	 */
	private AcideMenuSubmenuConfiguration _themeSubmenuConfiguration;
	/**
	 * ACIDE - A Configurable IDE inserted menus hashmap.
	 */
	private HashMap<String, AcideInsertedMenu> _insertedMenus;
	/**
	 * ACIDE - A Configurable IDE inserted items hashmap.
	 */
	private HashMap<String, AcideInsertedItem> _insertedItems;
	/**
	 * ACIDE - A Configurable IDE array list of inserted objects.
	 */
	private ArrayList<AcideMenuObjectConfiguration> _insertedObjects;

	/**
	 * Creates a new ACIDE - A Configurable IDE tool bar menu.
	 */
	public AcideThemesMenu() {

		_themesConfigurationInserted = false;

		_insertedItems = new HashMap<String, AcideInsertedItem>();

		_insertedMenus = new HashMap<String, AcideInsertedMenu>();

		_insertedObjects = new ArrayList<AcideMenuObjectConfiguration>();

		// Builds the menu components
		buildComponents();

		// Adds the components to the menu
		addComponents();

		// Sets the text of the tool bar menu components
		setTextOfMenuComponents();
	}

	/**
	 * Adds the components to the ACIDE - A Configurable IDE tool bar menu.
	 */
	public void addComponents() {

		File folder = new File("./configuration/themes");
		File[] themesFiles = folder.listFiles();
		Properties prop = new Properties();
		if (themesFiles.length != 0) {
			for (File arch : themesFiles) {
				JMenuItem openMenuItem = new JMenuItem(arch.getName().replaceFirst("[.][^.]+$", ""), null);
				openMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							prop.load(new FileInputStream(arch.getPath()));
							String colorB = prop.getProperty("backgroundColor");
							String colorF = prop.getProperty("foregroundColor");
							changeTheme(new Color(Integer.parseInt(colorB)), new Color(Integer.parseInt(colorF)));
						} catch (IOException e1) {}
					}
				});
				if(!_insertedItems.containsKey(arch.getName().replaceFirst("[.][^.]+$", ""))) {
				add(openMenuItem);
				_insertedItems.put(arch.getName().replaceFirst("[.][^.]+$", ""), null);
				}
			}
		}

		Iterator<Object> it = AcideMenuItemsConfiguration.getInstance().getMenuItemsManager()
				.getSubmenu(AcideConfigurationMenu.CONFIGURATION_MENU_NAME).getItemsManager()
				.getSubmenu(THEME_MENU_NAME).getItemsManager().managerIterator();
		while (it.hasNext()) {
			AcideMenuObjectConfiguration ob = (AcideMenuObjectConfiguration) it.next();
			String name = ob.getName();
			if (name.equals(THEMES_CONFIGURATION)) {
				// Adds the new tool bar menu item to the menu
				add(themesConfiguration);
				_themesConfigurationInserted = true;
			} else {
				if (ob.isSubmenu()) {
					add(_insertedMenus.get(ob.getName()));
				} else {
					add(_insertedItems.get(ob.getName()));
				}
			}
		}

		if (!_themesConfigurationInserted)
			add(themesConfiguration);

	}

	/**
	 * Builds the ACIDE - A Configurable IDE theme menu components.
	 */
	private void buildComponents() {

		if (!AcideMenuItemsConfiguration.getInstance().getMenuItemsManager()
				.getSubmenu(AcideConfigurationMenu.CONFIGURATION_MENU_NAME).hasSubmenu(THEME_MENU_NAME)) {
			AcideMenuItemsConfiguration.getInstance().getMenuItemsManager()
					.getSubmenu(AcideConfigurationMenu.CONFIGURATION_MENU_NAME)
					.insertObject(new AcideMenuSubmenuConfiguration(THEME_MENU_NAME));
		}

		Iterator<Object> it = AcideMenuItemsConfiguration.getInstance().getMenuItemsManager()
				.getSubmenu(AcideConfigurationMenu.CONFIGURATION_MENU_NAME).getItemsManager()
				.getSubmenu(THEME_MENU_NAME).getItemsManager().managerIterator();

		while (it.hasNext()) {
			AcideMenuObjectConfiguration ob = (AcideMenuObjectConfiguration) it.next();
			String name = ob.getName();
			if (isOriginal(name)) {
				_insertedObjects.add(ob);
				if (ob.isSubmenu()) {
					AcideMenuSubmenuConfiguration obSubmenu = (AcideMenuSubmenuConfiguration) ob;
					_insertedMenus.put(ob.getName(), new AcideInsertedMenu(obSubmenu));
				} else {
					AcideMenuItemConfiguration obItem = (AcideMenuItemConfiguration) ob;
					_insertedItems.put(obItem.getName(),
							new AcideInsertedItem(IconsUtils.getIcon(obItem.getImage()), obItem));
				}
			}
		}

		// Creates the new theme menu item
		ImageIcon icon = IconsUtils.getIcon(AcideMenuItemsConfiguration.getInstance().getMenuItemsManager()
				.getSubmenu(AcideConfigurationMenu.CONFIGURATION_MENU_NAME).getSubmenu(THEME_MENU_NAME)
				.getItem(THEMES_CONFIGURATION).getImage());
		if (icon != null)
			themesConfiguration = new JMenuItem(icon);
		else
			themesConfiguration = new JMenuItem();

		// Sets the new tool bar menu item name
		themesConfiguration.setName(THEME_MENU_NAME);
	}

	/**
	 * Sets the text of the ACIDE - A Configurable IDE tool bar menu components with
	 * the labels in the selected language to display.
	 */
	public void setTextOfMenuComponents() {

		// Sets the new tool bar menu item text
		themesConfiguration.setText(AcideLanguageManager.getInstance().getLabels().getString("s2381"));

		Iterator<AcideMenuObjectConfiguration> it = _insertedObjects.iterator();
		while (it.hasNext()) {
			AcideMenuObjectConfiguration ob = it.next();
			if (ob.isSubmenu()) {
				_insertedMenus.get(ob.getName()).setText(ob.getName());
				_insertedMenus.get(ob.getName()).setTextOfMenuComponents();
			} else {
				_insertedItems.get(ob.getName()).setText(ob.getName());
			}
		}
	}

	/**
	 * Gets if the menu name given as parameter is original
	 * 
	 * @param name the name we want to check
	 * @return if the name given as parameter is original
	 */
	public boolean isOriginal(String name) {
		if (!(name.equals(THEMES_CONFIGURATION))) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Sets the ACIDE - A Configurable IDE tool bar menu menu item listeners.
	 */
	public void setListeners() {

		// Sets the new tool bar menu item action listener
		themesConfiguration
				// .addActionListener(new AcideNewToolBarMenuItemListener());
				.addActionListener(new AcideInsertedItemListener(AcideMenuItemsConfiguration.getInstance()
						.getSubmenu(AcideConfigurationMenu.CONFIGURATION_MENU_NAME).getSubmenu(THEME_MENU_NAME)
						.getItem(THEMES_CONFIGURATION)));

	}

	/**
	 * Updates the ACIDE - A Configurable IDE tool bar menu components visibility
	 * with the menu configuration.
	 */
	public void updateComponentsVisibility() {

		AcideMenuItemConfiguration themeConfiguration;

		_themeSubmenuConfiguration = AcideMenuItemsConfiguration.getInstance()
				.getSubmenu(AcideConfigurationMenu.CONFIGURATION_MENU_NAME).getSubmenu(THEME_MENU_NAME);

		// Sets the new tool bar menu item to visible or not visible
		themeConfiguration = _themeSubmenuConfiguration.getItem(THEMES_CONFIGURATION);
		themesConfiguration.setVisible(themeConfiguration.isVisible());

		Iterator<AcideMenuObjectConfiguration> it = _insertedObjects.iterator();
		while (it.hasNext()) {
			AcideMenuObjectConfiguration ob = it.next();
			if (ob.isSubmenu()) {
				_insertedMenus.get(ob.getName()).updateComponentsVisibility();
				_insertedMenus.get(ob.getName()).setVisible(ob.isVisible());
			} else {
				_insertedItems.get(ob.getName()).setVisible(ob.isVisible());
			}
		}

		// Sets the tool bar menu to visible or not visible
		_themeSubmenuConfiguration.setVisible(themesConfiguration.isVisible());

		_themeSubmenuConfiguration.setErasable(false);

		try {
			// Save the configuration for the menu that could have been modified
			AcideMenuConfiguration.getInstance()
					.saveMenuConfigurationFile("./configuration/menu/lastModified.menuConfig");

			// Gets the the ACIDE - A Configurable IDE current menu
			// configuration
			String currentMenuConfiguration = AcideResourceManager.getInstance()
					.getProperty("currentMenuConfiguration");

			if (!currentMenuConfiguration.endsWith("lastModified.menuConfig")
					&& !currentMenuConfiguration.endsWith("newMenu.menuConfig")) {

				// Updates the the ACIDE - A Configurable IDE previous
				// menu
				// configuration
				AcideResourceManager.getInstance().setProperty("previousMenuConfiguration", currentMenuConfiguration);
			}

			// Updates the the ACIDE - A Configurable IDE current menu
			// configuration
			AcideResourceManager.getInstance().setProperty("currentMenuConfiguration",
					"./configuration/menu/lastModified.menuConfig");
		} catch (Exception exception2) {

			// Updates the log
			AcideLog.getLog().error(exception2.getMessage());
			exception2.printStackTrace();
		}

	}

	/**
	 * Returns the ACIDE - A Configurable IDE tool bar menu new tool bar menu item.
	 * 
	 * @return the ACIDE - A Configurable IDE tool bar menu new tool bar menu item.
	 */
	public JMenuItem getThemeMenuItem() {
		return themesConfiguration;
	}
	
	private void changeTheme(Color backgroundColor, Color foregroundColor) {
		// Updates the log
					AcideLog.getLog().info("1043");

					// Apply the changes to the opened file editor panels
					AcideMainWindow.getInstance().getFileEditorManager().setBackground(backgroundColor);
					AcideMainWindow.getInstance().getFileEditorManager().getTabbedPane().setOpaque(true);
					AcideMainWindow.getInstance().getFileEditorManager().getTabbedPane().setBackground(backgroundColor.darker());
					AcideMainWindow.getInstance().getFileEditorManager().getTabbedPane().setForeground(foregroundColor);
					for (int index = 0; index < AcideMainWindow.getInstance().getFileEditorManager()
							.getNumberOfFileEditorPanels(); index++) {

						// Updates the ACIDE - A Configurable IDE file editor

						AcideMainWindow.getInstance().getFileEditorManager().getFileEditorPanelAt(index)
								.getActiveTextEditionArea().setBackground(backgroundColor);
						AcideMainWindow.getInstance().getFileEditorManager().getFileEditorPanelAt(index)
								.getActiveTextEditionArea().setForeground(foregroundColor);
						AcideMainWindow.getInstance().getFileEditorManager().getFileEditorPanelAt(index).changeColor(backgroundColor, foregroundColor);
					}
					
					//Apply changes to toolbar
					AcideMainWindow.getInstance().getToolBarPanel().changeColor(backgroundColor.darker(), foregroundColor);
					
					//Apply changes to statusBar
					AcideMainWindow.getInstance().getStatusBar().changeColor(backgroundColor.darker(), foregroundColor);
					
					// Apply changes to menuBar
					AcideMainWindow.getInstance().getMenu().paintMenuBar(backgroundColor.darker(), foregroundColor);

					// Apply changes to the database panel
					AcideMainWindow.getInstance().getDataBasePanel().changeColor(backgroundColor,
							foregroundColor);

					// Apply changes to the explorer panel
					AcideMainWindow.getInstance().getExplorerPanel().setBackgroundColor(backgroundColor,
							foregroundColor);

					// Apply changes to debugPanel
					AcideMainWindow.getInstance().getDebugPanel().setBackgroundColor(backgroundColor,
							foregroundColor);
					AcideMainWindow.getInstance().getGraphPanel().setBackgroundColor(backgroundColor,
							foregroundColor);

					// Apply changes to the console panel
					AcideMainWindow.getInstance().getConsolePanel().changeColor(backgroundColor, foregroundColor);
					AcideResourceManager.getInstance().setProperty("consolePanel.backgroundColor",
							Integer.toString(backgroundColor.getRGB()));
					AcideResourceManager.getInstance().setProperty("consolePanel.foregroundColor",
							Integer.toString(foregroundColor.getRGB()));

					// Notify that main configuration has been changed
					AcideProjectConfiguration.getInstance().setIsModified(true);
	}

}
