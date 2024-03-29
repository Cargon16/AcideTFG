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
package acide.gui.menuBar.configurationMenu.debugPanelMenu.listeners;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JColorChooser;

import acide.gui.graphCanvas.AcideGraphCanvas;
import acide.gui.mainWindow.AcideMainWindow;
/**
 * ACIDE - A Configurable IDE debug panel arrow color direct menu item listener.
 * 
 * @version 0.16
 */
public class AcideDebugPanelArrowColorInverseMenuItemListener implements
		ActionListener {

	/*
	 * (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		action(actionEvent);

	}
	
	public static void action(ActionEvent actionEvent){
		//shows the color chooser dialog
		Color c =JColorChooser.showDialog(AcideMainWindow.getInstance(), "Color", AcideGraphCanvas.getInstance().getNodeColor());
		if(c!=null){
			//updates the color of the arrow and repaint the graph		
			AcideMainWindow.getInstance().getDebugPanel().getTraceDatalogPanel().getCanvas().setLinkColor2(c);
			AcideMainWindow.getInstance().getDebugPanel().getTraceDatalogPanel().getCanvas().repaint();
			AcideMainWindow.getInstance().getDebugPanel().getTraceSQLPanel().getCanvas().setLinkColor2(c);
			AcideMainWindow.getInstance().getDebugPanel().getTraceSQLPanel().getCanvas().repaint();
			AcideMainWindow.getInstance().getDebugPanel().getDebugSQLPanel().getCanvas().setLinkColor2(c);
			AcideMainWindow.getInstance().getDebugPanel().getDebugSQLPanel().getCanvas().repaint();
		}	
	}

}
