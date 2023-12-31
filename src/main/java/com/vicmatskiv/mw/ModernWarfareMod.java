package com.vicmatskiv.mw;

import java.io.File;

import javax.xml.transform.stream.StreamSource;

import com.ginsway.mwn.ModernWarfareInfo;
import com.vicmatskiv.weaponlib.ModContext;
import com.vicmatskiv.weaponlib.command.BalancePackCommand;
import com.vicmatskiv.weaponlib.command.CraftingFileCommand;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlInitializationEvent;
import com.vicmatskiv.weaponlib.compatibility.CompatibleFmlPreInitializationEvent;
import com.vicmatskiv.weaponlib.config.BalancePackManager;
import com.vicmatskiv.weaponlib.config.ConfigurationManager;
import com.vicmatskiv.weaponlib.crafting.CraftingFileManager;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;

@Mod(modid = ModernWarfareInfo.MODID, version = ModernWarfareInfo.VERSION, guiFactory = "com.vicmatskiv.weaponlib.config.ConfigGUIFactory")
public class ModernWarfareMod {

	private static final String DEFAULT_CONFIG_RESOURCE = "/mw.cfg";
    private static final String MODERN_WARFARE_CONFIG_FILE_NAME = "ModernWarfare.cfg";
    //public static final String MODID = "mw";
	//public static final String VERSION = "0.7.1";

    @SidedProxy(serverSide = "com.vicmatskiv.weaponlib.CommonModContext", clientSide = "com.vicmatskiv.weaponlib.ClientModContext")
    public static ModContext MOD_CONTEXT;

    public static final SimpleNetworkWrapper CHANNEL = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);

    public static CreativeTabs ArmorTab = new ArmorTab(
            CreativeTabs.getNextID(), "ArmorTab");

	public static CreativeTabs AssaultRiflesTab = new AssaultRiflesTab(
            CreativeTabs.getNextID(), "AssaultRifles_tab");

	public static CreativeTabs CombatServerTab = new CombatServerTab(
            CreativeTabs.getNextID(), "CombatServer_tab");

	public static CreativeTabs AmmoTab = new AmmoTab(
            CreativeTabs.getNextID(), "AmmoTab");

	public static CreativeTabs AttachmentsTab = new AttachmentsTab(
            CreativeTabs.getNextID(), "AttachmentsTab");

	public static CreativeTabs GrenadesTab = new GrenadesTab(
            CreativeTabs.getNextID(), "GrenadesTab");

	public static CreativeTabs GadgetsTab = new GadgetsTab(
            CreativeTabs.getNextID(), "GadgetsTab");

	public static CreativeTabs PropsTab = new PropsTab(
            CreativeTabs.getNextID(), "props_tab");

	public static CreativeTabs BlocksTab = new BlocksTab(
	        CreativeTabs.getNextID(), "BlocksTab");

    @SidedProxy(serverSide = "com.vicmatskiv.mw.CommonProxy",
            clientSide = "com.vicmatskiv.mw.ClientProxy")
    public static CommonProxy proxy;

    public static boolean oreGenerationEnabled = true;
    private ConfigurationManager configurationManager;

    @EventHandler
    public void init(FMLPreInitializationEvent event) {
        initConfigurationManager(event);
        proxy.preInit(this, configurationManager, new CompatibleFmlPreInitializationEvent(event));
        initRecipies(event);
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(this, configurationManager, new CompatibleFmlInitializationEvent(event));
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
        if(configurationManager != null) {
            configurationManager.save();
        }

        proxy.postInit(this, configurationManager, event);
    }

    @EventHandler
    public void onServerStarting(FMLServerStartingEvent event) {
        event.registerServerCommand(new BalancePackCommand());
        event.registerServerCommand(new CraftingFileCommand());
        BalancePackManager.loadDirectory();
        CraftingFileManager.getInstance().loadDirectory();
    }

    private void initConfigurationManager(FMLPreInitializationEvent event) {
        File parentDirectory = event.getSuggestedConfigurationFile().getParentFile();
	    File configFile;
	    if(parentDirectory != null) {
	        configFile = new File(parentDirectory, MODERN_WARFARE_CONFIG_FILE_NAME);
	    } else {
	        configFile = new File(MODERN_WARFARE_CONFIG_FILE_NAME);
	    }
		configurationManager = new ConfigurationManager.Builder()
		        .withUserConfiguration(configFile)
		        .withDefaultConfiguration(new StreamSource(getClass().getResourceAsStream(DEFAULT_CONFIG_RESOURCE)))
		        .build();
    }

    // ItemRecipes
    //@EventHandler
    public void initRecipies(FMLPreInitializationEvent event) {
        RecipeManager.init(MOD_CONTEXT);
    }
}
