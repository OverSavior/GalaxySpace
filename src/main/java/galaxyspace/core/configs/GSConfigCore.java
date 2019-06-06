package galaxyspace.core.configs;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.lwjgl.input.Keyboard;

import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.config.IConfigElement;
import net.minecraftforge.fml.common.FMLLog;

public class GSConfigCore
{
    public static boolean loaded;

    static Configuration config;

    public GSConfigCore(File file)
    {
        if (!GSConfigCore.loaded)
        {
        	GSConfigCore.config = new Configuration(file);
        	GSConfigCore.syncConfig(true);
        }
    }

    public static boolean enableCheckVersion;
    public static boolean enableMusic;
    
    public static boolean enableOresGeneration;
    public static boolean enableDungeonsGeneration;
    public static boolean enableNewMenu;
    public static boolean enableFogVenus;
    public static boolean enableSkyOverworld;
    public static boolean enableGCMars;
    public static boolean enableOverworldOres;

    public static int idSolarRadiation = 29;
    public static int idAntiRadiation = 30;
    public static int speedTimeTravel = 1;
    public static int guiIDGuideBook = 1001;
    
    public static boolean enableMethaneParticle;
    public static boolean enableUnreachable;
    public static boolean enableHardMode;
    public static boolean enableWorldEngine;
    public static boolean enablePlateOreDict;
    public static boolean enableSpaceSuitHUD;
    public static boolean enableRadiationSystem;
    public static boolean enablePressureSystem;
    
    public static String spacesuit_pos = "center";
    public static boolean spacesuit_small_button;
    
    public static boolean enableDebug;
    
    public static String[] radiation_armor;
    
    public static String keyOverrideToggleHelmet, keyOverrideToggleChest, keyOverrideToggleLegs, keyOverrideToggleBoots;
    public static int keyOverrideToggleHelmetI, keyOverrideToggleChestI, keyOverrideToggleLegsI, keyOverrideToggleBootsI;

    
    private static void setConfigBoolean(Property prop, List<String> propOrder, String category, String confname, String desc, boolean conf, boolean def)
    {
    	prop = config.get(category, confname, def);
        prop.setComment(desc);
        prop.setLanguageKey("gs.configgui." + confname).setRequiresMcRestart(true);;
        conf = prop.getBoolean(def);
        propOrder.add(prop.getName()); 
    }
    
