package com.sigma.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Configuration {
	private static Configuration instance = null;

	private class ConfigEntry {
		private Properties properties = null;
		private File file = null;
		private long modified = 0L;

		ConfigEntry(URL url) {
			file = new File(url.getFile());
			modified = file.lastModified();
			properties = new Properties();

			reload();
		}

		void reload() {
			try {
				properties.clear();
				InputStream is = new FileInputStream(file);
				InputStreamReader isr = new InputStreamReader(is, "utf-8");
				properties.load(isr);
				modified = file.lastModified();
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		String getConfig(String key) {
			if (file.lastModified() > this.modified) {
				reload();
			}

			return properties.getProperty(key);
		}
	}

	public void load(String resource) {
		load(resource, null);
	}

	private Map<String, ConfigEntry> map = new HashMap<String, ConfigEntry>();

	public void load(String resource, String prefix) {
		URL url = this.getClass().getClassLoader().getResource(resource);
		if (prefix == null) {
			prefix = "default";
		}
		map.put(prefix, new ConfigEntry(url));
	}

	public static Configuration getInstance() {
		if (instance == null) {
			instance = new Configuration();
		}

		return instance;
	}

	public String get(String k) {
		String prefix = "default", key = k;
		int pos = key.indexOf('@');
		if (pos > 0) {
			prefix = k.substring(0, pos);
			key = k.substring(pos + 1);
		}

		ConfigEntry entry = map.get(prefix);
		return entry.getConfig(key);
	}
	
	public boolean getBoolean(String k){
		return Boolean.parseBoolean(get(k));
	}
}
