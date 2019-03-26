#pragma once
#include <memory>
#include <string>
#include <SFML\Graphics.hpp>
#include "StateMachine.h"
#include "TextureManager.h"
#include "InputManager.h"


struct GameData			//dostepna dla wielu klas, zebranie potrzebnych informacji i "akcji"
{
	StateMachine machine;						//maszyna stanow do zarzadzania stanami w roznych klasach
	sf::RenderWindow window;					//przekazywanie okna na ktorym ma sie cos dziac
	TextureManager textures;					//operacje na teksturach - zapisywanie i wczytywanie
	InputManager input;							//zarzadzanie danymi z myszki
};

typedef std::shared_ptr <GameData> GameDataReference;			//wskaznik wspoldzielony na obiekty typu GameData (jest wspoldzielony aby w roznych klasach operowac na tych samych danych)

class Game
{
public:
	Game(int width, int height, std::string window_title);				//argumenty konstruktora: szerokosc, wysokosc i tytul okna gry

private:
	GameDataReference data = std::make_shared<GameData>(); 			//data - wskaznik do odwolywania sie do potrzebnych skladowych struktury GameData 

	const float frameRate = 1.0f / 60.0f;							//odswiezanie 60 razy na sekunde
	

	void Run();
};