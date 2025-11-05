package smd.morebaubles.item;

import smd.morebaubles.morebaubles;
import smd.morebaubles.item.base.GenericItemBB;
import net.minecraft.item.EnumDyeColor;

public class ItemFlare extends GenericItemBB {
	public final EnumDyeColor color;

	public ItemFlare(EnumDyeColor color) {
		super("flare_"+color.getName(), morebaubles.TAB);
		this.color = color;
	}
}
