#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QtSql>  //
#include <QDebug>
#include <QFileInfo>
#include <user.h>
#include <QObject>
#include "userbase.h"


#include <QCloseEvent>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(User * user = 0,QWidget *parent = 0);
    ~MainWindow();



private slots:


    void on_tableView_clicked(const QModelIndex &index);
    void on_pushButton_clicked();

    void on_tableView_2_clicked(const QModelIndex &index);

    void on_pushButton_2_clicked();



    void on_pushButton_add_clicked();

    void on_pushButton_delete_clicked();

private:
    Ui::MainWindow *ui;

    QSqlDatabase booksBase;
     QString cellText;
     QString cellText_return;
     QSqlDatabase usersBase;

      void closeEvent(QCloseEvent *event);
};

#endif // MAINWINDOW_H
