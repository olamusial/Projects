#pragma once
#include <SFML\Graphics.hpp>
#include "definitions.h"
#include "Game.h"

class Ship
{
public:

	Ship(GameDataReference data);
	void draw();
	void update();

	float left();			//metody zwracajace pozycje statku odpowiednio wzgledem lewej krawedzi, prawej, gornej i dolnej
	float right();
	float top();
	float bottom();

	sf::Vector2f getPosition();

private:

	GameDataReference data;
	sf::Sprite ship;

	float shipVelocity{ 6.0f };			//predkosc poczatkowa
	sf::Vector2f velocity{ shipVelocity, 0.f };			//vector2f przechowuje dwie wartosci - rozlozenie predkosci na dwie skladowe

};
