#include "stdafx.h"
#include "Sea.h"

Sea::Sea(GameDataReference data):data(data)
{

	try
	{
		sprite.setTexture(data->textures.GetTexture("Game background"));
		sprite2.setTexture(data->textures.GetTexture("Game background"));
	}
	catch (WrongKeyException ex)
	{
		std::cout << ex.what() << std::endl;
	}

	sprite.setPosition(0, 0);
	sprite2.setPosition(0, sprite.getGlobalBounds().height);

	background.push_back(sprite);
	background.push_back(sprite2);

	
	
}

void Sea::Move()
{
	for (int i = 0; i < background.size(); i++)
	{
		background[i].move(0.0f, -movement);

		if (background[i].getPosition().y <= (1 - background[i].getGlobalBounds().height ))
		{
			background[i].setPosition(background[i].getPosition().x, data->window.getSize().y);
		}
	}
}

void Sea::Draw()
{
	for (int i = 0; i < background.size(); i++)
	{
		data->window.draw(background[i]);
	}
}

