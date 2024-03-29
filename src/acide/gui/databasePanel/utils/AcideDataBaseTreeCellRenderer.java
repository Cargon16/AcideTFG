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
package acide.gui.databasePanel.utils;


import java.awt.Color;
import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

import acide.gui.databasePanel.Nodes.NodeConstraint;
import acide.gui.databasePanel.Nodes.NodeDB;
import acide.gui.databasePanel.Nodes.NodeDDBB;
import acide.gui.databasePanel.Nodes.NodeDefinition;
import acide.gui.databasePanel.Nodes.NodeTable;
import acide.gui.databasePanel.Nodes.NodeView;


/**																
 * ACIDE - A Configurable IDE database tree cell renderer. 
 * 
 * @version 0.11	
 * @see DefaultTreeCellRenderer																													
 */

public class AcideDataBaseTreeCellRenderer extends DefaultTreeCellRenderer {

		/**
		 * ACIDE - A Configurable IDE database tree cell renderer class serial version UID.
		 */
		private static final long serialVersionUID = 1L;
		/**
		 * ACIDE - A Configurable IDE database tree cell renderer compilable file image icon.
		 */
		private static Icon ICON_DATABASE = new ImageIcon(
				"./resources/icons/database/dataBase.png");
		/**
		 * ACIDE - A Configurable IDE database tree cell renderer main file image icon.
		 */
		private static Icon ICON_TABLE = new ImageIcon(
				"./resources/icons/database/table.png");
		/**
		 * ACIDE - A Configurable IDE database tree cell renderer folder image icon.
		 */
		private static Icon ICON_FOLDER = new ImageIcon(
				"./resources/icons/explorer/folder.png");
		/**
		 * ACIDE - A Configurable IDE database tree cell renderer default file image icon.
		 */
		private static Icon ICON_VIEW = new ImageIcon(
				"./resources/icons/database/view.png");
		
		/**
		 * ACIDE - A Configurable IDE explorer tree cell renderer default file image icon.
		 */
		private static Icon ICON_DEFAULT = new ImageIcon(
				"./resources/icons/explorer/default.png");

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * javax.swing.tree.DefaultTreeCellRenderer#getTreeCellRendererComponent
		 * (javax.swing.JTree, java.lang.Object, boolean, boolean, boolean, int,
		 * boolean)
		 */
		@Override
		public Component getTreeCellRendererComponent(JTree tree, final Object value,
				boolean isSelected, boolean expanded, boolean leaf, int row,
				boolean hasFocus) {

			super.getTreeCellRendererComponent(tree, value, isSelected, expanded, leaf,
					row, hasFocus);
			
			setIcon(ICON_FOLDER);
			
			this.setBackgroundNonSelectionColor(null);
			
			if(value instanceof NodeDDBB || value instanceof NodeDB){
				setIcon(ICON_DATABASE);
			}
			if(value instanceof NodeTable){
				setIcon(ICON_TABLE);
			}
			else if(value instanceof NodeView){
				setIcon(ICON_VIEW);
			} if(value instanceof NodeDefinition)
				setIcon(ICON_DEFAULT);
			else if((value instanceof DefaultMutableTreeNode &&  ((DefaultMutableTreeNode) value).isLeaf())||
					(value instanceof NodeConstraint))
				setIcon(ICON_DEFAULT);
				
			
			return this;
		}

		public void setForegroundColor(Color foreGroundColor) {
			this.textNonSelectionColor = foreGroundColor;
			
		}

}
