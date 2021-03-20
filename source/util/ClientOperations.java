package net.tslat.aoa3.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.SimpleReloadableResourceManager;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.util.concurrent.ThreadTaskExecutor;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.tslat.aoa3.client.gui.hud.RecoilRenderer;
import net.tslat.aoa3.client.gui.hud.toasts.LevelRequirementToast;
import net.tslat.aoa3.client.gui.hud.toasts.ResourceRequirementToast;
import net.tslat.aoa3.client.gui.hud.toasts.TributeRequirementToast;
import net.tslat.aoa3.client.gui.realmstone.BlankRealmstoneScreen;
import net.tslat.aoa3.common.packet.packets.ToastPopupPacket;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.data.client.BestiaryManager;
import net.tslat.aoa3.data.client.GuidesManager;
import net.tslat.aoa3.data.client.MiscTextFileManager;
import net.tslat.aoa3.entity.mob.greckon.SilencerEntity;
import net.tslat.aoa3.item.misc.WornBook;
import net.tslat.aoa3.util.constant.Deities;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.constant.Skills;

public abstract class ClientOperations {
	public static void displayWornBookGui() {
		PlayerEntity player = Minecraft.getInstance().player;
		ItemStack bookStack = player.getMainHandItem().getItem() == AoAItems.WORN_BOOK.get() ? player.getMainHandItem() : player.getOffhandItem();

		Minecraft.getInstance().setScreen(new ReadBookScreen(new ReadBookScreen.WrittenBookInfo(WornBook.getBook(bookStack))));
	}

	public static void displayBlankRealmstoneGui() {
		Minecraft.getInstance().setScreen(new BlankRealmstoneScreen());
	}

	public static void addRecoil(final float recoil, final int firingTime) {
		RecoilRenderer.recoilTicks = Math.min(50, firingTime);
		RecoilRenderer.recoilTicksRemaining = Math.min(50, firingTime);
		RecoilRenderer.recoilAngle = recoil;
	}

	public static void spawnClientOnlyEntity(Entity entity) {
		if (entity.level instanceof ClientWorld) {
			ThreadTaskExecutor<?> executor = LogicalSidedProvider.WORKQUEUE.get(LogicalSide.CLIENT);
			Runnable runnable = () -> ((ClientWorld)entity.level).putNonPlayerEntity(entity.getId(), entity);

			if (!executor.isSameThread()) {
				executor.submitAsync(runnable);
			}
			else {
				runnable.run();
			}
		}
	}

	public static void removeClientOnlyEntity(Entity entity) {
		if (entity.level instanceof ClientWorld)
			((ClientWorld)entity.level).removeEntity(entity.getId());
	}

	public static void registerResourceListeners() {
		Minecraft mc = Minecraft.getInstance();

		if (mc == null)
			return;

		SimpleReloadableResourceManager resourceManager = (SimpleReloadableResourceManager)mc.getResourceManager();

		resourceManager.registerReloadListener(new GuidesManager());
		resourceManager.registerReloadListener(new BestiaryManager());
		resourceManager.registerReloadListener(new MiscTextFileManager());
	}

	public static void addToast(ToastPopupPacket.ToastPopupType type, Object subject, Object value) {
		switch (type) {
			case SKILL_REQUIREMENT:
				if (AoAConfig.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToasts().addToast(new LevelRequirementToast((Skills)subject, (Integer)value));
				}
				else {
					Minecraft.getInstance().player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientLevels", TextFormatting.RED, LocaleUtil.numToComponent((Integer)value), new StringTextComponent(StringUtil.toSentenceCase(subject.toString()))), Util.NIL_UUID);
				}
				break;
			case RESOURCE_REQUIREMENT:
				if (AoAConfig.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToasts().addToast(new ResourceRequirementToast((Resources)subject, (Float)value));
				}
				else {
					Minecraft.getInstance().player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientResource", TextFormatting.RED, new StringTextComponent(NumberUtil.roundToNthDecimalPlace((Float)value, 2)), new StringTextComponent(StringUtil.toSentenceCase(subject.toString()))), Util.NIL_UUID);
				}
				break;
			case TRIBUTE_REQUIREMENT:
				if (AoAConfig.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToasts().addToast(new TributeRequirementToast((Deities)subject, (Integer)value));
				}
				else {
					Minecraft.getInstance().player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientTribute", TextFormatting.RED, LocaleUtil.numToComponent((Integer)value), new StringTextComponent(StringUtil.toSentenceCase(subject.toString()))), Util.NIL_UUID);
				}
				break;
		}
	}

	public static void doSilencerSilence(SilencerEntity silencer) {
		if (!SilencerEntity.isClientNearby) {
			Minecraft mc = Minecraft.getInstance();

			if (mc.getSoundManager().soundEngine.getVolume(SoundCategory.MASTER) > 0) {
				if (silencer.distanceToSqr(mc.player) < 8 * 8) {
					SilencerEntity.isClientNearby = true;
					SilencerEntity.prevVolume = mc.getSoundManager().soundEngine.getVolume(SoundCategory.MASTER);

					mc.getSoundManager().updateSourceVolume(SoundCategory.MASTER, 0);
				}
			}
		}
	}
}
