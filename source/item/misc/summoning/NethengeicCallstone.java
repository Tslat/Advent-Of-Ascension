package net.tslat.aoa3.item.misc.summoning;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoADimensions;
import net.tslat.aoa3.common.registration.AoAEntities;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.entity.boss.NethengeicWitherEntity;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class NethengeicCallstone extends BossSpawningItem {
	public NethengeicCallstone() {
		super(AoASounds.ENTITY_NETHENGEIC_WITHER_AMBIENT, ParticleTypes.ENTITY_EFFECT, ParticleTypes.INSTANT_EFFECT);
	}

	@Override
	public void spawnBoss(World world, ServerPlayerEntity summoner, double posX, double posY, double posZ) {
		NethengeicWitherEntity nethengeicWither = new NethengeicWitherEntity(AoAEntities.Mobs.NETHENGEIC_WITHER.get(), world);

		nethengeicWither.moveTo(posX, posY, posZ, RandomUtil.randomValueUpTo(360f), 0f);
		world.addFreshEntity(nethengeicWither);
		PlayerUtil.messageAllPlayersInRange(LocaleUtil.getLocaleMessage(AoAEntities.Mobs.NETHENGEIC_WITHER.get().getDescriptionId() + ".spawn", summoner.getDisplayName()), world, new BlockPos(posX, posY, posZ), 50);
	}

	@Override
	public boolean canSpawnHere(World world, ServerPlayerEntity player, double posX, double posY, double posZ) {
		if (world.getDifficulty() == Difficulty.PEACEFUL) {
			PlayerUtil.notifyPlayer(player, "message.feedback.spawnBoss.difficultyFail", TextFormatting.RED);

			return false;
		}

		if (!WorldUtil.isWorld(world, AoADimensions.NETHER.key)) {
			PlayerUtil.notifyPlayer(player, AoAEntities.Mobs.NETHENGEIC_WITHER.get().getDescriptionId() + ".wrongDimension", TextFormatting.RED);

			return false;
		}

		if (!world.noCollision(new AxisAlignedBB(posX - 1d, posY, posZ - 1d, posX + 1d, posY + 2.5d, posZ + 1d))) {
			PlayerUtil.notifyPlayer(player, "message.feedback.spawnBoss.noSpace", TextFormatting.RED);

			return false;
		}

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 2));
		tooltip.add(LocaleUtil.getLocaleMessage("items.description.boss_summon_item.unstable", TextFormatting.AQUA));
	}
}
