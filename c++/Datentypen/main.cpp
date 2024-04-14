#include <iostream>


int main(){

    int integer = 15; // Integer
    double gleitkomma = 21.5;  // Gleitkommazahl
    char c = 'A'; // Character
    std::string s = "Das hier ist eine Zeichenkette aka String"; // String

    std::cout << " Integer = "<< integer << std::endl; // '\n' geht auch anstatt std::endl
    std::cout << "Kommazahl = "<< gleitkomma << std::endl;
    std::cout << "Char = "<<c <<std::endl;
    std::cout << "String = "<< s << std::endl;

}