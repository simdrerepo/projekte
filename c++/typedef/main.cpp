#include <iostream>


int main(int argc, char const *argv[])
{
    typedef std::string text_t;

    text_t text = "Das hier ist ein String mit einem neuen Typ text_t, definiert mit typedef";

    std::cout << text << std::endl;
    return 0;
}