    public static void syncConfig(boolean load)
    {
        List<String> propOrder = new ArrayList<String>();

        try
        {
            Property prop;

            if (!config.isChild)
            {
                if (load)
                {
                    config.load();
                }
            }

            prop = config.get(Constants.CONFIG_CATEGORY_GENERAL, "enableCheckVersion", true);
            prop.setComment("Enable/Disable Check Version.");
            prop.setLanguageKey("gc.configgui.enableCheckVersion").setRequiresMcRestart(true);
            enableCheckVersion = prop.getBoolean(true);
            propOrder.add(prop.getName());         
            
            prop = config.get(Constants.CONFIG_CATEGORY_GENERAL, "enableMusic", true);
            prop.setComment("Enable/Disable new music on GS Planets/Moons.");
            prop.setLanguageKey("gc.configgui.enableMusic").setRequiresMcRestart(true);
            enableMusic = prop.getBoolean(true);
            propOrder.add(prop.getName());      
            
            prop = config.get(Constants.CONFIG_CATEGORY_WORLDGEN, "enableOverworldOres", true);
            prop.setComment("Enable/Disable Generation Ores on Overworld.");
            prop.setLanguageKey("gc.configgui.enableOverworldOres").setRequiresMcRestart(true);
            enableOverworldOres = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_WORLDGEN, "enableOresGeneration", true);
            prop.setComment("Enable/Disable Generation Ores on Planets/Moon (Global Config).");
            prop.setLanguageKey("gc.configgui.enableOresGeneration").setRequiresMcRestart(true);
            enableOresGeneration = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_WORLDGEN, "enableDungeonsGeneration", true);
            prop.setComment("Enable/Disable Dungeons Generation on Planets/Moon (Global Config).");
            prop.setLanguageKey("gc.configgui.enableDungeonsGeneration").setRequiresMcRestart(true);
            enableDungeonsGeneration = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_DIMENSIONS, "enableRadiationSystem", true);
            prop.setComment("Enable/Disable solar radiation system.");
            prop.setLanguageKey("gc.configgui.enableRadiationSystem").setRequiresMcRestart(true);
            enableRadiationSystem = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_DIMENSIONS, "enablePressureSystem", true);
            prop.setComment("Enable/Disable atmosphere pressure system.");
            prop.setLanguageKey("gc.configgui.enablePressureSystem").setRequiresMcRestart(true);
            enablePressureSystem = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_GENERAL, "enableNewMenu", true);
            prop.setComment("Enable/Disable new Main Menu.");
            prop.setLanguageKey("gc.configgui.enableNewMenu").setRequiresMcRestart(true);
            enableNewMenu = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_DIMENSIONS, "enableMethaneParticle", true);
            prop.setComment("Enable/Disable Methane Particles.");
            prop.setLanguageKey("gc.configgui.enableMethaneParticle").setRequiresMcRestart(true);
            enableMethaneParticle = prop.getBoolean(true);
            propOrder.add(prop.getName());   
            
            prop = config.get(Constants.CONFIG_CATEGORY_DIMENSIONS, "enableGCMars", true);
            prop.setComment("Enable/Disable register Phobos and Deimos on GC Mars.");
            prop.setLanguageKey("gc.configgui.enableGCMars").setRequiresMcRestart(true);
            enableGCMars = prop.getBoolean(true);
            propOrder.add(prop.getName());     
            /*
            prop = config.get(Constants.CONFIG_CATEGORY_WORLDGEN, "Other mods ores for GC to generate on GS planets", new String [] { });
            prop.setComment("Enter IDs of other mods' ores here for Galacticraft to generate them on GalaxySpace planets. Format is BlockName or BlockName:metadata. Use optional parameters at end of each line: /RARE /UNCOMMON or /COMMON for rarity in a chunk; /DEEP /SHALLOW or /BOTH for height; /SINGLE /STANDARD or /LARGE for clump size; /XTRARANDOM for ores sometimes there sometimes not at all.  /ONLYMOON or /ONLYMARS if wanted on one planet only.  If nothing specified, defaults are /COMMON, /BOTH and /STANDARD.  Repeat lines to generate a huge quantity of ores.");
            prop.setComment("/ONLYPHOBOS, /ONLYDEIMOS, /ONLYEUROPA, /ONLYIO, /ONLYENCELADUS, /ONLYVENUS, /ONLYMERCURY, /ONLYCERES if wanted on one planet only.");
            prop.setLanguageKey("gc.configgui.otherModOreGenIDs");
            oregenIDs = prop.getStringList();
            propOrder.add(prop.getName());
            */
            prop = config.get(Constants.CONFIG_CATEGORY_DIMENSIONS, "enableFogVenus", true);
            prop.setComment("Enable/Disable Fog on Venus.");
            prop.setLanguageKey("gc.configgui.enableFogVenus").setRequiresMcRestart(true);
            enableFogVenus = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_DIMENSIONS, "enableSkyOverworld", true);
            prop.setComment("Enable/Disable new render sky on Overworld.");
            prop.setLanguageKey("gc.configgui.enableSkyOverworld").setRequiresMcRestart(true);
            enableSkyOverworld = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_GENERAL, "idSolarRadiation", idSolarRadiation);
            prop.setComment("ID Potion 'Solar Radiation'");
            prop.setLanguageKey("gc.configgui.idSolarRadiation").setRequiresMcRestart(true);
            idSolarRadiation = prop.getInt();
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_GENERAL, "idAntiRadiation", idAntiRadiation);
            prop.setComment("ID Potion 'Anti Radiation'");
            prop.setLanguageKey("gc.configgui.idAntiRadiation").setRequiresMcRestart(true);
            idAntiRadiation = prop.getInt();
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_GENERAL, "speedTimeTravel", speedTimeTravel);
            prop.setComment("Time Travel speed factor.");
            prop.setLanguageKey("gc.configgui.speedTimeTravel").setRequiresMcRestart(false);
            speedTimeTravel = prop.getInt();
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_GENERAL, "guiIDGuideBook", guiIDGuideBook);
            prop.setComment("ID gui for Guide Book.");
            prop.setLanguageKey("gc.configgui.guiIDGuideBook").setRequiresMcRestart(true);
            guiIDGuideBook = prop.getInt();
            propOrder.add(prop.getName());
                          
            prop = config.get(Constants.CONFIG_CATEGORY_GENERAL, "enableUnreachable", true);
            prop.setComment("Enable/Disable Unreachable Planets/Moons");
            prop.setLanguageKey("gc.configgui.enableUnreachable").setRequiresMcRestart(true);
            enableUnreachable = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_GENERAL, "enableHardMode", true);
            prop.setComment("Enable/Disable Hard Mode (Death of Atm. Pressure)");
            prop.setLanguageKey("gc.configgui.enableHardMode").setRequiresMcRestart(true);
            enableHardMode = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_WORLDGEN, "enableWorldEngine", true);
            prop.setComment("Enable/Disable 'World Engine' - advanced world generation");
            prop.setLanguageKey("gc.configgui.enableWorldEngine").setRequiresMcRestart(true);
            enableWorldEngine = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_GENERAL, "enablePlateOreDict", true);
            prop.setComment("Enable/Disable register OreDict for GC Compressed Plate");
            prop.setLanguageKey("gc.configgui.enablePlateOreDict").setRequiresMcRestart(true);
            enablePlateOreDict = prop.getBoolean(true);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_CLIENT, "enableSpaceSuitHUD", true);
            prop.setComment("Enable/Disable space suit HUD.");
            prop.setLanguageKey("gc.configgui.enableSpaceSuitHUD").setRequiresMcRestart(false);
            enableSpaceSuitHUD = prop.getBoolean(true);
            propOrder.add(prop.getName());
           
            prop = config.get(Constants.CONFIG_CATEGORY_DIMENSIONS, "spacesuit_small_button", false);
            prop.setComment("Enable/Disable small text for spacesuit HUD.");
            prop.setLanguageKey("gc.configgui.spacesuit_small_button").setRequiresMcRestart(true);
            spacesuit_small_button = prop.getBoolean(false);
            propOrder.add(prop.getName());   
            
            prop = config.get(Constants.CONFIG_CATEGORY_DIMENSIONS, "spacesuit_pos", spacesuit_pos);
            prop.setComment("Set HUD position. (up or top, center, down or bottom)");
            prop.setLanguageKey("gc.configgui.spacesuit_pos");
            spacesuit_pos = prop.getString();
            propOrder.add(prop.getName());   
            
            prop = config.get("development", "enableDebug", false);
            prop.setComment("Enable/Disable Debug mode");
            prop.setLanguageKey("gc.configgui.enableDebug").setRequiresMcRestart(false);
            enableDebug = prop.getBoolean(false);
            propOrder.add(prop.getName());
            
            
            prop = config.get(Constants.CONFIG_CATEGORY_GENERAL, "Radiation and Pressure Armor List", 
            		new String[] 
            		{        
            			"ic2:quantum_helmet",
            			"ic2:quantum_chestplate",
            			"ic2:quantum_leggings",
            			"ic2:quantum_boots"   				
            		
            		});
            
            prop.setComment("List armor with protect radiation and pressure. Format: 'modid:item' ");
            prop.setLanguageKey("gc.configgui.tradeIDs").setRequiresMcRestart(true);
            radiation_armor = prop.getStringList();
            propOrder.add(prop.getName());
                        
            prop = config.get(Constants.CONFIG_CATEGORY_KEYS, "Toggle Helmet Key", "KEY_F");
            prop.setComment("Leave 'KEY_' value, adding the intended keyboard character to replace the letter. Values 0-9 and A-Z are accepted");
            prop.setLanguageKey("gc.configgui.keyOverrideToggleHelmet").setRequiresMcRestart(false);
            keyOverrideToggleHelmet = prop.getString();
            keyOverrideToggleHelmetI = parseKeyValue(keyOverrideToggleHelmet);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_KEYS, "Toggle Chestplate Key", "KEY_G");
            prop.setComment("Leave 'KEY_' value, adding the intended keyboard character to replace the letter. Values 0-9 and A-Z are accepted");
            prop.setLanguageKey("gc.configgui.keyOverrideToggleChest").setRequiresMcRestart(false);
            keyOverrideToggleChest = prop.getString();
            keyOverrideToggleChestI = parseKeyValue(keyOverrideToggleChest);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_KEYS, "Toggle Legs Key", "KEY_H");
            prop.setComment("Leave 'KEY_' value, adding the intended keyboard character to replace the letter. Values 0-9 and A-Z are accepted");
            prop.setLanguageKey("gc.configgui.keyOverrideToggleLegs").setRequiresMcRestart(false);
            keyOverrideToggleLegs = prop.getString();
            keyOverrideToggleLegsI = parseKeyValue(keyOverrideToggleLegs);
            propOrder.add(prop.getName());
            
            prop = config.get(Constants.CONFIG_CATEGORY_KEYS, "Toggle Boots Key", "KEY_J");
            prop.setComment("Leave 'KEY_' value, adding the intended keyboard character to replace the letter. Values 0-9 and A-Z are accepted");
            prop.setLanguageKey("gc.configgui.keyOverrideToggleBoots").setRequiresMcRestart(false);
            keyOverrideToggleBoots = prop.getString();
            keyOverrideToggleBootsI = parseKeyValue(keyOverrideToggleBoots);
            propOrder.add(prop.getName());
            
            config.setCategoryPropertyOrder(CATEGORY_GENERAL, propOrder);

            if (config.hasChanged())
            {
                config.save();
            }
        }
        catch (final Exception e)
        {
            FMLLog.log(Level.ERROR, e, "GalaxySpace (Core) has a problem loading it's config");
        }
    }
    
    public static List<IConfigElement> getConfigElements()
    {
        List<IConfigElement> list = new ArrayList<IConfigElement>();
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_DIFFICULTY)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_GENERAL)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_CLIENT)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_CONTROLS)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_COMPATIBILITY)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_WORLDGEN)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_SERVER)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_DIMENSIONS)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_SCHEMATIC)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_ACHIEVEMENTS)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_ENTITIES)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory(Constants.CONFIG_CATEGORY_KEYS)).getChildElements());
        list.addAll(new ConfigElement(config.getCategory("development")).getChildElements());

        return list;
    }
    
    private static int parseKeyValue(String key)
    {
    	if (key.equals("KEY_A"))
    	{
    		return Keyboard.KEY_A;
    	}
    	else if (key.equals("KEY_B"))
    	{
    		return Keyboard.KEY_B;
    	}
    	else if (key.equals("KEY_C"))
    	{
    		return Keyboard.KEY_C;
    	}
    	else if (key.equals("KEY_D"))
    	{
    		return Keyboard.KEY_D;
    	}
    	else if (key.equals("KEY_E"))
    	{
    		return Keyboard.KEY_E;
    	}
    	else if (key.equals("KEY_F"))
    	{
    		return Keyboard.KEY_F;
    	}
    	else if (key.equals("KEY_G"))
    	{
    		return Keyboard.KEY_G;
    	}
    	else if (key.equals("KEY_H"))
    	{
    		return Keyboard.KEY_H;
    	}
    	else if (key.equals("KEY_I"))
    	{
    		return Keyboard.KEY_I;
    	}
    	else if (key.equals("KEY_J"))
    	{
    		return Keyboard.KEY_J;
    	}
    	else if (key.equals("KEY_K"))
    	{
    		return Keyboard.KEY_K;
    	}
    	else if (key.equals("KEY_L"))
    	{
    		return Keyboard.KEY_L;
    	}
    	else if (key.equals("KEY_M"))
    	{
    		return Keyboard.KEY_M;
    	}
    	else if (key.equals("KEY_N"))
    	{
    		return Keyboard.KEY_N;
    	}
    	else if (key.equals("KEY_O"))
    	{
    		return Keyboard.KEY_O;
    	}
    	else if (key.equals("KEY_P"))
    	{
    		return Keyboard.KEY_P;
    	}
    	else if (key.equals("KEY_Q"))
    	{
    		return Keyboard.KEY_Q;
    	}
    	else if (key.equals("KEY_R"))
    	{
    		return Keyboard.KEY_R;
    	}
    	else if (key.equals("KEY_S"))
    	{
    		return Keyboard.KEY_S;
    	}
    	else if (key.equals("KEY_T"))
    	{
    		return Keyboard.KEY_T;
    	}
    	else if (key.equals("KEY_U"))
    	{
    		return Keyboard.KEY_U;
    	}
    	else if (key.equals("KEY_V"))
    	{
    		return Keyboard.KEY_V;
    	}
    	else if (key.equals("KEY_W"))
    	{
    		return Keyboard.KEY_W;
    	}
    	else if (key.equals("KEY_X"))
    	{
    		return Keyboard.KEY_X;
    	}
    	else if (key.equals("KEY_Y"))
    	{
    		return Keyboard.KEY_Y;
    	}
    	else if (key.equals("KEY_Z"))
    	{
    		return Keyboard.KEY_Z;
    	}
    	else if (key.equals("KEY_1"))
    	{
    		return Keyboard.KEY_1;
    	}
    	else if (key.equals("KEY_2"))
    	{
    		return Keyboard.KEY_2;
    	}
    	else if (key.equals("KEY_3"))
    	{
    		return Keyboard.KEY_3;
    	}
    	else if (key.equals("KEY_4"))
    	{
    		return Keyboard.KEY_4;
    	}
    	else if (key.equals("KEY_5"))
    	{
    		return Keyboard.KEY_5;
    	}
    	else if (key.equals("KEY_6"))
    	{
    		return Keyboard.KEY_6;
    	}
    	else if (key.equals("KEY_7"))
    	{
    		return Keyboard.KEY_7;
    	}
    	else if (key.equals("KEY_8"))
    	{
    		return Keyboard.KEY_8;
    	}
    	else if (key.equals("KEY_9"))
    	{
    		return Keyboard.KEY_9;
    	}
    	else if (key.equals("KEY_0"))
    	{
    		return Keyboard.KEY_0;
    	}
    	
    	GCLog.severe("Failed to parse keyboard key: " + key + "... Use values A-Z or 0-9" );
    	
    	return 0;
    }
}