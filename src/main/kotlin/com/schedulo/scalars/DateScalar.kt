package com.schedulo.scalars

import graphql.schema.Coercing
import graphql.schema.CoercingSerializeException
import graphql.schema.CoercingParseLiteralException
import graphql.language.StringValue
import com.netflix.graphql.dgs.DgsScalar
import java.time.format.DateTimeFormatter
import java.time.LocalDate

@DgsScalar(name="Date")
public class DateScalar: Coercing<LocalDate, String> {

    override fun serialize(dataFetcherResult: Any): String {
        if (dataFetcherResult is LocalDate) {
            return dataFetcherResult.format(DateTimeFormatter.ISO_DATE)
        }

        throw CoercingSerializeException("Expected a LocalDateTime object.")
    }

    override fun parseValue(input: Any): LocalDate {
        return LocalDate.parse(input.toString(), DateTimeFormatter.ISO_DATE)
    }

    @Override
    override fun parseLiteral(input: Any): LocalDate {
        if (input is StringValue) {
            return LocalDate.parse(input.value, DateTimeFormatter.ISO_DATE)
        }

        throw CoercingParseLiteralException("Value is not a valid ISO date time");
    }
}