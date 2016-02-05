package rrncheck;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {

	// 주민등록번호 검증기 (- 포함)
	// Resident registration number validator
	static boolean isRRN(String a) {
		if (a.length() != 14)
			return false;
		if (a.charAt(6) != '-')
			return false;
		String b = a.substring(0, 6) + a.substring(7); // - 제외
		int s = 0;
		for (int i = 0; i <= 11; i++) {
			try {
				s += (2 + (i % 8)) * Integer.parseInt("" + b.charAt(i));
			} catch (NumberFormatException e) {
				return false;
			}
		}
		s = (11 - (s % 11)) % 10;
		try {
			return s == Integer.parseInt("" + b.charAt(12));
		} catch (NumberFormatException e) {
			return false;
		}			
	}
	
	static List<String> filterRRN(String a) {
		List<String> r = new ArrayList<String>();
		for (int i = 0; i <= a.length() - 14; i++) {
			String sub = a.substring(i, i + 14);
			if (isRRN(sub)) r.add(sub);
		}
		return r;
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Resident registration number detector");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			System.out.print("Enter text (possibly containing resident registration numbers): ");
			String text = br.readLine();
			System.out.print("Detected resident registration numbers: ");
			System.out.println(filterRRN(text) + "\n");
		}
	}
}
