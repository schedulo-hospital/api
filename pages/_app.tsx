import * as React from 'react'
import Head from 'next/head'
import { AppProps } from 'next/app'
import { ThemeProvider } from '@mui/material/styles'
import CssBaseline from '@mui/material/CssBaseline'
import { CacheProvider, EmotionCache } from '@emotion/react'
import theme from '../src/theme'
import createEmotionCache from '../src/createEmotionCache'
import { ApolloProvider } from '@apollo/client'
import { client } from '../src/gql/client'
import AppHeader from '../src/components/AppHeader'
import Container from '@mui/material/Container'
import { appWithTranslation, useTranslation } from 'next-i18next'
import moment from 'moment/min/moment-with-locales'
import { Button } from '@mui/material'

// Client-side cache, shared for the whole session of the user in the browser.
const clientSideEmotionCache = createEmotionCache()

interface MyAppProps extends AppProps {
  emotionCache?: EmotionCache
}

const App = (props: MyAppProps) => {
  const { t } = useTranslation()

  const { Component, emotionCache = clientSideEmotionCache, pageProps } = props

  moment.locale('cs')

  return (
    <CacheProvider value={emotionCache}>
      <Head>
        <title>Schedulo Hospital</title>
        <meta name="viewport" content="initial-scale=1, width=device-width" />
        <link rel="icon" href="/favicon.ico" />
      </Head>
      <ApolloProvider client={client}>
        <ThemeProvider theme={theme}>
          {/* CssBaseline kickstart an elegant, consistent, and simple baseline to build upon. */}
          <CssBaseline />

          <AppHeader />

          <Container maxWidth="lg">
            <Component {...pageProps} />
          </Container>
        </ThemeProvider>
      </ApolloProvider>
    </CacheProvider>
  )
}

export default appWithTranslation(App)
