package com.filenamechanger.handler;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.TransferHandler;

public class FileDropDownHandler extends TransferHandler {

	private String pattern;
	private DateTimeFormatter formatter;

	public FileDropDownHandler() {
		this.formatter = null;
	}

	public void setFormatPattern(String pattern) {
		if (pattern == null) {
			formatter = null;
			return;
		}
		this.pattern = pattern;
		formatter = DateTimeFormatter.ofPattern(pattern);
	}

	@Override
	public boolean canImport(TransferSupport support) {
		return formatter != null;
	}

	@Override
	public boolean importData(TransferSupport support) {
		if (!support.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
			return false;
		}
		Transferable transferable = support.getTransferable();
		try {
			List<?> files = (List<?>) transferable.getTransferData(DataFlavor.javaFileListFlavor);
			files.stream().forEach(f -> addDateAtStart((File) f));
			return true;
		} catch (UnsupportedFlavorException e) {
			JOptionPane.showMessageDialog(null, "サポートされない形式です", "エラー", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "ファイルの読み取りに失敗しました", "エラー", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}

	private void addDateAtStart(File file) {
		String fileName = file.getName();
		String filePath = file.getPath();
		String now = LocalDate.now().format(formatter);
		try {
			LocalDate.parse(fileName.substring(0, pattern.length()), formatter);
		} catch (DateTimeParseException e) {
			var renameFile = new File(filePath.replace(fileName, "") + now + fileName);
			file.renameTo(renameFile);
		}
	}

}
