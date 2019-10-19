package seedu.address.logic.commands.exercise;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.FindCommand;
import seedu.address.model.Model;
import seedu.address.model.exercise.components.MusclesTrainedContainsKeywordsPredicate;

/**
 * Finds and lists all Exercise in Workout Planner whose muscles trained contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindExerciseByMuscleCommand extends FindCommand {

    public static final String COMMAND_WORD = "findMuscle";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all exercises with muscles trained containing"
            + " any of the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: MUSCLES [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " Chest, Cardiovascular";

    private final MusclesTrainedContainsKeywordsPredicate predicate;

    public FindExerciseByMuscleCommand(MusclesTrainedContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredExerciseList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredExerciseList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindExerciseByMuscleCommand // instanceof handles nulls
                && predicate.equals(((FindExerciseByMuscleCommand) other).predicate)); // state check
    }
}
