#include <stdio.h>
#include <stdlib.h>
int d[8], dr[8];

int main()
{
    printf("Enter 4 bits one by one: ");
    scanf("%d", &d[0]);
    scanf("%d", &d[1]);
    scanf("%d", &d[2]);
    scanf("%d", &d[4]);

    d[6] = d[4] ^ d[2] ^ d[0];
    d[5] = d[4] ^ d[1] ^ d[0];
    d[3] = d[2] ^ d[1] ^ d[0];

    printf("the modified data is: ");
    for (int i = 0; i < 7; i++)
        printf("%d", d[i]);

    printf("Enter received data one by one: ");
    for (int i = 0; i < 7; i++)
        scanf("%d", &dr[i]);

    int c1, c2, c3;
    c1 = dr[6] ^ dr[2] ^ dr[4] ^ dr[0];
    c2 = dr[5] ^ dr[1] ^ dr[4] ^ dr[0];
    c3 = dr[3] ^ dr[1] ^ dr[2] ^ dr[0];
    int c = 4 * c3 + 2 * c2 + c1;

    if (c == 0)
        printf("No error");
    else
    {
        printf("Error: ");
        dr[7 - c] = !dr[7 - c];
    }
    printf("the recieved data is: ");
    for (int i = 0; i < 7; i++)
        printf("%d", dr[i]);
    return 0;
}