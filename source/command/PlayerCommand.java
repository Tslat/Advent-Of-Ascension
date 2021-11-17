package net.tslat.aoa3.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.FloatArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.ArgumentBuilder;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.FoodStats;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.StringTextComponent;
import net.tslat.aoa3.command.argument.AoAResourceArgument;
import net.tslat.aoa3.command.argument.AoASkillArgument;
import net.tslat.aoa3.player.resource.AoAResource;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.NumberUtil;
import net.tslat.aoa3.util.PlayerUtil;

public class PlayerCommand implements Command<CommandSource> {
	private static final PlayerCommand CMD = new PlayerCommand();

	public static ArgumentBuilder<CommandSource, ?> register(CommandDispatcher<CommandSource> dispatcher) {
		LiteralArgumentBuilder<CommandSource> builder = Commands.literal("player").executes(CMD);

		builder.then(Commands.argument("player", EntityArgument.player())
			.then(Commands.literal("skills")
				.then(Commands.argument("skill", AoASkillArgument.skill())
					.then(Commands.literal("level")
						.then(Commands.literal("add").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 999))
								.executes((context) -> adjustSkills(context, "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 999))
								.executes((context) -> adjustSkills(context, "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 1000))
								.executes((context) -> adjustSkills(context, "set"))))
						.then(Commands.literal("check").executes(PlayerCommand::checkSkill)))
					.then(Commands.literal("xp")
						.then(Commands.literal("add").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", FloatArgumentType.floatArg(1, 544132359))
								.executes((context) -> adjustXp(context, "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", FloatArgumentType.floatArg(1, 544132359))
								.executes((context) -> adjustXp(context, "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", FloatArgumentType.floatArg(0, 544132358))
								.executes((context) -> adjustXp(context, "set"))))
						.then(Commands.literal("check").executes(PlayerCommand::checkXp)))
					.then(Commands.literal("cycle")
						.then(Commands.literal("add").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 10))
								.executes((context) -> adjustCycle(context, "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(1, 10))
								.executes((context) -> adjustCycle(context, "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermission(4))
							.then(Commands.argument("value", IntegerArgumentType.integer(0, 10))
								.executes((context) -> adjustCycle(context, "set"))))
						.then(Commands.literal("check").executes(PlayerCommand::checkCycle)))))
			.then(Commands.literal("resources")
				.then(Commands.argument("resource", AoAResourceArgument.resource())
					.then(Commands.literal("add").requires(command -> command.hasPermission(4))
						.then(Commands.argument("value", FloatArgumentType.floatArg(1, Float.MAX_VALUE))
							.executes((context) -> adjustResources(context, "add"))))
					.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
						.then(Commands.argument("value", FloatArgumentType.floatArg(1, Float.MAX_VALUE))
							.executes((context) -> adjustResources(context, "subtract"))))
					.then(Commands.literal("set").requires(command -> command.hasPermission(4))
						.then(Commands.argument("value", FloatArgumentType.floatArg(0, Float.MAX_VALUE))
							.executes((context) -> adjustResources(context, "set"))))
					.then(Commands.literal("check").executes(PlayerCommand::checkResource))))
			.then(Commands.literal("stats")
				.then(Commands.literal("experience")
					.then(Commands.literal("add").requires(command -> command.hasPermission(4))
						.then(Commands.argument("amount", IntegerArgumentType.integer(1, Integer.MAX_VALUE))
							.executes((context) -> adjustVanillaXp(context, "add"))))
					.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
						.then(Commands.argument("amount", IntegerArgumentType.integer(1, Integer.MAX_VALUE))
							.executes((context) -> adjustVanillaXp(context, "subtract"))))
					.then(Commands.literal("check").executes(PlayerCommand::checkVanillaXp)))
				.then(Commands.literal("food")
					.then(Commands.literal("hunger")
						.then(Commands.literal("add").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "hunger", "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "hunger", "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(0, 20))
								.executes((context) -> adjustFoodStats(context, "hunger", "set"))))
						.then(Commands.literal("check").executes((context) -> checkFoodStats(context, "hunger"))))
					.then(Commands.literal("saturation")
						.then(Commands.literal("add").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "saturation", "add"))))
						.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "saturation", "subtract"))))
						.then(Commands.literal("set").requires(command -> command.hasPermission(4))
							.then(Commands.argument("amount", IntegerArgumentType.integer(1, 20))
								.executes((context) -> adjustFoodStats(context, "saturation", "set"))))
						.then(Commands.literal("check").executes((context) -> checkFoodStats(context, "saturation")))))
				.then(Commands.literal("health")
					.then(Commands.literal("add").requires(command -> command.hasPermission(4))
						.then(Commands.argument("amount", FloatArgumentType.floatArg(0, Integer.MAX_VALUE))
							.executes((context) -> adjustHealth(context, "add"))))
					.then(Commands.literal("subtract").requires(command -> command.hasPermission(4))
						.then(Commands.argument("amount", FloatArgumentType.floatArg(0, Integer.MAX_VALUE))
							.executes((context) -> adjustHealth(context, "subtract"))))
					.then(Commands.literal("set").requires(command -> command.hasPermission(4))
						.then(Commands.argument("amount", FloatArgumentType.floatArg(0, Integer.MAX_VALUE))
							.executes((context) -> adjustHealth(context, "set"))))
					.then(Commands.literal("check").executes(PlayerCommand::checkHealth)))));

		return builder;
	}

	private static int checkFoodStats(CommandContext<CommandSource> context, String subsection) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (subsection.equals("hunger")) {
			if (pl != context.getSource().getEntityOrException()) {
				if (!context.getSource().hasPermission(2))
					throw AoACommand.NO_PERMISSION_EXCEPTION.create();

				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.hunger.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(pl.getFoodData().getFoodLevel()));
			}
			else {
				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.hunger.check.self", AoACommand.CommandFeedbackType.SUCCESS, LocaleUtil.numToComponent(pl.getFoodData().getFoodLevel()));
			}
		}
		else {
			if (pl != context.getSource().getEntityOrException()) {
				if (!context.getSource().hasPermission(2))
					throw AoACommand.NO_PERMISSION_EXCEPTION.create();

				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.saturation.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), new StringTextComponent(NumberUtil.roundToNthDecimalPlace(pl.getFoodData().getSaturationLevel(), 2)));
			}
			else {
				AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
				AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.saturation.check.self", AoACommand.CommandFeedbackType.SUCCESS, new StringTextComponent(NumberUtil.roundToNthDecimalPlace(pl.getFoodData().getSaturationLevel(), 2)));
			}
		}

		return 1;
	}

	private static int checkVanillaXp(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.exp.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(pl.totalExperience), LocaleUtil.numToComponent(pl.experienceLevel));
		}
		else {
			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.exp.check.self", AoACommand.CommandFeedbackType.SUCCESS, LocaleUtil.numToComponent(pl.totalExperience), LocaleUtil.numToComponent(pl.experienceLevel));
		}

		return 1;
	}

	private static int checkHealth(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.health.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), new StringTextComponent(NumberUtil.roundToNthDecimalPlace(pl.getHealth(), 2)), new StringTextComponent(NumberUtil.roundToNthDecimalPlace(pl.getMaxHealth(), 2)));
		}
		else {
			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.health.check.self", AoACommand.CommandFeedbackType.SUCCESS, new StringTextComponent(NumberUtil.roundToNthDecimalPlace(pl.getHealth(), 2)), new StringTextComponent(NumberUtil.roundToNthDecimalPlace(pl.getMaxHealth(), 2)));
		}

		return 1;
	}

	private static int checkSkill(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoASkill skill = context.getArgument("skill", AoASkill.class);
			int level = PlayerUtil.getLevel(pl, skill);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.skill.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(level));
		}
		else {
			AoASkill skill = context.getArgument("skill", AoASkill.class);
			int level = PlayerUtil.getLevel(pl, skill);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.skill.check.self", AoACommand.CommandFeedbackType.SUCCESS, skill.getName(), LocaleUtil.numToComponent(level));
		}

		return 1;
	}

	private static int checkXp(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoASkill skill = context.getArgument("skill", AoASkill.class);
			float xp = PlayerUtil.getAdventPlayer(pl).getSkill(skill).getXp();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.xp.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(xp));
		}
		else {
			AoASkill skill = context.getArgument("skill", AoASkill.class);
			float xp = PlayerUtil.getAdventPlayer(pl).getSkill(skill).getXp();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.xp.check.self", AoACommand.CommandFeedbackType.SUCCESS, skill.getName(), LocaleUtil.numToComponent(xp));
		}

		return 1;
	}

	private static int checkCycle(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoASkill skill = context.getArgument("skill", AoASkill.class);
			int cycles = PlayerUtil.getAdventPlayer(pl).getSkill(skill).getCycles();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.cycle.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(cycles));
		}
		else {
			AoASkill skill = context.getArgument("skill", AoASkill.class);
			int cycles = PlayerUtil.getAdventPlayer(pl).getSkill(skill).getCycles();

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.cycle.check.self", AoACommand.CommandFeedbackType.SUCCESS, skill.getName(), LocaleUtil.numToComponent(cycles));
		}

		return 1;
	}

	private static int checkResource(CommandContext<CommandSource> context) throws CommandSyntaxException {
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");

		if (pl != context.getSource().getEntityOrException()) {
			if (!context.getSource().hasPermission(2))
				throw AoACommand.NO_PERMISSION_EXCEPTION.create();

			AoAResource resource = context.getArgument("resource", AoAResource.class);
			float value = PlayerUtil.getResourceValue(pl, resource);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.resource.check.other", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(value), resource.getName());
		}
		else {
			AoAResource resource = context.getArgument("resource", AoAResource.class);
			float value = PlayerUtil.getResourceValue(pl, resource);

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.resource.check.self", AoACommand.CommandFeedbackType.SUCCESS, resource.getName(), LocaleUtil.numToComponent(value));
		}

		return 1;
	}

	private static int adjustSkills(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "value");
		AoASkill skill = context.getArgument("skill", AoASkill.class);
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		AoASkill.Instance instance = PlayerUtil.getSkill(pl, skill);

		int adjustment = 0;

		switch (operation) {
			case "add":
				value = Math.abs(value);
				adjustment = Math.min(value, 1000 - instance.getLevel(true));

				instance.setLevel(instance.getLevel(true) + value);
				break;
			case "subtract":
				value = Math.abs(value);
				adjustment = -Math.min(value, instance.getLevel(true) - 1);

				instance.setLevel(instance.getLevel(true) - value);
				break;
			case "set":
				value = MathHelper.clamp(value, 1, 1000);
				adjustment = value - instance.getLevel(true);

				instance.setLevel(value);
				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.skill.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(instance.getLevel(true)));

		return 1;
	}

	private static int adjustXp(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		float value = FloatArgumentType.getFloat(context, "value");
		AoASkill skill = context.getArgument("skill", AoASkill.class);
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		AoASkill.Instance instance = PlayerUtil.getSkill(pl, skill);
		float adjustment = value;

		switch (operation) {
			case "add":
				value = Math.abs(value);

				instance.adjustXp(value, true, true);
				break;
			case "subtract":
				value = Math.abs(value);

				instance.adjustXp(-value, true, true);
				break;
			case "set":
				float maxXpForLevel = PlayerUtil.getXpRequiredForNextLevel(instance.getLevel(true));
				float curXp = instance.getXp();
				value = MathHelper.clamp(value, 0, maxXpForLevel);
				adjustment = value - curXp;

				if (adjustment > 0) {
					instance.adjustXp(adjustment, true, true);
				}
				else {
					instance.adjustXp(-adjustment, true, true);
				}

				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.xp.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(instance.getXp()));

		return 1;
	}

	private static int adjustCycle(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "value");
		AoASkill skill = context.getArgument("skill", AoASkill.class);
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		AoASkill.Instance instance = PlayerUtil.getSkill(pl, skill);
		float adjustment = value;

		switch (operation) {
			case "add":
				value = MathHelper.clamp(value, 1, 10 - instance.getCycles());
				adjustment = Math.min(value, 10 - instance.getCycles());

				instance.setCycle(instance.getCycles() + value);
				break;
			case "subtract":
				value = MathHelper.clamp(value, 1, instance.getCycles());
				adjustment = Math.min(value, instance.getCycles());

				instance.setCycle(instance.getCycles() - value);
				break;
			case "set":
				value = MathHelper.clamp(value, 0, 10);
				adjustment = value - instance.getCycles();

				instance.setCycle(value);
				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.cycle.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), skill.getName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(instance.getCycles()));

		return 1;
	}

	private static int adjustResources(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		float value = FloatArgumentType.getFloat(context, "value");
		AoAResource resource = context.getArgument("resource", AoAResource.class);
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		AoAResource.Instance instance = PlayerUtil.getResource(pl, resource);
		float adjustment = 0;

		switch (operation) {
			case "add":
				adjustment = Math.min(value, instance.getMaxValue() - instance.getCurrentValue());

				instance.addValue(adjustment);
				break;
			case "subtract":
				adjustment = -Math.min(value, instance.getCurrentValue());

				instance.consume(adjustment, true);
				break;
			case "set":
				instance.setValue(value);

				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.resource.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), resource.getName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(instance.getCurrentValue()));

		return 1;
	}

	private static int adjustHealth(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		float value = FloatArgumentType.getFloat(context, "amount");
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		float adjustment = 0;

		switch (operation) {
			case "add":
				adjustment = Math.min(value, pl.getMaxHealth() - pl.getHealth());

				pl.setHealth(pl.getHealth() + adjustment);
				break;
			case "subtract":
				adjustment = -Math.min(value, pl.getHealth());

				pl.setHealth(pl.getHealth() + adjustment);
				break;
			case "set":
				float newHealth = MathHelper.clamp(value, 0, pl.getMaxHealth());
				adjustment = newHealth - pl.getHealth();

				pl.setHealth(newHealth);
				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.health.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), new StringTextComponent(NumberUtil.roundToNthDecimalPlace(adjustment, 2)), new StringTextComponent(NumberUtil.roundToNthDecimalPlace(pl.getHealth(), 2)), new StringTextComponent(NumberUtil.roundToNthDecimalPlace(pl.getMaxHealth(), 2)));

		return 1;
	}

	private static int adjustVanillaXp(CommandContext<CommandSource> context, String operation) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "amount");
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		int adjustment = 0;

		switch (operation) {
			case "add":
				adjustment = Math.min(value, Integer.MAX_VALUE - pl.totalExperience);

				pl.giveExperiencePoints(adjustment);
				break;
			case "subtract":
				adjustment = -Math.min(value, pl.totalExperience);

				pl.giveExperiencePoints(adjustment);
				break;
			case "set":
				adjustment = value - pl.totalExperience;

				pl.giveExperiencePoints(adjustment);
				break;
		}

		AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.exp.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(pl.totalExperience), LocaleUtil.numToComponent(pl.experienceLevel));

		return 1;
	}

	private static int adjustFoodStats(CommandContext<CommandSource> context, String operation, String subsection) throws CommandSyntaxException {
		int value = IntegerArgumentType.getInteger(context, "amount");
		ServerPlayerEntity pl = EntityArgument.getPlayer(context, "player");
		FoodStats food = pl.getFoodData();

		if (operation.equals("hunger")) {
			int adjustment = 0;

			switch (subsection) {
				case "add":
					adjustment = Math.min(value, 20 - food.getFoodLevel());

					food.eat(adjustment, 0);
					break;
				case "subtract":
					adjustment = -Math.min(value, food.getFoodLevel());

					food.setFoodLevel(food.getFoodLevel() + adjustment);
					break;
				case "set":
					int newHunger = MathHelper.clamp(value, 0, 20);
					adjustment = newHunger - food.getFoodLevel();

					food.setFoodLevel(newHunger);
					break;
			}

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.food.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(food.getFoodLevel()));
		}
		else {
			float adjustment = 0;

			switch (subsection) {
				case "add":
					adjustment = value;

					food.eat(0, value);
					break;
				case "subtract":
					adjustment = -Math.min(value, food.getSaturationLevel());

					food.eat(0, adjustment);
					break;
				case "set":
					adjustment = value - food.getSaturationLevel();

					food.eat(0, adjustment);
					break;
			}

			AoACommand.feedback(context.getSource(), "Player", "--", AoACommand.CommandFeedbackType.INFO);
			AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.saturation.adjust", AoACommand.CommandFeedbackType.SUCCESS, pl.getDisplayName(), LocaleUtil.numToComponent(adjustment), LocaleUtil.numToComponent(food.getSaturationLevel()));
		}

		return 1;
	}

	@Override
	public int run(CommandContext<CommandSource> context) {
		AoACommand.feedback(context.getSource(), "Player", "command.aoa.player.desc", AoACommand.CommandFeedbackType.INFO);

		return 1;
	}
}
