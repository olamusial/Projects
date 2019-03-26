#pragma once

class State
{
public:
	
	virtual ~State() = default;
	virtual void Init() = 0;			//inicjalizacja 
	virtual void HandleInput() = 0;		//uchwyt wszelkich danych wejsciowych
	virtual void Update() = 0;			//aktualizacja na podstawie np. przycisnietego klawisza przez gracza
	virtual void Draw() = 0;		//data - ró¿nica czasowa pomiêdzy klatkami; metoda rysujaca  

	virtual void Pause() { }
	virtual void Resume() { }
};