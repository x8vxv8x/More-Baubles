package smd.morebaubles.item;

import smd.morebaubles.morebaubles;
import smd.morebaubles.item.base.GenericItemBB;

public class ItemDisintegrationTablet extends GenericItemBB {
	public ItemDisintegrationTablet() {
		super("disintegrationTablet", morebaubles.TAB);
		setContainerItem(this);
		setMaxStackSize(1);
	}
}
