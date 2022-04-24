package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.client.IItemRenderProperties;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RenderUtil;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.function.Consumer;

public class NightVisionGoggles extends AdventArmour {
	private static final ResourceLocation OVERLAY_TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/helmet/night_vision_goggles.png");

	public NightVisionGoggles() {
		super(ItemUtil.customArmourMaterial("aoa3:night_vision_goggles", 27, new int[] {2, 2, 2, 2}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, 1), EquipmentSlot.HEAD);
	}

	@Override
	public Type setType() {
		return Type.ALL;
	}

	@Override
	public void onEffectTick(ServerPlayerDataManager plData, @Nullable HashSet<EquipmentSlot> slots) {
		plData.player().addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 300, 0, true, false));
	}

	@Override
	public void onUnequip(ServerPlayerDataManager plData, @Nullable EquipmentSlot slot) {
		MobEffectInstance nightVision = plData.player().getEffect(MobEffects.NIGHT_VISION);

		if (nightVision != null && nightVision.getDuration() < 300)
			plData.player().removeEffect(MobEffects.NIGHT_VISION);
	}

	@Override
	public void initializeClient(Consumer<IItemRenderProperties> consumer) {
		consumer.accept(new IItemRenderProperties() {
			@Override
			public void renderHelmetOverlay(ItemStack stack, Player player, int width, int height, float partialTick) {
				RenderUtil.prepRenderTexture(OVERLAY_TEXTURE);
				RenderUtil.renderFullscreenTexture();
			}
		});
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(anySetEffectHeader());
	}
}
