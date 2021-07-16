package me.desertfox.prison.utils;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nullable;

/**
 * @author DesertFoxHU
 * @version 1.3.5
 */

public class CustomYml {

	/**
	 * Load all possible files
	 */
	public static List<CustomYml> loadAllFromDirectory(JavaPlugin plugin, String directory){
		List<CustomYml> list = new ArrayList<>();
		File file = new File(plugin.getDataFolder() + File.separator + directory);

		if(!file.exists()) return list;

		for(File child : file.listFiles()){
			if(child.isFile()){
				CustomYml configFile = new CustomYml(plugin).createNewOrLoad(directory + File.separator + child.getName(), null);
				if(configFile != null){
					list.add(configFile);
				}
			}
		}
		return list;
	}

	private JavaPlugin p;
	private File file;
	private FileConfiguration config;
	
	public CustomYml(JavaPlugin p) {
		this.p = p;
	}

	/**
	 * Creates or load a configuration file<br>
	 *
	 * @param path Path to physical file
	 */
	public CustomYml createNewOrLoad(String path) {
		createNewOrLoad(path, path);
		return this;
	}

	/**
	 * Creates or load a configuration file<br>
	 *
	 * @param path Path to physical file
	 * @param fileFromJarPath Path to the file in the .jar
	 *                           <br>If the value is not null, the file from this jar file will be extracted to the physical path
	 */
	public CustomYml createNewOrLoad(String path, @Nullable String fileFromJarPath) {
		if(!path.contains(".yml")) {
			path += ".yml";
		}
		
		file = new File(p.getDataFolder() + File.separator + path);
		if(fileFromJarPath == null && !file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(fileFromJarPath != null && !file.exists()){
			file.getParentFile().mkdirs();
			p.saveResource(fileFromJarPath, false);
		}
		
		config = new YamlConfiguration();
		config = YamlConfiguration.loadConfiguration(file);

		return this;
	}
	
	public FileConfiguration getConfig() {
		return config;
	}

	public File getFile(){
		return file;
	}

	/**
	 * Saves the changes into the physical file
	 */
	public void saveToFile() {
		try {
			config.save(file);
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Load the changes from the physical file<br>
	 * If somebody change the file, you can refresh the file with this method<br>
	 * Otherwise we wont notice the changes
	 */
	public void loadFromFile() {
		try {
			config.load(file);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
	}

}