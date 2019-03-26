#pragma once
#include <iostream>

class WrongKeyException : std::exception
{

public:

	char const* what() const
	{
		return "The element does not exist in map or the key is wrong \n";
	};

};