package com.schedulo.solver

import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalDate

import com.schedulo.models.AvailabilityModel
import com.schedulo.models.ShiftModel
import com.schedulo.generated.types.AvailabilityType

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.api.score.stream.Constraint
import org.optaplanner.core.api.score.stream.ConstraintFactory
import org.optaplanner.core.api.score.stream.ConstraintProvider
import org.optaplanner.core.api.score.stream.Joiners

class ScheduloConstraintProvider: ConstraintProvider {

    override fun defineConstraints(constraintFactory: ConstraintFactory): Array<Constraint>? {
        return arrayOf(
            // Hard constraints
            requiredSeniority(constraintFactory),
            atLeast24HoursBetweenTwoShifts(constraintFactory),
            unavailableEmployee(constraintFactory),
            // Soft constraints
            desiredDayForEmployee(constraintFactory),
            undesiredDayForEmployee(constraintFactory),
        )
    }

    fun requiredSeniority(constraintFactory: ConstraintFactory): Constraint {
        return constraintFactory
            .forEach(ShiftModel::class.java)
            .filter({ shift -> !(shift.userLoaded?.seniority?.name.equals(shift.requiredSeniority) ) })
            .penalize(HardSoftScore.ONE_HARD)
            .asConstraint("Missing required seniority")
    }

    fun atLeast24HoursBetweenTwoShifts(constraintFactory: ConstraintFactory): Constraint {
        return constraintFactory
            .forEachUniquePair(
                ShiftModel::class.java,
                Joiners.equal(ShiftModel::userLoaded),
                Joiners.lessThanOrEqual(ShiftModel::end, ShiftModel::start)
            )
            .filter { shift1: ShiftModel, shift2: ShiftModel -> Duration.between(shift1.end, shift2.start).toHours() < 25 }
            .penalize(HardSoftScore.ONE_HARD,
                { shift1: ShiftModel, shift2: ShiftModel ->
                    (25 * 60) - Duration.between(shift1.end, shift2.start).toMinutes().toInt()
                }
            )
            .asConstraint("At least 24 hours between 2 shifts")
    }

    fun unavailableEmployee(constraintFactory: ConstraintFactory): Constraint {
        return constraintFactory
            .forEach(ShiftModel::class.java)
            .join(AvailabilityModel::class.java)
            .filter { shift: ShiftModel, availability: AvailabilityModel -> shift.start == LocalDateTime.of(availability.date.year, availability.date.month, availability.date.dayOfMonth, 0, 0, 0) && shift.userLoaded?.user == availability.user.id && availability.type == AvailabilityType.UNAVAILABLE }
            .penalize(HardSoftScore.ONE_HARD)
            .asConstraint("Unavailable employee");
    }

    fun desiredDayForEmployee(constraintFactory: ConstraintFactory): Constraint {
        return constraintFactory.forEach(ShiftModel::class.java)
            .join(AvailabilityModel::class.java)
            .filter { shift: ShiftModel, availability: AvailabilityModel -> shift.start == LocalDateTime.of(availability.date.year, availability.date.month, availability.date.dayOfMonth, 0, 0, 0) && shift.userLoaded?.user == availability.user.id && availability.type == AvailabilityType.DESIRED }
            .reward(HardSoftScore.ONE_SOFT)
            .asConstraint("Desired day for employee");
    }

    fun undesiredDayForEmployee(constraintFactory: ConstraintFactory): Constraint {
        return constraintFactory.forEach(ShiftModel::class.java)
            .join(AvailabilityModel::class.java)
            .filter { shift: ShiftModel, availability: AvailabilityModel -> shift.start == LocalDateTime.of(availability.date.year, availability.date.month, availability.date.dayOfMonth, 0, 0, 0) && shift.userLoaded?.user == availability.user.id && availability.type == AvailabilityType.UNDESIRED }
            .penalize(HardSoftScore.ONE_SOFT)
            .asConstraint("Undesired day for employee");
    }

    // TODO: fairness
    // rule "Fairness: all employees should work about the same number of shifts"
    //     when
    //         $e : Employee()
    //         accumulate(
    //             $a : ShiftAssignment(employee == $e);
    //             $total : count($a)
    //         )
    //     then
    //         // Fairness and load balancing trick (see docs): squared to assure correctness in corner cases
    //         // Negative to balance it across employees
    //         scoreHolder.addSoftConstraintMatch(kcontext, - ($total.intValue() * $total.intValue()));
    // end
        
}

