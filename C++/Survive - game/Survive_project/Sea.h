#pragma once
#include <SFML\Graphics.hpp>
#include "Game.h"
#include <vector>

class Sea
{
public:
	Sea(GameDataReference data);

	void Move();
	void Draw();

private:
	GameDataReference data;

	std::vector<sf::Sprite> background;
	float movement = { 1.5f };

	sf::Sprite sprite;
	sf::Sprite sprite2;
};