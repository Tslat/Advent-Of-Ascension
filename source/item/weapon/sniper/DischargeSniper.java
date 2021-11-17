package net.tslat.aoa3.item.weapon.sniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.entity.projectile.gun.DischargeSlugEntity;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class DischargeSniper extends BaseSniper {
	private static final ResourceLocation DISCHARGE_SCOPE_TEXTURE = new ResourceLocation(AdventOfAscension.MOD_ID, "textures/gui/overlay/scope/discharge.png");

	public DischargeSniper(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_DISCHARGE_GUN_FIRE.get();
	}

	@Override
	public ResourceLocation getScopeTexture(ItemStack stack) {
		return DISCHARGE_SCOPE_TEXTURE;
	}

	@Override
	public Item getAmmoItem() {
		return AoAItems.DISCHARGE_CAPSULE.get();
	}

	@Override
	public BaseBullet createProjectileEntity(LivingEntity shooter, ItemStack gunStack, Hand hand) {
		return new DischargeSlugEntity(shooter, this, 0);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.EXPLODES_ON_HIT, LocaleUtil.ItemDescriptionType.BENEFICIAL));

		super.appendHoverText(stack, world, tooltip, flag);
	}
}
