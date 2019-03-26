#include "stdafx.h"
#include "Health.h"

Health::Health(GameDataReference data):data(data)
{
	life = 3;

	try
	{
		heart1.setTexture((this->data)->textures.GetTexture("Heart texture"));
		heart2.setTexture((this->data)->textures.GetTexture("Heart texture"));
		heart3.setTexture((this->data)->textures.GetTexture("Heart texture"));
	}
	catch (WrongKeyException ex)
	{
		std::cout << ex.what() << std::endl;
	}

	heart1.setScale(0.08f, 0.08f);
	heart2.setScale(0.08f, 0.08f);
	heart3.setScale(0.08f, 0.08f);

	heart1.setPosition(windowWIDTH - heart1.getGlobalBounds().width * 1.5 , heart1.getGlobalBounds().height / 2);
	heart2.setPosition(windowWIDTH - heart2.getGlobalBounds().width * 2.5, heart2.getGlobalBounds().height / 2);
	heart3.setPosition(windowWIDTH - heart3.getGlobalBounds().width * 3.5, heart3.getGlobalBounds().height / 2);

}

void Health::Increase()
{
	if(life>0)
	--life;
}

void Health::Decrease()
{
	if(life < 3)
	++life;
}

void Health::Draw()
{
	switch (this->life)
	{
	case 1: 
		data->window.draw(heart1);
		break;
	case 2:
		data->window.draw(heart1);
		data->window.draw(heart2);
		break;
	case 3:
		data->window.draw(heart1);
		data->window.draw(heart2);
		data->window.draw(heart3);
		break;
	}


}

int Health::GetLife()
{
	return life;
}

