package acide.gui.menuBar.configurationMenu.themesMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import acide.factory.gui.AcideGUIFactory;
import acide.gui.mainWindow.AcideMainWindow;

/**
 * ACIDE - A Configurable IDE configuration menu theme menu item listener.
 * 
 * @version 0.11
 * @see ActionListener
 */
public class AcideThemeMenuItemListener implements ActionListener{
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		action(actionEvent);
	}
	
	public static void action(ActionEvent actionEvent){

		// Shows the compiler configuration window with the main window as
		// parent
		AcideGUIFactory.getInstance().buildAcideThemeConfigurationWindow(
				AcideMainWindow.getInstance());
	}
}
