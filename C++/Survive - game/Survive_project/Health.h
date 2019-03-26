#pragma once
#include <SFML\Graphics.hpp>
#include "Game.h"
#include "definitions.h"

class Health
{
public:
	Health(GameDataReference data);

	void Increase();
	void Decrease();

	void Draw();

	int GetLife();

private:
	GameDataReference data;
	int life;

	sf::Sprite heart1;
	sf::Sprite heart2;
	sf::Sprite heart3;


};