package net.tslat.aoa3.block.functional.utility;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.*;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.capabilities.handlers.AdventPlayerCapability;
import net.tslat.aoa3.common.registration.*;
import net.tslat.aoa3.item.misc.InfusionStone;
import net.tslat.aoa3.item.weapon.blaster.BaseBlaster;
import net.tslat.aoa3.item.weapon.greatblade.BaseGreatblade;
import net.tslat.aoa3.item.weapon.gun.BaseGun;
import net.tslat.aoa3.item.weapon.staff.BaseStaff;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PlayerUtil;
import net.tslat.aoa3.utils.skills.InfusionUtil;

import javax.annotation.Nullable;

import static net.minecraft.init.Enchantments.*;

public class InfusionTable extends Block {
	public InfusionTable() {
		super(Material.ROCK);
		setUnlocalizedName("InfusionTable");
		setRegistryName("aoa3:infusion_table");
		setHardness(10.0f);
		setResistance(15.0f);
		setSoundType(SoundType.STONE);
		setCreativeTab(CreativeTabsRegister.functionalBlocksTab);
	}

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer player, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			ItemStack stack = player.getHeldItem(hand);
			Item item = stack.getItem();

			if (item instanceof InfusionStone) {
				AdventPlayerCapability cap = PlayerUtil.getAdventPlayer(player);
				InfusionStone stone = (InfusionStone)item;

				if (player.capabilities.isCreativeMode || cap.getLevel(Enums.Skills.INFUSION) >= stone.getLvl()) {
					if (!player.capabilities.isCreativeMode)
						player.getHeldItem(hand).shrink(1);

					cap.addXp(Enums.Skills.INFUSION, stone.getXp(), false);
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.infusionSuccess, SoundCategory.BLOCKS, 1.0f, 1.0f);

					if (AdventOfAscension.rand.nextInt(100) == 0)
						ItemUtil.givePlayerItemOrDrop(player, new ItemStack(stone.getPowerStone()));
				}

