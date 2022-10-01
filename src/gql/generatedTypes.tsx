//@ts-nocheck
import { TypedDocumentNode as DocumentNode } from '@graphql-typed-document-node/core';
export type Maybe<T> = T | undefined;
export type InputMaybe<T> = T | undefined;
export type Exact<T extends { [key: string]: unknown }> = { [K in keyof T]: T[K] };
export type MakeOptional<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]?: Maybe<T[SubKey]> };
export type MakeMaybe<T, K extends keyof T> = Omit<T, K> & { [SubKey in K]: Maybe<T[SubKey]> };
/** All built-in and custom scalars, mapped to their actual values */
export type Scalars = {
  ID: string;
  String: string;
  Boolean: boolean;
  Int: number;
  Float: number;
  Date: any;
  /** The javascript `Date` as string. Type represents date and time as the ISO Date string. */
  DateTime: any;
};

export type Answer = {
  id: Scalars['String'];
  title: Scalars['String'];
  totalPercentage: Scalars['Float'];
  totalVotes: Scalars['Float'];
  votes: Array<Vote>;
};

/** Poll create input. */
export type AnswerInput = {
  title: Scalars['String'];
};

export type CalendarEvent = {
  calendarEventId: Scalars['String'];
  calendarId?: Maybe<Scalars['String']>;
  eventId: Scalars['String'];
};

export type CalendarEventInput = {
  calendarEventId: Scalars['String'];
  eventId?: InputMaybe<Scalars['String']>;
};

export type Division = {
  _id: Scalars['String'];
  description?: Maybe<Scalars['String']>;
  name?: Maybe<Scalars['String']>;
};

export type DivisionConnection = {
  nodes: Array<Division>;
  pageInfo: PageInfo;
};

export type Email = {
  address: Scalars['String'];
  verified: Scalars['Boolean'];
};

export type Establishment = {
  _id: Scalars['String'];
  confirmed?: Maybe<Scalars['Boolean']>;
  createdAt: Scalars['DateTime'];
  createdBy: Scalars['String'];
  groupId?: Maybe<Scalars['String']>;
  latitude?: Maybe<Scalars['Float']>;
  longitude?: Maybe<Scalars['Float']>;
  title: Scalars['String'];
};

/** Establishment input data */
export type EstablishmentInput = {
  confirmed?: InputMaybe<Scalars['Boolean']>;
  groupId?: InputMaybe<Scalars['String']>;
  latitude?: InputMaybe<Scalars['Float']>;
  longitude?: InputMaybe<Scalars['Float']>;
  title: Scalars['String'];
};

export type Event = {
  _id: Scalars['String'];
  activeDate: Scalars['DateTime'];
  admin: Scalars['Boolean'];
  calendarEvent?: Maybe<CalendarEvent>;
  canReply: Scalars['Boolean'];
  createdAt: Scalars['DateTime'];
  createdBy: Scalars['String'];
  date: Scalars['DateTime'];
  description?: Maybe<Scalars['String']>;
  distance?: Maybe<Scalars['Float']>;
  duration: Scalars['Float'];
  endDate?: Maybe<Scalars['DateTime']>;
  eventType?: Maybe<EventType>;
  gameType?: Maybe<GameType>;
  group?: Maybe<Group>;
  groupId: Scalars['String'];
  historical: Scalars['Boolean'];
  homeGame?: Maybe<Scalars['Boolean']>;
  hoursBeforeActive?: Maybe<Scalars['Float']>;
  inProgress: Scalars['Boolean'];
  info: Scalars['String'];
  lat: Scalars['Float'];
  leasePrice: Price;
  lng: Scalars['Float'];
  maxAttendants: Scalars['Float'];
  maxGoalies: Scalars['Float'];
  maxReferees: Scalars['Float'];
  maybeComing: Array<Rsvp>;
  minAttendants: Scalars['Float'];
  minGoalies: Scalars['Float'];
  minReferees: Scalars['Float'];
  myResponse?: Maybe<RsvpResponse>;
  name: Scalars['String'];
  notAnswered: Array<User>;
  notComing: Array<Rsvp>;
  numberOfAttendants: Scalars['Float'];
  numberOfGoalies: Scalars['Float'];
  opponentName?: Maybe<Scalars['String']>;
  placeId?: Maybe<Scalars['String']>;
  placeName?: Maybe<Scalars['String']>;
  price: Price;
  public?: Maybe<Scalars['Boolean']>;
  rsvps: Array<Rsvp>;
  scoreTeamA: Scalars['Float'];
  scoreTeamB: Scalars['Float'];
  substitutes?: Maybe<Array<Substitute>>;
  teamA: Array<Rsvp>;
  teamAName?: Maybe<Scalars['String']>;
  teamB: Array<Rsvp>;
  teamBName?: Maybe<Scalars['String']>;
  user?: Maybe<User>;
  winnerTeam: Scalars['Boolean'];
};

/** Event input data */
export type EventInput = {
  date: Scalars['DateTime'];
  description?: InputMaybe<Scalars['String']>;
  duration?: InputMaybe<Scalars['Float']>;
  endDate?: InputMaybe<Scalars['DateTime']>;
  eventType?: InputMaybe<Scalars['String']>;
  gameType?: InputMaybe<Scalars['String']>;
  groupId: Scalars['String'];
  homeGame?: InputMaybe<Scalars['Boolean']>;
  hoursBeforeActive?: InputMaybe<Scalars['Float']>;
  lat?: InputMaybe<Scalars['Float']>;
  lng?: InputMaybe<Scalars['Float']>;
  maxAttendants: Scalars['Float'];
  maxGoalies: Scalars['Float'];
  maxReferees: Scalars['Float'];
  name: Scalars['String'];
  opponentName?: InputMaybe<Scalars['String']>;
  placeId?: InputMaybe<Scalars['String']>;
  placeName?: InputMaybe<Scalars['String']>;
  price?: InputMaybe<PriceInput>;
  public?: InputMaybe<Scalars['Boolean']>;
  teamAName?: InputMaybe<Scalars['String']>;
  teamBName?: InputMaybe<Scalars['String']>;
};

