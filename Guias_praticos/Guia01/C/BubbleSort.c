// BubbleSort.cpp : Defines the entry point for the console application.
//
// C program for implementation of Bubble sort
#include "stdafx.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#define numListas 50

void swap(int *xp, int *yp)
{
	int temp = *xp;
	*xp = *yp;
	*yp = temp;
}

void bubbleSort(int arr[], int n)
{
	int i, j;
	for (i = 0; i < n - 1; i++)
   
		for (j = 0; j < n - i - 1; j++)
			if (arr[j] > arr[j + 1])
				swap(&arr[j], &arr[j + 1]);
}

float calculoDesvio(float lista[], int tamanho, float media)
{
	int i;
	float desvioPadrao = 0.0;

	for (i = 0; i < tamanho; ++i)
		desvioPadrao += pow(lista[i] - media, 2);

	return sqrt(desvioPadrao / tamanho);
}

void printArray(int arr[], int size)
{
	for (int i = 0; i < size; i++)
		printf("%d ", arr[i]);
}

int main()
{
	int tamanho = 0;
	int iteracoes = 31;
	int incremento = 150;
	FILE *f;
	errno_t err;
	if ((err = fopen_s(&f, "C:\\Algoritmos\\C\\BubbleSortC.txt", "w+")) != 0)
	{
		printf("File was not opened\n");
	}
	else
	{
		for (int j = 0; j < iteracoes; j++) {
			int ciclo = 1;
			int num;
			/* Variáveis usadadas no cálculo da média e desvio padrão */
			float soma = 0;
			float variancia;
			float dif;
			float somaVar = 0;
			float somaTempos = 0;
			float tempos[numListas];

			printf("\nITERACAO n.%d", j + 1);

			for (int k = 0; k < numListas; k++) {

				srand(time(NULL));
				int *lista;
				lista = malloc(sizeof(int) * tamanho);
				//int lista[tamanho];
				for (int i = 0; i < tamanho; i++) {
					num = rand() % 1000;
					lista[i] = num;
				}

				//printf("Lista nao-organizada de tamanho %d ::\n", tamanho);
				//printArray(lista, tamanho);
				printf("\n");
				clock_t inicio = clock();
				bubbleSort(lista, tamanho);
				clock_t fim = clock();

				printf("Lista n.%d de tamanho %d ::", ciclo, tamanho);
				//printArray(lista, tamanho);

				float tempo = ((float)(fim - inicio) / CLOCKS_PER_SEC);
				somaTempos += tempo;
				tempos[k] = tempo;
				printf(" %f s", tempo);
				ciclo++;
			}
			float media = somaTempos / (float)numListas;

			for (int i = 0; i < numListas; i++)
			{
				somaVar = somaVar + pow((tempos[i] - media), 2);
			}

			variancia = somaVar / (float)numListas;
			float desvioP = sqrt(variancia);

			printf("\nMedia para %d listas de %d elementos :: %f s\n", numListas, tamanho, media);
			printf("Desvio para %d listas de %d elementos :: %f s\n", numListas, tamanho, desvioP);
			fprintf(f, "%d %f %f\n", tamanho, media, desvioP);
			tamanho += incremento;
		}
	}
	fclose(f);
	printf("\nPrograma terminado. Pressione qualquer tecla para fechar a janela.\n");
	getchar();
	return 0;
}
