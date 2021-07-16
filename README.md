# Usage Example

```java
public class Configs extends JavaPlugin {

    private static CustomYml config;
    
    public void onEnable(){
      loadConfigs();
    }

    public void loadConfigs(){
        config = new CustomYml(this).createNewOrLoad("config.yml"); //Makes a new instance
        
        getConfig().set("a", "b");
        config.saveToFile(); //Saving the changes into the physical file
    }

    public static CustomYml getConfig() { return config; }
}
```

Its very easy to use, and pretty straight forward.<br>

