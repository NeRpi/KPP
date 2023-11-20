import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		System.out.println("Привет, введи свое имя");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.nextLine();
		System.out.println("Привет, " + name);
		System.out.println("Сколько цифр вы хотите ввести?");
		// Получаем число цифр которых хочет проверить пользователь 
		int n = scanner.nextInt();
		// Вызываем цикл с количеством n иттераций
		for (int i = 0; i < n; i++) {
			// Получаем число для проверки
			int number = scanner.nextInt();
			// Если функция isPrime(number) возвращает True, выводим 'просток', иначе 'непростое'
			System.out.println("Число " + number + (isPrime(number) ? " простое" : " непростое"));
		}
	}

	// Функция для проверки простое ли число
	static boolean isPrime(int n) {
		// Если число меньше 1, то возвращаем False, так как они не простые 
		if (n <= 1)
			return false;
		// Далее проверяем по циклу, делится ли наще число на числа в диапазоне [2; sqrt(n)]
		// sqrt() - возвращает квадратный корень нашего числа
		// n % i - возвращает отстаток от деления n на i
		// Если находтся i на которое n делется без отстка, то есть n % i == 0, возвращаем False
		for (int i = 2; i <= Math.sqrt(n); i++)
			if (n % i == 0)
				return false;
		
		// Если в цикле мы ниразу не нашли i при котором n делется без остатка, то возвращаем True
		return true;
	}
}