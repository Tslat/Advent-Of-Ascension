package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectile.staff.UltimatumShotEntity;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.scheduling.sync.UltimatumStaffTask;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class UltimatumStaff extends BaseStaff<Object> {
	public UltimatumStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_ULTIMATUM_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(AoAItems.LIFE_RUNE.get(), 5);
		runes.put(AoAItems.POWER_RUNE.get(), 3);
		runes.put(AoAItems.DISTORTION_RUNE.get(), 8);
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, Object args) {
		world.addFreshEntity(new UltimatumShotEntity(caster, this, 60));
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		if (target instanceof LivingEntity && !EntityUtil.isImmuneToSpecialAttacks(target, shooter)) {
			Vector3d lookVec = shooter.getLookAngle();

			double posX = shooter.getX() + lookVec.x * 4;
			double posZ = shooter.getZ() + lookVec.z * 4;

			target.absMoveTo(posX, shooter.getY(), posZ, (shooter.yHeadRot + 180) % 360, 0);
			target.setYHeadRot((shooter.yHeadRot + 180) % 360);
			target.teleportTo(posX, shooter.getY(), posZ);
			AoAScheduler.scheduleSyncronisedTask(new UltimatumStaffTask(shooter, (LivingEntity)target), 2);

			return true;
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(LocaleUtil.Constants.SPEC_IMMUNE, LocaleUtil.ItemDescriptionType.HARMFUL));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
