package com.filenamechanger.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.filenamechanger.handler.FileDropDownHandler;

public class DatePanel extends JPanel {

	private FileDropDownHandler fileDropDownHandler;

	public DatePanel() {
		this.fileDropDownHandler = new FileDropDownHandler();
		setLayout(new BorderLayout());
		add(createHeader(), BorderLayout.NORTH);
		add(createDropLocation(fileDropDownHandler), BorderLayout.CENTER);
	}

	private JPanel createHeader() {
		var panel = new JPanel();
		var formatCombo = new JComboBox<String>();
		formatCombo.addItem("フォーマットを選択");
		formatCombo.addItem("yyyyMMdd_");
		formatCombo.addItem("yyyy-MM-dd_");
		formatCombo.addActionListener((e) -> {
			if (formatCombo.getSelectedIndex() == 0) {
				fileDropDownHandler.setFormatPattern(null);
				return;
			}
			fileDropDownHandler.setFormatPattern((String) formatCombo.getSelectedItem());
		});
		panel.add(formatCombo);
		return panel;
	}

	private JLabel createDropLocation(FileDropDownHandler fileDropDownHandler) {
		var label = new JLabel("ファイルをドラッグアンドドロップしてください");
		label.setBorder(new LineBorder(Color.BLACK));
		label.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 12));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setTransferHandler(fileDropDownHandler);
		return label;
	}

}
