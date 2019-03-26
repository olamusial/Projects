#pragma once
#include <iostream>

class NotLoadFromFileException : std::exception
{

public:

	char const* what() const
	{
		return "Loading from file failed \n";
	};

};