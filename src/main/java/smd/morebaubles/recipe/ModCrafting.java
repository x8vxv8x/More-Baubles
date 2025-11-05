package smd.morebaubles.recipe;

import smd.morebaubles.Tags;
import smd.morebaubles.morebaubles;
import smd.morebaubles.ModConfig;
import smd.morebaubles.block.ModBlocks;
import smd.morebaubles.item.ModItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import net.minecraftforge.registries.IForgeRegistry;

public class ModCrafting {
	private static boolean doesOreExist(String oreName) {
		// we should be able to just use OreDictionary.doesOreNameExist
		// but for some reason, sometimes ore names can be registered without
		// any actual items being registered, when mods do things wrong.
		return !OreDictionary.getOres(oreName, false).isEmpty();
	}

	@SubscribeEvent
	public static void registerRecipes(RegistryEvent.Register<IRecipe> event) {
		// @formatter:off
		IForgeRegistry<IRecipe> r = event.getRegistry();

		boolean copperIngot = doesOreExist("ingotCopper");
		boolean steelIngot = doesOreExist("ingotSteel");
		boolean ingotEnderium = doesOreExist("ingotEnderium");
		boolean ingotCobalt = doesOreExist("ingotCobalt");
		
		boolean runeMana = doesOreExist("runeManaB");
		boolean runeFire = doesOreExist("runeFireB");
		boolean runeWater = doesOreExist("runeWaterB");
		boolean runeAir = doesOreExist("runeAirB");
		boolean runeEarth = doesOreExist("runeEarthB");
		boolean runeSummer = doesOreExist("runeSummerB");
		boolean runeAutumn = doesOreExist("runeAutumnB");
		boolean runeWinter = doesOreExist("runeWinterB");
		boolean runeSpring = doesOreExist("runeSpringB");
		boolean runeGluttony = doesOreExist("runeGluttonyB");
		boolean runePride = doesOreExist("runePrideB");
		boolean runeWrath = doesOreExist("runeWrathB");
		boolean runeGreed = doesOreExist("runeGreedB");
		boolean runeEnvy = doesOreExist("runeEnvyB");
		boolean runeSloth = doesOreExist("runeSlothB");
		boolean runeLust = doesOreExist("runeLustB");
		boolean gaiaSpirit = doesOreExist("eternalLifeEssence");
		
		boolean ruby = doesOreExist("gemRuby");
		
		if (ModConfig.recipesEnabled.getBoolean(true)) {
//		r.register(new ShapedOreRecipe(
//				new ResourceLocation(Tags.MOD_ID, "ringgold"), 
//				ModItems.goldRing, new String[] {
//				" g ",
//				"g g",
//				" g " },
//				'g', "ingotGold")
//				.setRegistryName(new ResourceLocation(Tags.MOD_ID, "ringgold")));
		r.register(new ShapedOreRecipe(
				new ResourceLocation(Tags.MOD_ID, "ringiron"),
				ModItems.ironRing, new String[] {
				" i ",
				"i i",
				" i " },
				'i', "ingotIron")
				.setRegistryName(new ResourceLocation(Tags.MOD_ID, "ringiron")));
		
		if (runeSummer && runeAutumn && runeWinter && runeSpring) {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "trinketankhcharm"), 
					ModItems.trinketAnkhCharm, new String[] {
					"SlA",
					"fsa",
					"PvW" },
					's', ModItems.trinketMixedDragonScale,
					'f', ModItems.ringFreeAction,
					'a', ModItems.trinketApple,
					'v', ModItems.trinketVitamins,
					'l', ModItems.trinketMagicLenses,
					'S', "runeSummerB",
					'A', "runeAutumnB",
					'W', "runeWinterB",
					'P', "runeSpringB")
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "trinketankhcharm")));
		} else {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "trinketankhcharm"), 
					ModItems.trinketAnkhCharm, new String[] {
					"glg",
					"fsa",
					"gvg" },
					's', ModItems.trinketMixedDragonScale,
					'f', ModItems.ringFreeAction,
					'a', ModItems.trinketApple,
					'v', ModItems.trinketVitamins,
					'l', ModItems.trinketMagicLenses,
					'g', "ingotGold")
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "trinketankhcharm")));
		}
		
		r.register(new ShapelessOreRecipe(new ResourceLocation(Tags.MOD_ID, "reforger"), 
				ModBlocks.reforger, 
				Item.getItemFromBlock(Blocks.CRAFTING_TABLE),
				Item.getItemFromBlock(Blocks.ANVIL),
				Items.LAVA_BUCKET)
				.setRegistryName(new ResourceLocation(Tags.MOD_ID, "reforger")));
		
		NBTTagCompound temp = new NBTTagCompound();
		temp.setString("Potion", "minecraft:fire_resistance");
		ItemStack potionFireRes = new ItemStack(Items.POTIONITEM);
		potionFireRes.setTagCompound(temp.copy());
		ItemStack potionFireResSplash = new ItemStack(Items.SPLASH_POTION);
		potionFireResSplash.setTagCompound(temp.copy());
		ItemStack potionFireResLinger = new ItemStack(Items.LINGERING_POTION);
		potionFireResLinger.setTagCompound(temp.copy());

		temp.setString("Potion", "minecraft:long_fire_resistance");
		ItemStack potionFireResLong = new ItemStack(Items.POTIONITEM);
		potionFireResLong.setTagCompound(temp.copy());
		ItemStack potionFireResSplashLong = new ItemStack(Items.SPLASH_POTION);
		potionFireResSplashLong.setTagCompound(temp.copy());
		ItemStack potionFireResLingerLong = new ItemStack(Items.LINGERING_POTION);
		potionFireResLingerLong.setTagCompound(temp.copy());

		r.register(new ShapedOreRecipe(
				new ResourceLocation(Tags.MOD_ID, "trinketobsidianskull"),
				ModItems.trinketObsidianSkull, new String[] {
				"oro", 
				"psp", 
				"odo" }, 
				'o', Item.getItemFromBlock(Blocks.OBSIDIAN), 
				'r', runeFire ? "runeFireB" : Items.BLAZE_POWDER,
				'd', Items.BLAZE_POWDER, 
				's', new CLIngredient(new ItemStack(Items.SKULL, 1, 0), new ItemStack(Items.SKULL, 1, 1)),
				'p', new CLIngredient(
						potionFireRes, potionFireResSplash, 
						potionFireResLinger, potionFireResLong, 
						potionFireResSplashLong, potionFireResLingerLong))
				.setRegistryName(new ResourceLocation(Tags.MOD_ID, "trinketobsidianskull")));
		if (gaiaSpirit) {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "trinketblackdragonscale"),
					ModItems.trinketBlackDragonScale, new String[] {
					" e ",
					"nbg" },
					'n', Items.NETHER_STAR,
					'e', "scaleDragonEnder",
					'b', ModItems.brokenBlackDragonScale,
					'g', "eternalLifeEssence")
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "trinketblackdragonscale")));
		} else {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "trinketblackdragonscale"),
					ModItems.trinketBlackDragonScale, new String[] {
					"n",
					"e",
					"b" },
					'n', Items.NETHER_STAR,
					'e', "scaleDragonEnder",
					'b', ModItems.brokenBlackDragonScale)
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "trinketblackdragonscale")));
		}
		r.register(new ShapedOreRecipe(
				new ResourceLocation(Tags.MOD_ID, "ringflywheel"), 
				ModItems.ringFlywheel, new String[] {
				"scs", 
				"crc",
				"scs" }, 
				'c', copperIngot ? "ingotCopper" : "ingotGold", 
				's', steelIngot ? "ingotSteel" : "ingotIron",
				'r', "ringIron")
				.setRegistryName(new ResourceLocation(Tags.MOD_ID, "ringflywheel")));
		r.register(new ShapedOreRecipe(
				new ResourceLocation(Tags.MOD_ID, "ringflywheeladvanced"), 
				ModItems.ringFlywheelAdvanced, new String[] {
				" s ", 
				"iri",
				" e " }, 
				'i', ingotEnderium ? "ingotEnderium" : Items.ENDER_PEARL, 
				'e', Items.ENDER_EYE,
				's', "scaleDragonEnder",
				'r', ModItems.ringFlywheel)
				.setRegistryName(new ResourceLocation(Tags.MOD_ID, "ringflywheeladvanced")));
		r.register(new ShapedOreRecipe(
				new ResourceLocation(Tags.MOD_ID, "amuletSinGluttony"),
				ModItems.sinPendantGluttony, new String[] {
				"c",
				"a",
				"g" },
				'c', runeGluttony ? "runeGluttonyB" : Items.CAKE,
				'a', ModItems.sinPendantEmpty,
				'g', new ItemStack(Items.GOLDEN_APPLE, 1, 0))
				.setRegistryName(new ResourceLocation(Tags.MOD_ID, "amuletSinGluttony")));
		r.register(new ShapedOreRecipe(
				new ResourceLocation(Tags.MOD_ID, "crownGold"),
				ModItems.crownGold, new String[] {
				" g ",
				"iii",
				"i i" },
				'i', "ingotGold",
				'g', ruby ? "gemRuby" : "gemDiamond")
				.setRegistryName(new ResourceLocation(Tags.MOD_ID, "crownGold")));
		if (runePride) {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "amuletSinPride"),
					ModItems.sinPendantPride, new String[] {
					"r",
					"a",
					"c"},
					'r', "runePrideB",
					'c', ModItems.crownGold,
					'a', ModItems.sinPendantEmpty)
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "amuletSinPride")));
		} else {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "amuletSinPride"),
					ModItems.sinPendantPride, new String[] {
					"a",
					"c" },
					'c', ModItems.crownGold,
					'a', ModItems.sinPendantEmpty)
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "amuletSinPride")));
		}
		if (runeWrath) {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "amuletSinWrath"),
					ModItems.sinPendantWrath, new String[] {
					"r",
					"a",
					"h"},
					'r', "runeWrathB",
					'h', Items.SKULL,
					'a', ModItems.sinPendantEmpty)
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "amuletSinWrath")));
		} else {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "amuletSinWrath"),
					ModItems.sinPendantWrath, new String[] {
					" b ",
					"bab",
					" h " },
					'h', Items.SKULL,
					'b', Items.BONE,
					'a', ModItems.sinPendantEmpty)
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "amuletSinWrath")));
		}
		
		if (ingotEnderium) {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "wormholeMirror"),
					ModItems.wormholeMirror, new String[] {
					"iei",
					"pmp",
					"ipi"},
					'e', Items.ENDER_EYE,
					'm', ModItems.magicMirror,
					'p', ModItems.potionWormhole,
					'i', "ingotEnderium")
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "wormholeMirror")));
		} else {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "wormholeMirror"),
					ModItems.wormholeMirror, new String[] {
					" e ",
					"pmp",
					" p "},
					'e', Items.ENDER_EYE,
					'm', ModItems.magicMirror,
					'p', ModItems.potionWormhole)
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "wormholeMirror")));
		}
		
		if (!morebaubles.isBotaniaLoaded) {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "phantomPrism"),
					ModItems.phantomPrism, new String[] {
					" e ",
					"gdg",
					"gog"},
					'e', Items.ENDER_EYE,
					'd', Items.DIAMOND,
					'o', "obsidian",
					'g', "blockGlass")
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "phantomPrism")));
		}
		r.register(new ShapedOreRecipe(
				new ResourceLocation(Tags.MOD_ID, "flare_red"),
				new ItemStack(ModItems.flareRed, 8), new String[] {
				"i",
				"g",
				"G"},
				'i', "ingotIron",
				'g', Items.GUNPOWDER,
				'G', Items.GLOWSTONE_DUST)
				.setRegistryName(new ResourceLocation(Tags.MOD_ID, "flare_red")));
		}
		
		if (ModConfig.brewingRecipesEnabled.getBoolean(true)) {
			NBTTagCompound temp = new NBTTagCompound();
			temp.setString("Potion", "minecraft:mundane");
			ItemStack mundanePotion = new ItemStack(Items.POTIONITEM);
			mundanePotion.setTagCompound(temp.copy());
			
			BrewingRecipeRegistry.addRecipe(
					mundanePotion,
					new ItemStack(Items.QUARTZ),
					new ItemStack(ModItems.potionRecall));
			
			BrewingRecipeRegistry.addRecipe(
					new ItemStack(ModItems.potionRecall),
					new ItemStack(Items.ENDER_PEARL),
					new ItemStack(ModItems.potionWormhole));
		}
		
		if (ModConfig.spectralSiltRecipesEnabled.getBoolean(true)) {
			if (runeAir) {
				r.register(new ShapedOreRecipe(
						new ResourceLocation(Tags.MOD_ID, "spectralSilt_balloon"),
						ModItems.balloon, new String[] {
						"sws",
						"wRw",
						"sws"},
						's', ModItems.spectralSilt,
						'w', "blockWool",
						'R', "runeAirB")
						.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_balloon")));
			} else {
				r.register(new ShapedOreRecipe(
						new ResourceLocation(Tags.MOD_ID, "spectralSilt_balloon"),
						ModItems.balloon, new String[] {
						"sws",
						"wsw",
						"sws"},
						's', ModItems.spectralSilt,
						'w', "blockWool")
						.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_balloon")));
			}
			if (ingotCobalt) {
				if (runeEarth) {
					r.register(new ShapedOreRecipe(
							new ResourceLocation(Tags.MOD_ID, "spectralSilt_shieldCobalt"),
							ModItems.shieldCobalt, new String[] {
							"sis",
							"iSi",
							"sRs"},
							's', ModItems.spectralSilt,
							'S', Items.SHIELD,
							'i', "ingotCobalt",
							'R', "runeEarthB")
							.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_shieldCobalt")));
				} else {
					r.register(new ShapedOreRecipe(
							new ResourceLocation(Tags.MOD_ID, "spectralSilt_shieldCobalt"),
							ModItems.shieldCobalt, new String[] {
							"sis",
							"iSi",
							"sis"},
							's', ModItems.spectralSilt,
							'S', Items.SHIELD,
							'i', "ingotCobalt")
							.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_shieldCobalt")));
				}
			} else {
				if (runeEarth) {
					r.register(new ShapedOreRecipe(
							new ResourceLocation(Tags.MOD_ID, "spectralSilt_shieldCobalt"),
							ModItems.shieldCobalt, new String[] {
							"sss",
							"sSs",
							"sRs"},
							's', ModItems.spectralSilt,
							'S', Items.SHIELD,
							'R', "runeEarthB")
							.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_shieldCobalt")));
				} else {
					r.register(new ShapedOreRecipe(
							new ResourceLocation(Tags.MOD_ID, "spectralSilt_shieldCobalt"),
							ModItems.shieldCobalt, new String[] {
							"sss",
							"sSs",
							"sss"},
							's', ModItems.spectralSilt,
							'S', Items.SHIELD)
							.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_shieldCobalt")));
				}
			}
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "spectralSilt_magicMirror"),
					ModItems.magicMirror, new String[] {
					"sps",
					"gdg",
					"sps"},
					's', ModItems.spectralSilt,
					'd', Items.DIAMOND,
					'g', "blockGlass",
					'p', ModItems.potionRecall)
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_magicMirror")));
			if (runeEarth && runeAir) {
				r.register(new ShapedOreRecipe(
						new ResourceLocation(Tags.MOD_ID, "spectralSilt_luckyHorseshoe"),
						ModItems.trinketLuckyHorseshoe, new String[] {
						"gsg",
						"EsA",
						"sgs"},
						's', ModItems.spectralSilt,
						'g', "ingotGold",
						'E', "runeEarthB",
						'A', "runeAirB")
						.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_luckyHorseshoe")));
			} else {
				r.register(new ShapedOreRecipe(
						new ResourceLocation(Tags.MOD_ID, "spectralSilt_luckyHorseshoe"),
						ModItems.trinketLuckyHorseshoe, new String[] {
						"gsg",
						"gsg",
						"sgs"},
						's', ModItems.spectralSilt,
						'g', "ingotGold")
						.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_luckyHorseshoe")));
			}
			//TODO broken heart
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "spectralSilt_magicLenses"),
					ModItems.trinketMagicLenses, new String[] {
					"s s",
					"gSg",
					"s s"},
					's', ModItems.spectralSilt,
					'g', "blockGlass",
					'S', Items.STICK)
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_magicLenses")));
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "spectralSilt_amuletCross"),
					ModItems.amuletCross, new String[] {
					"sgs",
					"ggg",
					"sgs"},
					's', ModItems.spectralSilt,
					'g', "ingotGold")
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_amuletCross")));
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "spectralSilt_sinPendantEmpty"),
					ModItems.sinPendantEmpty, new String[] {
					"sSs",
					"sis",
					"sss"},
					's', ModItems.spectralSilt,
					'i', "ingotIron",
					'S', Items.STRING)
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_sinPendantEmpty")));
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "spectralSilt_flareGun"),
					ModItems.flareGun, new String[] {
					"sss",
					"iii",
					"iss"},
					's', ModItems.spectralSilt,
					'i', "ingotIron")
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_flareGun")));
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "spectralSilt_brokenBlackDragonScale"),
					ModItems.brokenBlackDragonScale, new String[] {
					"sss",
					"sSs",
					"sss"},
					's', ModItems.spectralSilt,
					'S', "scaleDragonEnder")
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_brokenBlackDragonScale")));
		}
		
		if (ModConfig.spectralSiltEnabled.getBoolean(true)) {
			r.register(new ShapedOreRecipe(
					new ResourceLocation(Tags.MOD_ID, "spectralSilt_disintegrationTablet"),
					ModItems.disintegrationTablet, new String[] {
					"qbq",
					"brb",
					"qbq"},
					'r', Items.REDSTONE,
					'b', Items.BLAZE_POWDER,
					'q', Items.QUARTZ)
					.setRegistryName(new ResourceLocation(Tags.MOD_ID, "spectralSilt_disintegrationTablet")));
			addDisintegrationRecipe(r, ModItems.trinketBrokenHeart);
			addDisintegrationRecipe(r, ModItems.balloon);
			addDisintegrationRecipe(r, ModItems.shieldCobalt);
			addDisintegrationRecipe(r, ModItems.shieldObsidian);
			addDisintegrationRecipe(r, ModItems.magicMirror);
			addDisintegrationRecipe(r, ModItems.wormholeMirror);
			addDisintegrationRecipe(r, ModItems.trinketLuckyHorseshoe);
			addDisintegrationRecipe(r, ModItems.trinketMagicLenses);
			addDisintegrationRecipe(r, ModItems.amuletCross);
			addDisintegrationRecipe(r, ModItems.sinPendantEmpty);
			addDisintegrationRecipe(r, ModItems.brokenBlackDragonScale);
		}
	}
	
	private static void addDisintegrationRecipe(IForgeRegistry<IRecipe> r, Item item) {
		addDisintegrationRecipe(r, item, 1);
	}
	private static void addDisintegrationRecipe(IForgeRegistry<IRecipe> r, Item item, int amt) {
		r.register(new ShapelessOreRecipe(new ResourceLocation(Tags.MOD_ID, "disintegrate_"+item.getTranslationKey()),
				new ItemStack(ModItems.spectralSilt, amt),
				item,
				ModItems.disintegrationTablet)
				.setRegistryName(new ResourceLocation(Tags.MOD_ID, "disintegrate_"+item.getTranslationKey())));
	}
}
