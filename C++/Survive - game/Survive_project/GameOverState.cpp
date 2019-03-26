#include "stdafx.h"
#include "GameOverState.h"
#include "GameState.h"
#include <string>

GameOverState::GameOverState(GameDataReference data, int score) : data(data), final_score(score)
{
}

void GameOverState::Init()
{
	try
	{
		data->textures.LoadTexture(GAMEOVER_FILEPATH, "GameOver background");
		data->textures.LoadTexture(PLAY_BUTTON_FILEPATH, "Play button");
		data->textures.LoadTexture(QUIT_BUTTON_FILEPATH, "Quit button");
		
		data->textures.LoadFont(FONT, "Score font");
	}
	catch (NotLoadFromFileException ex)
	{
		std::cout << ex.what() << std::endl;
	}

	try
	{
		background.setTexture(this->data->textures.GetTexture("GameOver background"));
		playButton.setTexture(this->data->textures.GetTexture("Play button"));
		quitButton.setTexture(this->data->textures.GetTexture("Quit button"));

		text.setFont(data->textures.GetFont("Score font"));
	}
	catch (WrongKeyException ex)
	{
		std::cout << ex.what() << std::endl;
	}


	playButton.setPosition((windowWIDTH)-(playButton.getGlobalBounds().width /2) * 2.5, (windowHEIGHT)-(playButton.getGlobalBounds().height * 2));
	quitButton.setPosition( (quitButton.getGlobalBounds().width / 2) * 0.5 , (windowHEIGHT)-(quitButton.getGlobalBounds().height *2));
	
	text.setString(std::to_string(this->final_score));
	text.setCharacterSize(75);
	text.setFillColor(sf::Color::Black);
	text.setStyle(sf::Text::Bold);
	text.setOrigin(text.getGlobalBounds().width / 2, text.getGlobalBounds().height / 2);
	text.setPosition(480.0, 290.0);
}

void GameOverState::HandleInput()
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
			data->machine.AddState(stateReference(new GameState(this->data)));		

		}

		if (data->input.isClicked(quitButton, sf::Mouse::Left, data->window))
		{
			data->window.close();
		}
	}
}

void GameOverState::Update()
{
}

void GameOverState::Draw()
{
	data->window.clear();
	data->window.draw(background);
	data->window.draw(playButton);
	data->window.draw(quitButton);
	data->window.draw(text);
	data->window.display();
}
