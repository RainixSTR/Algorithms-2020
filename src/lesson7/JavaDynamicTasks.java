package lesson7;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class JavaDynamicTasks {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    //Асимптотика - O(N^2)
    //Ресурсоемкость - O(lengthFirst * lengthSecond),
    public static String longestCommonSubSequence(String first, String second) {
        int lengthFirst = first.length();
        int lengthSecond = second.length();
        int[][] matrix = new int[lengthFirst + 1][lengthSecond + 1];

        for (int i = lengthFirst - 1; i >= 0; i--) {
            for (int j = lengthSecond - 1; j >= 0; j--) {
                if (first.charAt(i) == second.charAt(j))
                    matrix[i][j] = matrix[i + 1][j + 1] + 1;
                else
                    matrix[i][j] = Math.max(matrix[i + 1][j], matrix[i][j + 1]);
            }
        }

        StringBuilder subsequence = new StringBuilder();
        int i = 0;
        int j = 0;

        while (i < lengthFirst && j < lengthSecond) {
            if (first.charAt(i) == second.charAt(j)) {
                subsequence.append(first.charAt(i));
                i++;
                j++;
            } else if (matrix[i + 1][j] >= matrix[i][j + 1]) i++;
            else j++;
        }
        return subsequence.toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */

    private static int findLowerBound(int[] a, int b) {
        int l = 0;
        int r = a.length - 1;
        while (r - l > 1) {
            int m = (l + r) / 2;
            if (b < a[m])
                l = m;
            else
                r = m;
        }
        return r;
    }

    // Асимптотика O(N*logN)
    // Ресурсоемкость O(N)
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {

        int listSize = list.size();

        int[] d = new int[listSize + 1];
        int[] pos = new int[listSize + 1];
        int[] prev = new int[listSize];
        int length = 0;

        d[0] = Integer.MAX_VALUE;
        pos[0] = -1;

        for (int i = listSize - 1; i >= 0; i--) {
            int j = findLowerBound(d, list.get(i));
            if (d[j] < list.get(i) && d[j - 1] > list.get(i)) {
                d[j] = list.get(i);
                pos[j] = i;
                prev[i] = pos[j - 1];
                length = Math.max(length, j);
            }
        }

        if (length == 1) return List.of(list.get(0));

        ArrayList<Integer> sequence = new ArrayList<>();
        int p = pos[length];
        while (p != -1) {
            sequence.add(list.get(p));
            p = prev[p];
        }
        return sequence;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
