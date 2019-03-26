#include "loginwindow.h"
#include "ui_loginwindow.h"


#include "definitions.cpp"

#include "userbase.cpp"
//#include "userbase.h"




LoginWindow::LoginWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::LoginWindow)
{
    ui->setupUi(this);

  usersBase = connect_to_userBase(usersBase);



}

LoginWindow::~LoginWindow()
{
    delete ui;
}

void LoginWindow::on_pushButton_clicked()
{

    QString nick, password;
    nick = ui->loginLine->text();
    password = ui->passwordLine->text();

int count = check_password(user, usersBase, password, nick);

        if(count == 1)
        {

            close();


            if((nick.toStdString()) == admin.nick)
            {

               user = &admin;

            }

            if((nick.toStdString()) == student.nick)
            {
                user = &student;
            }

            if((nick.toStdString()) == professor.nick)
            {
                user = &professor;
            }

            close_userBase(usersBase);
            mainWindow = new MainWindow(user);
            mainWindow->show();

        }
        if(count < 1)
        {
             ui->errorLabel->setText("Wrong login or password");
        }
   // }
}
//}