				return true;
			}
			else {
				InfusionType type = getInfusionType(world, pos.getX(), pos.getY(), pos.getZ());
				boolean success = false;

				if (type == null) {
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.infusionFail, SoundCategory.BLOCKS, 1.0f, 1.0f);
					return true;
				}

				if (item instanceof ItemSword) {
					if (type == InfusionType.DAMAGE) {
						if (EnchantmentHelper.getEnchantmentLevel(SHARPNESS, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneGlistening))) {
							InfusionUtil.infuseStack(player, stack, SHARPNESS);
							success = true;
						}
					}
					else if (type == InfusionType.WEIGHT) {
						if (EnchantmentHelper.getEnchantmentLevel(KNOCKBACK, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneGlaring))) {
							InfusionUtil.infuseStack(player, stack, KNOCKBACK);
							success = true;
						}
					}
					else if (type == InfusionType.DURABILITY) {
						if (EnchantmentHelper.getEnchantmentLevel(UNBREAKING, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneBlooming))) {
							InfusionUtil.infuseStack(player, stack, UNBREAKING);
							success = true;
						}
					}
				}
				else if (item instanceof ItemFishingRod) {
					if (type == InfusionType.DURABILITY) {
						if (EnchantmentHelper.getEnchantmentLevel(UNBREAKING, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneBlooming))) {
							InfusionUtil.infuseStack(player, stack, UNBREAKING);
							success = true;
						}
					}
					else if (type == InfusionType.MAGICAL) {
						if (EnchantmentHelper.getEnchantmentLevel(LUCK_OF_THE_SEA, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneRadiant))) {
							InfusionUtil.infuseStack(player, stack, LUCK_OF_THE_SEA);
							success = true;
						}

						if (EnchantmentHelper.getEnchantmentLevel(LURE, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneGlowing))) {
							InfusionUtil.infuseStack(player, stack, LURE);
							success = true;
						}
					}
				}
				else if (item instanceof ItemBow) {
					if (type == InfusionType.DURABILITY) {
						if (EnchantmentHelper.getEnchantmentLevel(UNBREAKING, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneBlooming))) {
							InfusionUtil.infuseStack(player, stack, UNBREAKING);
							success = true;
						}
					}
					else if (type == InfusionType.WEIGHT) {
						if (EnchantmentHelper.getEnchantmentLevel(PUNCH, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneGlaring))) {
							InfusionUtil.infuseStack(player, stack, PUNCH);
							success = true;
						}
					}
					else if (type == InfusionType.DAMAGE) {
						if (EnchantmentHelper.getEnchantmentLevel(POWER, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneGlistening))) {
							InfusionUtil.infuseStack(player, stack, POWER);
							success = true;
						}
					}
				}
				else if (item instanceof ItemTool) {
					if (type == InfusionType.DURABILITY) {
						if (EnchantmentHelper.getEnchantmentLevel(UNBREAKING, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneBlooming))) {
							InfusionUtil.infuseStack(player, stack, UNBREAKING);
							success = true;
						}
					}
					else if (type == InfusionType.SPEED) {
						if (EnchantmentHelper.getEnchantmentLevel(EFFICIENCY, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneGlowing))) {
							InfusionUtil.infuseStack(player, stack, EFFICIENCY);
							success = true;
						}
					}
				}
				else if (item instanceof ItemArmor) {
					if (type == InfusionType.DURABILITY) {
						if (EnchantmentHelper.getEnchantmentLevel(UNBREAKING, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneBlooming))) {
							InfusionUtil.infuseStack(player, stack, UNBREAKING);
							success = true;
						}
					}
					else if (type == InfusionType.RESISTANCE) {
						if (EnchantmentHelper.getEnchantmentLevel(PROTECTION, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneShining))) {
							InfusionUtil.infuseStack(player, stack, PROTECTION);
							success = true;
						}
					}
				}
				else if (item instanceof BaseGun) {
					if (type == InfusionType.DURABILITY) {
						if (EnchantmentHelper.getEnchantmentLevel(UNBREAKING, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneBlooming))) {
							InfusionUtil.infuseStack(player, stack, UNBREAKING);
							success = true;
						}
					}
					else if (type == InfusionType.DAMAGE) {
						if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.shell, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneGlaring))) {
							InfusionUtil.infuseStack(player, stack, EnchantmentsRegister.shell);
							success = true;
						}
					}
					else if (type == InfusionType.WEIGHT) {
						if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.control, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneRadiant))) {
							InfusionUtil.infuseStack(player, stack, EnchantmentsRegister.control);
							success = true;
						}
					}
				}
				else if (item instanceof BaseBlaster) {
					if (type == InfusionType.DURABILITY) {
						if (EnchantmentHelper.getEnchantmentLevel(UNBREAKING, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneBlooming))) {
							InfusionUtil.infuseStack(player, stack, UNBREAKING);
							success = true;
						}
					}
					else if (type == InfusionType.SPEED) {
						if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.recharge, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneRadiant))) {
							InfusionUtil.infuseStack(player, stack, EnchantmentsRegister.recharge);
							success = true;
						}
					}
				}
				else if (item instanceof BaseGreatblade) {
					if (type == InfusionType.DURABILITY) {
						if (EnchantmentHelper.getEnchantmentLevel(UNBREAKING, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneBlooming))) {
							InfusionUtil.infuseStack(player, stack, UNBREAKING);
							success = true;
						}
					}
					else if (type == InfusionType.DAMAGE) {
						if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.sever, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneGlaring))) {
							InfusionUtil.infuseStack(player, stack, EnchantmentsRegister.sever);
							success = true;
						}
					}
					else if (type == InfusionType.WEIGHT) {
						if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.crush, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneRadiant))) {
							InfusionUtil.infuseStack(player, stack, EnchantmentsRegister.crush);
							success = true;
						}
					}
				}
				else if (item instanceof BaseStaff) {
					if (type == InfusionType.DURABILITY) {
						if (EnchantmentHelper.getEnchantmentLevel(UNBREAKING, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneBlooming))) {
							InfusionUtil.infuseStack(player, stack, UNBREAKING);
							success = true;
						}
					}
					else if (type == InfusionType.MAGICAL) {
						if (EnchantmentHelper.getEnchantmentLevel(EnchantmentsRegister.archmage, stack) == 0 && ItemUtil.consumeItem(player, new ItemStack(ItemRegister.powerStoneRadiant))) {
							InfusionUtil.infuseStack(player, stack, EnchantmentsRegister.archmage);
							success = true;
						}
					}
				}

				if (success) {
					world.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundsRegister.infusionSuccess, SoundCategory.BLOCKS, 1.0f, 1.0f);
					player.inventoryContainer.detectAndSendChanges();
				}
			}

			return false;
		}

		return true;
	}

	@Nullable
	private InfusionType getInfusionType(final World world, final int posX, final int posY, final int posZ) {
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(posX + 3, posY + 1, posZ);
		String testBlockName = world.getBlockState(pos).getBlock().getUnlocalizedName();
		Block block2;
		Block block3;
		Block block4;

		switch (testBlockName) {
			case "tile.DamageEnhancer":
				block2 = world.getBlockState(pos.setPos(posX - 3, posY + 1, posZ)).getBlock();
				block3 = world.getBlockState(pos.setPos(posX, posY + 1, posZ + 3)).getBlock();
				block4 = world.getBlockState(pos.setPos(posX, posY + 1, posZ - 3)).getBlock();

				if (block2 == BlockRegister.enhancerDamage && block3 == BlockRegister.enhancerDamage && block4 == BlockRegister.enhancerDamage)
					return InfusionType.DAMAGE;
				break;
			case "tile.DurabilityEnhancer":
				block2 = world.getBlockState(pos.setPos(posX - 3, posY + 1, posZ)).getBlock();
				block3 = world.getBlockState(pos.setPos(posX, posY + 1, posZ + 3)).getBlock();
				block4 = world.getBlockState(pos.setPos(posX, posY + 1, posZ - 3)).getBlock();

				if (block2 == BlockRegister.enhancerDurability && block3 == BlockRegister.enhancerDurability && block4 == BlockRegister.enhancerDurability)
					return InfusionType.DURABILITY;
				break;
			case "tile.WeightEnhancer":
				block2 = world.getBlockState(pos.setPos(posX - 3, posY + 1, posZ)).getBlock();
				block3 = world.getBlockState(pos.setPos(posX, posY + 1, posZ + 3)).getBlock();
				block4 = world.getBlockState(pos.setPos(posX, posY + 1, posZ - 3)).getBlock();

				if (block2 == BlockRegister.enhancerWeight && block3 == BlockRegister.enhancerWeight && block4 == BlockRegister.enhancerWeight)
					return InfusionType.WEIGHT;
				break;
			case "tile.SpeedEnhancer":
				block2 = world.getBlockState(pos.setPos(posX - 3, posY + 1, posZ)).getBlock();
				block3 = world.getBlockState(pos.setPos(posX, posY + 1, posZ + 3)).getBlock();
				block4 = world.getBlockState(pos.setPos(posX, posY + 1, posZ - 3)).getBlock();

				if (block2 == BlockRegister.enhancerSpeed && block3 == BlockRegister.enhancerSpeed && block4 == BlockRegister.enhancerSpeed)
					return InfusionType.SPEED;
				break;
			case "tile.ResistanceEnhancer":
				block2 = world.getBlockState(pos.setPos(posX - 3, posY + 1, posZ)).getBlock();
				block3 = world.getBlockState(pos.setPos(posX, posY + 1, posZ + 3)).getBlock();
				block4 = world.getBlockState(pos.setPos(posX, posY + 1, posZ - 3)).getBlock();

				if (block2 == BlockRegister.enhancerResistance && block3 == BlockRegister.enhancerResistance && block4 == BlockRegister.enhancerResistance)
					return InfusionType.RESISTANCE;
				break;
			case "tile.MagicalEnhancer":
				block2 = world.getBlockState(pos.setPos(posX - 3, posY + 1, posZ)).getBlock();
				block3 = world.getBlockState(pos.setPos(posX, posY + 1, posZ + 3)).getBlock();
				block4 = world.getBlockState(pos.setPos(posX, posY + 1, posZ - 3)).getBlock();

				if (block2 == BlockRegister.enhancerMagical && block3 == BlockRegister.enhancerMagical && block4 == BlockRegister.enhancerMagical)
					return InfusionType.MAGICAL;
				break;
			default:
				return null;
		}

		return null;
	}

	private enum InfusionType {
		DAMAGE,
		DURABILITY,
		WEIGHT,
		SPEED,
		RESISTANCE,
		MAGICAL
	}
}
