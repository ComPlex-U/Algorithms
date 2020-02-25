 #include "stdafx.h"
#include <time.h>

long fibIT(int n) {
	long a = 0, b = 1, c, i;
	if (n == 0)
		return a;
	for (i = 2; i <= n; i++) {
		c = a + b;
		a = b;
		b = c;
	}
	return b;
}

long fibREC(int n) {
	if (n <= 1)
		return n;
	return fibREC(n - 1) + fibREC(n - 2);
}

void main(){
	int n = 40;
	int ciclo = 1;
	long fib;
	FILE *f;
	errno_t err;
	if ((err = fopen_s(&f, "C:\\Algoritmos\\C\\Fibonacci_Recursive.txt", "w+")) != 0)	{
		printf("File was not opened\n");
	}
	else	{
		for (int i = 0; i < n; i++) {

			clock_t inicio = clock();
			fib = fibREC(i);
			clock_t fim = clock();

			float tempo = ((float)(fim - inicio) / CLOCKS_PER_SEC);
			fprintf(f, "%d %f\n", ciclo, tempo);
			printf("%d :: %ld :: %f \n", ciclo, fibREC(i), tempo);
			ciclo++;
		}
		printf("\nTerminado!");
	}
	fclose(f);

	if ((err = fopen_s(&f, "C:\\Algoritmos\\C\\Fibonacci_Iterative.txt", "w+")) != 0) {
		printf("File was not opened\n");
	}
	else {
		for (int i = 0; i < n; i++) {

			clock_t inicio = clock();
			fib = fibIT(i);
			clock_t fim = clock();

			float tempo = ((float)(fim - inicio) / CLOCKS_PER_SEC);
			fprintf(f, "%d %f\n", ciclo, tempo);
			printf("%d :: %ld :: %f \n", ciclo, fibREC(i), tempo);
			ciclo++;
		}
		printf("\nTerminado!");
	}
	fclose(f);
	getchar();
}