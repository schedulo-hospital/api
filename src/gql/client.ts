import {
  ApolloClient,
  InMemoryCache,
} from '@apollo/client'
import { resolvers, typeDefs } from './mocks'

export const client = new ApolloClient({
  uri: 'https://api.schedulo-hospital.net',
  cache: new InMemoryCache(),
  resolvers,
  typeDefs
})
