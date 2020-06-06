package net.tslat.aoa3.item.misc.summon;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.entity.boss.nethengeicwither.EntityNethengeicWither;
import net.tslat.aoa3.utils.StringUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class NethengeicCallstone extends BossSpawningItem {
	public NethengeicCallstone() {
		super("NethengeicCallstone", "nethengeic_callstone", SoundsRegister.MOB_NETHENGEIC_WITHER_LIVING, EnumParticleTypes.SPELL_MOB, EnumParticleTypes.SPELL_INSTANT);
	}

	@Override
	public void spawnBoss(World world, EntityPlayer summoner, double posX, double posY, double posZ) {
		EntityNethengeicWither nethengeicWither = new EntityNethengeicWither(world);

		nethengeicWither.setLocationAndAngles(posX, posY, posZ, itemRand.nextFloat() * 360f, 0f);
		world.spawnEntity(nethengeicWither);
		StringUtil.sendMessageWithinRadius(StringUtil.getLocaleWithArguments("message.mob.nethengeicWither.spawn", summoner.getDisplayNameString()), nethengeicWither, 50);
	}

	@Override
	public boolean canSpawnHere(World world, EntityPlayer player, double posX, double posY, double posZ) {
		if (world.provider.getDimension() != -1) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.mob.nethengeicWither.incorrectDimension");

			return false;
		}

		if (world.collidesWithAnyBlock(new AxisAlignedBB(posX - 1d, posY, posZ - 1d, posX + 1d, posY + 2.5d, posZ + 1d))) {
			PlayerUtil.getAdventPlayer(player).sendThrottledChatMessage("message.feedback.spawnBoss.noSpace");

			return false;
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(StringUtil.getColourLocaleString("item.NethengeicCallstone.desc.1", TextFormatting.DARK_RED));
		tooltip.add(StringUtil.getColourLocaleString("item.NethengeicCallstone.desc.2", TextFormatting.DARK_RED));
		tooltip.add(StringUtil.getColourLocaleString("items.description.bossSummonItem.unstable", TextFormatting.DARK_RED));
	}
}
