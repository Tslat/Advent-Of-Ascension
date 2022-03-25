package net.tslat.aoa3.content.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.custom.AoAResources;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class ErebonScythe extends BaseGreatblade {
	public ErebonScythe() {
		super(19.0f, -3d, 1750);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level.isClientSide) {
			for (LivingEntity entity : target.level.getEntitiesOfClass(LivingEntity.class, new AxisAlignedBB(target.getX() - 1.5, target.getBoundingBox().minY, target.getZ() - 1.5, target.getX() + 1.5, target.getBoundingBox().minY + 1, target.getZ() + 1.5), EntityUtil.Predicates.HOSTILE_MOB)) {
				entity.setSecondsOnFire((int)(5 * attackCooldown));
			}

			AoAResource.Instance spirit = target instanceof ServerPlayerEntity ? PlayerUtil.getResource((ServerPlayerEntity)target, AoAResources.SPIRIT.get()) : null;
			float consumeAmount = (spirit != null ? Math.min(50, spirit.getCurrentValue()) : 50) * attackCooldown;

			if (consumeAmount > 0) {
				if (spirit != null && !spirit.consume(consumeAmount, true))
					return;

				if (attacker instanceof ServerPlayerEntity)
					PlayerUtil.addResourceToPlayer((ServerPlayerEntity)attacker, AoAResources.SPIRIT.get(), consumeAmount);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.scythe", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
