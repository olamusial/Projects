#include "user.h"

User::User(string n, string p, int max, int per)
{
nick = n;
password = p;
orders = 0;
maxOrders = max;
permission = per;
}
