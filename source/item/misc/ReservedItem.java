package net.tslat.aoa3.item.misc;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.WeaponRegister;
import net.tslat.aoa3.entity.mobs.overworld.fullmoon.*;
import net.tslat.aoa3.entity.mobs.shyrelands.EntityArcworm;
import net.tslat.aoa3.item.weapon.blaster.ExperimentW801;
import net.tslat.aoa3.utils.ItemUtil;

public class ReservedItem extends SimpleItem {
	private final String sequenceId;

	public ReservedItem(String name, String registryName, String sequenceName) {
		super(name, registryName);

		setCreativeTab(null);

		this.sequenceId = sequenceName;
	}

	@Override
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		if (!verifyStack(stack)) {
			stack.setCount(0);
			entityIn.replaceItemInInventory(itemSlot, ItemStack.EMPTY);
		}
	}

	@Override
	public EnumActionResult onItemUseFirst(EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		Block block = world.getBlockState(pos).getBlock();

		/*if (block == BlockRegister.logShyre) {
			if (!world.isRemote) {
				world.setBlockToAir(pos);
				Block.spawnAsEntity(world, pos, ItemRegister.soulbone.newValidStack());
				player.setHeldItem(hand, ItemStack.EMPTY);

				for (int i = 0; i < 5; i++) {
					((WorldServer)world).spawnParticle(EnumParticleTypes.FLAME, pos.getX() + itemRand.nextFloat(), pos.getY() + itemRand.nextFloat(), pos.getZ() + itemRand.nextFloat(), 1, 0, 0, 0, (double)0);
				}
			}

			return EnumActionResult.SUCCESS;
		}
		else */if (block == BlockRegister.chargingTable) {
			if (this == ItemRegister.fleshyBones) {
				player.setHeldItem(hand, ItemRegister.darkBones.newValidStack());


				return EnumActionResult.SUCCESS;
			}
		}
		else if (block == BlockRegister.mineralizationStation) {
			if (this == ItemRegister.darkBones) {
				player.setHeldItem(hand, ItemRegister.rockBones.newValidStack());


				return EnumActionResult.SUCCESS;
			}
		}

		return super.onItemUseFirst(player, world, pos, side, hitX, hitY, hitZ, hand);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, EntityLivingBase attacker) {
		if (!target.world.isRemote) {
			if (stack.getItem() == ItemRegister.milleniumUpgrader) {
				if (target.isInLava() && attacker.isInLava()) {
					attacker.setHeldItem(EnumHand.MAIN_HAND, ItemRegister.moltenUpgrader.newValidStack());

					return true;
				}
			}
			else if (stack.getItem() == ItemRegister.moltenUpgrader) {
				if (target.world.getCurrentMoonPhaseFactor() == 1 && target.getHealth() <= 0 && (target instanceof EntityDarkBeast || target instanceof EntityIrkling || target instanceof EntityNightWatcher || target instanceof EntityScrubby || target instanceof EntitySkellox)) {
					attacker.setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
					target.entityDropItem(ItemRegister.moonstone.newValidStack(), 0);

					return true;
				}
			}
			else if (stack.getItem() == ItemRegister.moonstone) {
				if (target instanceof EntityArcworm)
					target.addPotionEffect(new PotionEffect(MobEffects.LEVITATION, 200, 10, true, false));

				return true;
			}
		}

		return false;
	}

	@Override
	public boolean onEntityItemUpdate(EntityItem entity) {
		if (!verifyStack(entity.getItem())) {
			entity.setItem(ItemStack.EMPTY);
			entity.setDead();
		}

		return false;
	}

	public ItemStack newValidStack() {
		ItemStack stack = new ItemStack(this);
		NBTTagCompound tag = new NBTTagCompound();

		tag.setBoolean(sequenceId, true);
		stack.setTagCompound(tag);

		return stack;
	}

	private boolean verifyStack(ItemStack stack) {
		if (stack.isEmpty())
			return false;

		if (!stack.hasTagCompound())
			return false;

		NBTTagCompound tag = stack.getTagCompound();

		if (!tag.hasKey(sequenceId))
			return false;

		return tag.getBoolean(sequenceId);
	}

	public static void handleArcworm(EntityArcworm arcworm) {
		if (arcworm.posY > 275 && arcworm.getAttackingEntity() instanceof EntityPlayer) {
			if (arcworm.getAttackingEntity().getHeldItemMainhand().getItem() == ItemRegister.moonstone) {
				arcworm.entityDropItem(((ExperimentW801)WeaponRegister.blasterExperimentW801).newValidStack(), 0);
				arcworm.getAttackingEntity().setHeldItem(EnumHand.MAIN_HAND, ItemStack.EMPTY);
				arcworm.setDead();
			}
		}
	}

	public static void handlePlayerDeath(EntityPlayer pl) {
		if (pl.getHealth() > 0)
			return;

		if (!pl.getHeldItemMainhand().getItem().getRegistryName().getPath().toLowerCase().startsWith("a"))
			return;

		NonNullList<ItemStack> armour = pl.inventory.armorInventory;

		if (!armour.get(3).getItem().getRegistryName().getPath().toLowerCase().startsWith("l"))
			return;

		if (!armour.get(2).getItem().getRegistryName().getPath().toLowerCase().startsWith("i"))
			return;

		if (!armour.get(1).getItem().getRegistryName().getPath().toLowerCase().startsWith("e"))
			return;

		if (!armour.get(0).getItem().getRegistryName().getPath().toLowerCase().startsWith("n"))
			return;

		if (ItemUtil.findItemInInventory(pl, ItemRegister.alienOrb) != -1)
			return;

		pl.entityDropItem(ItemRegister.alienOrb.newValidStack(), 0);
	}
}
