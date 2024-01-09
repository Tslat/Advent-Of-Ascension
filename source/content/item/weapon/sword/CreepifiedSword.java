package net.tslat.aoa3.content.item.weapon.sword;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.item.AoATiers;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.smartbrainlib.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CreepifiedSword extends BaseSword {
	public CreepifiedSword() {
		super(AoATiers.CREEPIFIED);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity target, LivingEntity attacker, float attackCooldown) {
		if (!attacker.level().isClientSide && RandomUtil.oneInNChance(10) && (!(attacker instanceof Player) || attackCooldown > 0.75f)) {
			/*final FriendlyCreeperEntity creeper = new FriendlyCreeperEntity(AoAEntities.Minions.FRIENDLY_CREEPER.get(), target.level);

			creeper.moveTo(target.getX(), target.getY(), target.getZ(), random.nextFloat() * 360.0f, 0.0f);
			creeper.setTarget(target);
			creeper.setOwnerUUID(attacker.getUUID());
			target.level.addFreshEntity(creeper);*/ // TODO
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
