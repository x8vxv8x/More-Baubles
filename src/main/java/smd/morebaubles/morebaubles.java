package smd.morebaubles;

import org.apache.logging.log4j.Logger;

import smd.morebaubles.baubleeffect.BaubleAttributeModifierHandler;
import smd.morebaubles.block.ModBlocks;
import smd.morebaubles.block.TESRReforger;
import smd.morebaubles.block.TileReforger;
import smd.morebaubles.capability.CapabilityWormholePins;
import smd.morebaubles.entity.ModEntities;
import smd.morebaubles.event.EventHandler;
import smd.morebaubles.item.ItemAmuletSinGluttony;
import smd.morebaubles.item.ItemAmuletSinWrath;
import smd.morebaubles.item.ItemShieldCobalt;
import smd.morebaubles.item.ItemTrinketBrokenHeart;
import smd.morebaubles.item.ModItems;
import smd.morebaubles.loot.ModLoot;
import smd.morebaubles.network.PacketHandler;
import smd.morebaubles.potion.ModPotions;
import smd.morebaubles.proxy.GuiProxy;
import smd.morebaubles.proxy.ISideProxy;
import smd.morebaubles.recipe.AnvilRecipes;
import smd.morebaubles.recipe.ModCrafting;
import smd.morebaubles.util.Config;
import smd.morebaubles.util.RegistryHelper;
import smd.morebaubles.wormhole.CommandWormhole;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION)
@Mod.EventBusSubscriber
public class morebaubles {
	@Mod.Instance
	public static morebaubles instance;

	public static final RegistryHelper registryHelper = new RegistryHelper(Tags.MOD_ID);

	public static Config config;

	public static Logger logger;

	@SidedProxy(clientSide = "smd.morebaubles.proxy.ClientProxy",
			    serverSide = "smd.morebaubles.proxy.ServerProxy")

	public static ISideProxy proxy;

	public static final String ARMOR_TEXTURE_PATH = "textures/models/armor/";

	public static final CreativeTabs TAB = new CreativeTabs("morebaubles") {

		@Override
		@SideOnly(Side.CLIENT)
		public ItemStack createIcon() {
			return new ItemStack(ModItems.trinketObsidianSkull);
		}
	};

	public static boolean isQuarkLoaded = false;
	public static boolean isBotaniaLoaded = false;
	public static boolean isAlbedoLoaded = false;

	@Mod.EventHandler
	public static void preInit(FMLPreInitializationEvent event) {
		isBotaniaLoaded = Loader.isModLoaded("botania");
		isQuarkLoaded = Loader.isModLoaded("quark");
		isAlbedoLoaded = Loader.isModLoaded("albedo");

		logger = event.getModLog();
		config = new Config("1", logger);
		config.preInit(event);
		ModConfig.initConfig();

		MinecraftForge.EVENT_BUS.register(EventHandler.class);
		MinecraftForge.EVENT_BUS.register(AnvilRecipes.class);
		MinecraftForge.EVENT_BUS.register(ModCrafting.class);
		MinecraftForge.EVENT_BUS.register(ModPotions.class);
		MinecraftForge.EVENT_BUS.register(BaubleAttributeModifierHandler.class);
		MinecraftForge.EVENT_BUS.register(ModLoot.class);

		MinecraftForge.EVENT_BUS.register(ItemShieldCobalt.class);
		MinecraftForge.EVENT_BUS.register(ItemAmuletSinGluttony.class);
		MinecraftForge.EVENT_BUS.register(ItemAmuletSinWrath.class);
		MinecraftForge.EVENT_BUS.register(ItemTrinketBrokenHeart.class);

		CapabilityWormholePins.registerCapability();
		MinecraftForge.EVENT_BUS.register(CapabilityWormholePins.class);

		ModEntities.registerEntities();
	}

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		ModBlocks.registerToRegistry();
		registryHelper.registerBlocks(event);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		ModItems.registerToRegistry();
		registryHelper.registerItems(event);
		ModItems.registerOreDictionaryEntries();
	}

	// TODO maybe stop using sideonly
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		registryHelper.registerModels();
		ClientRegistry.bindTileEntitySpecialRenderer(TileReforger.class, new TESRReforger());
	}

	@Mod.EventHandler
	public static void init(FMLInitializationEvent event) {
		proxy.addRenderLayer();
		ModItems.registerOtherModOreDictionaryEntries();
		//		List<Item> order = Arrays.asList(item1, item2, item3...);
		//		tabSorter = Ordering.explicit(order).onResultOf(ItemStack::getItem);
		NetworkRegistry.INSTANCE.registerGuiHandler(instance, new GuiProxy());
		PacketHandler.registerMessages();
	}

	@Mod.EventHandler
	public static void postInit(FMLPostInitializationEvent event) {
		config.postInit(event);
	}

	@Mod.EventHandler
	public static void serverStart(FMLServerStartingEvent event) {
		// TODO does using `new` screw things up?
		event.registerServerCommand(new CommandWormhole());
	}
}
