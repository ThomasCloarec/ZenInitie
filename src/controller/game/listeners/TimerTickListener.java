package controller.game.listeners;

import view.utils.components.TimeComponent;

import javax.swing.SwingUtilities;
import java.awt.event.ActionListener;

/**
 * The type Timer tick listener.
 */
public class TimerTickListener {
    /**
     * The Time component.
     */
    private final TimeComponent timeComponent;

    /**
     * Instantiates a new Timer tick listener.
     *
     * @param timeComponent the time component
     */
    public TimerTickListener(TimeComponent timeComponent) {
        this.timeComponent = timeComponent;
    }

    /**
     * Invoke action listener.
     *
     * @return the action listener
     */
    public ActionListener invoke() {
        return actionEvent -> {
            if (SwingUtilities.getWindowAncestor(this.timeComponent) == null || !SwingUtilities.getWindowAncestor(this.timeComponent).isVisible()) {
                this.timeComponent.stopTimer();
            }

            this.timeComponent.setSeconds(this.timeComponent.getSeconds() + 1);
            if (this.timeComponent.getSeconds() >= 60) {
                this.timeComponent.setSeconds(0);
                this.timeComponent.setMinutes(this.timeComponent.getMinutes() + 1);
            }

            if (this.timeComponent.getMinutes() < 100) {
                this.timeComponent.setText("<html><center><h1 margin=\"0\">" + (this.timeComponent.getMinutes() == 0 ? "" : String.format("%02d", this.timeComponent.getMinutes()) + "m") + String.format("%02d", this.timeComponent.getSeconds()) + "s</h1></center></html>");
            } else {
                this.timeComponent.setText("<html><center><h1 margin=\"0\">" + this.timeComponent.getMinutes() + "m</h1></center></html>");
            }
        };
    }
}
