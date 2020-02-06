package Main;

import java.io.*;
import java.util.Scanner;
/**
 * Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
 * Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль.
 * Контролируем время выполнения: программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
 */
public class MainDZ {
    static String path = "book.txt";
    static int pageInByte = 1800 * 2; // предполагаем что файл на русском языке и не соедержит ASCII символов
    //а значит, каждый символ шифруется двумя байтами

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        File file = new File(path);
        int allPages = (int) file.length() / pageInByte;
        System.out.println("Для начала чтения нажмите Enter!");
        sc.nextLine();
        readFileToPage(0);
        do {
            System.out.println("Введите желаемую страницу: ");
            System.out.println("* всего страниц в книге " + allPages);
            System.out.println("**для выхода введите что угодно только не Натуральное чило");
            String page = sc.nextLine();
            if (!page.matches("[+]?\\d+")) {
                break;
            } else if (Integer.parseInt(page) <= allPages) {
                readFileToPage(Integer.parseInt(page));
            }
        } while (true);
        sc.close();
    }

    public static void readFileToPage(int page) {
        try (RandomAccessFile raf = new RandomAccessFile(path, "r")) {
            raf.seek(page * pageInByte);
            byte[] arr = new byte[pageInByte];
            raf.read(arr);
            System.out.print(new String(arr, 0, pageInByte, "UTF-8"));
            System.out.println("\n" + "\n");
            System.out.println("Конец страницы: " + page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
