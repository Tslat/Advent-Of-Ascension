package net.tslat.aoa3.client;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.ReadBookScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.tslat.aoa3.client.gui.adventgui.AdventGuiTabLore;
import net.tslat.aoa3.client.gui.hud.RecoilRenderer;
import net.tslat.aoa3.client.gui.hud.toasts.LevelRequirementToast;
import net.tslat.aoa3.client.gui.hud.toasts.ResourceRequirementToast;
import net.tslat.aoa3.client.gui.realmstone.BlankRealmstoneScreen;
import net.tslat.aoa3.client.render.entity.misc.OccultBlockRenderer;
import net.tslat.aoa3.common.packet.packets.ToastPopupPacket;
import net.tslat.aoa3.common.packet.packets.UpdateClientMovementPacket;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.config.AoAConfig;
import net.tslat.aoa3.data.client.*;
import net.tslat.aoa3.entity.mob.greckon.SilencerEntity;
import net.tslat.aoa3.item.misc.WornBook;
import net.tslat.aoa3.mixin.common.invoker.AccessibleSoundEngine;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.StringUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;

public final class ClientOperations {
	public static void displayWornBookGui() {
		PlayerEntity player = Minecraft.getInstance().player;
		ItemStack bookStack = player.getMainHandItem().getItem() == AoAItems.WORN_BOOK.get() ? player.getMainHandItem() : player.getOffhandItem();

		Minecraft.getInstance().setScreen(new ReadBookScreen(new ReadBookScreen.WrittenBookInfo(WornBook.getBook(bookStack))));
	}

	public static void displayBlankRealmstoneGui() {
		Minecraft.getInstance().setScreen(new BlankRealmstoneScreen());
	}

	public static void addRecoil(final float recoil, final int firingTime) {
		RecoilRenderer.addRecoil(recoil);
	}

	public static void addOccultBlocks(int renderUntil, ArrayList<Pair<BlockPos, BlockState>> blocks) {
		OccultBlockRenderer.addOccultBlocks(renderUntil, blocks);
	}

	public static void registerResourceListeners() {
		Minecraft mc = Minecraft.getInstance();

		if (mc == null)
			return;

		SimpleReloadableResourceManager resourceManager = (SimpleReloadableResourceManager)mc.getResourceManager();

		resourceManager.registerReloadListener(new BestiaryReloadListener());
		resourceManager.registerReloadListener(new MiscellaneousReloadListener());
		resourceManager.registerReloadListener(new RealmstoneInsertsReloadListener());
		resourceManager.registerReloadListener(new AdventGuiThemeReloadListener());
	}

	public static void addToast(ToastPopupPacket.ToastPopupType type, Object subject, Object value) {
		switch (type) {
			case SKILL_REQUIREMENT:
				if (AoAConfig.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToasts().addToast(new LevelRequirementToast((AoASkill)subject, (Integer)value));
				}
				else {
					Minecraft.getInstance().player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientLevels", TextFormatting.RED, LocaleUtil.numToComponent((Integer)value), new StringTextComponent(StringUtil.toSentenceCase(subject.toString()))), Util.NIL_UUID);
				}
				break;
			case RESOURCE_REQUIREMENT:
				if (AoAConfig.CLIENT.useToasts.get()) {
					Minecraft.getInstance().getToasts().addToast(new ResourceRequirementToast((AoAResource)subject, (Float)value));
				}
				else {
					Minecraft.getInstance().player.sendMessage(LocaleUtil.getLocaleMessage("message.feedback.insufficientResource", TextFormatting.RED, new StringTextComponent(NumberUtil.roundToNthDecimalPlace((Float)value, 2)), new StringTextComponent(StringUtil.toSentenceCase(subject.toString()))), Util.NIL_UUID);
				}
				break;
		}
	}

	public static void doSilencerSilence(SilencerEntity silencer) {
		if (!SilencerEntity.isClientNearby) {
			Minecraft mc = Minecraft.getInstance();

			if (((AccessibleSoundEngine)mc.getSoundManager().soundEngine).getCategoryVolume(SoundCategory.MASTER) > 0) {
				if (silencer.distanceToSqr(mc.player) < 8 * 8) {
					SilencerEntity.isClientNearby = true;
					SilencerEntity.previousGain = mc.getSoundManager().soundEngine.listener.getGain();

					mc.getSoundManager().soundEngine.listener.setGain(0);
				}
			}
		}
	}

	public static void syncPatchouliBooks(ArrayList<ResourceLocation> books) {
		AdventGuiTabLore.syncBooks(books);
	}

	public static void adjustPlayerMovement(@Nullable Float x, @Nullable Float y, @Nullable Float z, UpdateClientMovementPacket.Operation operation) {
		PlayerEntity player = Minecraft.getInstance().player;
		Vector3d velocity = player.getDeltaMovement();

		switch (operation) {
			case SET:
				player.setDeltaMovement(x != null ? x : velocity.x(), y != null ? y : velocity.y(), z != null ? z : velocity.z());
				break;
			case ADD:
				player.setDeltaMovement(velocity.add(x != null ? x : 0, y != null ? y : 0, z != null ? z : 0));
				break;
			case MULTIPLY:
				player.setDeltaMovement(velocity.multiply(x != null ? x : 1, y != null ? y : 1, z != null ? z : 1));
				break;
			case MAX:
				player.setDeltaMovement(x != null ? Math.min(x, velocity.x()) : velocity.x(), y != null ? Math.min(y, velocity.y()) : velocity.y(), z != null ? Math.min(z, velocity.z()) : velocity.z());
				break;
			case MIN:
				player.setDeltaMovement(x != null ? Math.max(x, velocity.x()) : velocity.x(), y != null ? Math.max(y, velocity.y()) : velocity.y(), z != null ? Math.max(z, velocity.z()) : velocity.z());
				break;
		}
	}

	public static boolean harvestAdditionalBlock(BlockPos breakPos) {
		if (Minecraft.getInstance().gameMode == null)
			return false;

		return Minecraft.getInstance().gameMode.destroyBlock(breakPos);
	}
}
