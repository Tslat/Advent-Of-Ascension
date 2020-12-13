package net.tslat.aoa3.item.weapon.sword;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.entity.minion.FriendlyCreeperEntity;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class CreepifiedSword extends BaseSword {
	public CreepifiedSword() {
		super(ItemUtil.customItemTier(2000, AttackSpeed.NORMAL, 13.5f, 4, 10, null));
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.world.isRemote && RandomUtil.oneInNChance(10) && (!(attacker instanceof PlayerEntity) || attackCooldown > 0.75f)) {
			final FriendlyCreeperEntity creeper = new FriendlyCreeperEntity(AoAEntities.Minions.FRIENDLY_CREEPER.get(), target.world);

			creeper.setLocationAndAngles(target.getPosX(), target.getPosY(), target.getPosZ(), random.nextFloat() * 360.0f, 0.0f);
			creeper.setAttackTarget(target);
			creeper.setOwnerId(attacker.getUniqueID());
			target.world.addEntity(creeper);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
