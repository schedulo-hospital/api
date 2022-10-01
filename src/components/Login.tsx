import * as React from 'react'
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import Dialog from '@mui/material/Dialog';
import DialogActions from '@mui/material/DialogActions';
import DialogContent from '@mui/material/DialogContent';
import DialogContentText from '@mui/material/DialogContentText';
import DialogTitle from '@mui/material/DialogTitle';

import { useTranslation } from 'next-i18next'
import Link from 'next/link'

export const Login = () => {
  const { t } = useTranslation('common')

  const [open, setOpen] = React.useState(false);

  const handleClickOpen = () => {
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <div>
      <Button variant="outlined" onClick={handleClickOpen}>
        {t('Login')}
      </Button>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>{t('sch_login.formtitle')}</DialogTitle>
        <DialogContent>
          <DialogContentText>
	    {t('sch_login.descr')}
          </DialogContentText>
          <TextField
            autoFocus
            margin="dense"
            id="Login"
            label={t('sch_login.name')}
            type="text"
            fullWidth
            variant="standard"
          />
	 <TextField
            autoFocus
            margin="dense"
            id="Pwd"
            label={t('sch_login.pwd')}
            type="text"
            fullWidth
            variant="standard"
          />
        </DialogContent>
        <DialogActions>
          <Button onClick={handleClose}>{t('sch_login.cancel')}</Button>
          <Button onClick={handleClose}>{t('sch_login.save')}</Button>
        </DialogActions>
      </Dialog>
    </div>
  );
}
 
 
