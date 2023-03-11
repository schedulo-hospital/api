package com.schedulo.solver

import java.time.Duration
import java.time.LocalDateTime

import com.schedulo.models.AvailabilityModel
import com.schedulo.models.ShiftModel

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
                )
            }

            fun requiredSeniority(constraintFactory: ConstraintFactory): Constraint {
                return constraintFactory
                    .forEach(ShiftModel::class.java)
                    .filter({ shift -> !shift.user.seniority.equals(shift.requiredSeniority) })
                    .penalize(HardSoftScore.ONE_HARD)
                    .asConstraint("Missing required seniority")
            }
}

