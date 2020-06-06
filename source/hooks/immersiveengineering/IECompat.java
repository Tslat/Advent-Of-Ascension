package net.tslat.aoa3.hooks.immersiveengineering;

import blusunrize.immersiveengineering.api.ComparableItemStack;
import blusunrize.immersiveengineering.api.tool.BelljarHandler;
import blusunrize.immersiveengineering.api.tool.ChemthrowerHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.BlockFire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityBlaze;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.tslat.aoa3.block.functional.crops.CropBlock;
import net.tslat.aoa3.common.registration.BlockRegister;
import net.tslat.aoa3.common.registration.FluidsRegister;

import javax.annotation.Nullable;
import java.util.HashSet;

public class IECompat {
	public static void init() {
		registerPlantHandlers();

		ChemthrowerHandler.registerEffect(FluidsRegister.CANDIED_WATER, new ChemThrowerEffectCandiedWater());
	}

	private static void registerPlantHandlers() {
		registerPlantHandler(BlockRegister.BUBBLE_BERRY_CROP);
		registerPlantHandler(BlockRegister.EYE_BULB_CROP);
		registerPlantHandler(BlockRegister.FLORACLES_CROP);
		registerPlantHandler(BlockRegister.GOLDICAPS_CROP);
		registerPlantHandler(BlockRegister.HEART_FRUIT_CROP);
		registerPlantHandler(BlockRegister.HOLLY_TOPS_CROP);
		registerPlantHandler(BlockRegister.LUNACRIKE_CROP);
		registerPlantHandler(BlockRegister.LUNALON_CROP);
		registerPlantHandler(BlockRegister.LUNA_GLOBE_CROP);
		registerPlantHandler(BlockRegister.MAGIC_MARANG_CROP);
		registerPlantHandler(BlockRegister.MYSTIC_SHROOM_CROP);
		registerPlantHandler(BlockRegister.ROSIDON_CROP);
		registerPlantHandler(BlockRegister.TEA_CROP);
		registerPlantHandler(BlockRegister.THORNY_PLANT_CROP);
		registerPlantHandler(BlockRegister.TRILLIAD_CROP);
	}

	private static void registerPlantHandler(CropBlock crop) {
		final IBlockState cropBlockState = crop.getDefaultState();
		ItemStack[] outputStacks = new ItemStack[] {new ItemStack(crop.getCrop()), new ItemStack((Item)crop.getSeeds(), 1)};

		BelljarHandler.DefaultPlantHandler plantHandler = new BelljarHandler.DefaultPlantHandler() {
			private final Item seed = (Item)crop.getSeeds();
			private final HashSet<ComparableItemStack> seeds = new HashSet<ComparableItemStack>(1);

			@Override
			protected HashSet<ComparableItemStack> getSeedSet() {
				return seeds;
			}

			@Override
			public boolean isValid(ItemStack seed) {
				return seed.getItem() == this.seed;
			}

			@Override
			public boolean isCorrectSoil(ItemStack seed, ItemStack soil) {
				return Block.getBlockFromItem(soil.getItem()) == Blocks.DIRT;
			}

			@Override
			public IBlockState[] getRenderedPlant(ItemStack seed, ItemStack soil, float growth, TileEntity tile) {
				return new IBlockState[] {cropBlockState.withProperty(BlockCrops.AGE, Math.min(7, Math.round(7 * growth)))};
			}

			@Override
			public ItemStack[] getOutput(ItemStack seed, ItemStack soil, TileEntity tile) {
				return outputStacks;
			}
		};

		BelljarHandler.registerHandler(plantHandler);
	}

	private static class ChemThrowerEffectCandiedWater extends ChemthrowerHandler.ChemthrowerEffect {
		private ChemThrowerEffectCandiedWater() {}

		@Override
		public void applyToEntity(EntityLivingBase target, @Nullable EntityPlayer entityPlayer, ItemStack itemStack, Fluid fluid) {
			if (target.isBurning())
				target.extinguish();

			if ((target instanceof EntityBlaze || target instanceof EntityEnderman) && target.attackEntityFrom((entityPlayer == null ? DamageSource.DROWN : (new EntityDamageSource(DamageSource.DROWN.getDamageType(), entityPlayer)).setDamageBypassesArmor()), 3))
				target.hurtResistantTime = (int)((double)target.hurtResistantTime * 0.75d);

			if (target.getRNG().nextFloat() <= 0.5f)
				target.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 20));
		}

		@Override
		public void applyToBlock(World world, RayTraceResult rayTraceResult, @Nullable EntityPlayer entityPlayer, ItemStack itemStack, Fluid fluid) {
			BlockPos pos = rayTraceResult.getBlockPos().offset(rayTraceResult.sideHit);
			Block block = world.getBlockState(pos).getBlock();

			if (block instanceof BlockFire)
				world.setBlockToAir(pos);
		}
	}
}
