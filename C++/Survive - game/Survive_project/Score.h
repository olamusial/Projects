#pragma once

#include <SFML\Graphics.hpp>
#include "Game.h"
#include "definitions.h"

class Score
{
public:
	Score(GameDataReference data);
	void draw();

	int getScore();
	void increaseScore();
	void increaseScoreWithBonus();

private:
	GameDataReference data;
	int score;
	sf::Text text_score;
	sf::Text text;
};