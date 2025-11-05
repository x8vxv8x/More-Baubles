package smd.morebaubles.block;

import smd.morebaubles.morebaubles;

public class ModBlocks {
	public static GenericBlock reforger = null;

	public static void registerToRegistry() {
		morebaubles.registryHelper.useOldTileEntityNaming = false;
		reforger = new BlockReforger();
		morebaubles.registryHelper.addBlock(reforger).addItemBlock(reforger)
				.addItemBlockModel(reforger)
				.addTileEntity(reforger.getTranslationKey(), TileReforger.class);
	}
}
