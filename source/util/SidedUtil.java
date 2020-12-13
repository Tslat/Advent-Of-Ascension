package net.tslat.aoa3.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.SimpleReloadableResourceManager;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.client.gui.hud.RecoilRenderer;
import net.tslat.aoa3.client.gui.hud.toasts.LevelRequirementToast;
import net.tslat.aoa3.client.gui.hud.toasts.ResourceRequirementToast;
import net.tslat.aoa3.client.gui.hud.toasts.TributeRequirementToast;
import net.tslat.aoa3.client.gui.realmstone.BlankRealmstoneScreen;
import net.tslat.aoa3.common.packet.packets.ToastPopupPacket;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.entity.mob.greckon.SilencerEntity;
import net.tslat.aoa3.item.misc.WornBook;
import net.tslat.aoa3.library.resourcemanager.BestiaryManager;
import net.tslat.aoa3.library.resourcemanager.GuidesManager;
import net.tslat.aoa3.library.resourcemanager.MiscTextFileManager;
import net.tslat.aoa3.library.scheduling.AoAScheduler;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;

public abstract class SidedUtil {
	public static void displayWornBookGui() {
		PlayerEntity player = Minecraft.getInstance().player;
		ItemStack bookStack = player.getHeldItemMainhand().getItem() == AoAItems.WORN_BOOK.get() ? player.getHeldItemMainhand() : player.getHeldItemOffhand();

		Minecraft.getInstance().displayGuiScreen(new ReadBookScreen(new ReadBookScreen.WrittenBookInfo(WornBook.getBook(bookStack))));
	}

	public static void displayBlankRealmstoneGui() {
		Minecraft.getInstance().displayGuiScreen(new BlankRealmstoneScreen());
	}

	public static void addRecoil(final float recoil, final int firingTime) {
		RecoilRenderer.recoilTicks = Math.min(50, firingTime);
		RecoilRenderer.recoilTicksRemaining = Math.min(50, firingTime);
		RecoilRenderer.recoilAngle = recoil;
	}

	public static void spawnClientOnlyEntity(Entity entity) {
		if (entity.world instanceof ClientWorld)
			AoAScheduler.scheduleSyncronisedTask(() -> ((ClientWorld)entity.world).addEntity(entity.getEntityId(), entity), 1);
	}

	public static void removeClientOnlyEntity(Entity entity) {
		if (entity.world instanceof ClientWorld)
			((ClientWorld)entity.world).removeEntityFromWorld(entity.getEntityId());
	}

	public static void registerResourceListeners() {
		Minecraft mc = Minecraft.getInstance();

		if (mc == null)
			return;

		SimpleReloadableResourceManager resourceManager = (SimpleReloadableResourceManager)mc.getResourceManager();

		resourceManager.addReloadListener(new GuidesManager());
		resourceManager.addReloadListener(new BestiaryManager());
		resourceManager.addReloadListener(new MiscTextFileManager());
	}

	public static void addToast(ToastPopupPacket.ToastPopupType type, Object subject, Object value) {
		switch (type) {
			case SKILL_REQUIREMENT:
				if (AoAConfig.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToastGui().add(new LevelRequirementToast((Skills)subject, (Integer)value));
				}
				else {
					Minecraft.getInstance().player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientLevels", TextFormatting.RED, String.valueOf(value), StringUtil.toSentenceCase(subject.toString())));
				}
				break;
			case RESOURCE_REQUIREMENT:
				if (AoAConfig.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToastGui().add(new ResourceRequirementToast((Resources)subject, (Float)value));
				}
				else {
					Minecraft.getInstance().player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientResource", TextFormatting.RED, NumberUtil.roundToNthDecimalPlace((Float)value, 2), StringUtil.toSentenceCase(subject.toString())));
				}
				break;
			case TRIBUTE_REQUIREMENT:
				if (AoAConfig.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToastGui().add(new TributeRequirementToast((Deities)subject, (Integer)value));
				}
				else {
					Minecraft.getInstance().player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientTribute", TextFormatting.RED, String.valueOf(value), StringUtil.toSentenceCase(subject.toString())));
				}
				break;
		}
	}

	public static void doSilencerSilencer(SilencerEntity silencer) {
		if (silencer.getDistanceSq(Minecraft.getInstance().player) < 8 * 8)
			Minecraft.getInstance().getSoundHandler().stop();
	}
}
