package org.omegat.plugin.machinetranslators;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class LocalApertiumTranslate {

	private static boolean localApertiumShowUnknown;
	private static String whereIsApertium;
	private static String pair;
	private static String workingDir;
	private static final String delimiter = "\n\n\n";

	public static List<String> obtainLanguagesApertiumLocalInstallation() {
		List<String> ret = new ArrayList<String>();
		String wDir = "";
		if (!"".equals(workingDir)) {
			wDir = "-d " + workingDir;
		}
		// Call Apertium with a wrong mode and get the list of installed modes:
		ProcessBuilder pb = new ProcessBuilder("sh", "-c",
				whereIsApertium + " " + wDir + " xxxxx|sed '1d'|sed 's/^[ ]*//'|tr '\n' ','|sed 's/,$//'");
		Process proc = null;
		try {
			proc = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}

		BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		String resultLine = null;
		try {
			resultLine = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
			return new ArrayList<String>();
		}
		String[] pairs = resultLine.split(",");

		for (int i = 0, n = pairs.length; i < n; ++i) {
			ret.add(pairs[i]);
		}

		proc.destroy();
		return ret;
	}

	public static void init() {

	}

	public static void kill() {

	}

	public static String translateApertiumLocalInstallation(String text) {
		ProcessBuilder pb = null;
		if ("".equals(workingDir)) {
			if (localApertiumShowUnknown) {
				pb = new ProcessBuilder(whereIsApertium, "-u", pair);
			} else {
				pb = new ProcessBuilder(whereIsApertium, pair);
			}
		} else {
			if (localApertiumShowUnknown) {
				pb = new ProcessBuilder(whereIsApertium, "-d", workingDir, "-u", pair);
			} else {
				pb = new ProcessBuilder(whereIsApertium, "-d", workingDir, pair);
			}
		}
		Process proc = null;
		try {
			proc = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		PrintWriter out = new PrintWriter(new OutputStreamWriter(proc.getOutputStream()));
		BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		out.println(text + delimiter);
		out.flush();
		out.close();

		StringBuilder output = new StringBuilder();
		try {
			String resultLine = in.readLine();
			while (resultLine != null) {
				output.append(resultLine + "\n");
				resultLine = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}

		String[] targetSegments = output.toString().split(delimiter);
		String targetText = targetSegments[0];
		proc.destroy();

		if (!localApertiumShowUnknown
				&& (targetText.contains("@") || targetText.contains("#") || targetText.contains("*"))) {
			return targetText.toLowerCase().replace("@", "").replace("#", "").replace("*", "");
		} else {
			return targetText.toLowerCase();
		}

	}

	public static List<String> translateApertiumLocalInstallation(List<String> text) {

		List<String> toRet = new ArrayList<String>();

		String delimiter = "\n\n\n";

		ProcessBuilder pb = null;
		if ("".equals(workingDir)) {
			if (localApertiumShowUnknown) {
				pb = new ProcessBuilder(whereIsApertium, "-u", pair);
			} else {
				pb = new ProcessBuilder(whereIsApertium, pair);
			}
		} else {
			if (localApertiumShowUnknown) {
				pb = new ProcessBuilder(whereIsApertium, "-d", workingDir, "-u", pair);
			} else {
				pb = new ProcessBuilder(whereIsApertium, "-d", workingDir, pair);
			}
		}
		Process proc = null;
		try {
			proc = pb.start();
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		PrintWriter out = new PrintWriter(new OutputStreamWriter(proc.getOutputStream()));
		BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream()));

		StringBuilder q = new StringBuilder();
		for (String s : text) {
			q.append(s);
			q.append(delimiter);
		}

		out.println(q.toString());
		out.flush();
		out.close();

		StringBuilder output = new StringBuilder();
		try {
			String resultLine = in.readLine();
			while (resultLine != null) {
				output.append(resultLine + "\n");
				resultLine = in.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}

		String[] targetSegments = output.toString().split(delimiter);

		for (int i = 0, n = text.size(); i < n; ++i) {
			String targetText = targetSegments[i];

			if (!localApertiumShowUnknown
					&& (targetText.contains("@") || targetText.contains("#") || targetText.contains("*"))) {
				toRet.add("");
			} else {
				toRet.add(targetText);
			}
		}

		return toRet;
	}

	public static String getPair() {
		return pair;
	}

	public static void setPair(String p) {
		pair = p;
	}

	public static boolean isLocalApertiumShowUnknown() {
		return localApertiumShowUnknown;
	}

	public static void setLocalApertiumShowUnknown(boolean l) {
		localApertiumShowUnknown = l;
	}

	public static void setWhereIsApertium(String where) {
		whereIsApertium = where;
	}

	public static String getWhereIsApertium() {
		return whereIsApertium;
	}

	public static String getWorkingDir() {
		return workingDir;
	}

	public static void setWorkingDir(String workingDir) {
		LocalApertiumTranslate.workingDir = workingDir;
	}
}
