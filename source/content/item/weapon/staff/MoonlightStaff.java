package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.content.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.content.entity.projectile.staff.MoonlightFallEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class MoonlightStaff extends BaseStaff<BlockPos> {
	public MoonlightStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_MOONLIGHT_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.COMPASS_RUNE.get(), 1);
		runes.put(AoAItems.LUNAR_RUNE.get(), 2);
		runes.put(AoAItems.KINETIC_RUNE.get(), 2);
	}

	@Override
	public BlockPos checkPreconditions(LivingEntity caster, ItemStack staff) {
		BlockPos trace = null;

		if (caster instanceof Player)
			trace = PlayerUtil.getBlockAimingAt((Player)caster, 70);

		return trace;
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, BlockPos args) {
		world.addFreshEntity(new MoonlightFallEntity(caster, this, args.getX(), args.getY() + 30, args.getZ(), 3f));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vec3 hitPos, LivingEntity caster) {
		createCloud(shot, caster);
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity caster) {
		if (!target.isInvulnerable()) {
			createCloud(shot, caster);

			return true;
		}

		return false;
	}

	private void createCloud(BaseEnergyShot shot, LivingEntity caster) {
		AreaEffectCloud cloud = new AreaEffectCloud(shot.level, shot.getX(), shot.getY(), shot.getZ());

		cloud.setOwner(caster);
		cloud.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 140, 1, false, true));
		cloud.setRadius(0.1f);
		cloud.setRadiusPerTick(1);
		cloud.setDuration(10);
		cloud.setWaitTime(0);

		shot.level.addFreshEntity(cloud);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
