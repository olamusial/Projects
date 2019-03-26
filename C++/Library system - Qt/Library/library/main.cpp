#include "loginwindow.h"
#include <QApplication>

#include <QtSql>
#include <QDebug>
#include <QFileInfo>

int main(int argc, char *argv[])
{

    QApplication a(argc, argv);


    LoginWindow loginWindow;
    loginWindow.show();

  return a.exec();
}
