#include "stdafx.h"
#include "Score.h"
#include <String>

Score::Score(GameDataReference data):data(data)
{
	score = 0;
	try
	{
		text.setFont(data->textures.GetFont("Score font"));
	}
	catch (WrongKeyException ex)
	{
		std::cout << ex.what() << std::endl;
	}
	text.setString("0");
	text.setCharacterSize(64);
	text.setFillColor(sf::Color::Black);
	text.setOrigin(text.getGlobalBounds().width/2, text.getGlobalBounds().height / 2);
	text.setPosition(text.getGlobalBounds().height, text.getGlobalBounds().height / 2);

	try
	{
		text_score.setFont(data->textures.GetFont("Score font"));
	}
	catch (WrongKeyException ex)
	{
		std::cout << ex.what() << std::endl;
	}
	text_score.setString("SCORE:");
	text_score.setCharacterSize(30);
	text_score.setFillColor(sf::Color::Black);
	text_score.setStyle(sf::Text::Bold);
	text_score.setOrigin(text.getGlobalBounds().width / 2, text.getGlobalBounds().height / 2);
	text_score.setPosition(text.getGlobalBounds().height, text.getGlobalBounds().height / 2);
}

void Score::draw()
{
	data->window.draw(text);
	data->window.draw(text_score);
}

int Score::getScore()
{
	return score;
}

void Score::increaseScore()
{
	score += 10;
	text.setString(std::to_string(this->score));
}

void Score::increaseScoreWithBonus()
{
	score += 20;
	text.setString(std::to_string(this->score));
}
