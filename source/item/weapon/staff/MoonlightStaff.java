package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectile.staff.MoonlightFallEntity;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

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
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(AoAItems.COMPASS_RUNE.get(), 1);
		runes.put(AoAItems.LUNAR_RUNE.get(), 2);
		runes.put(AoAItems.KINETIC_RUNE.get(), 2);
	}

	@Override
	public BlockPos checkPreconditions(LivingEntity caster, ItemStack staff) {
		BlockPos trace = null;

		if (caster instanceof PlayerEntity)
			trace = PlayerUtil.getBlockAimingAt((PlayerEntity)caster, 70);

		return trace;
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, BlockPos args) {
		world.addFreshEntity(new MoonlightFallEntity(caster, this, args.getX(), args.getY() + 30, args.getZ(), 3f));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vector3d hitPos, LivingEntity caster) {
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
		AreaEffectCloudEntity cloud = new AreaEffectCloudEntity(shot.level, shot.getX(), shot.getY(), shot.getZ());

		cloud.setOwner(caster);
		cloud.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 140, 1, false, true));
		cloud.setRadius(0.1f);
		cloud.setRadiusPerTick(1);
		cloud.setDuration(10);
		cloud.setWaitTime(0);

		shot.level.addFreshEntity(cloud);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
