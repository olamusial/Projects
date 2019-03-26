#include "stdafx.h"
#include "GameState.h"
#include "GameOverState.h"

GameState::GameState(GameDataReference data) :data(data)
{
}


GameState::~GameState()
{
	bullets.clear();
	enemies.clear();
}

void GameState::Init()
{
	data->textures.DeleteTex();

	try
	{
	data->textures.LoadTexture(GAME_FILEPATH, "Game background");
	data->textures.LoadTexture(SHIP_FILEPATH, "Ship texture");
	data->textures.LoadTexture(ENEMY_TEXTURE, "Enemy texture");
	data->textures.LoadTexture(HEART_TEXTURE, "Heart texture");
	data->textures.LoadTexture(BONUS_FILEPATH, "Bonus texture");

	data->textures.LoadFont(FONT, "Score font");
	}
	catch (NotLoadFromFileException ex)
	{
		std::cout << ex.what() << std::endl;
	}


	data->window.setFramerateLimit(60);

	ship = std::make_unique<Ship>(Ship(data));
	health = std::make_unique<Health>(Health(data));
	score = std::make_unique<Score>(Score(data));
	enemyFactory = std::make_unique<EnemyFactory>(EnemyFactory(data));
	bonusFactory = std::make_unique<BonusFactory>(BonusFactory(data));
	sea = std::make_unique<Sea>(Sea(data));

	startTime = clock.getElapsedTime().asSeconds();		//wlaczenie zegara 
	generateStartTime = clock_duration.getElapsedTime().asSeconds();
	bonusStartTime = clock_bonus.getElapsedTime().asSeconds();
	
}

void GameState::HandleInput()
{
	sf::Event event;


	if (data->window.pollEvent(event))
	{
		if (sf::Event::Closed == event.type)
		{
			data->window.close();
			bullets.clear();
			enemies.clear();
		}

		if (data->input.isReleased(event, sf::Keyboard::Space))
		{
			this->bullets.push_back(Bullet(ship->getPosition(), data));
		}

	}

}

void GameState::Update()
{
	ship->update();
	sea->Move();


	std::vector<Bullet>::iterator itB;
	std::vector<Enemy>::iterator itE;
	std::vector<Bonus>::iterator itBon;

	newTime = clock.getElapsedTime().asSeconds();			//zapisanie czasu ktory uplynal od wlaczenia zegara
	generateEndTime = clock_duration.getElapsedTime().asSeconds();
	bonusEndTime = clock_bonus.getElapsedTime().asSeconds();

	if ((newTime - startTime) > generate_enemy)						//sprawdzenie czy minal odstep czeu wiekszy niz okreslony
	{
		enemies.push_back(enemyFactory->createEnemy());		//jesli minal odpowiedni czas to generowany jest nowy przeciwnik				
		clock.restart();									//zrestartowanie zegara aby odmierzyc kolejny okres czasu do wygenerowania kolejnego wroga
		startTime = clock.getElapsedTime().asSeconds();		//zapisanie nowego wyzerowanego czasu 
	}

	if (generate_enemy > maxFrequency && (generateEndTime - generateStartTime) > duration)
	{
		generate_enemy -= 0.05;
		clock_duration.restart();
		generateStartTime = clock_duration.getElapsedTime().asSeconds();
	}

	if ((bonusEndTime - bonusStartTime) > generate_bonus)
	{
		bonus.push_back(bonusFactory->createBonus());					//generowanie bonusu po okreslonym czasie
		clock_bonus.restart();
		bonusStartTime = clock_bonus.getElapsedTime().asSeconds();
	}

	for (itB = bullets.begin(); itB != bullets.end();)
	{
		(*itB).update();

		if ((*itB).top() < 0)			//usuwanie pociskow po wykryciu "wyjscia" za okno gry
		{
			itB = bullets.erase(itB);
			break;
		}
		else
			++itB;
	}

	for (itE = enemies.begin(); itE != enemies.end();)
	{
		(*itE).update();

		if ((*itE).bottom() > windowHEIGHT)				//wykrycie czy wrog "wyszedl" poza okno
		{
			itE = enemies.erase(itE);
			break;
		}
		else
			++itE;
	}

	for (itBon = bonus.begin(); itBon != bonus.end();)
	{
		(*itBon).update();

		if ((*itBon).bottom() > windowHEIGHT)				//wykrycie czy bonus "wyszedl" poza okno
		{
			itBon = bonus.erase(itBon);
			break;
		}
		else
			++itBon;
	}

	for (itE = enemies.begin(); itE != enemies.end(); )
	{
		for (itB = bullets.begin(); itB != bullets.end();)
		{
			if (isIntersecting((*itB), (*itE)))			//wykrycie kolizji - pocisk, wrog (uzycie funkcji szablonowej)
			{
				itB = bullets.erase(itB);
				itE = enemies.erase(itE);
				score->increaseScore();
				break;
			}
			else
				if (itB != bullets.end())
					++itB;
		}
		if (itE != enemies.end())
			++itE;
	}

	for (itE = enemies.begin(); itE != enemies.end();)
	{
		if (isIntersecting((*ship), (*itE)))				//wykrycie kolizji gracz - wrog
		{
			itE = enemies.erase(itE);
			health->Increase();
			break;
		}
		else
			++itE;
	}

	for (itBon = bonus.begin(); itBon != bonus.end();)
	{
		if (isIntersecting((*ship), (*itBon)))				//wykrycie kolizji gracz - wrog
		{
			itBon = bonus.erase(itBon);
			score->increaseScoreWithBonus();
			break;
		}
		else
			++itBon;
	}

	if (health->GetLife() == 0)
	{
		data->machine.AddState(stateReference(new GameOverState(this->data, score->getScore())));		//przejscie do stanu konca gry			
	}
}


void GameState::Draw()
{
	data->window.clear();
	sea->Draw();
	ship->draw();
	health->Draw();
	score->draw();

	for (int i = 0; i < bullets.size(); i++)
		bullets[i].draw();

	for (int i = 0; i < enemies.size(); i++)
		enemies[i].draw();

	for (int i = 0; i < bonus.size(); i++)
		bonus[i].draw();

	data->window.display();
}
