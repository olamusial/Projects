#pragma once
#include <SFML\Graphics.hpp>
#include "Game.h"
#include "definitions.h"
#include <ctime>
#include <random>
#include <functional> // dla std::bind
#include <windows.h>


class Enemy
{
public:

	Enemy(GameDataReference data, float position);
	void draw();
	void update();

	float left();			//metody zwracajace pozycje wrogiego statku odpowiednio wzgledem lewej krawedzi, prawej, gornej i dolnej
	float right();
	float top();
	float bottom();

private:
	GameDataReference data;
	sf::Sprite enemy;

	float enemyVelocity{ 5.5f };


};