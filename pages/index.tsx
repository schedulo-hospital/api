import type { NextPage } from 'next'
import { useQuery } from '@apollo/client'
import { Box, CircularProgress, Paper, TablePagination, Typography } from '@mui/material'
import { useTranslation } from 'next-i18next'
import { serverSideTranslations } from 'next-i18next/serverSideTranslations'
import Link from 'next/link'
import React from 'react'

export const getStaticProps = async ({ locale }: { locale: string }) => ({
  props: {
    ...await serverSideTranslations(locale, ['common']),
  },
})

const MainHospital: NextPage = () => {
  const { t } = useTranslation('common')

  return (
    <>
  
      <Typography variant="h1">{t('Hospital')}</Typography>

    </>
  )
}

export default MainHospital
