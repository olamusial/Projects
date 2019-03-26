#pragma once

#include "State.h"
#include "Game.h"
#include "definitions.h"
#include <SFML\Graphics.hpp>



class HelpState : public State
{
public:
	HelpState(GameDataReference data);

	void Init();
	void HandleInput();
	void Update();
	void Draw();


private:
	GameDataReference data;

	sf::Sprite background;
	sf::Sprite playButton;

};