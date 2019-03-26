#include "student.h"

Student::Student(string n, string p, int max, int per):User(n,p, max, per)
{

}

void Student::order_book()
{
    orders++;
}

void Student::return_book()
{
    if(orders>0)
    orders--;
}

