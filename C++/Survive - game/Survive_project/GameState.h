#pragma once
#include "State.h"
#include "Game.h"
#include <SFML\Graphics.hpp>
#include "Definitions.h"

#include "Ship.h"
#include "Bullet.h"
#include "Health.h"
#include "Score.h"
#include "EnemyFactory.h"
#include "BonusFactory.h"
#include "Sea.h"

#include <vector>
#include <iostream>



class GameState : public State
{
public:
	GameState(GameDataReference data);
	~GameState();

	void Init();
	void HandleInput();
	void Update();
	void Draw();

	template <class T1, class T2> bool isIntersecting(T1& A, T2& B);


private:
	GameDataReference data;		//lokalne

	std::unique_ptr<Ship> ship;
	std::unique_ptr<Health> health;
	std::unique_ptr<Score> score;
	std::unique_ptr<EnemyFactory> enemyFactory;
	std::unique_ptr<BonusFactory> bonusFactory;
	std::unique_ptr<Sea> sea;

	std::vector<Enemy> enemies;
	std::vector<Bullet> bullets;
	std::vector<Bonus> bonus;
	
	sf::Clock clock;			//do generowania przeciwnikow co okreslony czas
	sf::Clock clock_duration;	//do zmieniania czestotliwosci generowania przeciwnikow
	sf::Clock clock_bonus;

	float startTime;
	float newTime;
	float generateStartTime;
	float generateEndTime;
	float bonusStartTime;
	float bonusEndTime;

	float generate_enemy = { 0.7f };		//czas odstepu miedzy generowaniem nowego przeciwnika
	float duration = { 15.0f };				//czas do zmiany czestotliwosci generowania przeciwnikow
	float generate_bonus = { 10.0f };		//czas odstepu miedzy generowaniem bonusu punktowego

	float maxFrequency = { 0.4f };

};

template<class T1, class T2>
inline bool GameState::isIntersecting(T1 & A, T2 & B)
{
	return A.right() >= B.left() && A.left() <= B.right()
		&& A.bottom() >= B.top() && A.top() <= B.bottom();
}

