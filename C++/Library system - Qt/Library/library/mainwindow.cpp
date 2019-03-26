#include "mainwindow.h"
#include "ui_mainwindow.h"
#include "bookbase.cpp"
#include "userbase.h"

#include <QDate>

#include <string>
#include <iostream>
#include <sstream>





MainWindow::MainWindow(User * user, QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);

    ui->tableView->verticalHeader()->setVisible(false);
    ui->tableView_2->verticalHeader()->setVisible(false);

    ui->account_nick->setText(QString::fromStdString(user->nick));


    connect_to_userBase(usersBase);

    QSqlQuery *qry3 = new QSqlQuery(usersBase);

       qry3->prepare("select Orders from Users where Nick = '"+QString::fromStdString((user->nick))+"' ");
       qry3->exec();
       qry3->next();
       int orders = qry3->value(0).toInt();
       user->orders = orders;

       close_userBase(usersBase);

       if(user->permission == 1)
       {
           ui->pushButton_add->setEnabled(true);
           ui->pushButton_delete->setEnabled(true);
           ui->lineEdit_title->setEnabled(true);
           ui->lineEdit_author->setEnabled(true);
           ui->lineEdit_year->setEnabled(true);
           ui->lineEdit_delTitle->setEnabled(true);
           ui->label_title->setEnabled(true);
           ui->label_author->setEnabled(true);
           ui->label_year->setEnabled(true);
           ui->label_delTitle->setEnabled(true);
       }




    booksBase = connect_to_bookBase(booksBase);



    display_data(ui, booksBase, user);

}



void MainWindow::closeEvent(QCloseEvent *event)
{
    extern User * user;
    // Do something
    QSqlDatabase usersBase;
    //connect_to_userBase(usersBase);

    QSqlQuery *qry = new QSqlQuery(usersBase);

    int orders = user->orders;


    ostringstream oOStrStream;
     oOStrStream << orders;
     string str_orders =  oOStrStream.str();

       qry->prepare("UPDATE Users SET Orders = '"+QString::fromStdString(str_orders)+"'  WHERE Nick = '"+QString::fromStdString((user->nick))+"' ");
       qry->exec();


    event->accept();

    delete qry;
}

MainWindow::~MainWindow()//QSqlQueryModel * model, QSqlQueryModel * model2, QSqlQuery * qry, QSqlQuery * qry2)
{
     delete ui;
   /* delete model;
    delete model2;

    delete qry;
    delete qry2;*/
}



void MainWindow::on_tableView_clicked(const QModelIndex &index)
{
    int cellID = -100;
    if (index.isValid())
       {    cellID  = index.data().toInt();
        if(cellID > 0 && cellID <1000)
        {
            cellText = QString::number(cellID);
           ui->label_ID->setText(cellText);
        }
       }

}


void MainWindow::on_pushButton_clicked()
{
    extern User * user;


if ((user->orders)<(user->maxOrders))
{

    QSqlQuery *qry3 = new QSqlQuery(booksBase);

   qry3->prepare("select Nick from Books where ID = '"+cellText+"' ");
   qry3->exec();

    qry3->next();
    QString book_nick = qry3->value(0).toString();
   if(book_nick == "")
    {
    QString date = QDate::currentDate().toString("dd.MM.yy");

    QDate deadline_date = QDate::currentDate();
    QString deadline = deadline_date.addMonths(3).toString("dd.MM.yy");

     QSqlQuery *qry4 = new QSqlQuery(booksBase);
      qry4->prepare("UPDATE Books SET Nick = '"+QString::fromStdString((user->nick))+"', Date_order = '"+date+"', Deadline = '"+deadline+"' WHERE ID = '"+cellText+"'");

      qry4->exec();
      user->order_book();

    }

}
else
{
    QMessageBox::information(0, "limit exceeded", "You cannot order another book, your library is full");
}
     display_data(ui, booksBase, user);//,  model, model2, qry, qry2);

}

void MainWindow::on_tableView_2_clicked(const QModelIndex &index)
{
    int cellID_return = -100;
    if (index.isValid())
       {    cellID_return  = index.data().toInt();
        if(cellID_return > 0 && cellID_return <1000)
        {
            cellText_return = QString::number(cellID_return);
           ui->label_ID_return->setText(cellText_return);

        }
       }

}

void MainWindow::on_pushButton_2_clicked()
{
    extern User * user;

    QSqlQuery *qry3 = new QSqlQuery(booksBase);

    qry3->prepare("select Nick from Books where ID = '"+cellText_return+"' ");
    qry3->exec();

     qry3->next();
     QString book_nick = qry3->value(0).toString();
    if(book_nick != "")
    {
     QSqlQuery *qry4 = new QSqlQuery(booksBase);
      qry4->prepare("UPDATE Books SET Nick = null, Date_order = null, Deadline = null WHERE ID = '"+cellText_return+"'");

      qry4->exec();
      user->return_book();
    }
     display_data(ui, booksBase, user);//,  model, model2, qry, qry2);

}

void MainWindow::on_pushButton_add_clicked()
{
    extern User * user;
    QString title, author, year;
   title = ui->lineEdit_title->text();
   author = ui->lineEdit_author->text();
   year = ui->lineEdit_year->text();

   if(title =="" || author == "" || year == "")
   {
       QMessageBox::information(0, "Empty place", "All fields have to be filled");
   }
   else
   {
       connect_to_bookBase(booksBase);
       booksBase = add_book(booksBase, title, author, year);
       display_data(ui, booksBase, user);


   }
}

void MainWindow::on_pushButton_delete_clicked()
{

    extern User * user;
    QString title;
    title = ui->lineEdit_delTitle->text();

    if(title =="")
    {
         QMessageBox::information(0, "Empty place", "You have to write the title");
    }
    else
    {
        delete_book(booksBase, title);
        display_data(ui, booksBase, user);
    }
}
