package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.command.arguments.ILocationArgument;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.item.PaintingEntity;
import net.minecraft.nbt.*;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.MobSpawnerTileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.server.command.EnumArgument;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.util.StringUtil;
import net.tslat.aoa3.worldgen.structures.AoAStructure;
import net.tslat.aoa3.worldgen.structures.StructuresHandler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class StructureCommand implements Command<CommandSource> {
	public static boolean isConverting = false;
	public static BiConsumer<BlockState, BlockPos> conversionStateConsumer;
	public static BiConsumer<ResourceLocation, BlockPos> conversionChestConsumer;
	public static BiConsumer<EntityType<?>, BlockPos> conversionSpawnerConsumer;
	public static Consumer<Entity> conversionEntityConsumer;

	private static final StructureCommand CMD = new StructureCommand();

	private Map<ResourceLocation, Template> templates = null;

	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("structures").requires(sender -> sender.hasPermissionLevel(4)).executes(CMD);

		builder.then(Commands.literal("generate")
				.then(Commands.argument("structure_id", StructureIdArgument.instance())
						.then(Commands.argument("position", BlockPosArgument.blockPos())
								.executes(context -> spawnStructure(context, Mirror.NONE, Rotation.NONE, false)))
								.then(Commands.argument("mirror", EnumArgument.enumArgument(Mirror.class))
										.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), Rotation.NONE, false)))
										.then(Commands.argument("rotation", EnumArgument.enumArgument(Rotation.class))
												.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), context.getArgument("rotation", Rotation.class), false)))
												.then(Commands.argument("ignore_entities", BoolArgumentType.bool())
														.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), context.getArgument("rotation", Rotation.class), context.getArgument("ignore_entities", Boolean.class)))))
				.then(Commands.literal("aoa")
						.then(Commands.argument("structure_name", StringArgumentType.word())
							.then(Commands.argument("position", BlockPosArgument.blockPos())
									.executes(context -> spawnAoAStructure(context, Mirror.NONE, Rotation.NONE, false)))
							.then(Commands.argument("mirror", EnumArgument.enumArgument(Mirror.class))
									.executes(context -> spawnAoAStructure(context, context.getArgument("mirror", Mirror.class), Rotation.NONE, false)))
							.then(Commands.argument("rotation", EnumArgument.enumArgument(Rotation.class))
									.executes(context -> spawnAoAStructure(context, context.getArgument("mirror", Mirror.class), context.getArgument("rotation", Rotation.class), false)))
							.then(Commands.argument("ignore_entities", BoolArgumentType.bool())
									.executes(context -> spawnAoAStructure(context, context.getArgument("mirror", Mirror.class), context.getArgument("rotation", Rotation.class), context.getArgument("ignore_entities", Boolean.class)))))));
		builder.then(Commands.literal("convert").requires(context -> !FMLEnvironment.production)
				.then(Commands.argument("structure_name", StringArgumentType.word())
						.then(Commands.argument("ignore_air", BoolArgumentType.bool())
								.executes(StructureCommand::convertStructure))));

		return builder;
	}

	private static int convertStructure(CommandContext<CommandSource> cmd) {
		String structureId = cmd.getArgument("structure_name", String.class);
		boolean ignoreAir = cmd.getArgument("ignore_air", Boolean.class);
		AoAStructure structure = StructuresHandler.getStructure(structureId);

		if (structure == StructuresHandler.EMPTY_STRUCTURE) {
			AoACommand.feedback(cmd.getSource(), "Structures", "command.aoa.structures.invalidStructure", AoACommand.CommandFeedbackType.WARN, structureId);

			return 1;
		}

		try {
			doStructureConversion(cmd.getSource(), structure, ignoreAir);
		}
		catch (Exception ex) {
			AoACommand.feedback(cmd.getSource(), "Structures", "Failed to convert '" + structureId + "' to NBT.", AoACommand.CommandFeedbackType.ERROR);

			ex.printStackTrace();
		}
		finally {
			isConverting = false;
			conversionChestConsumer = null;
			conversionStateConsumer = null;
			conversionSpawnerConsumer = null;
			conversionEntityConsumer = null;
		}

		return 1;
	}

	private static int spawnStructure(CommandContext<CommandSource> cmd, Mirror mirror, Rotation rotation, boolean ignoreEntities) throws CommandSyntaxException {
		try {
			ResourceLocation id = StructureIdArgument.getStructureId(cmd, "structure_id");
			Template template = ((MinecraftServer)LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER)).getWorld(DimensionType.OVERWORLD).getStructureTemplateManager().getTemplate(id);
			BlockPos spawnPos = cmd.getArgument("position", ILocationArgument.class).getBlockPos(cmd.getSource());

			if (template == null) {
				AoACommand.feedback(cmd.getSource(), "Structures", "command.aoa.structures.invalidStructure", AoACommand.CommandFeedbackType.WARN, id.toString());

				return 1;
			}
			else {
				template.addBlocksToWorld(cmd.getSource().getWorld(), spawnPos, new PlacementSettings().setMirror(mirror).setRotation(rotation).setIgnoreEntities(ignoreEntities).setChunk(null));
			}

			AoACommand.feedback(cmd.getSource(), "Structures", "command.aoa.structures.spawn", AoACommand.CommandFeedbackType.SUCCESS, id.toString(), spawnPos.getX() + ", " + spawnPos.getY() + ", " + spawnPos.getZ());
		}
		catch (ResourceLocationException ex) {
			AoACommand.error(cmd.getSource(), "Structures", ex.getLocalizedMessage());
		}

		return 1;
	}

	private static int spawnAoAStructure(CommandContext<CommandSource> cmd, Mirror mirror, Rotation rotation, boolean ignoreEntities) throws CommandSyntaxException {
		try {
			String structureName = cmd.getArgument("structure_name", String.class);
			BlockPos spawnPos = cmd.getArgument("position", ILocationArgument.class).getBlockPos(cmd.getSource());
			AoAStructure structure = StructuresHandler.getStructure(structureName);

			if (structure == StructuresHandler.EMPTY_STRUCTURE) {
				AoACommand.feedback(cmd.getSource(), "Structures", "command.aoa.structures.invalidStructure", AoACommand.CommandFeedbackType.WARN, structureName);

				return 1;
			}
			else {
				structure.generate(cmd.getSource().getWorld(), new Random(), spawnPos);
			}

			AoACommand.feedback(cmd.getSource(), "Structures", "command.aoa.structures.spawn", AoACommand.CommandFeedbackType.SUCCESS, structureName, spawnPos.getX() + ", " + spawnPos.getY() + ", " + spawnPos.getZ());
		}
		catch (ResourceLocationException ex) {
			AoACommand.error(cmd.getSource(), "Structures", ex.getLocalizedMessage());
		}

		return 1;
	}

	private static ListNBT vec3iToListNbt(Vec3i vec) {
		ListNBT nbt = new ListNBT();

		nbt.add(0, IntNBT.valueOf(vec.getX()));
		nbt.add(0, IntNBT.valueOf(vec.getY()));
		nbt.add(0, IntNBT.valueOf(vec.getZ()));

		return nbt;
	}

	private static void doStructureConversion(CommandSource source, AoAStructure structure, boolean ignoreAir) {
		ServerWorld world = source.getWorld();

		if (world.isRemote())
			return;

		isConverting = true;
		CompoundNBT structureNbt = new CompoundNBT();
		ListNBT blocksNbt = new ListNBT();
		ListNBT entitiesNbt = new ListNBT();
		ListNBT paletteNbt = new ListNBT();
		BlockPos.Mutable sizePos = new BlockPos.Mutable(0, 0, 0);
		ArrayList<BlockState> states = new ArrayList<BlockState>();
		ArrayList<String> errors = new ArrayList<String>();

		conversionStateConsumer = (state, pos) -> {
			if (ignoreAir && state.getBlock() == Blocks.AIR)
				return;

			CompoundNBT entry = new CompoundNBT();
			int stateId = -1;

			for (int i = 0; i < states.size(); i++) {
				if (states.get(i) == state) {
					stateId = i;

					break;
				}
			}

			if (stateId == -1) {
				stateId = states.size();
				states.add(state);
			}

			entry.put("pos", vec3iToListNbt(pos));
			entry.putInt("state", stateId);
			blocksNbt.add(entry);

			if (sizePos.getX() < pos.getX())
				sizePos.setPos(pos.getX(), sizePos.getY(), sizePos.getZ());

			if (sizePos.getY() < pos.getY())
				sizePos.setPos(sizePos.getX(), pos.getY(), sizePos.getZ());

			if (sizePos.getZ() < pos.getZ())
				sizePos.setPos(sizePos.getX(), sizePos.getY(), pos.getZ());
		};

		conversionChestConsumer = (id, pos) -> {
			for (INBT entry : blocksNbt) {
				CompoundNBT blockNbt = (CompoundNBT)entry;
				ListNBT blockPos = (ListNBT)blockNbt.get("pos");

				if (blockPos == null) {
					errors.add("Invalid block entry found, no pos marker exists. This is bad. " + pos.toString());

					return;
				}

				if (blockPos.equals(vec3iToListNbt(pos))) {
					CompoundNBT chestNbt = new CompoundNBT();
					Block bl;
					int stateId = blockNbt.getInt("state");

					if (stateId < states.size()) {
						bl = states.get(stateId).getBlock();
					}
					else {
						errors.add("Invalid state id retrieved for block at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ".");

						return;
					}

					chestNbt.putString("id", bl.getRegistryName().toString());
					chestNbt.putString("LootTable", id.toString());
					blockNbt.put("nbt", chestNbt);

					break;
				}
			}
		};

		conversionSpawnerConsumer = (entityType, pos) -> {
			for (INBT entry : blocksNbt) {
				CompoundNBT blockNbt = (CompoundNBT)entry;
				ListNBT blockPos = (ListNBT)blockNbt.get("pos");

				if (blockPos == null) {
					errors.add("Invalid block entry found, no pos marker exists. This is bad. " + pos.toString());

					return;
				}

				if (blockPos.equals(vec3iToListNbt(pos))) {
					MobSpawnerTileEntity tileEntity = new MobSpawnerTileEntity();
					CompoundNBT spawnerNbt = new CompoundNBT();

					tileEntity.getSpawnerBaseLogic().setEntityType(entityType);
					tileEntity.setWorldAndPos(world, pos);
					tileEntity.getSpawnerBaseLogic().write(spawnerNbt);
					blockNbt.put("nbt", spawnerNbt);

					break;
				}
			}
		};

		conversionEntityConsumer = entity -> {
			CompoundNBT entityNbt = new CompoundNBT();
			CompoundNBT entityData = new CompoundNBT();
			ListNBT posNbt = new ListNBT();
			ListNBT blockPosNbt = new ListNBT();

			Vec3d entityPos = entity.getPositionVector();
			BlockPos blockPos = entity instanceof PaintingEntity ? ((PaintingEntity)entity).getHangingPosition() : entity.getPosition();

			posNbt.add(DoubleNBT.valueOf(entityPos.getX()));
			posNbt.add(DoubleNBT.valueOf(entityPos.getY()));
			posNbt.add(DoubleNBT.valueOf(entityPos.getZ()));
			blockPosNbt.add(IntNBT.valueOf(blockPos.getX()));
			blockPosNbt.add(IntNBT.valueOf(blockPos.getY()));
			blockPosNbt.add(IntNBT.valueOf(blockPos.getZ()));
			entity.writeUnlessPassenger(entityData);

			entityNbt.put("pos", posNbt);
			entityNbt.put("blockPos", blockPosNbt);
			entityNbt.put("nbt", entityData);

			entitiesNbt.add(entityNbt);
		};

		structure.generate(world, world.getRandom(), new BlockPos(0, 0, 0));

		sizePos.move(1, 1, 1);

		if (sizePos.getX() > 48 || sizePos.getY() > 48 || sizePos.getZ() > 48)
			errors.add("Structure is too large, must fit within 32x32x32. Current size: " + sizePos.getX() + "x" + sizePos.getY() + "x" + sizePos.getZ());

		if (errors.isEmpty()) {
			for (BlockState state : states) {
				paletteNbt.add(NBTUtil.writeBlockState(state));
			}

			structureNbt.put("blocks", blocksNbt);
			structureNbt.put("entities", entitiesNbt);
			structureNbt.put("palette", paletteNbt);
			structureNbt.put("size", vec3iToListNbt(sizePos));
			structureNbt.putInt("DataVersion", SharedConstants.getVersion().getWorldVersion());

			saveStructureData(world, structure.getName(), structureNbt);
			AoACommand.feedback(source, "Structures", "Successfully converted '" + structure.getName() + "' to NBT.", AoACommand.CommandFeedbackType.SUCCESS);
		}
		else {
			AoACommand.feedback(source, "Structures", "Encountered " + errors.size() + " errors while attempting to convert. Cancelling operation", AoACommand.CommandFeedbackType.ERROR);

			for (String error : errors) {
				source.sendErrorMessage(new StringTextComponent(error));
			}
		}

		isConverting = false;
		conversionChestConsumer = null;
		conversionStateConsumer = null;
		conversionSpawnerConsumer = null;
		conversionEntityConsumer = null;
	}

	private static void saveStructureData(ServerWorld world, String structureId, CompoundNBT data) {
		TemplateManager templateManager = world.getStructureTemplateManager();
		Path path = templateManager.resolvePathStructures(new ResourceLocation(AdventOfAscension.MOD_ID, StringUtil.toSnakeCase(structureId)), ".nbt");
		Path parentPath = path.getParent();

		try {
			Files.createDirectories(Files.exists(parentPath) ? parentPath.toRealPath() : parentPath);

			try (OutputStream outStream = new FileOutputStream(path.toFile())) {
				CompressedStreamTools.writeCompressed(data, outStream);
			}
		}
		catch (IOException ex) {
			throw new IllegalStateException("Failed to create parent directory: " + parentPath.toAbsolutePath());
		}
	}

	@Override
	public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
		AoACommand.feedback(context.getSource(), "Structures", "command.aoa.structures.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}

	private static class StructureIdArgument implements ArgumentType<ResourceLocation> {
		private static final Collection<String> EXAMPLES = Arrays.asList("minecraft:bastion/starts", "aoa3:nether/nethengeic_pit/main_pool");
		private static final DynamicCommandExceptionType UNKNOWN_STRUCTURE_EXCEPTION = new DynamicCommandExceptionType(arg -> new TranslationTextComponent("command.aoa.structures.invalidStructure", arg));
		private static MinecraftServer server = null;

		private static StructureIdArgument instance() {
			return new StructureIdArgument();
		}

		public static ResourceLocation getStructureId(CommandContext<CommandSource> context, String name) throws CommandSyntaxException {
			return findStructure(context.getArgument(name, ResourceLocation.class));
		}

		private static ResourceLocation findStructure(ResourceLocation path) throws CommandSyntaxException {
			if (server == null)
				server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);

			TemplateManager templateManager = server.getWorld(DimensionType.OVERWORLD).getStructureTemplateManager();
			Template template = templateManager.getTemplate(path);

			if (template == null)
				throw UNKNOWN_STRUCTURE_EXCEPTION.create(path);

			return path;
		}

		@Override
		public ResourceLocation parse(StringReader reader) throws CommandSyntaxException {
			try {
				return ResourceLocation.read(reader);
			}
			catch (Exception ex) {
				if (StructuresHandler.getStructure(reader.getString()) != null)
					return new ResourceLocation(AdventOfAscension.MOD_ID, reader.getString());

				throw ex;
			}
		}

		@Override
		public Collection<String> getExamples() {
			return EXAMPLES;
		}
	}
}
