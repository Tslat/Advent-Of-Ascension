package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.block.UnbreakableBlock;
import net.tslat.aoa3.capabilities.providers.AdventPlayerProvider;
import net.tslat.aoa3.entity.boss.coniferon.EntityConiferon;
import net.tslat.aoa3.entity.boss.goldorth.EntityGoldorth;
import net.tslat.aoa3.entity.boss.horon.EntityHoron;
import net.tslat.aoa3.entity.boss.penumbra.EntityPenumbra;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ConfigurationUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

public class AncientCavernShrine extends UnbreakableBlock {
	private final Enums.Deities deity;

	public AncientCavernShrine(String name, String registryName, Enums.Deities deity) {
		super(name, registryName, Material.ROCK);
		this.deity = deity;
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote && !player.isSneaking() && world.provider.getDimension() == ConfigurationUtil.MainConfig.dimensionIds.ancientCavern && player.hasCapability(AdventPlayerProvider.ADVENT_PLAYER, null)) {
			PlayerDataManager plData = PlayerUtil.getAdventPlayer(player);
			BlockPos teleportPos = new BlockPos(0, 17, 0);

			switch (deity) {
				case EREBON:
					if (pos.getX() > -20 || pos.getZ() < 60) {
						teleportPos = new BlockPos(-70, 18, 80);

						if (!player.capabilities.isCreativeMode) {
							if (/*plData.stats().getLevel(Enums.Skills.AUGURY) < 70 || */plData.stats().getLevel(Enums.Skills.HUNTER) < 70) {
								if (player instanceof EntityPlayerMP) {
									//PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.AUGURY, 70);
									PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.HUNTER, 70);
								}

								return true;
							}

							if (plData.stats().getTribute(Enums.Deities.EREBON) < 200) {
								if (player instanceof EntityPlayerMP)
									PlayerUtil.notifyPlayerOfInsufficientTribute((EntityPlayerMP)player, Enums.Deities.EREBON, 200);

								return true;
							}
							else {
								plData.stats().addTribute(Enums.Deities.EREBON, -20);
							}
						}

						EntityPenumbra penumbra = new EntityPenumbra(world);

						penumbra.setLocationAndAngles(-51, 18, 92, 0, 0);
						world.spawnEntity(penumbra);
					}
					break;
				case LUXON:
					if (pos.getX() < 60 || pos.getZ() > -20) {
						teleportPos = new BlockPos(80, 18, -70);

						if (!player.capabilities.isCreativeMode) {
							if (/*plData.stats().getLevel(Enums.Skills.AUGURY) < 70 || */plData.stats().getLevel(Enums.Skills.RUNATION) < 70) {
								if (player instanceof EntityPlayerMP) {
									//PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.AUGURY, 70);
									PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.RUNATION, 70);
								}

								return true;
							}

							if (plData.stats().getTribute(Enums.Deities.LUXON) < 200) {
								if (player instanceof EntityPlayerMP)
									PlayerUtil.notifyPlayerOfInsufficientTribute((EntityPlayerMP)player, Enums.Deities.LUXON, 200);

								return true;
							}
							else {
								plData.stats().addTribute(Enums.Deities.LUXON, -20);
							}
						}

						EntityHoron horon = new EntityHoron(world);

						horon.setLocationAndAngles(80, 18, -70, 0, 0);
						world.spawnEntity(horon);
					}
					break;
				case PLUTON:
					if (pos.getX() < 60 || pos.getZ() < 60) {
						teleportPos = new BlockPos(80, 18, 80);

						if (!player.capabilities.isCreativeMode) {
							if (/*plData.stats().getLevel(Enums.Skills.AUGURY) < 70 || */plData.stats().getLevel(Enums.Skills.FORAGING) < 70) {
								if (player instanceof EntityPlayerMP) {
									//PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.AUGURY, 70);
									PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.FORAGING, 70);
								}

								return true;
							}

							if (plData.stats().getTribute(Enums.Deities.PLUTON) < 200) {
								if (player instanceof EntityPlayerMP)
									PlayerUtil.notifyPlayerOfInsufficientTribute((EntityPlayerMP)player, Enums.Deities.PLUTON, 200);

								return true;
							}
							else {
								plData.stats().addTribute(Enums.Deities.PLUTON, -20);
							}
						}

						EntityGoldorth goldorth = new EntityGoldorth(world);

						goldorth.setLocationAndAngles(80, 18, 80, 0, 0);
						world.spawnEntity(goldorth);
					}
					break;
				case SELYAN:
					if (pos.getX() > -20 || pos.getZ() > -20) {
						teleportPos = new BlockPos(-60, 18, -65);

						if (!player.capabilities.isCreativeMode) {
							if (/*plData.stats().getLevel(Enums.Skills.AUGURY) < 70 || */plData.stats().getLevel(Enums.Skills.INFUSION) < 70) {
								if (player instanceof EntityPlayerMP) {
									//PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.AUGURY, 70);
									PlayerUtil.notifyPlayerOfInsufficientLevel((EntityPlayerMP)player, Enums.Skills.INFUSION, 70);
								}

								return true;
							}

							if (plData.stats().getTribute(Enums.Deities.SELYAN) < 200) {
								if (player instanceof EntityPlayerMP)
									PlayerUtil.notifyPlayerOfInsufficientTribute((EntityPlayerMP)player, Enums.Deities.SELYAN, 200);

								return true;
							}
							else {
								plData.stats().addTribute(Enums.Deities.SELYAN, -20);
							}
						}

						EntityConiferon coniferon = new EntityConiferon(world);

						coniferon.setLocationAndAngles(-60, 18, -65, 0, 0);
						world.spawnEntity(coniferon);
					}
					break;
			}

			player.setPositionAndUpdate(teleportPos.getX(), teleportPos.getY(), teleportPos.getZ());
		}

		return true;
	}
}
