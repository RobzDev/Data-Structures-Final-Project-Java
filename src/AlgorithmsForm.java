import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class AlgorithmsForm extends JFrame {
    private int[] numbers;
    private JComboBox<String> cmbAlgorithms;
    private JList<String> lstNumbersSort;
    private DefaultListModel<String> listModel;

    public AlgorithmsForm() {
        initializeComponents();
        numbers = new int[]{};
    }

    private void initializeComponents() {
        setTitle("Sorting Algorithms");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        cmbAlgorithms = new JComboBox<>(new String[]{
                "Gnome Sort", "Heap Sort", "Pigeonhole Sort", "Bubble Sort",
                "Cocktail Sort", "Insertion Sort", "Shell Sort", "Selection Sort",
                "Quick Sort", "Merge Sort", "Direct Merge", "Natural Merge",
                "Comb Sort", "Counting Sort", "Bucket Sort"
        });
        cmbAlgorithms.setBounds(20, 20, 150, 25);
        cmbAlgorithms.setSelectedIndex(0);

        JButton btnSetNumbers = new JButton("Generate Numbers");
        btnSetNumbers.setBounds(200, 20, 150, 25);
        btnSetNumbers.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setNumbers();
            }
        });

        JButton btnSort = new JButton("Sort");
        btnSort.setBounds(20, 60, 150, 25);
        btnSort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortNumbers();
            }
        });

        listModel = new DefaultListModel<>();
        lstNumbersSort = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(lstNumbersSort);
        scrollPane.setBounds(20, 100, 350, 200);

        add(cmbAlgorithms);
        add(btnSetNumbers);
        add(btnSort);
        add(scrollPane);
    }

    private void setNumbers() {
        Random random = new Random();
        numbers = random.ints(10, 1, 100).toArray();
        displayNumbers(numbers);
    }

    private void sortNumbers() {
        if (numbers == null || numbers.length == 0) {
            JOptionPane.showMessageDialog(this, "Please generate numbers first.", "Warning", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String selectedMethod = (String) cmbAlgorithms.getSelectedItem();
        switch (selectedMethod) {
            case "Bubble Sort":
                BubbleSort.sort(numbers, this::displayNumbers);
                break;
            case "Gnome Sort":
                GnomeSort.sort(numbers, this::displayNumbers);
                break;
            case "Comb Sort":
                CombSort.sort(numbers, this::displayNumbers);
                break;
            case "Cocktail Sort":
                CocktailSort.sort(numbers, this::displayNumbers);
                break;
            case "Heap Sort":
                HeapSort.sort(numbers, this::displayNumbers);
                break;
            case "Insertion Sort":
                InsertionSort.sort(numbers, this::displayNumbers);
                break;
            case "Shell Sort":
                ShellSort.sort(numbers, this::displayNumbers);
                break;
            case "Selection Sort":
                SelectionSort.sort(numbers, this::displayNumbers);
                break;
            case "Counting Sort":
                CountingSort.sort(numbers, this::displayNumbers);
                break;
            case "Bucket Sort":
                //BucketSort.sort(numbers, this::displayNumbers);
                break;
            case "Merge Sort":
                MergeAlgorithms.mergeSort(numbers, 0, numbers.length - 1, this::displayNumbers);
                break;
            case "Natural Merge":
                MergeAlgorithms.naturalMerge(numbers, this::displayNumbers);
                break;
            case "Direct Merge":
                MergeAlgorithms.directMerge(numbers, this::displayNumbers);
                break;
            case "Quick Sort":
                QuickSort.sort(numbers, 0, numbers.length - 1, this::displayNumbers);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid sorting method.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
        }

        JOptionPane.showMessageDialog(this, "Sorting completed!", selectedMethod, JOptionPane.INFORMATION_MESSAGE);
    }

    private void displayNumbers(int[] array) {
        listModel.clear();
        for (int number : array) {
            listModel.addElement(String.valueOf(number));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AlgorithmsForm form = new AlgorithmsForm();
            form.setVisible(true);
        });
    }

    class BubbleSort {
        public static void sort(int[] array, java.util.function.Consumer<int[]> display) {
            int n = array.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (array[j] > array[j + 1]) {
                        // swap array[j] and array[j+1]
                        int temp = array[j];
                        array[j] = array[j + 1];
                        array[j + 1] = temp;
                    }
                }
                display.accept(array);
            }
        }
    }

    class GnomeSort {
        public static void sort(int[] array, java.util.function.Consumer<int[]> display) {
            int index = 0;
            while (index < array.length) {
                if (index == 0) {
                    index++;
                }
                if (array[index] >= array[index - 1]) {
                    index++;
                } else {
                    int temp = array[index];
                    array[index] = array[index - 1];
                    array[index - 1] = temp;
                    index--;
                }
                display.accept(array);
            }
        }
    }

    class CombSort {
        public static void sort(int[] array, java.util.function.Consumer<int[]> display) {
            int gap = array.length;
            boolean swapped = true;
            while (gap != 1 || swapped) {
                gap = (int) (gap / 1.3);
                if (gap < 1) {
                    gap = 1;
                }
                swapped = false;
                for (int i = 0; i < array.length - gap; i++) {
                    if (array[i] > array[i + gap]) {
                        int temp = array[i];
                        array[i] = array[i + gap];
                        array[i + gap] = temp;
                        swapped = true;
                    }
                }
                display.accept(array);
            }
        }
    }

    class CocktailSort {
        public static void sort(int[] array, java.util.function.Consumer<int[]> display) {
            boolean swapped = true;
            int start = 0;
            int end = array.length - 1;
            while (swapped) {
                swapped = false;
                for (int i = start; i < end; i++) {
                    if (array[i] > array[i + 1]) {
                        int temp = array[i];
                        array[i] = array[i + 1];
                        array[i + 1] = temp;
                        swapped = true;
                    }
                }
                if (!swapped) break;
                end--;
                swapped = false;
                for (int i = end; i > start; i--) {
                    if (array[i] < array[i - 1]) {
                        int temp = array[i];
                        array[i] = array[i - 1];
                        array[i - 1] = temp;
                        swapped = true;
                    }
                }
                start++;
                display.accept(array);
            }
        }
    }

    class HeapSort {
        public static void sort(int[] array, java.util.function.Consumer<int[]> display) {
            int n = array.length;
            for (int i = n / 2 - 1; i >= 0; i--) {
                heapify(array, n, i, display);
            }
            for (int i = n - 1; i > 0; i--) {
                int temp = array[0];
                array[0] = array[i];
                array[i] = temp;
                heapify(array, i, 0, display);
            }
        }

        private static void heapify(int[] array, int n, int i, java.util.function.Consumer<int[]> display) {
            int largest = i;
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            if (left < n && array[left] > array[largest]) {
                largest = left;
            }
            if (right < n && array[right] > array[largest]) {
                largest = right;
            }
            if (largest != i) {
                int swap = array[i];
                array[i] = array[largest];
                array[largest] = swap;
                heapify(array, n, largest, display);
            }
            display.accept(array);
        }
    }

    class InsertionSort {
        public static void sort(int[] array, java.util.function.Consumer<int[]> display) {
            for (int i = 1; i < array.length; i++) {
                int key = array[i];
                int j = i - 1;
                while (j >= 0 && array[j] > key) {
                    array[j + 1] = array[j];
                    j--;
                }
                array[j + 1] = key;
                display.accept(array);
            }
        }
    }

    class ShellSort {
        public static void sort(int[] array, java.util.function.Consumer<int[]> display) {
            int n = array.length;
            for (int gap = n / 2; gap > 0; gap /= 2) {
                for (int i = gap; i < n; i++) {
                    int temp = array[i];
                    int j;
                    for (j = i; j >= gap && array[j - gap] > temp; j -= gap) {
                        array[j] = array[j - gap];
                    }
                    array[j] = temp;
                    display.accept(array);
                }
            }
        }
    }

    class SelectionSort {
        public static void sort(int[] array, java.util.function.Consumer<int[]> display) {
            for (int i = 0; i < array.length - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] < array[minIndex]) {
                        minIndex = j;
                    }
                }
                int temp = array[minIndex];
                array[minIndex] = array[i];
                array[i] = temp;
                display.accept(array);
            }
        }
    }

    class CountingSort {
        public static void sort(int[] array, java.util.function.Consumer<int[]> display) {
            int max = Arrays.stream(array).max().orElse(0);
            int[] count = new int[max + 1];
            for (int num : array) {
                count[num]++;
            }
            int index = 0;
            for (int i = 0; i < count.length; i++) {
                while (count[i] > 0) {
                    array[index++] = i;
                    count[i]--;
                }
            }
            display.accept(array);
        }
    }



    class MergeAlgorithms {
        public static void mergeSort(int[] array, int left, int right, java.util.function.Consumer<int[]> display) {
            if (left < right) {
                int mid = (left + right) / 2;
                mergeSort(array, left, mid, display);
                mergeSort(array, mid + 1, right, display);
                merge(array, left, mid, right, display);
            }
        }

        private static void merge(int[] array, int left, int mid, int right, java.util.function.Consumer<int[]> display) {
            int n1 = mid - left + 1;
            int n2 = right - mid;
            int[] L = new int[n1];
            int[] R = new int[n2];
            System.arraycopy(array, left, L, 0, n1);
            System.arraycopy(array, mid + 1, R, 0, n2);
            int i = 0, j = 0, k = left;
            while (i < n1 && j < n2) {
                if (L[i] <= R[j]) {
                    array[k++] = L[i++];
                } else {
                    array[k++] = R[j++];
                }
            }
            while (i < n1) {
                array[k++] = L[i++];
            }
            while (j < n2) {
                array[k++] = R[j++];
            }
            display.accept(array);
        }

        public static void naturalMerge(int[] array, java.util.function.Consumer<int[]> display) {
            // Implementation of natural merge sort can be added here
        }

        public static void directMerge(int[] array, java.util.function.Consumer<int[]> display) {
            // Implementation of direct merge sort can be added here
        }
    }

    class QuickSort {
        public static void sort(int[] array, int low, int high, java.util.function.Consumer<int[]> display) {

            if (low < high) {
                int pi = partition(array, low, high, display);
                sort(array, low, pi - 1, display);
                sort(array, pi + 1, high, display);
            }
        }

        private static int partition(int[] array, int low, int high, java.util.function.Consumer<int[]> display) {
            int pivot = array[high];
            int i = (low - 1);
            for (int j = low; j < high; j++) {
                if (array[j] < pivot) {
                    i++;
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
            int temp = array[i + 1];
            array[i + 1] = array[high];
            array[high] = temp;
            display.accept(array);
            return i + 1;
        }
    }
}