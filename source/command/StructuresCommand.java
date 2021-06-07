package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.BlockPosArgument;
import net.minecraft.command.arguments.ILocationArgument;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.ResourceLocationException;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.LogicalSidedProvider;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import net.minecraftforge.server.command.EnumArgument;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

public class StructuresCommand implements Command<CommandSource> {
	private static final StructuresCommand CMD = new StructuresCommand();

	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("structures").requires(sender -> sender.hasPermission(4)).executes(CMD);

		builder.then(Commands.literal("generate")
				.then(Commands.argument("structure_id", StructureIdArgument.instance())
						.then(Commands.argument("position", BlockPosArgument.blockPos())
								.executes(context -> spawnStructure(context, Mirror.NONE, Rotation.NONE, false)))
								.then(Commands.argument("mirror", EnumArgument.enumArgument(Mirror.class))
										.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), Rotation.NONE, false)))
										.then(Commands.argument("rotation", EnumArgument.enumArgument(Rotation.class))
												.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), context.getArgument("rotation", Rotation.class), false)))
												.then(Commands.argument("ignore_entities", BoolArgumentType.bool())
														.executes(context -> spawnStructure(context, context.getArgument("mirror", Mirror.class), context.getArgument("rotation", Rotation.class), context.getArgument("ignore_entities", Boolean.class))))));

		return builder;
	}

	private static int spawnStructure(CommandContext<CommandSource> cmd, Mirror mirror, Rotation rotation, boolean ignoreEntities) throws CommandSyntaxException {
		try {
			ResourceLocation id = StructureIdArgument.getStructureId(cmd, "structure_id");
			Template template = ServerLifecycleHooks.getCurrentServer().getStructureManager().get(id);
			BlockPos spawnPos = cmd.getArgument("position", ILocationArgument.class).getBlockPos(cmd.getSource());

			if (template == null) {
				AoACommand.feedback(cmd.getSource(), "Structures", "command.aoa.structures.invalidStructure", AoACommand.CommandFeedbackType.WARN, new StringTextComponent(id.toString()));

				return 1;
			}
			else {
				template.placeInWorldChunk(cmd.getSource().getLevel(), spawnPos, new PlacementSettings().setMirror(mirror).setRotation(rotation).setIgnoreEntities(ignoreEntities).setFinalizeEntities(!ignoreEntities).setChunkPos(null), new Random());
			}

			AoACommand.feedback(cmd.getSource(), "Structures", "command.aoa.structures.spawn", AoACommand.CommandFeedbackType.SUCCESS, new StringTextComponent(id.toString()), new StringTextComponent(spawnPos.getX() + ", " + spawnPos.getY() + ", " + spawnPos.getZ()));
		}
		catch (ResourceLocationException ex) {
			AoACommand.error(cmd.getSource(), "Structures", ex.getLocalizedMessage());
		}

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSource> context) throws CommandSyntaxException {
		AoACommand.feedback(context.getSource(), "Structures", "command.aoa.structures.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}

	public static class StructureIdArgument implements ArgumentType<ResourceLocation> {
		private static final Collection<String> EXAMPLES = Arrays.asList("minecraft:bastion/starts", "aoa3:nether/nethengeic_pit/main_pool");
		private static final DynamicCommandExceptionType UNKNOWN_STRUCTURE_EXCEPTION = new DynamicCommandExceptionType(arg -> new TranslationTextComponent("command.aoa.structures.invalidStructure", arg));
		private static MinecraftServer server = null;

		public static StructureIdArgument instance() {
			return new StructureIdArgument();
		}

		public static ResourceLocation getStructureId(CommandContext<CommandSource> context, String name) throws CommandSyntaxException {
			return findStructure(context.getArgument(name, ResourceLocation.class));
		}

		private static ResourceLocation findStructure(ResourceLocation path) throws CommandSyntaxException {
			if (server == null)
				server = LogicalSidedProvider.INSTANCE.get(LogicalSide.SERVER);

			TemplateManager templateManager = server.getLevel(World.OVERWORLD).getStructureManager();
			Template template = templateManager.get(path);

			if (template == null)
				throw UNKNOWN_STRUCTURE_EXCEPTION.create(path);

			return path;
		}

		@Override
		public ResourceLocation parse(StringReader reader) throws CommandSyntaxException {
			return ResourceLocation.read(reader);
		}

		@Override
		public Collection<String> getExamples() {
			return EXAMPLES;
		}
	}
}
