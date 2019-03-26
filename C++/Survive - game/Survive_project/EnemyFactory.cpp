#include "stdafx.h"
#include "EnemyFactory.h"

EnemyFactory::EnemyFactory(GameDataReference data) :data(data)
{
	srand(time(0));
	for (int i = 0; i < 10; i++)
	{
		tab[i] = rand() % 10 + 1;
	}
	lastPosition = 0.0;

}

Enemy EnemyFactory::createEnemy()
{
	auto random = std::bind(std::uniform_real_distribution<float>(21.5f, 728.5f), std::mt19937((DWORD)time(NULL)));		//funkcja losujaca liczby z zakresu od 21,5 do 728,5

	float position = random();

	while (position == lastPosition || (position + 80 > lastPosition && position - 80 < lastPosition) )			//generowani po sobie przeciwnicy musza byc oddaleni od siebie o min. 80
	{
		position = random();
	}

	lastPosition = position;
	return Enemy(data, position);
}
