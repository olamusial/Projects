#ifndef STUDENT_H
#define STUDENT_H

#include "user.h"

class Student : public User
{
public:
    Student(string n, string p, int max, int per);

    virtual void order_book() override;
    virtual void return_book() override;

};

#endif // STUDENT_H
