package smd.morebaubles.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

/**
 * A button that can have a custom height without looking weird.
 * 
 * @author CursedFlames
 *
 */
public class GuiBetterButton extends GuiButton {
	public boolean buttonPressSound = true;
	public ResourceLocation buttonTextures = null;

	public GuiBetterButton(int buttonId, int x, int y, int widthIn, int heightIn,
			String buttonText) {
		super(buttonId, x, y, widthIn, heightIn, buttonText);
	}

	@Override
	public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
		if (this.visible) {
			FontRenderer fontRenderer = mc.fontRenderer;
			mc.getTextureManager()
					.bindTexture(buttonTextures==null ? BUTTON_TEXTURES : buttonTextures);
			GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			this.hovered = mouseX>=this.x&&mouseY>=this.y&&mouseX<this.x+this.width
					&&mouseY<this.y+this.height;
			int i = this.getHoverState(this.hovered);
			GlStateManager.enableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
					GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
					GlStateManager.DestFactor.ZERO);
			GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA,
					GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

			this.drawTexturedModalRect(this.x, this.y, 0, 46+i*20, this.width/2, this.height/2);
			this.drawTexturedModalRect(this.x+this.width/2, this.y, 200-this.width/2, 46+i*20,
					this.width/2, this.height/2);
			this.drawTexturedModalRect(this.x, this.y+this.height/2, 0, 66+i*20-this.height/2,
					this.width/2, this.height/2);
			this.drawTexturedModalRect(this.x+this.width/2, this.y+this.height/2, 200-this.width/2,
					66+i*20-this.height/2, this.width/2, this.height/2);
			this.mouseDragged(mc, mouseX, mouseY);
			int j = 14737632;

			if (packedFGColour!=0) {
				j = packedFGColour;
			} else if (!this.enabled) {
				j = 10526880;
			} else if (this.hovered) {
				j = 16777120;
			}

			this.drawCenteredString(fontRenderer, this.displayString, this.x+this.width/2,
					this.y+(this.height-8)/2, j);
		}
	}

	@Override
	public void playPressSound(SoundHandler soundHandlerIn) {
		if (buttonPressSound)
			super.playPressSound(soundHandlerIn);
	}
}
