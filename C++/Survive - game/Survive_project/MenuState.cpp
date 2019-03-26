#include "stdafx.h"
#include "MenuState.h"
#include "HelpState.h"
#include "GameState.h"

MenuState::MenuState(GameDataReference data) : data(data)
{
	
}


void MenuState::Init()
{
	try
	{
	data->textures.LoadTexture(MENU_FILEPATH, "Menu background");
	data->textures.LoadTexture(PLAY_BUTTON_FILEPATH, "Play button");
	data->textures.LoadTexture(HELP_BUTTON_FILEPATH, "Help button");
	}
	catch (NotLoadFromFileException ex)
	{
		std::cout << ex.what() << std::endl;
	}

	try
	{
		background.setTexture(this->data->textures.GetTexture("Menu background"));
		playButton.setTexture(this->data->textures.GetTexture("Play button"));
		helpButton.setTexture(this->data->textures.GetTexture("Help button"));
	}
	catch (WrongKeyException ex)
	{
		std::cout << ex.what() << std::endl;
	}

	playButton.setPosition((windowWIDTH / 3) - (playButton.getGlobalBounds().width / 2), (windowHEIGHT)-(playButton.getGlobalBounds().height * 3));
	helpButton.setPosition((windowWIDTH / 3) - (playButton.getGlobalBounds().width / 2), (windowHEIGHT)-(playButton.getGlobalBounds().height * 1.5));
}

void MenuState::HandleInput()
{
	sf::Event event;

	while (data->window.pollEvent(event))
	{
		if (sf::Event::Closed == event.type)
		{
			data->window.close();
		}

		if (data->input.isClicked(playButton, sf::Mouse::Left, data->window))
		{
			data->machine.AddState(stateReference(new GameState(this->data)));		//dodanie stanu gry (wskaznika) na stos 

		}

		if (data->input.isClicked(helpButton, sf::Mouse::Left, data->window))
		{
			data->machine.AddState(stateReference(new HelpState(this->data)));		//dodanie stanu pomocy - instrukcja 
		}
	}
}

void MenuState::Update()
{

}

void MenuState::Draw()
{
	data->window.clear();
	data->window.draw(background);
	data->window.draw(playButton);
	data->window.draw(helpButton);
	data->window.display();
}
