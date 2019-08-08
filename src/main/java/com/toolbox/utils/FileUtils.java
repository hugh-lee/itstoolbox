package com.toolbox.utils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Collection;

public class FileUtils {

	public static String readFileToString(File file) {
		try {
			return org.apache.commons.io.FileUtils.readFileToString(file, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static String readFileToString(String file) {
		try {
			return org.apache.commons.io.FileUtils.readFileToString(new File(file), "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeStringToFile(File file, String str) {
		try {
			org.apache.commons.io.FileUtils.writeStringToFile(file, str, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void writeStringToFile(String file, String str) {
		try {
			org.apache.commons.io.FileUtils.writeStringToFile(new File(file), str, "UTF-8");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Collection<File> listFiles(final File directory, final String[] extensions, final boolean recursive) {
		Collection<File> files = org.apache.commons.io.FileUtils.listFiles(directory, extensions, recursive);
		return files;
	}

	public static Collection<File> listFiles(final File directory, final String extension, final boolean recursive) {
		Collection<File> files = org.apache.commons.io.FileUtils.listFiles(directory, new String[] { extension },
				recursive);
		return files;
	}

	public static Collection<File> listFiles(final String directory, final String extension, final boolean recursive) {
		Collection<File> files = org.apache.commons.io.FileUtils.listFiles(new File(directory),
				new String[] { extension }, recursive);
		return files;
	}

	public static void copyDirectory(String srcDir, String destDir) {
		try {
			org.apache.commons.io.FileUtils.copyDirectory(new File(srcDir), new File(destDir));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static void copyFileToDirectory(String srcFile, String destDir) {
		try {
			org.apache.commons.io.FileUtils.copyFileToDirectory(new File(srcFile), new File(destDir));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
