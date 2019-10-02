import java.io.File;

import java.io.IOException;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author DesertFoxHU
 * @version 1.2.4
 *
 * LoadYML feature added by Xzhauloss
 */

public class CustomYML {

	private JavaPlugin p;
	private File file;
	private File folder;
	private FileConfiguration config;
	
	public CustomYML(JavaPlugin p) {
		this.p = p;
	}
	
	public File createFile(String name) {
		
		if(!name.contains(".yml")) {
			name = name + ".yml";
		}
		
		file = new File(p.getDataFolder() + File.separator + name);
		return file;
	}
	
	public File createFile(String folder, String name) {
		
		this.folder = new File(p.getDataFolder() + File.separator + folder);
		if(!this.folder.exists()) {
			this.folder.mkdir();
		}
		
		if(!name.contains(".yml")) {
			name = name + ".yml";
		}
		
		file = new File(p.getDataFolder() + File.separator + folder + File.separator + name);
		return file;
	}
	
	public FileConfiguration createYML(File f , String name) {
		
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
	
	public FileConfiguration createYML(File f, String folder, String name) {
		
		this.folder = new File(p.getDataFolder() + "\\" + folder);
		if(!this.folder.exists()) {
			this.folder.mkdir();
		}
		
		if(!name.contains(".yml")) {
			name = name + ".yml";
		}
		
		f = new File(p.getDataFolder() + "\\" + folder + "\\" + name);
		if(!f.exists()) {
			f.getParentFile().mkdirs();
			p.saveResource(folder + "\\" + name, false);
		}
		
		config = new YamlConfiguration();
		config = YamlConfiguration.loadConfiguration(f);
		return config;
		
	}
	
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
	
	public void loadYML(FileConfiguration fc, File f) {
		
		try {
			fc.load(f);
		} catch (IOException | InvalidConfigurationException e) {
			e.printStackTrace();
		}
		
	}

}
