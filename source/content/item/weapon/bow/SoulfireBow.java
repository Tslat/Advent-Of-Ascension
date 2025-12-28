package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulfireBow extends AoABow {
	public SoulfireBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public Projectile applyArrowMods(Projectile projectile, @Nullable Entity shooter, ItemStack stack) {
		if (shooter instanceof ServerPlayer pl && PlayerUtil.consumeResource(pl, AoAResources.SPIRIT.get(), 200, false))
			projectile.setGlowingTag(true);

		return super.applyArrowMods(projectile, shooter, stack);
	}

	@Override
	public void onEntityImpact(Projectile projectile, @Nullable Entity shooter, EntityHitResult hitResult, ItemStack stack, float velocity) {
		if (projectile.isCurrentlyGlowing() && shooter instanceof LivingEntity livingShooter)
			EntityUtil.healEntity(livingShooter, 8);

		projectile.setGlowingTag(false);
	}

	@Override
	public void onBlockImpact(Projectile projectile, @Nullable Entity shooter, BlockHitResult hitResult, ItemStack stack) {
		projectile.setGlowingTag(false);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
