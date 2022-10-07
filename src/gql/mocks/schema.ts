import { gql } from '@apollo/client'

export default gql`
  # General
  type PageInfo {
    total: Int!
    hasNextPage: Boolean!
    endCursor: String
    hasPreviousPage: Boolean!
    startCursor: String
  }

  input PaginationInput {
    first: Int
    after: String
    last: Int
    before: String
  }

  enum SortOrder {
    ASC
    DESC
  }

  input SortInput {
    sortBy: String!
    order: SortOrder!
  }

  scalar Date

  # Leagues
  type League {
    _id: String!
    name: String
    description: String
    logo: String
  }

  type LeagueConnection {
    pageInfo: PageInfo!
    nodes: [League!]!
  }

  # Divisions
  type Division {
    _id: String!
    name: String
    description: String
  }

  type DivisionConnection {
    pageInfo: PageInfo!
    nodes: [Division!]!
  }

  # Seasons
  enum SeasonType {
    SEASON_REGULAR
    SEASON_PLAYOFF
  }

  type Season {
    _id: String!
    name: String
    startDate: Date
    endDate: Date
    type: SeasonType
  }

  type SeasonConnection {
    pageInfo: PageInfo!
    nodes: [Season!]!
  }

  type PlayerStats {
    goals: Int!
    assists: Int!
    points: Int!
    penaltyMinutes: Int!

    saves: Int!
    goalsReceived: Int!
  }

  # Standings and stats
  type Player {
    _id: String!
    name: String!
    picture: String
    jerseyNumber: Int
    team: SeasonTeam!

    gamesPlayed: Int!
    stats: PlayerStats!
  }

  type PlayerConnection {
    pageInfo: PageInfo!
    nodes: [Player!]!
  }

  type Last10 {
    wins: Int!
    loses: Int!
    draws: Int!
  }

  type SeasonTeam {
    _id: String!
    name: String!
    picture: String

    gamesPlayed: Int!
    wins: Int!
    loses: Int!
    draws: Int!
    points: Int!
    goalsFor: Int!
    goalsAgainst: Int!
    goalsDifference: Int!
    last10: Last10!
  }

  # Games
  enum GameEventType {
    FACEOFF
    GOAL
    PENALTY
    SHOT
  }

  type GamePlayers {
    playerId: String!
    name: String!
    picture: String
    jerseyNumber: Int
    
    stats: PlayerStats!
  }

  type GameEventPlayer {
    name: String!
    jerseyNumber: Int
  }

  type GameEvent {
    teamId: String!
    time: Int!
    period: Int!
    type: GameEventType!

    shot: GameEventPlayer
    save: GameEventPlayer
    goal: GameEventPlayer
    assist: GameEventPlayer
    secondAssist: GameEventPlayer
    penalty: GameEventPlayer
  }
  
  enum GameStatus {
    PLANNED
    IN_PROGRESS
    FINISHED
  }

  type GamePeriod {
    shots: Int!
    goals: Int!
    faceoffs: Int!
  }

  type GameTeam {
    _id: String!
    name: String!
    score: Int!
    shots: Int!
    faceoffs: Int!
    penaltyMinutes: Int!
    players: [GamePlayers!]!
    periodsStats: [GamePeriod!]!
  }

  type Game {
    _id: String!
    date: Date!
    status: GameStatus!
    homeTeam: GameTeam!
    awayTeam: GameTeam!

    place: String
    events: [GameEvent!]!
  }

  type GameConnection {
    pageInfo: PageInfo!
    nodes: [Game!]!
  }

  extend type Query {
    schedulo (scheduloId: String!): Schedulo
  }
`
