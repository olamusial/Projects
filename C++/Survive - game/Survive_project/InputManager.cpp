#include "stdafx.h"
#include "InputManager.h"

bool InputManager::isClicked(sf::Sprite object, sf::Mouse::Button button, sf::RenderWindow & window)
{
	if (sf::Mouse::isButtonPressed(button))
	{
		sf::IntRect playButton(object.getPosition().x, object.getPosition().y, object.getGlobalBounds().width, object.getGlobalBounds().height);		//IntRect - prostokat, utorzony na podstawie przekazanych parametrow o przycisku ktory chcemy sprawdzic

		if (playButton.contains(sf::Mouse::getPosition(window)))				//jesli stworzony tymczasowy prostokat zawiera przycisk ktory chcemy sprawdzic czy jest klikniety w oknie window
		{																		// to zwracam true
			return true;
		}
	}
	return false;
}


bool InputManager::isPressed(sf::Keyboard::Key key)
{
	if (sf::Keyboard::isKeyPressed(key))
		return true;
	else
		return false;


}

bool InputManager::isReleased(sf::Event event, sf::Keyboard::Key key)
{
	if (event.type == sf::Event::KeyReleased && event.key.code == key)
		return true;
	else
		return false;
}

sf::Vector2i InputManager::GetMousePosition(sf::RenderWindow & window)
{
	return sf::Mouse::getPosition(window);
}