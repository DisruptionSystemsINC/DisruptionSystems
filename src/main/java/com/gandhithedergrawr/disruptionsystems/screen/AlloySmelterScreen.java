package com.gandhithedergrawr.disruptionsystems.screen;

import com.gandhithedergrawr.disruptionsystems.container.AlloySmelterContainer;
import com.gandhithedergrawr.disruptionsystems.tileentity.AlloySmelterTile;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

import java.time.Year;

import static com.gandhithedergrawr.disruptionsystems.disruptionsystems.MOD_ID;
import static com.gandhithedergrawr.disruptionsystems.tileentity.AlloySmelterTile.processingTime;

public class AlloySmelterScreen extends ContainerScreen<AlloySmelterContainer> {

    private final ResourceLocation GUI = new ResourceLocation(MOD_ID,
            "textures/gui/alloy_smelter_gui.png");

    public AlloySmelterScreen(AlloySmelterContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bindTexture(GUI);
        int i = this.guiLeft;
        int j = this.guiTop;
        this.blit(matrixStack, i, j, 0, 0, xSize, ySize);

        if (AlloySmelterTile.isProcessing){
            this.blit(matrixStack, i + 78, j + 20, 177, 0, 25, 36);
        }



    }
}