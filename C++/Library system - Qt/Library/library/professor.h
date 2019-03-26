#ifndef PROFESSOR_H
#define PROFESSOR_H

#include "user.h"

class Professor : public User
{
public:
    Professor(string n, string p, int max, int per);

    virtual void order_book() override;
    virtual void return_book() override;

};

#endif // PROFESSOR_H
