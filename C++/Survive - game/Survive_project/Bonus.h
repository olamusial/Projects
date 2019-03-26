#pragma once
#include <SFML\Graphics.hpp>
#include "Game.h"

class Bonus
{
public:

	Bonus(GameDataReference data, float position);
	void draw();
	void update();

	float left();			
	float right();
	float top();
	float bottom();

private:
	GameDataReference data;
	sf::Sprite bonus;

	float bonusVelocity{ 5.0f };
};