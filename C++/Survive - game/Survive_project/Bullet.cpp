#include "stdafx.h"
#include "Bullet.h"

Bullet::Bullet(sf::Vector2f position, GameDataReference data) :data(data)
{
	bullet.setRadius(bulletRadius);
	bullet.setFillColor(sf::Color::Black);
	bullet.setOrigin(bulletRadius, bulletRadius);
	bullet.setPosition(position);
}


void Bullet::update()
{
	bullet.move(0.0f, -bulletVelocity);  //pocisk ma poruszac sie tylko w pionie (dlatego tylko skladowa y)

}

void Bullet::draw()
{
	data->window.draw(bullet);
}

float Bullet::top()
{
	return this->bullet.getPosition().y - bullet.getGlobalBounds().height / 2;
}

float Bullet::bottom()
{
	return this->bullet.getPosition().y + bullet.getGlobalBounds().height / 2;
}


float Bullet::left()
{
	return this->bullet.getPosition().x - bullet.getGlobalBounds().width / 2;
}

float Bullet::right()
{
	return this->bullet.getPosition().x + bullet.getGlobalBounds().width / 2;
}


Bullet & Bullet::operator=(const Bullet & other)			//operator przypisania wymagany do metody klasy std::vector erase 
{
	if (&other != this)
	{
		this->data = other.data;
		this->bulletVelocity = other.bulletVelocity;
		this->bullet = other.bullet;
	}
	return *this;
}
