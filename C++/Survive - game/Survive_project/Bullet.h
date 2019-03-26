#pragma once
#include <SFML\Graphics.hpp>
#include "Game.h"
#include <vector>

class Bullet
{
public:
	Bullet(sf::Vector2f position, GameDataReference data);

	void update();
	void draw();

	float left();			//metody zwracajace pozycje pocisku odpowiednio wzgledem lewej krawedzi, prawej, gornej i dolnej
	float right();
	float top();
	float bottom();

	Bullet & operator = (const Bullet &other);

private:

	GameDataReference data;
	sf::CircleShape bullet;
	const float bulletRadius{ 10.0f };
	float bulletVelocity{ 13.0f };


};