/** Event message input data */
export type EventMessageInput = {
  eventId: Scalars['String'];
  message: Scalars['String'];
};

/** Event reply input data */
export type EventReplyInput = {
  eventId: Scalars['String'];
  jerseyNumber?: InputMaybe<Scalars['Float']>;
  line?: InputMaybe<Scalars['Float']>;
  position?: InputMaybe<Scalars['String']>;
  role?: InputMaybe<Scalars['String']>;
  team?: InputMaybe<Scalars['String']>;
  username?: InputMaybe<Scalars['String']>;
};

/** Event reply input data v2 */
export type EventReplyInputV2 = {
  eventId: Scalars['String'];
  jerseyNumber?: InputMaybe<Scalars['Float']>;
  line?: InputMaybe<Scalars['Float']>;
  position?: InputMaybe<Scalars['String']>;
  response: Scalars['String'];
  role?: InputMaybe<Scalars['String']>;
  team?: InputMaybe<Scalars['String']>;
  username?: InputMaybe<Scalars['String']>;
};

/** Event rsvp input */
export type EventRsvpInput = {
  eventId: Scalars['String'];
  jerseyNumber?: InputMaybe<Scalars['Float']>;
  userId?: InputMaybe<Scalars['String']>;
  username?: InputMaybe<Scalars['String']>;
};

/** EventType type definition. */
export enum EventType {
  Dropin = 'dropin',
  Event = 'event',
  Game = 'game',
  Practice = 'practice'
}

export type EventsPaginated = {
  actualPage: Scalars['Float'];
  data: Array<Event>;
  hasNextPage: Scalars['Boolean'];
  hasPreviousPage: Scalars['Boolean'];
  totalCount: Scalars['Float'];
  totalPages: Scalars['Float'];
};

export type Game = {
  _id: Scalars['String'];
  awayTeam: GameTeam;
  date: Scalars['Date'];
  events: Array<GameEvent>;
  homeTeam: GameTeam;
  place?: Maybe<Scalars['String']>;
  status: GameStatus;
};

export type GameConnection = {
  nodes: Array<Game>;
  pageInfo: PageInfo;
};

export type GameEvent = {
  assist?: Maybe<GameEventPlayer>;
  goal?: Maybe<GameEventPlayer>;
  penalty?: Maybe<GameEventPlayer>;
  period: Scalars['Int'];
  save?: Maybe<GameEventPlayer>;
  secondAssist?: Maybe<GameEventPlayer>;
  shot?: Maybe<GameEventPlayer>;
  teamId: Scalars['String'];
  time: Scalars['Int'];
  type: GameEventType;
};

export type GameEventPlayer = {
  jerseyNumber?: Maybe<Scalars['Int']>;
  name: Scalars['String'];
};

export enum GameEventType {
  Faceoff = 'FACEOFF',
  Goal = 'GOAL',
  Penalty = 'PENALTY',
  Shot = 'SHOT'
}

export type GamePeriod = {
  faceoffs: Scalars['Int'];
  goals: Scalars['Int'];
  shots: Scalars['Int'];
};

export type GamePlayers = {
  jerseyNumber?: Maybe<Scalars['Int']>;
  name: Scalars['String'];
  picture?: Maybe<Scalars['String']>;
  playerId: Scalars['String'];
  stats: PlayerStats;
};

export enum GameStatus {
  Finished = 'FINISHED',
  InProgress = 'IN_PROGRESS',
  Planned = 'PLANNED'
}

export type GameTeam = {
  _id: Scalars['String'];
  faceoffs: Scalars['Int'];
  name: Scalars['String'];
  penaltyMinutes: Scalars['Int'];
  periodsStats: Array<GamePeriod>;
  players: Array<GamePlayers>;
  score: Scalars['Int'];
  shots: Scalars['Int'];
};

/** GameType type definition. */
export enum GameType {
  Playoff = 'playoff',
  Preseason = 'preseason',
  Regular = 'regular',
  Tournament = 'tournament'
}

export type Group = {
  _id: Scalars['String'];
  admin: Scalars['Boolean'];
  admins?: Maybe<Array<Scalars['String']>>;
  category?: Maybe<Scalars['String']>;
  city?: Maybe<Scalars['String']>;
  createdAt?: Maybe<Scalars['DateTime']>;
  createdBy: Scalars['String'];
  description?: Maybe<Scalars['String']>;
  emails: Array<GroupEmail>;
  groupPicture?: Maybe<Scalars['String']>;
  inviteKey?: Maybe<Scalars['String']>;
  name: Scalars['String'];
  numberOfEvents: Scalars['Float'];
  numberOfUsers: Scalars['Float'];
  priceFeeStrategy?: Maybe<PriceFeeStrategyType>;
  public?: Maybe<Scalars['Boolean']>;
  signature?: Maybe<Scalars['String']>;
  stripeUserId?: Maybe<Scalars['String']>;
};

export type GroupEmail = {
  confirmed: Scalars['Boolean'];
  core: Scalars['Boolean'];
  date: Scalars['DateTime'];
  email: Scalars['String'];
};

/** Group input data */
export type GroupInput = {
  category?: InputMaybe<Scalars['String']>;
  city: Scalars['String'];
  description?: InputMaybe<Scalars['String']>;
  name: Scalars['String'];
  signature?: InputMaybe<Scalars['String']>;
};

/** Group message input data */
export type GroupMessageInput = {
  groupId: Scalars['String'];
  message: Scalars['String'];
  title: Scalars['String'];
};

