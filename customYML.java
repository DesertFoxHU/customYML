import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author DesertFoxHU
 * @version 1.1
 */

public class CustomYML {

	private JavaPlugin p;
	private File file;
	private File folder;
	private FileConfiguration config;
	
	public CustomYML(JavaPlugin p) {
		this.p = p;
	}
	
	/**
	 * @param create a new file
	 */
	public File createFile(String name) {
		
		if(!name.contains(".yml")) {
			name = name + ".yml";
		}
		
		file = new File(p.getDataFolder() + File.separator + name);
		return file;
	}
	
	/**
	 * @param create a new file in a new folder
	 */
	public File createFile(String folder, String name) {
		this.folder = new File(p.getDataFolder() + File.separator + folder);
		if(!this.folder.exists()) {
			this.folder.mkdirs();
		}
		
		if(!name.contains(".yml")) {
			name = name + ".yml";
		}
		
		file = new File(p.getDataFolder() + File.separator + folder + File.separator + name);
		return file;
	}
	
	/**
	 * @param create a new FileConfiguration
	 */
	public FileConfiguration createYML(File f ,String name) {
		
		if(!name.contains(".yml")) {
			name = name + ".yml";
		}
		
		f = new File(p.getDataFolder() + File.separator + name);
		if(!f.exists()) {
			f.getParentFile().mkdirs();
			p.saveResource(name, false);
		}
		
		config = new YamlConfiguration();
		config = YamlConfiguration.loadConfiguration(f);
		return config;
	}
	
	/**
	 * @param create a new FileConfiguration in a new folder
	 */
	public FileConfiguration createYML(File f, String folder, String name) {
		
		this.folder = new File(p.getDataFolder() + File.separator + folder);
		if(!this.folder.exists()) {
			this.folder.mkdirs();
		}
		
		if(!name.contains(".yml")) {
			name = name + ".yml";
		}
		
		f = new File(p.getDataFolder() + File.separator + folder + File.separator + name);
		if(!f.exists()) {
			f.getParentFile().mkdirs();
			p.saveResource(name, false);
		}
		
		config = new YamlConfiguration();
		config = YamlConfiguration.loadConfiguration(f);
		return config;
	}
	
	/**
	 * @param save a yml
	 */
	public boolean saveYML(FileConfiguration fc, File f) {
		try {
			fc.save(f);
			return true;
		}
		catch(IOException ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
