#include "stdafx.h"
#include "Enemy.h"



Enemy::Enemy(GameDataReference data, float position): data(data)
{
	
	try
	{
		enemy.setTexture((this->data)->textures.GetTexture("Enemy texture"));		//pobranie tekstury na podstawie klucza, czyli nazwy "Ship texture" - sama tekstura jest dodawana do mapy w klasie GameState
	}
	catch(WrongKeyException ex)
	{
		std::cout << ex.what() << std::endl;
	}
	enemy.setPosition(position,  (enemy.getGlobalBounds().height / 2));
	enemy.setOrigin(enemy.getGlobalBounds().width / 2, enemy.getGlobalBounds().height / 2);
}


void Enemy::draw()
{
	data->window.draw(enemy);
}

void Enemy::update()
{
	enemy.move(0.0f, enemyVelocity);  //pocisk ma poruszac sie tylko w pionie (dlatego tylko skladowa y)
}

float Enemy::bottom()
{
	return this->enemy.getPosition().y + enemy.getGlobalBounds().height / 2;
}


float Enemy::left()
{
	return this->enemy.getPosition().x - enemy.getGlobalBounds().width / 2;
}

float Enemy::right()
{
	return this->enemy.getPosition().x + enemy.getGlobalBounds().width / 2;
}

float Enemy::top()
{
	return this->enemy.getPosition().y - enemy.getGlobalBounds().height / 2;
}