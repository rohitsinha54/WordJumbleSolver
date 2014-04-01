package com.rohitsinha.wordjumble;

/**
 * Class to determine the Operating System on which the machine.
 * <p/>
 * References: http://www.mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname/
 */
public class OSType {

	private static final String OS = System.getProperty("os.name").toLowerCase();

	public static boolean isWindows() { return (OS.contains("win")); }

	public static boolean isMac() {
		return (OS.contains("mac"));
	}

	public static boolean isUnix() {
		return (OS.contains("nix") || OS.contains("nux") || OS.indexOf("aix") > 0);
	}

	public static boolean isSolaris() {
		return (OS.contains("sunos"));
	}
}
