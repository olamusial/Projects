#ifndef USER_H
#define USER_H

#include <iostream>
using namespace std;

class User
{
public:
    string nick;
    string password;
    int orders;
    int maxOrders;
    int permission;

    User(string n, string p, int max, int per);

   virtual void order_book() = 0;
   virtual void return_book() = 0;


};

#endif // USER_H
