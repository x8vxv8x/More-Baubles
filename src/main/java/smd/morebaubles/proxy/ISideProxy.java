package smd.morebaubles.proxy;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public interface ISideProxy {
	default String translateWithArgs(String string, Object... args) {
		return string;
	}

	default String translate(String string) {
		return string;
	}

	default boolean hasTranslationKey(String string) {
		return false;
	}

	default void spawnParticleGradient(World world, double x, double y, double z, float r,
                                       float g, float b, float rT, float gT, float bT, int maxAge) {
	}

	default void spawnParticleGradient(World world, double x, double y, double z, float r,
                                       float g, float b, float rT, float gT, float bT, int maxAge, double velX, double velY,
                                       double velZ) {
	}

	default void displayGuiWormhole() {
	}

	default ModelBiped getArmorModel(String modelName) {
		return null;
	}

	default <T extends Entity> void registerWithRenderer(String name, Class<T> c, Item i,
                                                         int id) {
	}

	default <T extends Entity> void registerWithRenderer(String name, Class<T> c,
                                                         ResourceLocation texture, int id) {
	}

	default void registerEntityRenderingHandlers() {
	}

	default void addRenderLayer() {
	}

}
