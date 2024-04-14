#include <stdio.h>

int main(int argc, char const *argv[])
{
    int B[3][3]; // B[0], B[1] = 1-D Arrays of 3 integers

    int (*p)[3] = B; // -> pointer to 1-D array with 3 integers

    // print B or &B[0] -> address of first integer 
    // print *B or B[0] or &B[0][0] // address of first integer
    // print B+1 or &B[1] -> address of first integer + size of 1-D array of 3 integers

    // B[i][j] = *(B[i]+j) = *(*(B+i)+j) 
    return 0;
}
 