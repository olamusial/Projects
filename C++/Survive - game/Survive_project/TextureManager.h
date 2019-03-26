#pragma once
#include <map>
#include <SFML\Graphics.hpp>
#include "NotLoadFromFileException.h"
#include "WrongKeyException.h"

class TextureManager
{
public:
	TextureManager() { };
	~TextureManager() {	};

	void LoadTexture(std::string file_name, std::string name);	//name - nazwa przydzielona dla tekstury; file_name - nazwa pliku z tekstura
	sf::Texture &GetTexture(std::string name);	//zwraca teksture o nazwie name

	void LoadFont(std::string file_name, std::string name);	//name - nazwa przydzielona dla czcionki; file_name - nazwa pliku z czcionka
	sf::Font &GetFont(std::string name);	//zwraca czcionke

	void DeleteTex();			//czysci zawartosc mapy


private:
	std::map<std::string, sf::Texture> textures; //mapa z nazwa tekstury i tekstura
	std::map<std::string, sf::Font> fonts;	//mapa z nazw¹ czcionki i czcionka
};
