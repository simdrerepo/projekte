#include <iostream>
#include <vector>
#include <string>

using namespace std; // macht std:: ... überflüssig (siehe forschleife)

int addTwoInteger(int a, int b){
    return a+b;

}
int main()
{
    vector<string> msg {"Hello", "C++", "World", "from", "VS Code", "and the C++ extension!"};

    int ersteZahl {3}; // Statement
    int zweiteZahl {4};

    cout << ersteZahl << " + " << zweiteZahl << " = " << addTwoInteger(ersteZahl,zweiteZahl) << endl;

    for (const string& word : msg)
    {
        cout << word << " ";
    }
    cout << endl;   // fügt /n (newline) character hinzu
}