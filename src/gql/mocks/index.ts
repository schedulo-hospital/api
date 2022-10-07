import { DivisionQueryVariables, LeagueQueryVariables, SeasonQueryVariables, PlayerQueryVariables, PlayersQueryVariables, LeaguesQueryVariables, DivisionsQueryVariables, SeasonsQueryVariables, LeagueConnection, TeamQueryVariables, GamesQueryVariables, GameQueryVariables } from '../generatedTypes';
import { schedulo } from './data/schedulo'
import typeDefs from './schema'
//import { getPaginatedResult } from './utils'

// TODO: add seasonGames (filter to get games per team), seasonGame, stats

const resolvers = {
  Query: {
    schedulo: (_: any, variables: LeaguesQueryVariables): ScheduloConnection => getPaginatedResult(variables, schedulo)
  }
}

export {
  typeDefs,
  resolvers
}
