// Radix sort implementação JAVA

/*RadixSort(LSD)
Considerado rapido e estavel, porem com auto consumo de memoria.

OBS: 1 é expoente, 10 é multiplicado pelo expoente, a partir da segunda iteração ambos crescem em uma proporção multiplicada por 10

1: Identificar o maior elemento do vetor
2: Identificar o digito menos significativo do elemento
3: Divide o digito menos significativo por 10, o resultado é divido por 1


 */

import java.io.*;
import java.util.*;

public class RadixSort {


    //Uma função de utilidade para obter o valor máximo em arr []
    static int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx)
                mx = arr[i];
        return mx;
    }

    /*
        Uma função para fazer a contagem de arr [] de acordo com o dígito representado por exp.
     */
    static void countSort(int arr[], int n, int exp) {
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);

        //Armazenar contagem de ocorrências em count []
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;

            /* Contagem de alterações [i] para que a contagem [i] agora
             contenha a posição real desse dígito na saída []
             */
        for (i = 1; i < 10; i++)
            count[i] += count[i - 1];

        // Construa o array de saída
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

            /*
                Copie o array de saída para arr [],
                de modo que arr [] agora contenha números classificados de acordo com o dígito atual
             */
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    // A principal função para esse tipo arr [] de tamanho n usando Radix Sort
    static void radixsort(int arr[], int n) {
        //Encontre o número máximo para saber o número de dígitos
        int m = getMax(arr, n);

            /*
                Faça a contagem para cada dígito. Note que ao invés
                de passar o número do dígito, exp é passado. exp é 10 ^ i
                onde eu é o número do dígito atual
            */
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    // Uma função de utilidade para imprimir um Array
    static void print(int arr[], int n) {
        for (int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }


    /*Função principal do sistema*/
    public static void main(String[] args) {
        int arr[];
        int n;

        Scanner s = new Scanner(System.in);
        Scanner valorInformado = new Scanner(System.in);
        Scanner tamanhoArray = new Scanner(System.in);

        System.out.println("########### Escolha um a opção ###########");
        System.out.println("+       1: Informar valores do Array    +");
        System.out.println("+       2: Usar Array pré-definido      +");
        System.out.println("########### Escolha um a opção ###########");
        System.out.println();

        int op = s.nextInt();

        if (op == 1) {
            System.out.println("Informe o tamanho do array: ");
            n = tamanhoArray.nextInt();


            System.out.println("Informe os valores do Array");
            arr = new int[n];
            for (int i = 0; i < n ; i++) {
                arr[i] = valorInformado.nextInt();
            }

            System.out.println("Array desordenado: ");
            print(arr, n);

            System.out.println();
            System.out.println("Array ordenado: ");
            radixsort(arr, n);
            print(arr, n);
        } else {
            if (op == 2) {
                int arr2[] = {170, 45, 75, 90, 802, 24, 2, 66};
                int n2 = arr2.length;

                System.out.println("Array desordenado: ");
                print(arr2, n2);

                System.out.println();
                System.out.println("Array Ordenado: ");
                radixsort(arr2, n2);
                print(arr2, n2);
            }
        }

    }

}
