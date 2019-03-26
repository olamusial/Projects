#pragma once
#include "State.h"
#include "Game.h"
#include <SFML\Graphics.hpp>
#include "definitions.h"


class GameOverState : public State
{
public:
	GameOverState(GameDataReference data, int score);

	void Init();
	void HandleInput();
	void Update();
	void Draw();

private:
	GameDataReference data;

	sf::Sprite background;
	sf::Sprite playButton;
	sf::Sprite quitButton;

	sf::Text text;
	int final_score;
};
