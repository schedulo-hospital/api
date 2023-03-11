package com.schedulo.solver

import java.time.Duration
import java.time.LocalDateTime

import com.schedulo.models.AvailabilityModel
import com.schedulo.models.ShiftModel
import com.schedulo.generated.types.AvailabilityType

import org.optaplanner.core.api.score.buildin.hardsoft.HardSoftScore
import org.optaplanner.core.api.score.stream.Constraint
import org.optaplanner.core.api.score.stream.ConstraintFactory
import org.optaplanner.core.api.score.stream.ConstraintProvider
import org.optaplanner.core.api.score.stream.Joiners

class EmployeeSchedulingConstraintProvider: ConstraintProvider {

        override fun defineConstraints(constraintFactory: ConstraintFactory): Array<Constraint>? {
                return arrayOf(
                    // Hard constraints
                    requiredSeniority(constraintFactory),
                    atLeast10HoursBetweenTwoShifts(constraintFactory),
                    unavailableEmployee(constraintFactory),
                    // Soft constraints
                    desiredDayForEmployee(constraintFactory),
                    undesiredDayForEmployee(constraintFactory),
                )
            }

            fun requiredSeniority(constraintFactory: ConstraintFactory): Constraint {
                return constraintFactory
                    .forEach(ShiftModel::class.java)
                    .filter({ shift -> !shift.user?.seniority.equals(shift.requiredSeniority) })
                    .penalize(HardSoftScore.ONE_HARD)
                    .asConstraint("Missing required seniority")
            }

            fun atLeast10HoursBetweenTwoShifts(constraintFactory: ConstraintFactory): Constraint {
                return constraintFactory
                    .forEachUniquePair(
                        ShiftModel::class.java,
                        Joiners.equal(ShiftModel::user),
                        Joiners.lessThanOrEqual(ShiftModel::end, ShiftModel::start)
                    )
                    .filter { shift1: ShiftModel, shift2: ShiftModel -> Duration.between(shift1.end, shift2.start).toHours() < 10 }
                    .penalize(HardSoftScore.ONE_HARD,
                        { shift1: ShiftModel, shift2: ShiftModel ->
                            (10 * 60) - Duration.between(shift1.end, shift2.start).toMinutes().toInt()
                        }
                    )
                    .asConstraint("At least 10 hours between 2 shifts")
            }

            fun getShiftDurationInMinutes(shift: ShiftModel): Int {
                return Duration.between(shift.start, shift.end).toMinutes() as Int
            }

            fun unavailableEmployee(constraintFactory: ConstraintFactory): Constraint {
                return constraintFactory.forEach(ShiftModel::class.java)
                    .join(
                        AvailabilityModel::class.java, 
                        Joiners.equal({ shift: ShiftModel -> shift.start }, AvailabilityModel::date),
                        Joiners.equal(ShiftModel::user, AvailabilityModel::user)
                    )
                    .filter { shift: ShiftModel, availability: AvailabilityModel -> availability.type == AvailabilityType.UNAVAILABLE }
                    .penalize(
                        HardSoftScore.ONE_HARD,
                        { shift: ShiftModel, availability: AvailabilityModel -> getShiftDurationInMinutes(shift) }
                    )
                    .asConstraint("Unavailable employee");
            }
        
            fun desiredDayForEmployee(constraintFactory: ConstraintFactory): Constraint {
                return constraintFactory.forEach(ShiftModel::class.java)
                    .join(
                        AvailabilityModel::class.java, 
                        Joiners.equal({ shift: ShiftModel -> shift.start }, AvailabilityModel::date),
                        Joiners.equal(ShiftModel::user, AvailabilityModel::user)
                    )
                    .filter { shift: ShiftModel, availability: AvailabilityModel -> availability.type == AvailabilityType.DESIRED }
                    .reward(
                        HardSoftScore.ONE_SOFT,
                        { shift: ShiftModel, availability: AvailabilityModel -> getShiftDurationInMinutes(shift) }
                    )
                    .asConstraint("Desired day for employee");
            }
        
            fun undesiredDayForEmployee(constraintFactory: ConstraintFactory): Constraint {
                return constraintFactory.forEach(ShiftModel::class.java)
                    .join(
                        AvailabilityModel::class.java, 
                        Joiners.equal({ shift: ShiftModel -> shift.start }, AvailabilityModel::date),
                        Joiners.equal(ShiftModel::user, AvailabilityModel::user)
                    )
                    .filter { shift: ShiftModel, availability: AvailabilityModel -> availability.type == AvailabilityType.UNDESIRED }
                    .penalize(
                        HardSoftScore.ONE_SOFT,
                        { shift: ShiftModel, availability: AvailabilityModel -> getShiftDurationInMinutes(shift) }
                    )
                    .asConstraint("Undesired day for employee");
            }
        
}

