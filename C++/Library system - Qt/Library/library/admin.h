#ifndef ADMIN_H
#define ADMIN_H


#include "user.h"

class Admin  : public User
{
public:
    Admin(string n, string p, int max, int per);

    virtual void order_book() override;
    virtual void return_book() override;


    void add_book(string title, string author, int year, int amount);
    void delete_book(string title);
};
#endif // ADMIN_H
