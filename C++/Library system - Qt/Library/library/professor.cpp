#include "professor.h"

Professor::Professor(string n, string p, int max, int per):User(n,p, max, per)
{

}

void Professor::order_book()
{
    orders++;
}

void Professor::return_book()
{
    if(orders>0)
    orders--;
}


