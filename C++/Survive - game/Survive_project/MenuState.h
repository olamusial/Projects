#pragma once
#include "State.h"
#include "Game.h"
#include <SFML\Graphics.hpp>
#include "definitions.h"


class MenuState : public State
{
public:
	MenuState(GameDataReference data);

	void Init();
	void HandleInput();
	void Update();
	void Draw();

private:
	GameDataReference data;

	sf::Sprite background;
	sf::Sprite playButton;
	sf::Sprite helpButton;
};

