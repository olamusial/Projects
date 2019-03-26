#include "stdafx.h"
#include "Ship.h"

Ship::Ship(GameDataReference data) : data(data)
{
	try
	{
		ship.setTexture((this->data)->textures.GetTexture("Ship texture"));		//pobranie tekstury na podstawie klucza, czyli nazwy "Ship texture" - sama tekstura jest dodawana do mapy w klasie GameState
	}
	catch (WrongKeyException ex)
	{
		std::cout << ex.what() << std::endl;
	}
	ship.setPosition((windowWIDTH / 2) - (ship.getGlobalBounds().width / 2), windowHEIGHT - (ship.getGlobalBounds().height / 2));		//ustawianie poczatkowej pozycji statku
	ship.setOrigin(ship.getGlobalBounds().width / 2, ship.getGlobalBounds().height / 2);
}


void Ship::draw()
{
	data->window.draw(ship);
}

void Ship::update()
{
	this->ship.move(this->velocity);

	if (data->input.isPressed(sf::Keyboard::Key::Left) && this->left() > 0)		//wywolanie funkcji z klasy InputManager i sprawdzenie czy gracz nie jest "poza" oknem gry
	{
		velocity.x = -shipVelocity;
	}
	else
		if (data->input.isPressed(sf::Keyboard::Key::Right) && this->right() < windowWIDTH)
		{
			velocity.x = shipVelocity;
		}
		else
		{
			velocity.x = 0;
		}
}

float Ship::left()
{
	return this->ship.getPosition().x - ship.getGlobalBounds().width / 2;
}

float Ship::right()
{
	return this->ship.getPosition().x + ship.getGlobalBounds().width / 2;
}

float Ship::top()
{
	return this->ship.getPosition().y - ship.getGlobalBounds().height / 2;
}

float Ship::bottom()
{
	return this->ship.getPosition().y + ship.getGlobalBounds().height / 2;
}

sf::Vector2f Ship::getPosition()
{
	return ship.getPosition();
}
