#include <stdio.h>


int main(void)
{
    //char counting program
    double nc;
    int c = getchar();
    int counter =0;
   while(c!=EOF){
    counter++;
    printf("%d\n",counter);
    c=getchar();
   }

   printf("%d\n",counter);
   
   
}