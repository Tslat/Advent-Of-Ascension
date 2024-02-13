package net.tslat.aoa3.content.item.misc;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.registries.DeferredItem;
import net.tslat.aoa3.common.registration.custom.AoASkills;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.InteractionResults;
import net.tslat.aoa3.util.PlayerUtil;

public class EnergyStone extends Item {
	private final DeferredItem<Item> powerStone;
	private final float levelFractionXp;

	public EnergyStone(float levelFraction, DeferredItem<Item> powerStone) {
		super(new Item.Properties());
		this.levelFractionXp = levelFraction;
		this.powerStone = powerStone;
	}

	public Item getPowerStone() {
		return this.powerStone.get();
	}

	public float getLevelFractionXp() {
		return this.levelFractionXp;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);

		if (player instanceof ServerPlayer pl) {
			float cumulativeXp = 0;
			AoASkill.Instance imbuing = PlayerUtil.getSkill(pl, AoASkills.IMBUING.get());
			float levelResidualXp = imbuing.getXp();
			int imbuingLevel = imbuing.getLevel(true);
			float requiredXp = PlayerUtil.getXpRequiredForNextLevel(imbuingLevel);

			for (int i = 0; i < stack.getCount(); i++) {
				float xp = PlayerUtil.getXpForFractionOfLevel(imbuingLevel, this.levelFractionXp);
				cumulativeXp += xp;
				levelResidualXp += xp;

				if (levelResidualXp >= requiredXp) {
					levelResidualXp -= requiredXp;
					requiredXp = PlayerUtil.getXpRequiredForNextLevel(++imbuingLevel);
				}
			}

			PlayerUtil.givePartialLevelToPlayer(pl, AoASkills.IMBUING.get(), cumulativeXp, false);

			if (!player.getAbilities().instabuild)
				stack.shrink(stack.getCount());
		}

		return InteractionResults.ItemUse.succeedAndSwingArmBothSides(stack, level.isClientSide);
	}
}
