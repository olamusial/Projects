#include "stdafx.h"
#include "TextureManager.h"

void TextureManager::LoadTexture(std::string file_name, std::string name)
{
	sf::Texture texture;

	if (texture.loadFromFile(file_name))
	{
		this->textures[name] = texture;
	}
	else
	{
		throw NotLoadFromFileException();
	}
}

sf::Texture & TextureManager::GetTexture(std::string name)
{
	if (textures.count(name) > 0)
	{
		return this->textures.at(name);
	}
	else
	{
		throw WrongKeyException();
	}

}

void TextureManager::LoadFont(std::string file_name, std::string name)
{
	sf::Font font;

	if (font.loadFromFile(file_name))
	{
		this->fonts[name] = font;
	}
	else
	{
		throw NotLoadFromFileException();
	}
}

sf::Font & TextureManager::GetFont(std::string name)
{
	if (fonts.count(name) > 0)
	{
		return this->fonts.at(name);
	}
	else
	{
		throw WrongKeyException();
	}
}


void TextureManager::DeleteTex()
{
	if (!this->textures.empty())
		textures.clear();
}


