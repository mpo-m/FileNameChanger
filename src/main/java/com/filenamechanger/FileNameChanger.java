package com.filenamechanger;

import java.awt.BorderLayout;

import com.filenamechanger.frame.MainFrame;
import com.filenamechanger.panel.DatePanel;

public class FileNameChanger {

	public static void main(String[] args) {
		MainFrame frame = new MainFrame("日付データ追加");
		frame.add(new DatePanel(), BorderLayout.CENTER);
		frame.disp();
	}

}
