package net.tslat.aoa3.object.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.object.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.object.entity.projectile.staff.CelestialFallEntity;
import net.tslat.aoa3.util.DamageUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class CelestialStaff extends BaseStaff<BlockPos> {
	public CelestialStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_CELESTIAL_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.COMPASS_RUNE.get(), 1);
		runes.put(AoAItems.LUNAR_RUNE.get(), 2);
		runes.put(AoAItems.ENERGY_RUNE.get(), 2);
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
		BlockPos pos = args;

		world.addFreshEntity(new CelestialFallEntity(caster, this, pos.getX(), pos.getY() + 25, pos.getZ(), 3.0f));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vector3d hitPos, LivingEntity shooter) {
		WorldUtil.createExplosion(shooter, shot.level, shot, 2.5f);
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		WorldUtil.createExplosion(shooter, shot.level, shot, 2.5f);

		return DamageUtil.dealMagicDamage(shot, shooter, target, getDmg(), false);
	}

	@Override
	public float getDmg() {
		return 22;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
