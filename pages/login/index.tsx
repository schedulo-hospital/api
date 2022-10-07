import { serverSideTranslations } from 'next-i18next/serverSideTranslations'
import * as React from 'react';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import { Login } from '../../src/components/Login'
import { useTranslation } from 'next-i18next'

export const getStaticProps = async ({ locale }: { locale: string }) => ({
  props: {
    ...await serverSideTranslations(locale, ['common']),
  },
})


export default function CreateLeagueForm() {
  const { t } = useTranslation('common')

  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <>
      <Login/>
    </>
  );
}

