package acide.gui.debugPanel.debugSQLPanel.listeners;

import acide.gui.debugPanel.debugCanvas.AcideDebugCanvas;
import acide.gui.debugPanel.debugSQLPanel.AcideDebugSQLDebugWindow;
import acide.gui.debugPanel.utils.AcideDebugHelper;
import acide.gui.mainWindow.AcideMainWindow;
import acide.language.AcideLanguageManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AcideDebugSQLPanelMissingNodeListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Gets the canvas
            AcideDebugCanvas canvas = AcideMainWindow.getInstance()
                    .getDebugPanel().getDebugSQLPanel().getCanvas();
            String view = canvas.getSelectedNode().getLabel().split("/")[0];
            String userInput = AcideDebugHelper.getUserInputTuple(view, "missing");
            if(!userInput.isEmpty()) {
                String action = "missing(" + view + "(" + userInput +"))";
                if (AcideDebugHelper.isDebugging() && !AcideDebugHelper.isRootView(view)) {
                    AcideDebugHelper.performNodeDebug(view, action);
                } else {
                    AcideMainWindow.getInstance().getDebugPanel().getDebugSQLPanel().setDebuging(true);
                    AcideDebugHelper.startNodeDebug(view, action);
                }
                AcideDebugSQLDebugWindow.getInstance().addError(AcideLanguageManager.getInstance()
                        .getLabels().getString("s2363") + " " + view + " " +
                        AcideLanguageManager.getInstance()
                                .getLabels().getString("s2364") + " (" + userInput + ")");
            }
        } catch (Exception ex) {
            AcideMainWindow.getInstance().getDebugPanel()
                    .setCursor(Cursor.getDefaultCursor());
            throw ex;
        }
    }
}