export type GroupSetting = {
  groupId: Scalars['String'];
  notifications: Array<NotificationSetting>;
};

export type GroupsPaginated = {
  actualPage: Scalars['Float'];
  data: Array<Group>;
  hasNextPage: Scalars['Boolean'];
  hasPreviousPage: Scalars['Boolean'];
  totalCount: Scalars['Float'];
  totalPages: Scalars['Float'];
};

export type Last10 = {
  draws: Scalars['Int'];
  loses: Scalars['Int'];
  wins: Scalars['Int'];
};

export type Schedulo = {
  _id: Scalars['String'];
  description?: Maybe<Scalars['String']>;
  name?: Maybe<Scalars['String']>;
};

export type Location = {
  latitude: Scalars['Float'];
  longitude: Scalars['Float'];
};

/** User credentials */
export type LoginInput = {
  email: Scalars['String'];
  password: Scalars['String'];
};

export type LoginOrRegisterResponse = {
  newUser: Scalars['Boolean'];
  token: Scalars['String'];
};

export type LoginToken = {
  hashedToken: Scalars['String'];
  when: Scalars['DateTime'];
};

export type Message = {
  _id: Scalars['String'];
  createdAt: Scalars['DateTime'];
  createdBy: Scalars['String'];
  eventId?: Maybe<Scalars['String']>;
  groupId?: Maybe<Scalars['String']>;
  message: Scalars['String'];
  title: Scalars['String'];
  username: Scalars['String'];
};

export type Mutation = {
  addCalendarEvent: Scalars['Boolean'];
  addPlayer: Event;
  addStripePaymentMethod: Scalars['Boolean'];
  addToGroupWithInviteKey: Scalars['Boolean'];
  changeScore: Event;
  confirmStripePaymentIntent: Scalars['String'];
  connectStripeAccount: Scalars['Boolean'];
  createEstablishment: Establishment;
  createEstablishmentPublic: Establishment;
  createEvent: Event;
  createEventMessage: Message;
  createGroup: Group;
  createGroupMessage: Message;
  createGroupOpponent: Opponent;
  createGroupPlace: Place;
  createPoll: Poll;
  createStripePaymentIntent: StripePaymentIntent;
  deleteEstablishment: Scalars['Boolean'];
  deleteEvent: Scalars['Boolean'];
  deleteGroup: Scalars['Boolean'];
  deleteGroupMessage: Scalars['Boolean'];
  deleteGroupPlace: Scalars['Boolean'];
  deleteUser: Scalars['Boolean'];
  eventReply: Event;
  eventReplyMaybe: Event;
  eventReplyNo: Event;
  eventReplyYes: Event;
  generateInviteKey: Group;
  inviteToGroup: Scalars['Boolean'];
  joinGroup: Scalars['Boolean'];
  kickFromGroup: Scalars['Boolean'];
  leaveGroup: Scalars['Boolean'];
  login: Scalars['String'];
  loginOrRegisterApple: LoginOrRegisterResponse;
  loginOrRegisterFacebookV2: LoginOrRegisterResponse;
  loginOrRegisterGoogleV2: LoginOrRegisterResponse;
  pollVote: Poll;
  profile: User;
  readNotifications: Array<Notification>;
  register: Scalars['String'];
  registerDevice: User;
  removeCalendarEvent: Scalars['Boolean'];
  resetLastSharpening: User;
  resetPassword: Scalars['String'];
  savePriceFeeStrategy: Group;
  sendEventReminder: Scalars['Boolean'];
  sendResetPassword: Scalars['Boolean'];
  sendVerifyAccountEmail: Scalars['Boolean'];
  subscribeAsSubstitute: Event;
  switchAdmin: Scalars['Boolean'];
  switchPlayerPayed: Event;
  switchPlayerSubscription: Event;
  switchPlayerTeam: Event;
  unVerifyAccount: Scalars['Boolean'];
  unregisterDevice: User;
  unsubscribeAsSubstitute: Event;
  updateAppVersion: Scalars['Boolean'];
  updateCalendar: Scalars['Boolean'];
  updateColorScheme: Scalars['Boolean'];
  updateEstablishment: Establishment;
  updateEvent: Event;
  updateGroup: Group;
  updateGroupOpponent: Opponent;
  updateGroupPlace: Place;
  updateNotificationSetting: Array<NotificationSetting>;
  updatePassword: Scalars['Boolean'];
  updatePlayersState: Event;
  updateRsvp: Event;
  uploadGroupPicture: Group;
  uploadProfilePicture: User;
  verifyAccount: Scalars['String'];
};


export type MutationAddCalendarEventArgs = {
  data: CalendarEventInput;
};


export type MutationAddPlayerArgs = {
  data: EventReplyInput;
};


export type MutationAddStripePaymentMethodArgs = {
  data: PaymentMethodInput;
};


export type MutationAddToGroupWithInviteKeyArgs = {
  email: Scalars['String'];
  inviteKey: Scalars['String'];
};


export type MutationChangeScoreArgs = {
  score: ScoreInput;
};


export type MutationConfirmStripePaymentIntentArgs = {
  eventId: Scalars['String'];
  paymentIntentId: Scalars['String'];
};


export type MutationConnectStripeAccountArgs = {
  code: Scalars['String'];
  groupId: Scalars['String'];
};


export type MutationCreateEstablishmentArgs = {
  data: EstablishmentInput;
};


export type MutationCreateEstablishmentPublicArgs = {
  data: EstablishmentInput;
};


export type MutationCreateEventArgs = {
  data: EventInput;
};


export type MutationCreateEventMessageArgs = {
  data: EventMessageInput;
};


export type MutationCreateGroupArgs = {
  data: GroupInput;
};


