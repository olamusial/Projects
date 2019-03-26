#pragma once
#include "Bonus.h"
#include <random>
#include <functional>
#include <windows.h>

class BonusFactory
{
public:
	BonusFactory(GameDataReference data);

	Bonus createBonus();

private:
	GameDataReference data;
	float lastPosition;
};