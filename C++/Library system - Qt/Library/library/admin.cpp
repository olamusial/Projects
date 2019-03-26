#include "admin.h"

Admin::Admin(string n, string p, int max, int per):User(n,p,max, per)
{

}

void Admin::order_book()
{
    orders++;
}

void Admin::return_book()
{
    if(orders>0)
    orders--;
}