export type MutationCreateGroupMessageArgs = {
  data: GroupMessageInput;
};


export type MutationCreateGroupOpponentArgs = {
  data: OpponentInput;
};


export type MutationCreateGroupPlaceArgs = {
  data: PlaceInput;
};


export type MutationCreatePollArgs = {
  data: PollInput;
};


export type MutationCreateStripePaymentIntentArgs = {
  eventId: Scalars['String'];
};


export type MutationDeleteEstablishmentArgs = {
  establishmentId: Scalars['String'];
};


export type MutationDeleteEventArgs = {
  eventId: Scalars['String'];
};


export type MutationDeleteGroupArgs = {
  groupId: Scalars['String'];
};


export type MutationDeleteGroupMessageArgs = {
  messageId: Scalars['String'];
};


export type MutationDeleteGroupPlaceArgs = {
  placeId: Scalars['String'];
};


export type MutationDeleteUserArgs = {
  userId: Scalars['String'];
};


export type MutationEventReplyArgs = {
  data: EventReplyInputV2;
};


export type MutationEventReplyMaybeArgs = {
  data: EventReplyInput;
};


export type MutationEventReplyNoArgs = {
  data: EventReplyInput;
};


export type MutationEventReplyYesArgs = {
  data: EventReplyInput;
};


export type MutationGenerateInviteKeyArgs = {
  groupId: Scalars['String'];
};


export type MutationInviteToGroupArgs = {
  emails: Array<Scalars['String']>;
  groupId: Scalars['String'];
};


export type MutationJoinGroupArgs = {
  inviteKey: Scalars['String'];
};


export type MutationKickFromGroupArgs = {
  email: Scalars['String'];
  groupId: Scalars['String'];
};


export type MutationLeaveGroupArgs = {
  groupId: Scalars['String'];
};


export type MutationLoginArgs = {
  data: LoginInput;
};


export type MutationLoginOrRegisterAppleArgs = {
  accessToken?: InputMaybe<Scalars['String']>;
  code?: InputMaybe<Scalars['String']>;
  idToken?: InputMaybe<Scalars['String']>;
  locale?: InputMaybe<Scalars['String']>;
};


export type MutationLoginOrRegisterFacebookV2Args = {
  accessToken?: InputMaybe<Scalars['String']>;
  code?: InputMaybe<Scalars['String']>;
  idToken?: InputMaybe<Scalars['String']>;
  locale?: InputMaybe<Scalars['String']>;
};


export type MutationLoginOrRegisterGoogleV2Args = {
  accessToken?: InputMaybe<Scalars['String']>;
  code?: InputMaybe<Scalars['String']>;
  idToken?: InputMaybe<Scalars['String']>;
  locale?: InputMaybe<Scalars['String']>;
};


export type MutationPollVoteArgs = {
  data: PollVoteInput;
};


export type MutationProfileArgs = {
  data: ProfileInput;
};


export type MutationReadNotificationsArgs = {
  ids: Array<Scalars['String']>;
};


export type MutationRegisterArgs = {
  data: UserRegisterInput;
};


export type MutationRegisterDeviceArgs = {
  deviceId: Scalars['String'];
  platform: Scalars['String'];
  version?: InputMaybe<Scalars['String']>;
};


export type MutationRemoveCalendarEventArgs = {
  calendarEventId: Scalars['String'];
};


export type MutationResetPasswordArgs = {
  password: Scalars['String'];
  token: Scalars['String'];
};


export type MutationSavePriceFeeStrategyArgs = {
  groupId: Scalars['String'];
  strategy: PriceFeeStrategyType;
};


export type MutationSendEventReminderArgs = {
  eventId: Scalars['String'];
};


export type MutationSendResetPasswordArgs = {
  email: Scalars['String'];
};


export type MutationSendVerifyAccountEmailArgs = {
  email: Scalars['String'];
};


export type MutationSubscribeAsSubstituteArgs = {
  eventId: Scalars['String'];
};


export type MutationSwitchAdminArgs = {
  email: Scalars['String'];
  groupId: Scalars['String'];
};


export type MutationSwitchPlayerPayedArgs = {
  data: SwitchPlayerPayedInput;
};


export type MutationSwitchPlayerSubscriptionArgs = {
  data: SubscriptionPlayerInput;
};


export type MutationSwitchPlayerTeamArgs = {
  data: SwitchTeamInput;
};


export type MutationUnVerifyAccountArgs = {
  email: Scalars['String'];
};


export type MutationUnregisterDeviceArgs = {
  deviceId: Scalars['String'];
};


export type MutationUnsubscribeAsSubstituteArgs = {
  eventId: Scalars['String'];
};


export type MutationUpdateAppVersionArgs = {
  deviceId: Scalars['String'];
  version: Scalars['String'];
};


export type MutationUpdateCalendarArgs = {
  calendarId: Scalars['String'];
};


export type MutationUpdateColorSchemeArgs = {
  colorScheme: Scalars['String'];
};


export type MutationUpdateEstablishmentArgs = {
  data: EstablishmentInput;
  establishmentId: Scalars['String'];
};


export type MutationUpdateEventArgs = {
  data: EventInput;
  eventId: Scalars['String'];
};


export type MutationUpdateGroupArgs = {
  data: GroupInput;
  groupId: Scalars['String'];
};


export type MutationUpdateGroupOpponentArgs = {
  data: OpponentInput;
  opponentId: Scalars['String'];
};


export type MutationUpdateGroupPlaceArgs = {
  data: PlaceInput;
  placeId: Scalars['String'];
};


export type MutationUpdateNotificationSettingArgs = {
  data: NotificationSettingInput;
};


export type MutationUpdatePasswordArgs = {
  newPassword: Scalars['String'];
};


export type MutationUpdatePlayersStateArgs = {
  data: PlayerStatesInput;
};


