package week01.day02;

import java.util.ArrayList;
import java.util.List;

public class Algorithms {

	public int findDuplication(List<Integer> integers) {
		List<Integer> checked = new ArrayList<>();
		for (int integer: integers) {
			if (checked.contains(integer)) {
				return integer;
			} else {
				checked.add(integer);
			}
		}
		throw new IllegalArgumentException("There is no duplication");
	}
}
