#include <stdio.h>

void main()
{
	int data[15], datar[15], c1, c2, c3, c4, i;
	printf("Enter 7 bits of data one by one\n");
	scanf("%d", &data[0]);
	scanf("%d", &data[1]);
	scanf("%d", &data[2]);
	scanf("%d", &data[4]);
	scanf("%d", &data[5]);
	scanf("%d", &data[6]);
	scanf("%d", &data[8]);
	data[10] = data[0] ^ data[2] ^ data[4] ^ data[6] ^ data[8];
	data[9] = data[0] ^ data[1] ^ data[4] ^ data[5] ^ data[8];
	data[7] = data[4] ^ data[5] ^ data[6];
	data[3] = data[0] ^ data[1] ^ data[2];
	printf("Encoded data is: ");
	for (i = 0; i < 11; i++)
	{
		printf("%d", data[i]);
	}
	printf("\nEnter the data bits received one by one: ");
	for (i = 0; i < 11; i++)
	{
		scanf("%d", &datar[i]);
	}
	c1 = data[0] ^ data[2] ^ data[4] ^ data[6] ^ data[8] ^ data[10];
	c2 = data[0] ^ data[1] ^ data[4] ^ data[5] ^ data[8] ^ data[9];
	c3 = data[4] ^ data[5] ^ data[6] ^ data[7];
	c4 = data[0] ^ data[1] ^ data[2] ^ data[3];
	int c = 8 * c4 + 4 * c3 + 2 * c2 + c1;
	if (c == 0)
	{
		printf("\nNo error while transmission of data\n");
	}
	else
	{
		printf("\nError on position %d", c);

		printf("\nData sent : ");
		for (i = 0; i < 11; i++)
			printf("%d", data[i]);

		printf("\nData received : ");
		for (i = 0; i < 11; i++)
			printf("%d", datar[i]);

		printf("\nCorrect message is : ");
		if (datar[11 - c] == 0)
			datar[11 - c] = 1;
		else
			datar[11 - c] = 0;

		for (i = 0; i < 11; i++)
		{
			printf("%d", datar[i]);
		}
		printf("\n");
	}
}
