#include "stdafx.h"
#include "HelpState.h"
#include "GameState.h"

HelpState::HelpState(GameDataReference data) :data(data)
{
}


void HelpState::Init()
{
	try
	{
	data->textures.LoadTexture(HELP_FILEPATH, "Help background");
	data->textures.LoadTexture(PLAY_BUTTON_FILEPATH, "Play button");
	}
	catch (NotLoadFromFileException ex)
	{
		std::cout << ex.what() << std::endl;
	}

	try
	{
		background.setTexture(this->data->textures.GetTexture("Help background"));
		playButton.setTexture(this->data->textures.GetTexture("Play button"));
	}
	catch (WrongKeyException ex)
	{
		std::cout << ex.what() << std::endl;
	}

	playButton.setPosition((windowWIDTH)-(playButton.getGlobalBounds().width * 2), (windowHEIGHT)-(playButton.getGlobalBounds().height * 1.5));		//ustawianie pozycji 
}

void HelpState::HandleInput()
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

	}
}

void HelpState::Update()
{
}

void HelpState::Draw()
{
	data->window.clear();						//czyszczenie okna
	data->window.draw(background);				//rysowanie spritow
	data->window.draw(playButton);
	data->window.display();						//wyswietlanie 
}
