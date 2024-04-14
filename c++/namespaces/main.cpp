#include <iostream>

namespace ns{
    /* Namespaces definiert man außerhalb von main */
    int x = 12;
}
int main(int argc, char const *argv[])
{
   int x = 0;

   std::cout << x << std::endl;
    /* Namespaces nutzt man mit dem :: Operator, oder auch mit using namespace ... oder using std::cout; z.B. ; der Prefix ist dann nicht mehr notwendig*/
   std::cout << ns::x << std::endl; 

   using std::cout;
   cout << "hello" << '\n';

   using namespace std;

   cout << "hUhU" << endl;
    return 0;
}