export type MutationUpdateRsvpArgs = {
  data: EventRsvpInput;
};


export type MutationUploadGroupPictureArgs = {
  groupId: Scalars['String'];
  image: Scalars['String'];
};


export type MutationUploadProfilePictureArgs = {
  image: Scalars['String'];
};


export type MutationVerifyAccountArgs = {
  token: Scalars['String'];
};

export type Notification = {
  _id: Scalars['String'];
  body: Scalars['String'];
  createdAt: Scalars['DateTime'];
  eventId?: Maybe<Scalars['String']>;
  groupId?: Maybe<Scalars['String']>;
  read: Scalars['Boolean'];
  section?: Maybe<Scalars['String']>;
  title: Scalars['String'];
  userId: Scalars['String'];
};

export type NotificationSetting = {
  enabled: Scalars['Boolean'];
  key: NotificationType;
};

/** Event reply input data */
export type NotificationSettingInput = {
  enabled: Scalars['Boolean'];
  groupId: Scalars['String'];
  key: NotificationType;
};

/** Group notification settings. */
export enum NotificationType {
  EventDeleted = 'event_deleted',
  EventFullGoalies = 'event_full_goalies',
  EventFullPlayers = 'event_full_players',
  EventFullReferees = 'event_full_referees',
  EventReminder = 'event_reminder',
  EventSubstituteSubscribed = 'event_substitute_subscribed',
  EventSubstituteSubscribedAdmin = 'event_substitute_subscribed_admin',
  EventUnsubscribed = 'event_unsubscribed',
  EventUpdated = 'event_updated',
  NewEvent = 'new_event',
  NewEventMessage = 'new_event_message',
  NewGroupMessage = 'new_group_message'
}

export type Opponent = {
  _id: Scalars['String'];
  createdAt: Scalars['DateTime'];
  createdBy: Scalars['String'];
  groupId?: Maybe<Scalars['String']>;
  title: Scalars['String'];
};

/** Opponent input data */
export type OpponentInput = {
  groupId: Scalars['String'];
  title: Scalars['String'];
};

export type PageInfo = {
  endCursor?: Maybe<Scalars['String']>;
  hasNextPage: Scalars['Boolean'];
  hasPreviousPage: Scalars['Boolean'];
  startCursor?: Maybe<Scalars['String']>;
  total: Scalars['Int'];
};

export type PaginationInput = {
  after?: InputMaybe<Scalars['String']>;
  before?: InputMaybe<Scalars['String']>;
  first?: InputMaybe<Scalars['Int']>;
  last?: InputMaybe<Scalars['Int']>;
};

export type PaginationResult = {
  actualPage: Scalars['Float'];
  hasNextPage: Scalars['Boolean'];
  hasPreviousPage: Scalars['Boolean'];
  totalCount: Scalars['Float'];
  totalPages: Scalars['Float'];
};

/** Payment method input */
export type PaymentMethodInput = {
  cvc: Scalars['String'];
  expMonth: Scalars['Float'];
  expYear: Scalars['Float'];
  number: Scalars['String'];
  postalCode?: InputMaybe<Scalars['String']>;
};

export type Place = {
  _id: Scalars['String'];
  createdAt: Scalars['DateTime'];
  createdBy: Scalars['String'];
  groupId?: Maybe<Scalars['String']>;
  latitude: Scalars['Float'];
  longitude: Scalars['Float'];
  title: Scalars['String'];
};

/** Place input data */
export type PlaceInput = {
  groupId: Scalars['String'];
  latitude: Scalars['Float'];
  longitude: Scalars['Float'];
  title: Scalars['String'];
};

export type Platform = {
  _id: Scalars['String'];
  count: Scalars['Float'];
};

export type Poll = {
  _id: Scalars['String'];
  answers: Array<Answer>;
  createdAt: Scalars['DateTime'];
  createdBy: Scalars['String'];
  objectId: Scalars['String'];
  title: Scalars['String'];
};

/** Poll create input. */
export type PollInput = {
  answers: Array<AnswerInput>;
  objectId: Scalars['String'];
  title: Scalars['String'];
};

/** Poll create input. */
export type PollVoteInput = {
  answerId: Scalars['String'];
  pollId: Scalars['String'];
};

export type Price = {
  currency: Scalars['String'];
  price: Scalars['Float'];
};

/** Price fee strategy. */
export enum PriceFeeStrategyType {
  Middle = 'MIDDLE',
  Organizer = 'ORGANIZER',
  User = 'USER'
}

export type PriceInput = {
  currency: Scalars['String'];
  price: Scalars['Float'];
};

export type Profile = {
  calendar?: Maybe<Scalars['String']>;
  calendarEvents?: Maybe<Array<CalendarEvent>>;
  eventsFromLastSharpening?: Maybe<Scalars['Float']>;
  hollowRadius?: Maybe<Scalars['String']>;
  jerseyNumber?: Maybe<Scalars['Float']>;
  language?: Maybe<Scalars['String']>;
  mobileNotifications?: Maybe<Scalars['Boolean']>;
  name?: Maybe<Scalars['String']>;
  phone?: Maybe<Scalars['String']>;
  picture?: Maybe<Scalars['String']>;
  registeredDevices?: Maybe<Array<RegisteredDevice>>;
  role?: Maybe<Scalars['String']>;
};

/** User profile data */
export type ProfileInput = {
  hollowRadius?: InputMaybe<Scalars['String']>;
  jerseyNumber?: InputMaybe<Scalars['Float']>;
  language?: InputMaybe<Scalars['String']>;
  name?: InputMaybe<Scalars['String']>;
  password?: InputMaybe<Scalars['String']>;
  phone?: InputMaybe<Scalars['String']>;
  role?: InputMaybe<Scalars['String']>;
};

