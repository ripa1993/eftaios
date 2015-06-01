package it.polimi.ingsw.cg_8.client.gui;

import javax.swing.JOptionPane;

public final class OptionPaneUtils {
	private OptionPaneUtils() {
	}

	public static int getSelection(JOptionPane optionPane) {
		// Default return value, signals nothing selected
		int returnValue = JOptionPane.CLOSED_OPTION;
		// Get selected value
		Object selectedValue = optionPane.getValue();
		// If none, then nothing selected
		if (selectedValue != null) {
			Object options[] = optionPane.getOptions();
			if (options == null) {
				// Default buttons, no array specified
				if (selectedValue instanceof Integer) {
					returnValue = ((Integer) selectedValue).intValue();
				}
			} else {
				// Array of option buttons specified
				for (int i = 0, n = options.length; i < n; i++) {
					if (options[i].equals(selectedValue)) {
						returnValue = i;
						break; // out of for loop
					}
				}
			}
		}
		return returnValue;
	}
}