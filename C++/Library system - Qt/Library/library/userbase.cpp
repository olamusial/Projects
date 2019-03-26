#include "userbase.h"




QSqlDatabase connect_to_userBase(QSqlDatabase usersBase)
{
usersBase = QSqlDatabase::addDatabase("QSQLITE");
usersBase.setDatabaseName("userDataBase.db");

if(!usersBase.open())
    {
        QMessageBox::information(0, "Failed", "Failed to connect to data base");
    }

return usersBase;
}

QSqlDatabase close_userBase(QSqlDatabase usersBase)
{
 usersBase.close();
 return usersBase;
}


int check_password(User *user, QSqlDatabase usersBase, QString password, QString nick)
{


    if(!usersBase.isOpen())
    {
        QMessageBox::information(0, "Failed", "Data base is not open");
     }

    QSqlQuery qry;

int count = 0;
    if(qry.exec("select * from Users where Nick = '"+nick+"' and Password = '"+password+"'" ))
    {

        while(qry.next())
        {
            count++;
        }
    }


    return count;
}
