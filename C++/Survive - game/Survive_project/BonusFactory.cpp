#include "stdafx.h"
#include "BonusFactory.h"

BonusFactory::BonusFactory(GameDataReference data):data(data)
{
	lastPosition = 0.0;
}

Bonus BonusFactory::createBonus()
{
	auto random = std::bind(std::uniform_real_distribution<float>(21.5f, 728.5f), std::mt19937((DWORD)time(NULL)));		//funkcja losujaca liczby z zakresu od 21,5 do 728,5

	float position = random();

	while (position == lastPosition || (position + 200 > lastPosition && position - 200 < lastPosition))			//generowane po sobie bonus musza byc oddalone od siebie o min. 200
	{
		position = random();
	}

	lastPosition = position;
	return Bonus(data, position);
}
