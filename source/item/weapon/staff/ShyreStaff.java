package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.block.BlockState;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.projectile.staff.BaseEnergyShot;
import net.tslat.aoa3.entity.projectile.staff.ShyreShotEntity;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;

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
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.ENERGY_RUNE.get(), 3);
		runes.put(AoAItems.DISTORTION_RUNE.get(), 3);
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, Object args) {
		world.addFreshEntity(new ShyreShotEntity(caster, this, 120));
	}

	@Override
	public void doBlockImpact(BaseEnergyShot shot, Vector3d hitPos, LivingEntity shooter) {
		World world = shooter.level;
		BlockPos.Mutable testPos = new BlockPos.Mutable(hitPos.x(), hitPos.y(), hitPos.z());
		BlockState state = world.getBlockState(testPos);
		Vector3d shotMotion = shot.getDeltaMovement();
		Vector3d testVec = hitPos;
		int tests = 0;

		while (tests <= 10 && !(state = world.getBlockState(testPos)).getBlock().isAir(state, world, testPos)) {
			testVec = testVec.subtract(shotMotion.x() * 0.15f, shotMotion.y() * 0.15f, shotMotion.z() * 0.15f);
			testPos.set(testVec.x() + shooter.getBbWidth(), testVec.y(), testVec.z() + shooter.getBbWidth());
			tests++;
		}

		if (state.getBlock().isAir(state, world, testPos) && !ForgeEventFactory.onEntityTeleportCommand(shooter, testVec.x(), testVec.y(), testVec.z()).isCanceled()) {
			shooter.teleportTo(testVec.x(), testVec.y(), testVec.z());

			if (shooter instanceof ServerPlayerEntity && WorldUtil.isWorld(shooter.level, AoADimensions.LUNALUS.key))
				AdvancementUtil.completeAdvancement((ServerPlayerEntity)shooter, new ResourceLocation(AdventOfAscension.MOD_ID, "lunalus/200_iq"), "lunalus_shyre_staff_travel");
		}
	}

	@Override
	public boolean doEntityImpact(BaseEnergyShot shot, Entity target, LivingEntity shooter) {
		Vector3d teleportPos = new Vector3d((target.getX() + shot.getX()) / 2d, (target.getY() + shot.getY()) / 2d, (target.getZ() + shot.getZ()) / 2d);

		if (!ForgeEventFactory.onEntityTeleportCommand(shooter, teleportPos.x(), teleportPos.y(), teleportPos.z()).isCanceled())
			shooter.teleportTo(teleportPos.x(), teleportPos.y(), teleportPos.z());

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
