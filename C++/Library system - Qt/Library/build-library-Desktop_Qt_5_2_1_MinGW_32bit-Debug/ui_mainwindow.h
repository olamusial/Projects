/********************************************************************************
** Form generated from reading UI file 'mainwindow.ui'
**
** Created by: Qt User Interface Compiler version 5.2.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_MAINWINDOW_H
#define UI_MAINWINDOW_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QHBoxLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QMainWindow>
#include <QtWidgets/QMenuBar>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QStatusBar>
#include <QtWidgets/QTableView>
#include <QtWidgets/QToolBar>
#include <QtWidgets/QWidget>

QT_BEGIN_NAMESPACE

class Ui_MainWindow
{
public:
    QWidget *centralWidget;
    QLabel *account_nick;
    QTableView *tableView;
    QLabel *label;
    QLabel *account;
    QTableView *tableView_2;
    QWidget *layoutWidget;
    QHBoxLayout *horizontalLayout;
    QLabel *label_3;
    QLabel *label_4;
    QWidget *layoutWidget1;
    QHBoxLayout *horizontalLayout_2;
    QLabel *label_2;
    QLabel *label_ID;
    QPushButton *pushButton;
    QWidget *layoutWidget2;
    QHBoxLayout *horizontalLayout_3;
    QLabel *label_5;
    QLabel *label_ID_return;
    QPushButton *pushButton_2;
    QPushButton *pushButton_add;
    QPushButton *pushButton_delete;
    QLineEdit *lineEdit_title;
    QLineEdit *lineEdit_author;
    QLineEdit *lineEdit_year;
    QLineEdit *lineEdit_delTitle;
    QLabel *label_title;
    QLabel *label_delTitle;
    QLabel *label_author;
    QLabel *label_year;
    QMenuBar *menuBar;
    QToolBar *mainToolBar;
    QStatusBar *statusBar;

    void setupUi(QMainWindow *MainWindow)
    {
        if (MainWindow->objectName().isEmpty())
            MainWindow->setObjectName(QStringLiteral("MainWindow"));
        MainWindow->resize(868, 659);
        MainWindow->setAutoFillBackground(false);
        centralWidget = new QWidget(MainWindow);
        centralWidget->setObjectName(QStringLiteral("centralWidget"));
        account_nick = new QLabel(centralWidget);
        account_nick->setObjectName(QStringLiteral("account_nick"));
        account_nick->setGeometry(QRect(680, 10, 47, 13));
        tableView = new QTableView(centralWidget);
        tableView->setObjectName(QStringLiteral("tableView"));
        tableView->setGeometry(QRect(30, 330, 641, 271));
        label = new QLabel(centralWidget);
        label->setObjectName(QStringLiteral("label"));
        label->setGeometry(QRect(40, 60, 71, 16));
        account = new QLabel(centralWidget);
        account->setObjectName(QStringLiteral("account"));
        account->setGeometry(QRect(585, 10, 89, 13));
        tableView_2 = new QTableView(centralWidget);
        tableView_2->setObjectName(QStringLiteral("tableView_2"));
        tableView_2->setGeometry(QRect(30, 80, 641, 121));
        layoutWidget = new QWidget(centralWidget);
        layoutWidget->setObjectName(QStringLiteral("layoutWidget"));
        layoutWidget->setGeometry(QRect(580, 10, 171, 16));
        horizontalLayout = new QHBoxLayout(layoutWidget);
        horizontalLayout->setSpacing(6);
        horizontalLayout->setContentsMargins(11, 11, 11, 11);
        horizontalLayout->setObjectName(QStringLiteral("horizontalLayout"));
        horizontalLayout->setContentsMargins(0, 0, 0, 0);
        label_3 = new QLabel(centralWidget);
        label_3->setObjectName(QStringLiteral("label_3"));
        label_3->setGeometry(QRect(190, 310, 261, 16));
        label_4 = new QLabel(centralWidget);
        label_4->setObjectName(QStringLiteral("label_4"));
        label_4->setGeometry(QRect(200, 60, 231, 16));
        layoutWidget1 = new QWidget(centralWidget);
        layoutWidget1->setObjectName(QStringLiteral("layoutWidget1"));
        layoutWidget1->setGeometry(QRect(460, 300, 211, 31));
        horizontalLayout_2 = new QHBoxLayout(layoutWidget1);
        horizontalLayout_2->setSpacing(6);
        horizontalLayout_2->setContentsMargins(11, 11, 11, 11);
        horizontalLayout_2->setObjectName(QStringLiteral("horizontalLayout_2"));
        horizontalLayout_2->setContentsMargins(0, 0, 0, 0);
        label_2 = new QLabel(layoutWidget1);
        label_2->setObjectName(QStringLiteral("label_2"));

        horizontalLayout_2->addWidget(label_2);

        label_ID = new QLabel(layoutWidget1);
        label_ID->setObjectName(QStringLiteral("label_ID"));
        label_ID->setAutoFillBackground(false);
        label_ID->setFrameShape(QFrame::Box);
        label_ID->setFrameShadow(QFrame::Sunken);
        label_ID->setLineWidth(1);
        label_ID->setMidLineWidth(0);
        label_ID->setIndent(-1);

        horizontalLayout_2->addWidget(label_ID);

        pushButton = new QPushButton(layoutWidget1);
        pushButton->setObjectName(QStringLiteral("pushButton"));

        horizontalLayout_2->addWidget(pushButton);

        layoutWidget2 = new QWidget(centralWidget);
        layoutWidget2->setObjectName(QStringLiteral("layoutWidget2"));
        layoutWidget2->setGeometry(QRect(460, 50, 211, 31));
        horizontalLayout_3 = new QHBoxLayout(layoutWidget2);
        horizontalLayout_3->setSpacing(6);
        horizontalLayout_3->setContentsMargins(11, 11, 11, 11);
        horizontalLayout_3->setObjectName(QStringLiteral("horizontalLayout_3"));
        horizontalLayout_3->setContentsMargins(0, 0, 0, 0);
        label_5 = new QLabel(layoutWidget2);
        label_5->setObjectName(QStringLiteral("label_5"));

        horizontalLayout_3->addWidget(label_5);

        label_ID_return = new QLabel(layoutWidget2);
        label_ID_return->setObjectName(QStringLiteral("label_ID_return"));
        label_ID_return->setFrameShape(QFrame::Box);
        label_ID_return->setFrameShadow(QFrame::Sunken);

        horizontalLayout_3->addWidget(label_ID_return);

        pushButton_2 = new QPushButton(layoutWidget2);
        pushButton_2->setObjectName(QStringLiteral("pushButton_2"));

        horizontalLayout_3->addWidget(pushButton_2);

        pushButton_add = new QPushButton(centralWidget);
        pushButton_add->setObjectName(QStringLiteral("pushButton_add"));
        pushButton_add->setEnabled(false);
        pushButton_add->setGeometry(QRect(730, 450, 75, 23));
        pushButton_delete = new QPushButton(centralWidget);
        pushButton_delete->setObjectName(QStringLiteral("pushButton_delete"));
        pushButton_delete->setEnabled(false);
        pushButton_delete->setGeometry(QRect(730, 550, 75, 23));
        lineEdit_title = new QLineEdit(centralWidget);
        lineEdit_title->setObjectName(QStringLiteral("lineEdit_title"));
        lineEdit_title->setEnabled(false);
        lineEdit_title->setGeometry(QRect(700, 330, 131, 21));
        lineEdit_author = new QLineEdit(centralWidget);
        lineEdit_author->setObjectName(QStringLiteral("lineEdit_author"));
        lineEdit_author->setEnabled(false);
        lineEdit_author->setGeometry(QRect(700, 370, 131, 21));
        lineEdit_year = new QLineEdit(centralWidget);
        lineEdit_year->setObjectName(QStringLiteral("lineEdit_year"));
        lineEdit_year->setEnabled(false);
        lineEdit_year->setGeometry(QRect(700, 410, 131, 21));
        lineEdit_delTitle = new QLineEdit(centralWidget);
        lineEdit_delTitle->setObjectName(QStringLiteral("lineEdit_delTitle"));
        lineEdit_delTitle->setEnabled(false);
        lineEdit_delTitle->setGeometry(QRect(700, 510, 131, 21));
        label_title = new QLabel(centralWidget);
        label_title->setObjectName(QStringLiteral("label_title"));
        label_title->setEnabled(false);
        label_title->setGeometry(QRect(750, 350, 47, 13));
        label_delTitle = new QLabel(centralWidget);
        label_delTitle->setObjectName(QStringLiteral("label_delTitle"));
        label_delTitle->setEnabled(false);
        label_delTitle->setGeometry(QRect(750, 530, 47, 13));
        label_author = new QLabel(centralWidget);
        label_author->setObjectName(QStringLiteral("label_author"));
        label_author->setEnabled(false);
        label_author->setGeometry(QRect(750, 390, 47, 13));
        label_year = new QLabel(centralWidget);
        label_year->setObjectName(QStringLiteral("label_year"));
        label_year->setEnabled(false);
        label_year->setGeometry(QRect(750, 430, 47, 13));
        MainWindow->setCentralWidget(centralWidget);
        menuBar = new QMenuBar(MainWindow);
        menuBar->setObjectName(QStringLiteral("menuBar"));
        menuBar->setGeometry(QRect(0, 0, 868, 21));
        MainWindow->setMenuBar(menuBar);
        mainToolBar = new QToolBar(MainWindow);
        mainToolBar->setObjectName(QStringLiteral("mainToolBar"));
        MainWindow->addToolBar(Qt::TopToolBarArea, mainToolBar);
        statusBar = new QStatusBar(MainWindow);
        statusBar->setObjectName(QStringLiteral("statusBar"));
        MainWindow->setStatusBar(statusBar);

        retranslateUi(MainWindow);

        QMetaObject::connectSlotsByName(MainWindow);
    } // setupUi

    void retranslateUi(QMainWindow *MainWindow)
    {
        MainWindow->setWindowTitle(QApplication::translate("MainWindow", "MainWindow", 0));
        account_nick->setText(QApplication::translate("MainWindow", "TextLabel", 0));
        label->setText(QApplication::translate("MainWindow", "Your orders:", 0));
        account->setText(QApplication::translate("MainWindow", "You are signed as:", 0));
        label_3->setText(QApplication::translate("MainWindow", "Click on the ID of the book which you want to order", 0));
        label_4->setText(QApplication::translate("MainWindow", "Click on the ID of the book you want to return", 0));
        label_2->setText(QApplication::translate("MainWindow", "Book's ID to order:", 0));
        label_ID->setText(QApplication::translate("MainWindow", "ID", 0));
        pushButton->setText(QApplication::translate("MainWindow", "Submit", 0));
        label_5->setText(QApplication::translate("MainWindow", "Book's ID to return:", 0));
        label_ID_return->setText(QApplication::translate("MainWindow", "ID", 0));
        pushButton_2->setText(QApplication::translate("MainWindow", "Submit", 0));
        pushButton_add->setText(QApplication::translate("MainWindow", "Add book", 0));
        pushButton_delete->setText(QApplication::translate("MainWindow", "Delete book", 0));
        label_title->setText(QApplication::translate("MainWindow", "Title", 0));
        label_delTitle->setText(QApplication::translate("MainWindow", "Title", 0));
        label_author->setText(QApplication::translate("MainWindow", "Author", 0));
        label_year->setText(QApplication::translate("MainWindow", "Year", 0));
    } // retranslateUi

};

namespace Ui {
    class MainWindow: public Ui_MainWindow {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_MAINWINDOW_H
