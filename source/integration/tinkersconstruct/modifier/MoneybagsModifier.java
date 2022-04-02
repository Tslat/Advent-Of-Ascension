package net.tslat.aoa3.integration.tinkersconstruct.modifier;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.RandomUtil;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.RomanNumeralHelper;

public class MoneybagsModifier extends Modifier {
	@Override
	public Component getDisplayName(int level) {
		return applyStyle(new TranslatableComponent(getTranslationKey())
				.append(" ")
				.append(RomanNumeralHelper.getNumeral(level))
				.withStyle(Style.EMPTY.withColor(TextColor.fromRgb(0xFFCC00))));
	}

	@Override
	public int afterEntityHit(IToolStackView tool, int level, ToolAttackContext context, float damageDealt) {
		LivingEntity attacker = context.getAttacker();
		LivingEntity target = context.getLivingTarget();

		if (damageDealt > 0 && !attacker.level.isClientSide() && !target.isAlive()) {

			for (float x = -0.5f; x <= 0.5f; x += 0.5f) {
				for (float z = -0.5f; z <= 0.5f; z += 0.5f) {
					if (RandomUtil.randomNumberUpTo(40) < level * 12) {
						ItemEntity coin = new ItemEntity(attacker.level, target.getX(), target.getY() + target.getBbHeight(), target.getZ(), new ItemStack(RandomUtil.randomNumberUpTo(30) < level ? AoAItems.SILVER_COIN.get() : AoAItems.COPPER_COIN.get()));

						coin.setPickUpDelay(10);
						coin.setDeltaMovement(coin.getDeltaMovement().add(x, 0.5, z));
						coin.lifespan = 200;
						attacker.level.addFreshEntity(coin);
					}
				}
			}
		}

		return super.afterEntityHit(tool, level, context, damageDealt);
	}
}
