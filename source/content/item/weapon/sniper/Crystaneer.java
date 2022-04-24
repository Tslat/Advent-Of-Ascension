package net.tslat.aoa3.content.item.weapon.sniper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.content.entity.projectile.gun.BaseBullet;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.LootUtil;

import javax.annotation.Nullable;
import java.util.List;

public class Crystaneer extends BaseSniper {
	public Crystaneer(double dmg, int durability, int firingDelayTicks, float recoil) {
		super(dmg, durability, firingDelayTicks, recoil);
	}

	@Nullable
	@Override
	public SoundEvent getFiringSound() {
		return AoASounds.ITEM_GUN_SNIPER_HEAVY_FIRE_LONG.get();
	}

	@Override
	public ResourceLocation getScopeTexture(ItemStack stack) {
		return SCOPE_4;
	}

	@Override
	protected void doImpactEffect(Entity target, LivingEntity shooter, BaseBullet bullet, float bulletDmgMultiplier) {
		if (!shooter.level.isClientSide() && target instanceof LivingEntity && ((LivingEntity)target).getHealth() <= 0) {
			for (ItemStack drop : LootUtil.generateLoot((ServerWorld)shooter.level, AdventOfAscension.id("items/crystaneer"), LootUtil.getGiftContext((ServerWorld)shooter.level, target.position(), (shooter instanceof PlayerEntity ? ((PlayerEntity)shooter).getLuck() : 0), shooter))) {
				target.spawnAtLocation(drop, 0f);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