export type Query = {
  adminStats: Array<Platform>;
  findEvents: EventsPaginated;
  findGroups: GroupsPaginated;
  findUsers: UsersPaginated;
  getEstablishments: Array<Establishment>;
  getEvent: Event;
  getEventMessages: Array<Message>;
  getEventSmsList: Array<SmsList>;
  getFacebookAuthUrl: Scalars['String'];
  getGoogleAuthUrl: Scalars['String'];
  getGroup: Group;
  getGroupEvents: EventsPaginated;
  getGroupMembers: Array<User>;
  getGroupMessages: Array<Message>;
  getGroupNotificationSettings: Array<NotificationSetting>;
  getGroupOpponents: Array<Opponent>;
  getGroupPlaces: Array<Place>;
  getMyEvents: Array<Event>;
  getMyEventsPaginated: EventsPaginated;
  getNotifications: Array<Notification>;
  getPoll: Poll;
  getPublicEvents: EventsPaginated;
  getStripeCustomer: Scalars['String'];
  getStripePaymentMethod: StripeCard;
  getUnreadNotificationsCount: Scalars['Float'];
  getUser: User;
  getUserByEmail: User;
  getUserById: User;
  getUserEvents: Array<Event>;
  getUserGroupEvents: Array<Event>;
  getUserGroupEventsHistory: EventsPaginated;
  getUserGroups: Array<Group>;
  getUsers: Array<User>;
  loginOrRegisterFacebook: Scalars['String'];
  loginOrRegisterGoogle: Scalars['String'];
};


export type QueryAdminStatsArgs = {
  limit?: InputMaybe<Scalars['Float']>;
  skip?: InputMaybe<Scalars['Float']>;
};


export type QueryFindEventsArgs = {
  limit?: InputMaybe<Scalars['Float']>;
  search: Scalars['String'];
  skip?: InputMaybe<Scalars['Float']>;
  sortBy?: InputMaybe<Scalars['String']>;
  sortDir?: InputMaybe<Scalars['String']>;
};


export type QueryFindGroupsArgs = {
  limit?: InputMaybe<Scalars['Float']>;
  search: Scalars['String'];
  skip?: InputMaybe<Scalars['Float']>;
  sortBy?: InputMaybe<Scalars['String']>;
  sortDir?: InputMaybe<Scalars['String']>;
};


export type QueryFindUsersArgs = {
  limit?: InputMaybe<Scalars['Float']>;
  search: Scalars['String'];
  skip?: InputMaybe<Scalars['Float']>;
  sortBy?: InputMaybe<Scalars['String']>;
  sortDir?: InputMaybe<Scalars['String']>;
};


export type QueryGetEstablishmentsArgs = {
  groupId?: InputMaybe<Scalars['String']>;
  search: Scalars['String'];
};


export type QueryGetEventArgs = {
  eventId: Scalars['String'];
};


export type QueryGetEventMessagesArgs = {
  eventId: Scalars['String'];
};


export type QueryGetEventSmsListArgs = {
  eventId: Scalars['String'];
};


export type QueryGetGroupArgs = {
  groupId: Scalars['String'];
};


export type QueryGetGroupEventsArgs = {
  groupId: Scalars['String'];
  limit?: InputMaybe<Scalars['Float']>;
  search: Scalars['String'];
  skip?: InputMaybe<Scalars['Float']>;
  sortBy?: InputMaybe<Scalars['String']>;
  sortDir?: InputMaybe<Scalars['String']>;
};


export type QueryGetGroupMembersArgs = {
  groupId: Scalars['String'];
  registered?: InputMaybe<Scalars['Boolean']>;
};


export type QueryGetGroupMessagesArgs = {
  groupId: Scalars['String'];
};


export type QueryGetGroupNotificationSettingsArgs = {
  groupId: Scalars['String'];
};


export type QueryGetGroupOpponentsArgs = {
  groupId: Scalars['String'];
  search: Scalars['String'];
};


export type QueryGetGroupPlacesArgs = {
  groupId: Scalars['String'];
  search: Scalars['String'];
};


export type QueryGetMyEventsPaginatedArgs = {
  limit?: InputMaybe<Scalars['Float']>;
  skip?: InputMaybe<Scalars['Float']>;
};


export type QueryGetPollArgs = {
  objectId: Scalars['String'];
};


export type QueryGetPublicEventsArgs = {
  data: SearchInput;
};


export type QueryGetUserByEmailArgs = {
  email: Scalars['String'];
};


export type QueryGetUserByIdArgs = {
  userId: Scalars['String'];
};


export type QueryGetUserEventsArgs = {
  email: Scalars['String'];
};


export type QueryGetUserGroupEventsArgs = {
  groupId: Scalars['String'];
};


export type QueryGetUserGroupEventsHistoryArgs = {
  groupId: Scalars['String'];
  limit?: InputMaybe<Scalars['Float']>;
  skip?: InputMaybe<Scalars['Float']>;
};


export type QueryGetUserGroupsArgs = {
  email?: InputMaybe<Scalars['String']>;
};


export type QueryGetUsersArgs = {
  limit?: InputMaybe<Scalars['Float']>;
  skip?: InputMaybe<Scalars['Float']>;
};


export type QueryLoginOrRegisterFacebookArgs = {
  accessToken?: InputMaybe<Scalars['String']>;
  code?: InputMaybe<Scalars['String']>;
  idToken?: InputMaybe<Scalars['String']>;
  locale?: InputMaybe<Scalars['String']>;
};


export type QueryLoginOrRegisterGoogleArgs = {
  accessToken?: InputMaybe<Scalars['String']>;
  code?: InputMaybe<Scalars['String']>;
  idToken?: InputMaybe<Scalars['String']>;
  locale?: InputMaybe<Scalars['String']>;
};


