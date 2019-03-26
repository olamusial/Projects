#pragma once
#include <memory>
#include "State.h"
#include <vector>
#include <iostream>

typedef std::unique_ptr<State> stateReference;		//wkaznik inteligenty na dany stan

class StateMachine
{
public:
	StateMachine() { }
	~StateMachine() { states.clear(); }
	void AddState(stateReference &newState, bool isReplacing = true);
	void ChangeStates();
	State *GetActualStatee();
	void EmptyState();

private:
	stateReference newState;
	std::vector<stateReference> states;  
	State * currentState;

	bool isAdding;				//czy dodajemy stan
	bool isReplacing;			//czy podmieniamy stan
};
