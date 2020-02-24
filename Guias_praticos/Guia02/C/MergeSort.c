// MergeSort.cpp : Defines the entry point for the console application.
//
#include "stdafx.h"
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#define numListas 2

void merge(int lista[], int esq, int m, int drt)
{
	int i, j, k;
	int n1 = m - esq + 1;
	int n2 = drt - m;

	/* Arrays temporárias */	
	int *L = n1;
	int *R = n2;
	L = (int*) malloc(sizeof(int) * n1);
	R = (int*) malloc(sizeof(int) * n2);
	
	for (i = 0; i < n1; i++)
		L[i] = lista[esq + i];
	for (j = 0; j < n2; j++)
		R[j] = lista[m + 1 + j];

	/* Merge das arrays temporárias com a lista */
	i = 0;
	j = 0;
	k = esq;
	while (i < n1 && j < n2)
	{
		if (L[i] <= R[j])
		{
			lista[k] = L[i];
			i++;
		}
		else
		{
			lista[k] = R[j];
			j++;
		}
		k++;
	}

	while (i < n1)	{
		lista[k] = L[i];
		i++;
		k++;
	}
	while (j < n2)	{
		lista[k] = R[j];
		j++;
		k++;
	}
	free(L);
	free(R);
}

void mergeSort(int lista[], int esq, int drt)
{
	if (esq < drt)
	{
		// O mesmo que (esq+drt)/2, mas evita overflow para números muito
		// grandes em "esq" e "drt"
		int m = esq + (drt - esq) / 2;

		// Sort da primeira e segunda metades
		mergeSort(lista, esq, m);
		mergeSort(lista, m + 1, drt);

		merge(lista, esq, m, drt);
	}
}

void printArray(int lista[], int size)
{
	for (int i = 0; i < size; i++)
		printf("%d ", lista[i]);
}

int main()
{
	int tamanho = 0;
	int iteracoes = 31;
	int incremento = 5000;
	FILE *f;
	f = fopen("C:\\Algoritmos\\C\\MergeSort.txt", "w+");
	srand(time(NULL));

	for (int j = 0; j < iteracoes; j++) {
		int ciclo = 1;
		int num;
		/* Variáveis usadadas no cálculo da média e desvio padrão */
		float soma = 0;
		float variancia;
		float somaVar = 0;
		float somaTempos = 0;
		float tempos[numListas];

		printf("\nITERACAO n.%d", j + 1);

		for (int k = 0; k < numListas; k++) {
			int *lista;
			lista = malloc(sizeof(int) * tamanho);
			for (int i = 0; i < tamanho; i++) {
				num = rand() % 1000;
				lista[i] = num;
			}
			
			printf("\n\nLista nao-organizada de tamanho %d ::\n", tamanho);
			printArray(lista, tamanho);
			
			printf("\n");
			clock_t inicio = clock();
			mergeSort(lista, 0, tamanho - 1);
			clock_t fim = clock();
			printf("Lista n.%d de tamanho %d ::", ciclo, tamanho);
			
			printf("\n");
			printArray(lista, tamanho);
			printf("\n");
			
			float tempo = ((float)(fim - inicio) / CLOCKS_PER_SEC);
			somaTempos += tempo;
			tempos[k] = tempo;
			printf(" %f s", tempo);
			ciclo++;
			free(lista);
		}
		float media = somaTempos / (float)numListas;
		for (int i = 0; i < numListas; i++)		{
			somaVar = somaVar + pow((tempos[i] - media), 2);
		}
		variancia = somaVar / (float)numListas;
		float desvioP = sqrt(variancia);

		printf("\n\nMedia para %d listas de %d elementos :: %f s\n", numListas, tamanho, media);
		printf("Desvio para %d listas de %d elementos :: %f s\n", numListas, tamanho, desvioP);
		fprintf(f, "%d %f %f\n", tamanho, media, desvioP);
		tamanho += incremento;
	}
	fclose(f);
	printf("\nPrograma terminado. Pressione qualquer tecla para fechar a janela.\n");
	getchar();
	return 0;
}
