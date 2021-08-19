package kr.or.ddit.basic.excoding;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class RepoterChoice {
	public static void main(String[] args) {
		String[][] members = { {}, {}, {}, {}, {} };
		String[] reporters = new String[members.length];
		Random rnd = new Random();
		for (int i = 0; i < members.length; i++) {
			int index = rnd.nextInt(members[i].length);
			reporters[i] = members[i][index];
		}
		boolean chk;
		String reporter;
		do {
			chk = false;
			int group = rnd.nextInt(members.length);
			int index = rnd.nextInt(members[group].length);

			reporter = members[group][index];

			for (String name : reporters) {
				if (name.equals(reporter)) {
					chk = true;
					break;
				}
			}
		} while (chk);

		System.out.println("      == 발 표 자 ==     ");
		for (int i = 0; i < reporters.length; i++) {
			System.out.println((i + 1) + "조 발표자 : " + reporters[i]);
		}
		System.out.println();
		System.out.println("공통 발표자 : " + reporters);
		
		Collections.shuffle(Arrays.asList(reporters));
		System.out.println("발표 순서 : " + Arrays.toString(reporters));
		
	}
}
