package com.spiritlight.silentclose;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class GUIHandler {
    @SubscribeEvent
    public void onInitGui(final GuiScreenEvent.InitGuiEvent.Post event) {
        final Minecraft mc = Minecraft.getMinecraft();
        if (mc.player == null || mc.world == null || mc.ingameGUI == null) {
            return;
        }
        if (Minecraft.getMinecraft().player.inventory == null) {
            return;
        }
        final ScaledResolution sr = new ScaledResolution(mc);
        final int x = (sr.getScaledWidth() + 225) / 2;
        final int y = (sr.getScaledHeight() + 100) / 2;
        //final int xsave = (sr.getScaledWidth() - 200) / 2;
        //final int ysave = (sr.getScaledHeight() + 250) / 2;
        if (event.getGui() instanceof GuiContainer) {
            event.getButtonList().add(new GuiButton(6969, x, y, "Close Inventory Without Packet"));
            // event.getButtonList().add(new GuiButton(6976, xsave, ysave, EnumChatFormatting.GREEN + "Save GUI "));
        }
    }

    @SubscribeEvent
    public void onPostActionPerformedGui(final GuiScreenEvent.ActionPerformedEvent.Post event) {
        final Minecraft mc = Minecraft.getMinecraft();
        if (event.getGui() instanceof GuiContainer && event.getButton().id == 6969) {
            mc.player.sendMessage(new TextComponentString("Silently closed GUI."));
            Main.dropChestPacket = true;
            mc.player.closeScreen();
        }
    }
}
