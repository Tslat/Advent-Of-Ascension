package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectile.staff.ShyreShotEntity;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class ShyreStaff extends BaseStaff<Object> {
	public ShyreStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_SHYRE_STAFF_CAST.get();
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(AoAItems.ENERGY_RUNE.get(), 3);
		runes.put(AoAItems.DISTORTION_RUNE.get(), 3);
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, Object args) {
		world.addEntity(new ShyreShotEntity(caster, this, 120));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vec3d hitPos, LivingEntity shooter) {
		World world = shooter.world;
		BlockPos.Mutable testPos = new BlockPos.Mutable(hitPos.getX(), hitPos.getY(), hitPos.getZ());
		BlockState state = world.getBlockState(testPos);
		Vec3d shotMotion = shot.getMotion();
		Vec3d testVec = hitPos;
		int tests = 0;

		while (tests <= 10 && !(state = world.getBlockState(testPos)).getBlock().isAir(state, world, testPos)) {
			testVec = testVec.subtract(shotMotion.getX() * 0.15f, shotMotion.getY() * 0.15f, shotMotion.getZ() * 0.15f);
			testPos.setPos(testVec.getX() + shooter.getWidth(), testVec.getY(), testVec.getZ() + shooter.getWidth());
			tests++;
		}

		if (state.getBlock().isAir(state, world, testPos)) {
			shooter.setPositionAndUpdate(testVec.getX(), testVec.getY(), testVec.getZ());

			if (shooter instanceof ServerPlayerEntity && shooter.world.getDimension().getType() == AoADimensions.LUNALUS.type())
				AdvancementUtil.completeAdvancement((ServerPlayerEntity)shooter, new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus/200_iq"), "lunalus_shyre_staff_travel");
		}
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		shooter.setPositionAndUpdate((target.getPosX() + shot.getPosX()) / 2d, (target.getPosY() + shot.getPosY()) / 2d, (target.getPosZ() + shot.getPosZ()) / 2d);

		return true;
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.addInformation(stack, world, tooltip, flag);
	}
}
