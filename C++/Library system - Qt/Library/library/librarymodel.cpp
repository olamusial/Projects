/*#include "librarymodel.h"


libraryModel::libraryModel()
{
QSqlDatabase usersBase;
QSqlDatabase booksBase;

QSqlQueryModel * model = new QSqlQueryModel();
QSqlQueryModel * model2 = new QSqlQueryModel();

QSqlQuery *qry = new QSqlQuery(booksBase);
QSqlQuery *qry2 = new QSqlQuery(booksBase);

}

void libraryModel::connect_to_bookBase()
{
booksBase = QSqlDatabase::addDatabase("QSQLITE");
booksBase.setDatabaseName("bookDB.db");

if(!booksBase.open())
   //  ui->dataLabel->setText("Failed to connect");
    QMessageBox::information(0, "Failed", "Failed to connect to data base");
}

void libraryModel::display_data(Ui::MainWindow *ui, User *user)
{
qry->prepare("select ID, Title, Author, Year from Books where Nick is null");

qry->exec();
model->setQuery(*qry);
ui->tableView->setModel(model);

qry2->prepare("select ID, Title, Author, Year, Date_order, Deadline from Books where Nick = '"+QString::fromStdString((user->nick))+"'");


qry2->exec();
model2->setQuery(*qry2);
ui->tableView_2->setModel(model2);
}

void libraryModel::connect_to_userBase()
{
usersBase = QSqlDatabase::addDatabase("QSQLITE");
usersBase.setDatabaseName("userDataBase.db");

if(!usersBase.open())
    {
        QMessageBox::information(0, "Failed", "Failed to connect to data base");
    }

}


void libraryModel::close_userBase(QSqlDatabase usersBase)
{
 usersBase.close();
}

libraryModel::~libraryModel()
{


delete model;
delete model2;

delete qry;
delete qry2;

}*/