export type QueryPlayerArgs = {
  playerId: Scalars['String'];
};


export type QueryPlayersArgs = {
  pagination?: InputMaybe<PaginationInput>;
  seasonId?: InputMaybe<Scalars['String']>;
  sorting?: InputMaybe<SortInput>;
  teamId?: InputMaybe<Scalars['String']>;
};


export type QueryStandingsArgs = {
  seasonId: Scalars['String'];
};


export type QueryTeamArgs = {
  teamId: Scalars['String'];
};

export type RegisteredDevice = {
  deviceId: Scalars['String'];
  platform: Scalars['String'];
  version?: Maybe<Scalars['String']>;
};

export type Resume = {
  loginTokens: Array<LoginToken>;
};

export type Rsvp = {
  date: Scalars['DateTime'];
  jerseyNumber?: Maybe<Scalars['Float']>;
  line?: Maybe<Scalars['Float']>;
  payed?: Maybe<Scalars['Boolean']>;
  picture?: Maybe<Scalars['String']>;
  position?: Maybe<RsvpPosition>;
  response: RsvpResponse;
  role?: Maybe<RsvpRole>;
  team?: Maybe<Team>;
  userId?: Maybe<Scalars['String']>;
  username?: Maybe<Scalars['String']>;
};

/** Rsvp position. */
export enum RsvpPosition {
  Center = 'center',
  Goalie = 'goalie',
  LeftDefense = 'leftDefense',
  LeftWing = 'leftWing',
  RightDefense = 'rightDefense',
  RightWing = 'rightWing'
}

/** Rsvp response. */
export enum RsvpResponse {
  Maybe = 'maybe',
  No = 'no',
  Yes = 'yes'
}

/** Rsvp role. */
export enum RsvpRole {
  Goalie = 'goalie',
  None = 'none',
  Player = 'player',
  Referee = 'referee'
}

export type ScoreInput = {
  eventId: Scalars['String'];
  score: Scalars['Float'];
  team: Scalars['String'];
};

export type SearchInput = {
  limit: Scalars['Float'];
  location: Location;
  radius?: InputMaybe<Scalars['Float']>;
  skip: Scalars['Float'];
};

export type Service = {
  apple?: Maybe<ServiceApple>;
  email?: Maybe<ServiceEmail>;
  facebook?: Maybe<ServiceFacebook>;
  google?: Maybe<ServiceGoogle>;
  password?: Maybe<ServicePassword>;
  resume?: Maybe<Resume>;
};

export type ServiceApple = {
  email: Scalars['String'];
  email_verified: Scalars['Boolean'];
  is_private_email: Scalars['Boolean'];
  sub: Scalars['String'];
};

export type ServiceEmail = {
  verificationTokens: Array<VerificationToken>;
};

export type ServiceFacebook = {
  accessToken: Scalars['String'];
  email: Scalars['String'];
  expiresAt: Scalars['Float'];
  gender?: Maybe<Scalars['String']>;
  id: Scalars['String'];
  locale?: Maybe<Scalars['String']>;
  name: Scalars['String'];
};

export type ServiceGoogle = {
  accessToken: Scalars['String'];
  email: Scalars['String'];
  expiresAt?: Maybe<Scalars['Float']>;
  gender?: Maybe<Scalars['String']>;
  given_name?: Maybe<Scalars['String']>;
  id: Scalars['String'];
  idToken?: Maybe<Scalars['String']>;
  locale?: Maybe<Scalars['String']>;
  name?: Maybe<Scalars['String']>;
  picture?: Maybe<Scalars['String']>;
  scope: Array<Scalars['String']>;
  verified_email: Scalars['Boolean'];
};

export type ServicePassword = {
  bcrypt: Scalars['String'];
};

export type SmsList = {
  phone: Scalars['String'];
};

export type SortInput = {
  order: SortOrder;
  sortBy: Scalars['String'];
};

export enum SortOrder {
  Asc = 'ASC',
  Desc = 'DESC'
}

export type StripeCard = {
  brand: Scalars['String'];
  country: Scalars['String'];
  expMonth: Scalars['Float'];
  expYear: Scalars['Float'];
  last4: Scalars['String'];
};

export type StripePaymentIntent = {
  clientSecret: Scalars['String'];
  id: Scalars['String'];
  status: Scalars['String'];
};

export type Subscription = {
  eventUpdate: Event;
  newEventMessage: Message;
  pollUpdated: Poll;
};


export type SubscriptionEventUpdateArgs = {
  eventId: Scalars['String'];
};


export type SubscriptionNewEventMessageArgs = {
  eventId: Scalars['String'];
};


export type SubscriptionPollUpdatedArgs = {
  objectId: Scalars['String'];
};

export type SubscriptionPlayerInput = {
  eventId: Scalars['String'];
  role?: InputMaybe<Scalars['String']>;
  userId?: InputMaybe<Scalars['String']>;
  username?: InputMaybe<Scalars['String']>;
};

export type Substitute = {
  date: Scalars['DateTime'];
  picture?: Maybe<Scalars['String']>;
  role: RsvpRole;
  userId: Scalars['String'];
  username: Scalars['String'];
};

export type SwitchPlayerPayedInput = {
  eventId: Scalars['String'];
  userId?: InputMaybe<Scalars['String']>;
  username?: InputMaybe<Scalars['String']>;
};

export type SwitchTeamInput = {
  eventId: Scalars['String'];
  userId?: InputMaybe<Scalars['String']>;
  username?: InputMaybe<Scalars['String']>;
};

/** Team type definition. */
export enum Team {
  TeamA = 'teamA',
  TeamB = 'teamB'
}

