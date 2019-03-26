#ifndef USERBASE_H
#define USERBASE_H

#endif // USERBASE_H


//#include "loginwindow.h"
#include <QtSql>
#include <QDebug>
#include <QFileInfo>
#include <QMessageBox>



QSqlDatabase connect_to_userBase(QSqlDatabase usersBase);

QSqlDatabase close_userBase(QSqlDatabase usersBase);

int check_password(User *user, QSqlDatabase usersBase, QString password, QString nick);
