#pragma once

#include <SFML\Graphics.hpp>

class InputManager
{
public:
	InputManager() { }
	~InputManager() { }

	bool isClicked(sf::Sprite object, sf::Mouse::Button button, sf::RenderWindow &window);	//object - sprite, ktory ma zostac sprawdzony czy zostal klikniety; button - odpowiedni przysik ktory ma byc sprawdzony; window - okno, w ktorym ma "pojawic sie" klikniecie
	bool isPressed(sf::Keyboard::Key key);			//zwraca true jesli podany jako arkument klawisz jest wcisniety
	bool isReleased(sf::Event event, sf::Keyboard::Key key);  //zwraca true jesli podany jakoargument klawisz zostal zwolniony po wczesniejszym nacisnieciu
	sf::Vector2i GetMousePosition(sf::RenderWindow &window);  //zwraca polozenie kursora; window - okno, z ktorego chcemy pobrac polozenie
};