export type User = {
  _id: Scalars['String'];
  admin?: Maybe<Scalars['Boolean']>;
  colorScheme?: Maybe<Scalars['String']>;
  createdAt: Scalars['DateTime'];
  emails: Array<Email>;
  groupDate?: Maybe<Scalars['DateTime']>;
  groupSettings?: Maybe<Array<GroupSetting>>;
  groupsLimit: Scalars['Float'];
  myself?: Maybe<Scalars['Boolean']>;
  numberOfGroups: Scalars['Float'];
  profile: Profile;
  profilePicture?: Maybe<Scalars['String']>;
  resetToken?: Maybe<Scalars['String']>;
  services?: Maybe<Service>;
  superadmin?: Maybe<Scalars['Boolean']>;
  verifyToken?: Maybe<Scalars['String']>;
};

/** User data */
export type UserRegisterInput = {
  email: Scalars['String'];
  locale?: InputMaybe<Scalars['String']>;
  name?: InputMaybe<Scalars['String']>;
  password: Scalars['String'];
};

export type UsersPaginated = {
  actualPage: Scalars['Float'];
  data: Array<User>;
  hasNextPage: Scalars['Boolean'];
  hasPreviousPage: Scalars['Boolean'];
  totalCount: Scalars['Float'];
  totalPages: Scalars['Float'];
};

export type VerificationToken = {
  address: Scalars['String'];
  token: Scalars['String'];
  when: Scalars['DateTime'];
};

export type Vote = {
  date: Scalars['DateTime'];
  userId: Scalars['String'];
  username: Scalars['String'];
};

export type DivisionFragment = { _id: string, name?: string | undefined };

export type GameListFragment = { _id: string, date: any, status: GameStatus, place?: string | undefined, homeTeam: { name: string, score: number }, awayTeam: { name: string, score: number } };

export type GameTeamPlayerFragment = { name: string, picture?: string | undefined, jerseyNumber?: number | undefined, stats: { goals: number, assists: number, points: number, penaltyMinutes: number, saves: number, goalsReceived: number } };

export type GameTeamFragment = { _id: string, name: string, score: number, shots: number, faceoffs: number, penaltyMinutes: number, players: Array<{ name: string, picture?: string | undefined, jerseyNumber?: number | undefined, stats: { goals: number, assists: number, points: number, penaltyMinutes: number, saves: number, goalsReceived: number } }>, periodsStats: Array<{ shots: number, goals: number, faceoffs: number }> };

export type GameEventPlayerFragment = { name: string, jerseyNumber?: number | undefined };

export type GameEventFragment = { time: number, period: number, type: GameEventType, teamId: string, shot?: { name: string, jerseyNumber?: number | undefined } | undefined, save?: { name: string, jerseyNumber?: number | undefined } | undefined, goal?: { name: string, jerseyNumber?: number | undefined } | undefined, assist?: { name: string, jerseyNumber?: number | undefined } | undefined, secondAssist?: { name: string, jerseyNumber?: number | undefined } | undefined, penalty?: { name: string, jerseyNumber?: number | undefined } | undefined };

export type GameFragment = { _id: string, date: any, status: GameStatus, place?: string | undefined, homeTeam: { _id: string, name: string, score: number, shots: number, faceoffs: number, penaltyMinutes: number, players: Array<{ name: string, picture?: string | undefined, jerseyNumber?: number | undefined, stats: { goals: number, assists: number, points: number, penaltyMinutes: number, saves: number, goalsReceived: number } }>, periodsStats: Array<{ shots: number, goals: number, faceoffs: number }> }, awayTeam: { _id: string, name: string, score: number, shots: number, faceoffs: number, penaltyMinutes: number, players: Array<{ name: string, picture?: string | undefined, jerseyNumber?: number | undefined, stats: { goals: number, assists: number, points: number, penaltyMinutes: number, saves: number, goalsReceived: number } }>, periodsStats: Array<{ shots: number, goals: number, faceoffs: number }> }, events: Array<{ time: number, period: number, type: GameEventType, teamId: string, shot?: { name: string, jerseyNumber?: number | undefined } | undefined, save?: { name: string, jerseyNumber?: number | undefined } | undefined, goal?: { name: string, jerseyNumber?: number | undefined } | undefined, assist?: { name: string, jerseyNumber?: number | undefined } | undefined, secondAssist?: { name: string, jerseyNumber?: number | undefined } | undefined, penalty?: { name: string, jerseyNumber?: number | undefined } | undefined }> };

export type PageInfoFragment = { total: number, hasNextPage: boolean, endCursor?: string | undefined, hasPreviousPage: boolean, startCursor?: string | undefined };

export type UserFragment = { _id: string, createdAt: any, profilePicture?: string | undefined, profile: { name?: string | undefined, language?: string | undefined, role?: string | undefined, phone?: string | undefined, jerseyNumber?: number | undefined, registeredDevices?: Array<{ platform: string, deviceId: string, version?: string | undefined }> | undefined }, emails: Array<{ address: string, verified: boolean }>, services?: { password?: { bcrypt: string } | undefined, google?: { given_name?: string | undefined } | undefined, facebook?: { name: string } | undefined } | undefined };

export type LoginMutationVariables = Exact<{
  data: LoginInput;
}>;


export type LoginMutation = { login: string };


export type GetUserQueryVariables = Exact<{ [key: string]: never; }>;


export type GetUserQuery = { getUser: { _id: string, createdAt: any, profilePicture?: string | undefined, profile: { name?: string | undefined, language?: string | undefined, role?: string | undefined, phone?: string | undefined, jerseyNumber?: number | undefined, registeredDevices?: Array<{ platform: string, deviceId: string, version?: string | undefined }> | undefined }, emails: Array<{ address: string, verified: boolean }>, services?: { password?: { bcrypt: string } | undefined, google?: { given_name?: string | undefined } | undefined, facebook?: { name: string } | undefined } | undefined } };

