#pragma once
#include "Enemy.h"
#include "Game.h"
#include "definitions.h"
#include <random>

class EnemyFactory
{
public:
	EnemyFactory(GameDataReference data);

	Enemy createEnemy();

private:
	GameDataReference data;
	int tab[10];
	float lastPosition;
};