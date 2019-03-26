#include "stdafx.h"
#include "StateMachine.h"

void StateMachine::AddState(stateReference & newState, bool isReplacing)
{
	this->isAdding = true;
	this->isReplacing = isReplacing;
	this->newState = std::unique_ptr<State>(newState.release());
}

void StateMachine::ChangeStates()
{
	if (this->isAdding)
	{
		if (!this->states.empty())
		{
			if (this->isReplacing)
			{

				this->states.erase(this->states.begin());
			}
		}

		this->states.push_back(std::move(this->newState));

		this->states.front()->Init();
		this->isAdding = false;
	}
}

State * StateMachine::GetActualStatee()
{
		return 	(this->states.front()).get();
}

void StateMachine::EmptyState()
{
	if (!states.empty())
	{
		states.clear();
	}
}
