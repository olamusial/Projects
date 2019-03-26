#include "mainwindow.h"
#include <QtSql>
#include <QDebug>
#include <QFileInfo>
#include <QMessageBox>
#include <QTableView>



QSqlDatabase connect_to_bookBase(QSqlDatabase booksBase)
{
    booksBase = QSqlDatabase::addDatabase("QSQLITE");
    booksBase.setDatabaseName("bookDB.db");

    if(!booksBase.open())
        QMessageBox::information(0, "Failed", "Failed to connect to data base");
    else
    return booksBase;

}

void display_data(Ui::MainWindow *ui, QSqlDatabase booksBase, User *user)
{

QSqlQueryModel * model = new QSqlQueryModel();
QSqlQueryModel * model2 = new QSqlQueryModel();

QSqlQuery *qry = new QSqlQuery(booksBase);
QSqlQuery *qry2 = new QSqlQuery(booksBase);

qry->prepare("select ID, Title, Author, Year from Books where Nick is null");

qry->exec();
model->setQuery(*qry);
ui->tableView->setModel(model);

qry2->prepare("select ID, Title, Author, Year, Date_order, Deadline from Books where Nick = '"+QString::fromStdString((user->nick))+"'");


qry2->exec();
model2->setQuery(*qry2);
ui->tableView_2->setModel(model2);


}

QSqlDatabase add_book(QSqlDatabase booksBase, QString title, QString author,QString year)
{

    QSqlQuery *qry = new QSqlQuery(booksBase);

    qry->prepare("INSERT INTO Books (Title, Author, Year) VALUES ('"+title+"', '"+author+"', '"+year+"' )");
    qry->exec();


    delete qry;
 return booksBase;

}

QSqlDatabase delete_book(QSqlDatabase booksBase, QString title)
{


    QSqlQuery *qry = new QSqlQuery(booksBase);

    qry->prepare("select Nick from Books where Title =  '"+title+"' ");
    qry->exec();
    qry->next();
    QString nick = qry->value(0).toString();

    if(nick == "")
    {
    qry->prepare("DELETE FROM Books WHERE Title =  '"+title+"' ");
    qry->exec();
    }
    else
    {
        QMessageBox::information(0, "Cannot delete", "You cannot delete ordered book");
    }
    delete qry;
    return booksBase;


}

QSqlDatabase close_bookBase(QSqlDatabase booksBase)
{
 booksBase.close();
 return booksBase;
}

