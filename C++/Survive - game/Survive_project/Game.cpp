#include "stdafx.h"
#include "Game.h"
#include "MenuState.h"

Game::Game(int width, int height, std::string window_title)
{
	data->window.create(sf::VideoMode(width, height), window_title, sf::Style::Close | sf::Style::Titlebar);

	data->machine.AddState(stateReference(new MenuState(this->data)));

	this->Run();
}

void Game::Run()
{
	while (this->data->window.isOpen())
	{
		this->data->machine.ChangeStates();


		this->data->machine.GetActualStatee()->HandleInput();
		this->data->machine.GetActualStatee()->Update();

		this->data->machine.GetActualStatee()->Draw();
	}

	if (!this->data->window.isOpen())
	{
		this->data->machine.EmptyState();
		this->data->textures.DeleteTex();
	}

}

