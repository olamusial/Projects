#include "stdafx.h"
#include "Bonus.h"

Bonus::Bonus(GameDataReference data, float position):data(data)
{
	try
	{
		bonus.setTexture((this->data)->textures.GetTexture("Bonus texture"));		//pobranie tekstury na podstawie klucza, czyli nazwy "Ship texture" - sama tekstura jest dodawana do mapy w klasie GameState
	}
	catch (WrongKeyException ex)
	{
		std::cout << ex.what() << std::endl;
	}
	bonus.setPosition(position, (bonus.getGlobalBounds().height / 2));
	
	bonus.setOrigin(bonus.getGlobalBounds().width / 2, bonus.getGlobalBounds().height / 2);
}

void Bonus::draw()
{
	data->window.draw(bonus);
}


void Bonus::update()
{
	bonus.move(0.0f, bonusVelocity);  //pocisk ma poruszac sie tylko w pionie (dlatego tylko skladowa y)
}

float Bonus::bottom()
{
	return this->bonus.getPosition().y + bonus.getGlobalBounds().height / 2;
}


float Bonus::left()
{
	return this->bonus.getPosition().x - bonus.getGlobalBounds().width / 2;
}

float Bonus::right()
{
	return this->bonus.getPosition().x + bonus.getGlobalBounds().width / 2;
}

float Bonus::top()
{
	return this->bonus.getPosition().y - bonus.getGlobalBounds().height / 2;
}